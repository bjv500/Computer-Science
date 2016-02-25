package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.sql.Array;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import models.Character;
import models.CharacterList;
import views.CharacterListCellRenderer;

public class LauncherNew {

	private static CharacterList characterList;
	private static int index = 0;
	private static Character selectedModel;
	private static JLabel lId, lHappiness, lSelfish;
	JPanel panel;

	public static void main(String[] args) {

		characterList = new CharacterList();

		Runnable r = new Runnable() {

			public void run() {
				final JFrame frame = new JFrame("Simulation");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				final JPanel gui = new JPanel(new BorderLayout(5, 5));
				gui.setBorder(new TitledBorder("BorderLayout(5,5)"));

				JPanel plafComponents = new JPanel(new FlowLayout(FlowLayout.RIGHT, 3, 3));
				plafComponents.setBorder(new TitledBorder("FlowLayout(FlowLayout.RIGHT, 3,3)"));

				final UIManager.LookAndFeelInfo[] plafInfos = UIManager.getInstalledLookAndFeels();
				String[] plafNames = new String[plafInfos.length];
				for (int ii = 0; ii < plafInfos.length; ii++) {
					plafNames[ii] = plafInfos[ii].getName();
				}
				final JComboBox plafChooser = new JComboBox(plafNames);
				plafComponents.add(plafChooser);

				final JCheckBox pack = new JCheckBox("Pack on PLAF change", true);
				plafComponents.add(pack);

				plafChooser.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						int index = plafChooser.getSelectedIndex();
						try {
							UIManager.setLookAndFeel(plafInfos[index].getClassName());
							SwingUtilities.updateComponentTreeUI(frame);
							if (pack.isSelected()) {
								frame.pack();
								frame.setMinimumSize(frame.getSize());
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

				gui.add(plafComponents, BorderLayout.NORTH);

				JPanel dynamicLabels = new JPanel(new BorderLayout(4, 4));
				dynamicLabels.setBorder(new TitledBorder("Run(4,4)"));
				gui.add(dynamicLabels, BorderLayout.WEST);

				final JPanel labels = new JPanel(new GridLayout(0, 2, 3, 3));
				labels.setBorder(new TitledBorder("GridLayout(0,2,3,3)"));

				JButton addNew = new JButton("Add Another Label");
				dynamicLabels.add(addNew, BorderLayout.NORTH);
				addNew.addActionListener(new ActionListener() {

					private int labelCount = 0;

					public void actionPerformed(ActionEvent ae) {
						labels.add(new JLabel("Label " + ++labelCount));
						frame.validate();
					}
				});

				dynamicLabels.add(new JScrollPane(labels), BorderLayout.CENTER);

				final CharacterListController myList = new CharacterListController(characterList);
				
				for(int i = 0; i<200; i++){
					Character newC = new Character(characterList);
					myList.addtolist(newC);
					newC.setId(i+1);
				}

				final JList<Character> listCharacter = new JList<Character>(myList);
				
				listCharacter.setCellRenderer(new CharacterListCellRenderer());
				listCharacter.setPreferredSize(new Dimension(200, myList.getSize() * 18));
				listCharacter.setVisibleRowCount(10);
				
				final JPanel imagePanel = new JPanel(new GridLayout(3, 1));
				imagePanel.setBorder(new TitledBorder("Details"));
				imagePanel.setPreferredSize( new Dimension( 200, 100 ) );
				
				lHappiness = new JLabel();
				lSelfish = new JLabel();
				lId = new JLabel();
				imagePanel.add(lId);
				imagePanel.add(lHappiness);
				imagePanel.add(lSelfish);
				
				listCharacter.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						if(evt.getClickCount() == 1) {
							index = listCharacter.locationToIndex(evt.getPoint());
							selectedModel = myList.getElementAt(index);
							lId.setText("Id: " + selectedModel.getId());
							lHappiness.setText("Happiness: " + selectedModel.getHappiness());
							lSelfish.setText("Selfishness: " + selectedModel.getSelfishness());
							frame.validate();
						}
					}
				});

				JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new JScrollPane(listCharacter), new JScrollPane(imagePanel));
				gui.add(splitPane, BorderLayout.CENTER);

				frame.setContentPane(gui);
				frame.pack();
				frame.setLocationRelativeTo(null);
				try {
					// 1.6+
					frame.setLocationByPlatform(true);
					frame.setMinimumSize(frame.getSize());
				} catch (Throwable ignoreAndContinue) {
				}

				frame.setVisible(true);
			}

		};
		SwingUtilities.invokeLater(r);
	}
}