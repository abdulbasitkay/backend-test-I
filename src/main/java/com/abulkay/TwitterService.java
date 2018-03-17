package com.abulkay;

import spark.Route;
import twitter4j.*;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static com.abulkay.Util.twitter;

/**
 * Created by AbdulBasit KABIR on 3/15/18.
 */
public class TwitterService {
    private static Twitter twitter = twitter();

    static final AtomicInteger value = new AtomicInteger(50);

    public static Route getUser = (request, response) -> {
        User user = twitter.showUser(request.params("handle"));
        StringBuilder tmp = new StringBuilder();
        tmp.append("@" + user.getScreenName());
        tmp.append("<br \\>Name: " + user.getName());
        tmp.append("<br \\>Followers:" + user.getFollowersCount());
        return tmp.toString();
    };

    public static Route streamByHashTag = (request, response) -> {
        StatusListener listener = new StatusListener(){
            public void onStatus(Status status) {
                System.out.println(status.getText());
                User user = status.getUser();
                List<Object> list = Arrays.
                        asList( user.getScreenName(),user.getName(),user.getFollowersCount());
                GoogleSheetService.addMultiValue(list);
                TwitterService.tryStopStream();

            }
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {}
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {}
            public void onScrubGeo(long l, long l1) {}
            public void onStallWarning(StallWarning stallWarning) {}
            public void onException(Exception ex) {
                ex.printStackTrace();
            }
        };
        TwitterStream twitterStream = TwitterStreamFactory.getSingleton();
        twitterStream.addListener(listener);
        twitterStream.filter(request.params("tags"));

        return "Streaming tweets for " + request.params("tags");
    };

    public static Route streamByfollowers = (request, response) -> {
        StatusListener listener = new StatusListener(){
            public void onStatus(Status status) {
                System.out.println(status.getUser().getName());
                User user = status.getUser();
                if (user.getFollowersCount() >= 1000 && user.getFollowersCount() <= 5000) {
                    List<Object> list = Arrays.
                            asList(user.getScreenName(), user.getName(), user.getFollowersCount());
                    GoogleSheetService.addMultiValue(list);
                    TwitterService.tryStopStream();
                }
            }
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {}
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {}
            public void onScrubGeo(long l, long l1) {}
            public void onStallWarning(StallWarning stallWarning) {}
            public void onException(Exception ex) {
                ex.printStackTrace();
//                TwitterService.stopStream();
            }
        };
        TwitterStream twitterStream = TwitterStreamFactory.getSingleton();
        twitterStream.addListener(listener);
        twitterStream.sample();

        return "done";
    };


    static void tryStopStream(){
        if (value.decrementAndGet() <= 0) return;
        TwitterStream twitterStream = TwitterStreamFactory.getSingleton();
        twitterStream.cleanUp();
        twitterStream.shutdown();
    }
}