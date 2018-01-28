package agents;

import gui.EstudianteGUI;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import twitter.TwitterApiConf;
import twitter4j.*;

public class AgenteEstudiante extends Agent implements EstudianteGUI.EstudianteInterface {
    @Override
    protected void setup() {
        addBehaviour(new EstudianteBehaviour(this));

        //Show GUI
        EstudianteGUI estudianteGUI = new EstudianteGUI(this);
        estudianteGUI.showGUI();
    }

    @Override
    public void sendTweet(String tweetText) {
        //Twitter conf
        TwitterApiConf twitterApiConf = new TwitterApiConf();
        Twitter twitter = twitterApiConf.getTwitter();

        try {
            //Update status
            Status status = twitter.updateStatus(tweetText);
            System.out.println("Agente " + getLocalName() + " publicó: " + status.getText());
            //System.out.println("Updated status [" + status.getText() + "]");
        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }

    class EstudianteBehaviour extends CyclicBehaviour {

        public EstudianteBehaviour(Agent a) {
            super(a);
        }

        @Override
        public void action() {

        }
    }
}
