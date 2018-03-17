## Back-end Developer Test

### Devcenter Backend Developer Test I

The purpose of this test is not only to quickly gauge an applicant's abilities with writing codes, but also their approach to development.

Applicants may use whatever language they want to achieve the outcome.

## Task

Build a bot that extracts the following from people’s Twitter bio (on public/open accounts), into a Google spreadsheet:

* Twitter profile name 
* Number of followers

Target accounts using either of these criteria:
* Based on hashtags used
* Based on number of followers; Between 1,000 - 50,000

The bot is suppose to maintain a session and continously listen to the predefined hashtag

## How to complete the task

1. Fork this repository into your own public repo.

2. Complete the project and commit your work. Make a screencast of how it works with the googlespread sheet and progam side-by-side

3. Send the URL of your own repository and the screencast to @kolawole.balogun on the Slack here bit.ly/dcs-slack.

## Show your working

If you choose to use build tools to compile your CSS and Javascript (such as SASS of Coffescript) please include the original files as well. You may update this README file outlining the details of what tools you have used.

## Clean code

This fictitious project is part of a larger plan to reuse templates for multiple properties. When authoring your CSS ensure that it is easy for another developer to find and change things such as fonts and colours.


## Good luck!

We look forward to seeing what you can do. Remember, although it is a test, there are no specific right or wrong answers that we are looking for - just do the job as best you can. Any questions - create an issue in the panel on the right (requires a Github account).

## Solution
This is web api written in java using [spark framework][http://sparkjava.com/] 

### Setup

1. clone repo
2. run `gradle build`
3. use `gradle run` to startup the server

### Endpoints
root: `localhost:4567`
1. `/influencers` gets 50 random users that have follower between 1,000 & 5,000 and extracts the handle, name and number of followers. These detals are added to the [google sheet][https://docs.google.com/spreadsheets/d/1I5jwJujJjyT6LDoEKUs_bNIf4dTVjlRrb1V7HncMJ4Q/edit#gid=0]
2. `/trends/:tags` gets 50 random sets of tweets and extracts from each the users's handle, name and number of followers. These detals are added to the [google sheet][https://docs.google.com/spreadsheets/d/1I5jwJujJjyT6LDoEKUs_bNIf4dTVjlRrb1V7HncMJ4Q/edit#gid=0]
3. `/user/{handle}` gets detals of a user with the handle passed in the path
4. `/stop` : stops the spark server 
