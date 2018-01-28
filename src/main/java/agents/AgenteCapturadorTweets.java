package agents;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import twitter.TwitterApiConf;
import twitter4j.*;

import java.io.IOException;
import java.io.Serializable;
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

                //Capturar tweets
                //Twitter conf
                TwitterApiConf twitterApiConf = new TwitterApiConf();
                Twitter twitter = twitterApiConf.getTwitter();

                try {
                    mTweetsCapturados = new ArrayList<>();
                    Query query = new Query("#AIUTPL2018");
                    QueryResult result = twitter.search(query);
                    System.out.println("\n\nTweets encontrados:\n");
                    for (Status status : result.getTweets()) {
                        System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
                        mTweetsCapturados.add(status.getText());
                    }

                    //Enviar tweets a agente analizador de sentimientos
                    ACLMessage tweetsEncontradosMensaje = new ACLMessage(ACLMessage.INFORM);
                    //tweetsEncontradosMensaje.setContent("Analizar");
                    tweetsEncontradosMensaje.setContentObject((Serializable) mTweetsCapturados);
                    tweetsEncontradosMensaje.addReceiver( new AID( "analizador", AID.ISLOCALNAME) );
                    send(tweetsEncontradosMensaje);
                } catch (TwitterException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            block();
        }
    }
}
