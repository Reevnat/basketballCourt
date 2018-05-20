import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gui extends JFrame{
    public static void main (String [] args){
        court oneCourt = new court();//create one instance of court

        //Gangs share one instance of court
        gang jets = new gang ("Jets", 2, oneCourt);
        gang sharks = new gang ("Sharks", 1, oneCourt);

        //each gang is started in a separate thread
        new Thread(jets).start();
        new Thread(sharks).start();

        //creates and runs the GUI for the application
        gui newGUi = new gui(jets,sharks,oneCourt);
    }

    gui(gang jets, gang sharks, court oneCourt){
        runGui(jets, sharks, oneCourt);
    }
    void runGui(gang jets, gang sharks, court oneCourt){
        //<--------------GUI SECTION - SWING ---------------->
        JFrame f = new JFrame("Sharks and Jets");//creating instance of JFrame

        JLabel turnLbl=new JLabel("Turn Indicator");
        turnLbl.setBounds(125,375, 100,30);

        final JTextField turn=new JTextField(); //Turn Indicator updated with the turn of the next gang
        turn.setBounds(150,400, 30,20);

        final JTextField court=new JTextField("Empty"); //this is the basketball court where the text will be updated with the gang that is currently playing
        court.setBounds(70,10, 200,100);

        //Jets textbox and buttons
        final JTextField jetsTxt=new JTextField("Flag Lowered");
        jetsTxt.setBounds(15,150, 80,20);

        JButton jetsBtn = new JButton("Jets Flag Chng");
        jetsBtn.setBounds(15,200,130,30);
        jetsBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                if(turn.getText().isEmpty()||!jets.isFlagRaised() && turn.getText().equals("2")){
                    jets.startPlaying();
                    jetsTxt.setText("Flag Raised");
                    court.setText("Jets Playing");
                    turn.setText("1");
                }
                else if(jets.isFlagRaised()){
                    jets.stopPlaying();
                    oneCourt.leaveCourt();
                    jetsTxt.setText("Flag Lowered");
                    court.setText("Empty");
                }
            }
        });
        JButton sharksTurnId = new JButton("Sharks Turn Indicator");
        sharksTurnId.setBounds(15,240,130, 30);
        sharksTurnId.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                turn.setText("1");
            }
        });

        //Sharks textbox and buttons
        final JTextField sharksTxt=new JTextField("Flag Lowered");
        sharksTxt.setBounds(190,150, 80,20);

        JButton sharksBtn = new JButton("Sharks Flag Chng");
        sharksBtn.setBounds(190,200,130,30);
        sharksBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                if(turn.getText().isEmpty()||!sharks.isFlagRaised()&& turn.getText().equals("1")){
                    sharks.startPlaying();
                    sharksTxt.setText("Flag Raised");
                    court.setText("Sharks Playing");
                    turn.setText("2");
                }
                else if(sharks.isFlagRaised()){
                    sharks.stopPlaying();
                    oneCourt.leaveCourt();
                    sharksTxt.setText("Flag Lowered");
                    court.setText("Empty");
                }
            }
        });
        JButton jetsTurnId = new JButton("Jets Turn Indicator");
        jetsTurnId.setBounds(190,240,130, 30);
        jetsTurnId.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                turn.setText("2");
            }
        });

        //initialise
        f.add(jetsBtn);f.add(jetsTxt);f.add(sharksTurnId);f.add(sharksTxt);f.add(sharksBtn);f.add(jetsTurnId);f.add(court);f.add(turn);f.add(turnLbl);
        f.setSize(350, 500);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //<--------------------END GUI SECTION-------------------->
    }
}
