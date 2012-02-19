import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;

/*
 * GUI
 */
public class ImageTest extends JFrame {

	
	JButton button1;
	
	public ImageTest(String name) {
        super(name);
    }
	
	
	public void addComponentsToPane(final Container pane) {
		button1 = new JButton("BUTTON");
        pane.add(button1, BorderLayout.CENTER);
    }
	
	
	
	
	/**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
    	ImageTest frame = new ImageTest("GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        frame.addComponentsToPane(frame.getContentPane());
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
	
	
	
	
	
	public static void main(String[] args) {
		

		ImageFrame if1 = new ImageFrame("img1.png");
		ImageFrame if2 = new ImageFrame("img2.png");
		
		Engine.getInstance().addImage(if1);
		Engine.getInstance().addImage(if2);
		
		
        // Schedule a job for the event dispatch thread:
        // creating and showing this application's GUI.
		// from java/oracle tutorial
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
		
		
	}

}
