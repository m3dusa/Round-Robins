import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;

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
	private JCheckBox ja4;
	private JCheckBox ja3;
	private JCheckBox ja2;
	private JCheckBox ja1;
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
			//HashMap<Integer, Integer> myMap = Engine.getInstance().algorithmMap;
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				
				ListModel algMenuModel = new DefaultComboBoxModel(Engine.getInstance().getAlgoStrings());
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
				okayBut.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						okayButActionPerformed(evt);
					}
				});
			}
			{
				ja1 = new JCheckBox();
				ja1.setText("a1");
			}
			{
				ja2 = new JCheckBox();
				ja2.setText("a2");
			}
			{
				ja3 = new JCheckBox();
				ja3.setText("a3");
			}
			{
				ja4 = new JCheckBox();
				ja4.setText("a4");
			}
			{
				s1check = new JCheckBox();
				GridLayout pic2boxLayout = new GridLayout(1, 1);
				pic2boxLayout.setColumns(1);
				pic2boxLayout.setHgap(5);
				pic2boxLayout.setVgap(5);
				s1check.setText("Picture1");
				s1check.setLayout(null);
				s1check.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent evt) {
						s1checkItemStateChanged(evt);
					}
				});
							s1check.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									s1checkActionPerformed(evt);
								}
							});
			}
			{
				pic2box = new JCheckBox();
				pic2box.setText("Picture2");
				pic2box.setLayout(null);
				pic2box.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent evt) {
						pic2boxItemStateChanged(evt);
					}
				});
								pic2box.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent evt) {
										pic2boxActionPerformed(evt);
									}
								});
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
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(ja1, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(ja4, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(ja3, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(ja2, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addComponent(algLa, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(algMenu, 0, 166, Short.MAX_VALUE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(okayBut, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE));
			thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
				.addGap(7)
				.addGroup(thisLayout.createParallelGroup()
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addGroup(thisLayout.createParallelGroup()
				            .addGroup(thisLayout.createSequentialGroup()
				                .addGroup(thisLayout.createParallelGroup()
				                    .addComponent(sources, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
				                    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                        .addComponent(ja1, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
				                        .addGap(20)))
				                .addGap(39)
				                .addGroup(thisLayout.createParallelGroup()
				                    .addComponent(s1check, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
				                    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                        .addComponent(ja2, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
				                        .addGap(41))))
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addComponent(algLa, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
				                .addGap(63)))
				        .addGap(0, 19, Short.MAX_VALUE)
				        .addComponent(ja3, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
				        .addGroup(thisLayout.createParallelGroup()
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addComponent(pic2box, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
				                .addGap(34))
				            .addGroup(thisLayout.createSequentialGroup()
				                .addGap(44)
				                .addGroup(thisLayout.createParallelGroup()
				                    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                        .addComponent(ja4, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
				                        .addGap(34))
				                    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                        .addPreferredGap(ja4, okayBut, LayoutStyle.ComponentPlacement.INDENT)
				                        .addComponent(okayBut, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE))))))
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(algMenu, GroupLayout.PREFERRED_SIZE, 377, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 6, Short.MAX_VALUE))));
			pack();
			setSize(400, 300);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	private void s1checkActionPerformed(ActionEvent evt) {
		if (s1check.isSelected() == true && pic2box.isSelected() == true){
			System.out.println("s1 and s2!!!!!!!!");
		}
		System.out.println("s1check.actionPerformed, event="+evt);
		//TODO add your code for s1check.actionPerformed
	}
	
	private void pic2boxActionPerformed(ActionEvent evt) {
		if (s1check.isSelected() == true && pic2box.isSelected() == true){
			System.out.println("s1 and s2!!!!!!!!");
		}
		System.out.println("pic2box.actionPerformed, event="+evt);
		//TODO add your code for pic2box.actionPerformed
	}
	
	private void s1checkItemStateChanged(ItemEvent evt) {
		System.out.println("s1check.itemStateChanged, event="+evt);
		//TODO add your code for s1check.itemStateChanged
	}
	
	private void pic2boxItemStateChanged(ItemEvent evt) {
		System.out.println("pic2box.itemStateChanged, event="+evt);
		//TODO add your code for pic2box.itemStateChanged
	}
	
	private void okayButActionPerformed(ActionEvent evt) {
		this.setVisible(false);
		//TODO add your code for okayBut.actionPerformed
	}

}
