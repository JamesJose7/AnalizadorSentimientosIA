import twitter.TwitterApiConf;
import twitter4j.*;

public class Run {
    public static void main(String[] args) {
        //Twitter conf
        TwitterApiConf twitterApiConf = new TwitterApiConf();
        Twitter twitter = twitterApiConf.getTwitter();

        try {
            //Update status
            //Status status = twitter.updateStatus("Testing #AIUTPL2018");
            //System.out.println("Updated status [" + status.getText() + "]");

            Query query = new Query("#AIUTPL2018");
            QueryResult result = twitter.search(query);
            for (Status status : result.getTweets()) {
                System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
            }
        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }
}
