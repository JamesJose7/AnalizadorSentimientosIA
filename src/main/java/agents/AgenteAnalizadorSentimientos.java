package agents;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

import java.util.List;

public class AgenteAnalizadorSentimientos extends Agent {
    @Override
    protected void setup() {
        addBehaviour(new AnalizadorSentimientosBehaviour(this));
    }

    class AnalizadorSentimientosBehaviour extends CyclicBehaviour {
        public AnalizadorSentimientosBehaviour(Agent a) {
            super(a);
        }

        @Override
        public void action() {
            ACLMessage msg = receive();
            if (msg!=null) {
                /*System.out.println( "Agente: " +
                        myAgent.getLocalName() + " -> acción: " +
                        msg.getContent() );*/

                try {
                    List<String> list = (List<String>) msg.getContentObject();
                    System.out.println(list);

                    //Enviar sentimiento a profesor
                    ACLMessage sentimientosMensaje = new ACLMessage(ACLMessage.INFORM);
                    sentimientosMensaje.setContent("Sentimientos> " +list.size());
                    sentimientosMensaje.addReceiver( new AID( "profesor", AID.ISLOCALNAME) );
                    send(sentimientosMensaje);
                } catch (UnreadableException e) {
                    e.printStackTrace();
                }
            }
            block();
        }
    }
}
