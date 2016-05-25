


import twitter4j.*;


public class NewsReader {

    private static TwitterStream twitterStream;


    public void setFollow(long[] followList) {
        //This is a long array list because we pass twitter ids

        FilterQuery query = new FilterQuery();
        query.follow(followList);
        twitterStream.filter(query);

    }

    public static void main(String[] args) throws TwitterException {

        TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
        StatusListener listener = new StatusListener() {
            @Override
            public void onStatus(Status status) {
                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
                System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
                System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {
                System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
            }

            @Override
            public void onStallWarning(StallWarning warning) {
                System.out.println("Got stall warning:" + warning);
            }

            @Override
            public void onException(Exception ex) {
                ex.printStackTrace();
            }
        };
        twitterStream.addListener(listener);
        twitterStream.sample();
        //pss.setFollow(new long[]{20402945});
        FilterQuery query = new FilterQuery();
        query.follow(new long[]{20402945});
        twitterStream.filter(query);
    }
}