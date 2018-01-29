package agents;

import gui.ProfesorGUI;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import javax.swing.*;
import java.awt.*;

public class AgenteProfesor extends Agent implements ProfesorGUI.ProfesorInterface {

    private ProfesorGUI mProfesorGUI;

    public void setup() {
        addBehaviour(new SenderBehaviour(this));
        // Send messages to "a1" and "a2"
        mProfesorGUI = new ProfesorGUI(this);
        mProfesorGUI.showGUI();
    }

    @Override
    public void capturarTweets() {
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.setContent("Capturar");
        msg.addReceiver( new AID( "capturador", AID.ISLOCALNAME) );

        send(msg);
    }

    @Override
    public void mostrarSentimientos(String sentimiento) {

    }

    class SenderBehaviour extends CyclicBehaviour {

        public SenderBehaviour(Agent a) {
            super(a);
        }

        @Override
        public void action() {
            ACLMessage msg = receive();
            if (msg != null) {
                String msgContent = msg.getContent();
                System.out.println("== Respuesta" + " <- "
                        + msgContent + " de "
                        + msg.getSender().getName());

                mProfesorGUI.getSentimientosPromedioField().setText(msgContent);
                //Set appropiate image
                String resultImagePath;
                switch (msgContent) {
                    case "POSITIVO":
                        resultImagePath = "src\\main\\resources\\happy.png";
                        break;
                    case "NEGATIVO":
                        resultImagePath = "src\\main\\resources\\angry.png";
                        break;
                    default:
                        resultImagePath = "src\\main\\resources\\confused.png";
                }
                ImageIcon icon = new ImageIcon(resultImagePath);
                mProfesorGUI.getResultImageLabel().setIcon(icon);
            }
            block();
        }
    }

}
