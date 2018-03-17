package com.abulkay;

import static spark.Spark.get;

public class Application {
    public static void main(String[] args) {
        Util.TwitterAuth();
        get("influencers", TwitterService.streamByfollowers);
        get("trends/:tags", TwitterService.streamByHashTag);
        get("user/:handle", TwitterService.getUser);
        get("stop", (request, response) -> {
            spark.Spark.stop();
            return "";
        });
        int a = 2;
    }
}