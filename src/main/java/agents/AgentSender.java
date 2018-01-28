package agents;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class AgentSender extends Agent {
    public void setup() {
        /*ACLMessage message = new ACLMessage(ACLMessage.INFORM);
        message.addReceiver(new AID("AgenteOP", AID.ISLOCALNAME));
        message.setContent("u wot m8");
        send(message);*/

        addBehaviour(new SenderBehaviour(this));
        // Send messages to "a1" and "a2"

        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.setContent( "Ping" );
        for (int i = 1; i<=2; i++)
            msg.addReceiver( new AID( "a" + i, AID.ISLOCALNAME) );

        send(msg);
    }

    class SenderBehaviour extends CyclicBehaviour {

        public SenderBehaviour(Agent a) {
            super(a);
        }

        @Override
        public void action() {
            ACLMessage msg = receive();
            if (msg != null)
                System.out.println("== Answer" + " <- "
                    + msg.getContent() + " from "
                    + msg.getSender().getName());
            block();
        }
    }


}
