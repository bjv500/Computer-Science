package controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
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
				
				final CharacterListController myList = new CharacterListController(characterList);
				final JList<Character> listCharacter = new JList<Character>(myList);
				
				for(int i = 0; i < 2; i++){
					Character newC = new Character(characterList);
					myList.addtolist(newC);
					newC.setId(myList.getSize());
				}
				
				listCharacter.setCellRenderer(new CharacterListCellRenderer());
				listCharacter.setPreferredSize(new Dimension(200, myList.getSize() * 18));
				listCharacter.setVisibleRowCount(10);
				
				final JFrame frame = new JFrame("Simulation");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				final JPanel gui = new JPanel(new BorderLayout(5, 5));
				gui.setBorder(new TitledBorder(""));

				JPanel runOpt = new JPanel(new FlowLayout(FlowLayout.RIGHT, 3, 3));
				runOpt.setBorder(new TitledBorder("Run Options"));
				
				JLabel addChars = new JLabel("Characters");
				runOpt.add(addChars);
				
				final JPanel imagePanel = new JPanel(new GridLayout(3, 1));
				imagePanel.setBorder(new TitledBorder("Details"));
				imagePanel.setPreferredSize( new Dimension( 200, 100 ) );
				
				lHappiness = new JLabel();
				lSelfish = new JLabel();
				lId = new JLabel();
				imagePanel.add(lId);
				imagePanel.add(lHappiness);
				imagePanel.add(lSelfish);
				
				
				final JTextField fldNumChars = new JTextField("1");
				fldNumChars.setPreferredSize(new Dimension(80, 22));
				runOpt.add(fldNumChars);
				
				JButton cList = new JButton("Add to List");
				runOpt.add(cList);
				cList.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent ae) {
						
						int numChars = 0;
						String nChars = fldNumChars.getText();
						if(nChars == ""){
							numChars = 1;
						}
						else{
							numChars = Integer.parseInt(nChars);
						}
						
						lId.setText("Adding " + numChars + " characters to list");
						lSelfish.setText("");
						lHappiness.setText("");
						for(int i = 0; i < numChars; i++){
							
							Character newC = new Character(characterList);
							myList.addtolist(newC);
							newC.setId(myList.getSize());
							listCharacter.setPreferredSize(new Dimension(200, myList.getSize() * 18));
							listCharacter.setVisibleRowCount(10);
						}
						frame.validate();
					}
				});
				

				
				JButton clList = new JButton("Clear List");
				runOpt.add(clList);
				clList.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent ae) {
						lId.setText("Clearing list, must have 2");
						lSelfish.setText("");
						lHappiness.setText("characters.");
						myList.clearList();
						characterList.clearList();
						listCharacter.removeAll();
						for(int i = 0; i < 2; i++){
							Character newC = new Character(characterList);
							myList.addtolist(newC);
							newC.setId(myList.getSize());
						}
						listCharacter.setPreferredSize(new Dimension(200, myList.getSize() * 18));
						listCharacter.setVisibleRowCount(10);
						frame.validate();
					}
				});
				
				JButton run = new JButton("Run");
				runOpt.add(run);
				run.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent ae) {
						for(Character c : characterList.getList()){
							if(c.getSelfishness() == "Selfish"){
								c.setHappiness(c.getHappiness() + 5);
								
							}
							else if(c.getSelfishness() == "Not Selfish"){
								c.setHappiness(c.getHappiness() - 2);
								
							}
							else
								c.setHappiness(c.getHappiness() - 5);
								
						}
						if(index > 0){
						index = listCharacter.getMinSelectionIndex();
						selectedModel = myList.getElementAt(index);
						lId.setText("Id: " + selectedModel.getId());
						lHappiness.setText("Happiness: " + selectedModel.getHappiness());
						lSelfish.setText("Selfishness: " + selectedModel.getSelfishness());
						frame.validate();
						}
					}


				});



				gui.add(runOpt, BorderLayout.NORTH);

				JPanel dynamicLabels = new JPanel(new GridBagLayout());
				GridBagConstraints c = new GridBagConstraints();
				dynamicLabels.setBorder(new TitledBorder("Character Options"));
				dynamicLabels.setPreferredSize( new Dimension( 200, 50 ) );
				gui.add(dynamicLabels, BorderLayout.AFTER_LINE_ENDS);
				
				JButton editChar = new JButton("  Edit Character  ");
				dynamicLabels.add(editChar);
				c.weightx = 0.5;
				c.gridx = 0;
				c.gridy = 2;
				c.insets = new Insets(10,0,0,0);
				dynamicLabels.add(editChar, c);
				editChar.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent ae) {
//						labels.add(new JLabel("Label " + ++labelCount));
						frame.validate();
					}
				});
				
				JButton addChar = new JButton("  Add Character  ");
				dynamicLabels.add(addChar);
				c.weightx = .5;
				c.gridx = 0;
				c.gridy = 3;
				c.insets = new Insets(10,0,0,0);
				dynamicLabels.add(addChar, c);
				addChar.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent ae) {
						Character newC = new Character(characterList);
						myList.addtolist(newC);
						newC.setId(myList.getSize());
						listCharacter.setPreferredSize(new Dimension(200, myList.getSize() * 18));
						listCharacter.setVisibleRowCount(10);
						
						lId.setText("Adding one character to list");
						lSelfish.setText("");
						lHappiness.setText("");
						
						frame.validate();
					}
				});
				
				JButton delChar = new JButton("Delete Character");
				dynamicLabels.add(delChar);
				c.weightx = .5;
				c.gridx = 0;
				c.gridy = 4;
				c.insets = new Insets(10,0,0,0);
				dynamicLabels.add(delChar, c);
				delChar.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent ae) {

						characterList.removeCharacterFromList(myList.getElementAt(index));
						listCharacter.setPreferredSize(new Dimension(200, myList.getSize() * 18));
						listCharacter.setVisibleRowCount(10);
	
						listCharacter.updateUI();
						
						lId.setText("Deleting character from list");
						lSelfish.setText("");
						lHappiness.setText("");
						
						frame.validate();
					}
				});
				
				JButton sRun = new JButton(" Run on selected ");
				dynamicLabels.add(sRun);
				c.weightx = .5;
				c.gridx = 0;
				c.gridy = 5;
				c.insets = new Insets(10,0,0,0);
				dynamicLabels.add(sRun, c);
				sRun.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent ae) {
						listCharacter.setPreferredSize(new Dimension(200, myList.getSize() * 18));
						listCharacter.setVisibleRowCount(10);
						
						index = listCharacter.getMinSelectionIndex();
						selectedModel = myList.getElementAt(index);
						
						
						if(selectedModel.getSelfishness() == "Selfish"){
							selectedModel.setHappiness(selectedModel.getHappiness() + 5);
							
						}
						else if(selectedModel.getSelfishness() == "Not Selfish"){
							selectedModel.setHappiness(selectedModel.getHappiness() - 2);
							
						}
						else
							selectedModel.setHappiness(selectedModel.getHappiness() - 5);
							
						lId.setText("Id: " + selectedModel.getId());
						lHappiness.setText("Happiness: " + selectedModel.getHappiness());
						lSelfish.setText("Selfishness: " + selectedModel.getSelfishness());
						frame.validate();
					}
				});
				

				
				
				
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
	
	public JList popList(){
		return null;
		
	}
	
	
}