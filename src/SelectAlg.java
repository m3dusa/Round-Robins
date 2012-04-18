import java.awt.GridLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.LayoutStyle;
import javax.swing.ListModel;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;


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
public class SelectAlg extends javax.swing.JFrame {
	private JList algMenu;
	private JButton okayBut;
	private JCheckBox s1check;
	private JCheckBox pic2box;
	private JLabel algLa;
	private JLabel sources;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				SelectAlg inst = new SelectAlg();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public SelectAlg() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			GroupLayout thisLayout = new GroupLayout((JComponent)getContentPane());
			getContentPane().setLayout(thisLayout);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				ListModel algMenuModel = 
						new DefaultComboBoxModel(
								new String[] { "Item One", "Item Two" });
				algMenu = new JList();
				GridLayout algLaLayout = new GridLayout(1, 1);
				algLaLayout.setColumns(1);
				algLaLayout.setHgap(5);
				algLaLayout.setVgap(5);
				algMenu.setModel(algMenuModel);
				algMenu.setLayout(null);
			}
			{
				okayBut = new JButton();
				okayBut.setText("OK");
				okayBut.setLayout(null);
			}
			{
				s1check = new JCheckBox();
				GridLayout pic2boxLayout = new GridLayout(1, 1);
				pic2boxLayout.setColumns(1);
				pic2boxLayout.setHgap(5);
				pic2boxLayout.setVgap(5);
				s1check.setText("Picture1");
				s1check.setLayout(null);
			}
			{
				pic2box = new JCheckBox();
				pic2box.setText("Picture2");
				pic2box.setLayout(null);
			}
			{
				sources = new JLabel();
				sources.setText("Sources:");
				sources.setLayout(null);
			}
			{
				algLa = new JLabel();
				algLa.setText("Select Algorithm:");
				algLa.setLayout(null);
			}
			thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(s1check, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(sources, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(pic2box, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addComponent(algLa, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(algMenu, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(okayBut, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGap(0, 7, Short.MAX_VALUE));
			thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
				.addGap(7)
				.addGroup(thisLayout.createParallelGroup()
				    .addGroup(thisLayout.createSequentialGroup()
				        .addGroup(thisLayout.createParallelGroup()
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addComponent(sources, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
				                .addGap(38)
				                .addComponent(s1check, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addComponent(algLa, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
				                .addGap(62)))
				        .addGap(69)
				        .addGroup(thisLayout.createParallelGroup()
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addComponent(pic2box, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
				                .addGap(0, 33, Short.MAX_VALUE))
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addGap(0, 55, Short.MAX_VALUE)
				                .addComponent(okayBut, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE))))
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(algMenu, GroupLayout.PREFERRED_SIZE, 378, GroupLayout.PREFERRED_SIZE)
				        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 0, Short.MAX_VALUE))));
			pack();
			setSize(400, 300);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

}
