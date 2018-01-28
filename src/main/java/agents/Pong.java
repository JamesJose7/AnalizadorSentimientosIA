package agents;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import twitter.TwitterApiConf;
import twitter4j.*;

public class Pong extends Agent{

    protected void setup() {
        addBehaviour(new PongBehaviour(this));
    }

    class PongBehaviour extends CyclicBehaviour {

        public PongBehaviour(Agent a) {
            super(a);
        }

        @Override
        public void action() {
            ACLMessage msg = receive();
            if (msg!=null) {
                System.out.println( " - " +
                        myAgent.getLocalName() + " received: " +
                        msg.getContent() );

                //Update status
                //updateStatus("Status de un agente");

                ACLMessage reply = msg.createReply();
                reply.setPerformative( ACLMessage.INFORM );
                reply.setContent(" Pong ");
                send(reply);
            }
            block();
        }
    }

    protected void updateStatus(String newStatus) {
        //Twitter conf
        TwitterApiConf twitterApiConf = new TwitterApiConf();
        Twitter twitter = twitterApiConf.getTwitter();

        try {
            //Update status
            Status status = twitter.updateStatus(newStatus);
            System.out.println("Pong: Updated status [" + status.getText() + "]");
        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }


}
