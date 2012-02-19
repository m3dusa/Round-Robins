import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * GUI
 */
public class ImageTest extends JFrame {


	JButton buttonImg1;
	ImageIcon imgIc1;
	JButton buttonImg2;
	ImageIcon imgIc2;
	JButton buttonImg3;
	ImageIcon imgIc3;

	public ImageTest(String name) {
		super(name);
	}

	/**
	 * Setting up the GUI layout
	 * @param All components should be part of this container
	 */
	public void addComponentsToPane(final Container pane) {

		JPanel pTop = new JPanel();
		JPanel pTopLeft = new JPanel();
		imgIc1 = new ImageIcon("img1.png");
		imgIc1.setDescription("img1.png");
		buttonImg1 = new JButton(imgIc1);
		pTopLeft.add(buttonImg1);
		pTop.add(pTopLeft, BorderLayout.WEST);

		JPanel pTopRight = new JPanel();
		imgIc2 = new ImageIcon("img2.png");
		imgIc2.setDescription("img2.png");
		buttonImg2 = new JButton(imgIc2);
		pTopRight.add(buttonImg2);
		pTop.add(pTopRight, BorderLayout.EAST);

		pane.add(pTop, BorderLayout.NORTH);

		JPanel pBot = new JPanel();
		imgIc3 = new ImageIcon("img_out.png");
		buttonImg3 = new JButton(imgIc3);
		pBot.add(buttonImg3, BorderLayout.CENTER);

		pane.add(pBot, BorderLayout.SOUTH);

		// Pressing the left image
		buttonImg1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(ImageTest.this);

				if(returnVal == 0) {
					String path = fc.getSelectedFile().toString();
					//ImageIcon imgIc1 = new ImageIcon(path);
					imgIc1 = new ImageIcon(path);
					imgIc1.setDescription(path);
					buttonImg1.setIcon(imgIc1);

					ImageFrame newImgFrame = new ImageFrame(path);
					Engine.getInstance().addImage(newImgFrame);

					ImageFrame oldImgFrame = new ImageFrame(imgIc2.getDescription());
					Engine.getInstance().addImage(oldImgFrame);


					ImageIcon imgIc3 = new ImageIcon(Engine.getInstance().getBIOut());
					buttonImg3.setIcon(imgIc3);

				}

			}
		});

		// Pressing the right image
		buttonImg2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(ImageTest.this);

				if(returnVal == 0) {
					String path = fc.getSelectedFile().toString();
					//ImageIcon imgIc1 = new ImageIcon(path);
					imgIc2 = new ImageIcon(path);
					imgIc2.setDescription(path);
					buttonImg2.setIcon(imgIc2);

					ImageFrame newImgFrame = new ImageFrame(path);
					Engine.getInstance().addImage(newImgFrame);

					ImageFrame oldImgFrame = new ImageFrame(imgIc1.getDescription());
					Engine.getInstance().addImage(oldImgFrame);


					ImageIcon imgIc3 = new ImageIcon(Engine.getInstance().getBIOut());
					buttonImg3.setIcon(imgIc3);

				}

			}
		});
	}




	/**
	 * Create the GUI and show it.  For thread safety,
	 * this method should be invoked from the
	 * event dispatch thread.
	 */
	private static void createAndShowGUI() {
		ImageTest frame = new ImageTest("GUI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addComponentsToPane(frame.getContentPane());
		frame.pack();
		frame.setVisible(true);
	}


	public static void main(String[] args) {

		ImageFrame if1 = new ImageFrame("img1.png");
		ImageFrame if2 = new ImageFrame("img2.png");

		Engine.getInstance().addImage(if1);
		Engine.getInstance().addImage(if2);

		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});


	}

}
