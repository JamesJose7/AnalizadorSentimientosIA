package twitter;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterApiConf {
    private Twitter mTwitter;

    public TwitterApiConf() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("CZOB1SVFeV0x5gZLin0lsmVJT")
                .setOAuthConsumerSecret("dU4EM6lfv4d4NcHW5eWyWM4F4xAYeIjRFJlROkjbwmZFM1V8a1")
                .setOAuthAccessToken("957384767766450177-hw9KhZEMpZVkRkRh2qw8eDoomuFiWby")
                .setOAuthAccessTokenSecret("ksc1rVX1EsopHqaQtZ2bB7Ss7kf0HItDFnvmsc9a79T2Q");
        TwitterFactory tf = new TwitterFactory(cb.build());
        mTwitter = tf.getInstance();
    }

    public Twitter getTwitter() {
        return mTwitter;
    }
}
