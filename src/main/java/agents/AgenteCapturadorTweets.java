package agents;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import twitter.TwitterApiConf;
import twitter4j.*;

import java.util.ArrayList;
import java.util.List;

public class AgenteCapturadorTweets extends Agent {
    private List<String> mTweetsCapturados;

    @Override
    protected void setup() {
        mTweetsCapturados = new ArrayList<>();
        addBehaviour(new CapturadorTweets(this));
    }

    class CapturadorTweets extends CyclicBehaviour {
        public CapturadorTweets(Agent a) {
            super(a);
        }

        @Override
        public void action() {
            ACLMessage msg = receive();
            if (msg!=null) {
                System.out.println( "Agente: " +
                        myAgent.getLocalName() + " -> acción: " +
                        msg.getContent() );

                //Responder a agente emisor
                ACLMessage reply = msg.createReply();
                reply.setPerformative( ACLMessage.INFORM );
                reply.setContent(getLocalName() + " -----> Comenzando a captura tweets");
                send(reply);

                //Capturar tweets
                //Twitter conf
                TwitterApiConf twitterApiConf = new TwitterApiConf();
                Twitter twitter = twitterApiConf.getTwitter();

                try {
                    Query query = new Query("#AIUTPL2018");
                    QueryResult result = twitter.search(query);
                    System.out.println("\n\nTweets encontrados:\n");
                    for (Status status : result.getTweets()) {
                        System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
                        mTweetsCapturados.add(status.getText());
                    }
                } catch (TwitterException e) {
                    e.printStackTrace();
                }
            }
            block();
        }
    }
}
