package agents;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AgentSender extends Agent {

    private JButton mBtBut0;

    public void setup() {
        /*ACLMessage message = new ACLMessage(ACLMessage.INFORM);
        message.addReceiver(new AID("AgenteOP", AID.ISLOCALNAME));
        message.setContent("u wot m8");
        send(message);*/

        addBehaviour(new SenderBehaviour(this));
        // Send messages to "a1" and "a2"

        showGUI();
        mBtBut0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("do you remember?");

                ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                msg.setContent( "Ping" );
                for (int i = 1; i<=2; i++)
                    msg.addReceiver( new AID( "a" + i, AID.ISLOCALNAME) );

                send(msg);
            }
        });

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

    protected void showGUI() {
        JPanel pnMainPanel;

        JPanel pnPanel4;

        JFrame frame = new JFrame();
        pnMainPanel = new JPanel();
        pnMainPanel.setBorder( BorderFactory.createTitledBorder( "" ) );
        GridBagLayout gbMainPanel = new GridBagLayout();
        GridBagConstraints gbcMainPanel = new GridBagConstraints();
        pnMainPanel.setLayout( gbMainPanel );

        pnPanel4 = new JPanel();
        GridBagLayout gbPanel4 = new GridBagLayout();
        GridBagConstraints gbcPanel4 = new GridBagConstraints();
        pnPanel4.setLayout( gbPanel4 );

        mBtBut0 = new JButton( "Do"  );
        gbcPanel4.gridx = 9;
        gbcPanel4.gridy = 8;
        gbcPanel4.gridwidth = 7;
        gbcPanel4.gridheight = 4;
        gbcPanel4.fill = GridBagConstraints.BOTH;
        gbcPanel4.weightx = 1;
        gbcPanel4.weighty = 0;
        gbcPanel4.anchor = GridBagConstraints.NORTH;
        gbPanel4.setConstraints(mBtBut0, gbcPanel4 );
        pnPanel4.add(mBtBut0);
        gbcMainPanel.gridx = 0;
        gbcMainPanel.gridy = 4;
        gbcMainPanel.gridwidth = 20;
        gbcMainPanel.gridheight = 20;
        gbcMainPanel.fill = GridBagConstraints.BOTH;
        gbcMainPanel.weightx = 1;
        gbcMainPanel.weighty = 0;
        gbcMainPanel.anchor = GridBagConstraints.NORTH;
        gbMainPanel.setConstraints( pnPanel4, gbcMainPanel );
        pnMainPanel.add( pnPanel4 );

        frame.add(pnMainPanel);
        frame.setVisible(true);
    }

}
