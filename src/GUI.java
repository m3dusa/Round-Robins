import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.LayoutStyle;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;

import Models.ImageFrame;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class GUI extends javax.swing.JFrame {
	private JButton pic1;
	private JLabel a3La;
	private JLabel a4La;
	private JLabel a2La;
	private JLabel a1La;
	private JLabel pic2La;
	private JLabel jLabel1;
	private JButton a4;
	private JButton a3;
	private JButton a2;
	private JButton a1;
	private JButton pic2;
	private JButton diff;
	public SelectAlg alg;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GUI inst = new GUI();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public GUI() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			GroupLayout thisLayout = new GroupLayout((JComponent)getContentPane());
			getContentPane().setLayout(thisLayout);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				pic1 = new JButton();
				GridLayout a4Layout = new GridLayout(1, 1);
				a4Layout.setColumns(1);
				a4Layout.setHgap(5);
				a4Layout.setVgap(5);
				pic1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						pic1ActionPerformed(evt);
					}
				});
			}
			{
				jLabel1 = new JLabel();
				GridLayout pic2LaLayout = new GridLayout(1, 1);
				pic2LaLayout.setColumns(1);
				pic2LaLayout.setHgap(5);
				pic2LaLayout.setVgap(5);
				jLabel1.setText("Picuture1:");
				jLabel1.setLayout(null);
			}
			{
				pic2La = new JLabel();
				pic2La.setText("Picture 2:");
				pic2La.setLayout(null);
			}
			{
				a1La = new JLabel();
				GridLayout a4LaLayout = new GridLayout(1, 1);
				a4LaLayout.setColumns(1);
				a4LaLayout.setHgap(5);
				a4LaLayout.setVgap(5);
				a1La.setText("a1");
				a1La.setLayout(null);
			}
			{
				a2La = new JLabel();
				a2La.setText("a2");
				a2La.setLayout(null);
			}
			{
				a3La = new JLabel();
				a3La.setText("a3");
				a3La.setLayout(null);
			}
			{
				a4La = new JLabel();
				a4La.setText("a4");
				a4La.setLayout(null);
			}
			{
				diff = new JButton();
				diff.setLayout(null);
			}
			{
				pic2 = new JButton();
				pic2.setLayout(null);
				pic2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						pic2ActionPerformed(evt);
					}
				});
			}
			{
				a1 = new JButton();
				a1.setLayout(null);
				a1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						a1ActionPerformed(evt);
					}
				});
			}
			{
				a2 = new JButton();
				a2.setLayout(null);
				a2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						a2ActionPerformed(evt);
					}
				});
			}
			{
				a3 = new JButton();
				a3.setLayout(null);
				a3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						a3ActionPerformed(evt);
					}
				});
			}
			{
				a4 = new JButton();
				a4.setLayout(null);
				a4.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						a4ActionPerformed(evt);
					}
				});
			}
			thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(jLabel1, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(pic2La, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(pic1, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE)
				    .addComponent(diff, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE)
				    .addComponent(pic2, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(a4, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)
				    .addComponent(a1, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)
				    .addComponent(a3, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)
				    .addComponent(a2, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(a1La, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(a3La, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(a4La, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(a2La, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addContainerGap());
			thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
				.addContainerGap()
				.addGroup(thisLayout.createParallelGroup()
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(pic1, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)
				        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				        .addComponent(diff, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE)
				        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				        .addGroup(thisLayout.createParallelGroup()
				            .addGroup(thisLayout.createSequentialGroup()
				                .addComponent(pic2, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE)
				                .addGap(0, 0, Short.MAX_VALUE))
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addComponent(pic2La, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
				                .addGap(0, 175, Short.MAX_VALUE)))
				        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED))
				    .addGroup(thisLayout.createSequentialGroup()
				        .addGroup(thisLayout.createParallelGroup()
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addGap(7)
				                .addComponent(a1La, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE))
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addComponent(a1, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
				                .addGap(7))
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
				                .addGap(116)))
				        .addGroup(thisLayout.createParallelGroup()
				            .addComponent(a2La, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
				            .addComponent(a2, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
				        .addGap(21)
				        .addGroup(thisLayout.createParallelGroup()
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addGap(0, 0, Short.MAX_VALUE)
				                .addComponent(a3, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
				                .addGap(8))
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addGap(0, 8, Short.MAX_VALUE)
				                .addComponent(a3La, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)))
				        .addGroup(thisLayout.createParallelGroup()
				            .addComponent(a4La, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addComponent(a4, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
				                .addGap(10)))))
				.addGap(7));
			pack();
			this.setSize(817, 605);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	private void pic1ActionPerformed(ActionEvent evt) {
		System.out.println("Selected picture 1");
		final JFileChooser fc = new JFileChooser("./");
		int returnVal = fc.showOpenDialog(GUI.this);
		if(returnVal == 0) {
			String path = fc.getSelectedFile().toString();
			//ImageIcon imgIc1 = new ImageIcon(path);
			
			ImageIcon imgIc1 = new ImageIcon(path);
			Image image = imgIc1.getImage().getScaledInstance(250, 285, Image.SCALE_SMOOTH);
			imgIc1.setImage(image);
			pic1.setIcon(imgIc1);
			ImageFrame newImgFrame = new ImageFrame(path);
			Engine.getInstance().addImage(newImgFrame);
		}
	}
	
	private void pic2ActionPerformed(ActionEvent evt) {
		System.out.println("Selected picture 2");
		final JFileChooser fc = new JFileChooser("./");
		int returnVal = fc.showOpenDialog(GUI.this);
		if(returnVal == 0) {
			String path = fc.getSelectedFile().toString();
			//ImageIcon imgIc1 = new ImageIcon(path);
			ImageIcon imgIc2 = new ImageIcon(path);
			Image image = imgIc2.getImage().getScaledInstance(250, 285, Image.SCALE_SMOOTH);
			imgIc2.setImage(image);
			imgIc2.setDescription(path);
			pic2.setIcon(imgIc2);

			ImageFrame newImgFrame = new ImageFrame(path);
			Engine.getInstance().addImage(newImgFrame);
		}
	}
	
	private void a1ActionPerformed(ActionEvent evt) {
		alg = new SelectAlg();
		alg.setVisible(true);
	}
	
	private void a2ActionPerformed(ActionEvent evt) {
		alg = new SelectAlg();
		alg.setVisible(true);
	}
	
	private void a3ActionPerformed(ActionEvent evt) {
		alg = new SelectAlg();
		alg.setVisible(true);
	}
	
	private void a4ActionPerformed(ActionEvent evt) {
		alg = new SelectAlg();
		alg.setVisible(true);
	}

}
