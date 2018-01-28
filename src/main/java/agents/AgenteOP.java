package agents;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;

public class AgenteOP extends Agent {
    @Override
    public void setup() {
        /*System.out.println("nani??? " + getLocalName());

        ACLMessage msg = null;
        msg = blockingReceive();
        System.out.println("Message: " + msg.getContent());*/

        //addBehaviour(new TypicalBehaviour(this));

        addBehaviour(new CyclicBehaviour(this)
        {
            public void action() {
                ACLMessage msg= receive();
                if (msg!=null)
                    System.out.println( " - " +
                            myAgent.getLocalName() + " <- " +
                            msg.getContent() );
                block();
            }
        });
    }

    class TypicalBehaviour extends SimpleBehaviour {

        public TypicalBehaviour(Agent a) {
            super(a);
        }

        @Override
        public void action() {
            System.out.println("nani??? " + getLocalName());
        }

        @Override
        public boolean done() {
            return false;
        }
    }
}
