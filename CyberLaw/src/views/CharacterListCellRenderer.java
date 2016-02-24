package views;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import models.Character;

public class CharacterListCellRenderer implements ListCellRenderer<Character> {

	private final DefaultListCellRenderer DEFAULT_RENDERER = new DefaultListCellRenderer();
	
	@Override
	public Component getListCellRendererComponent(JList<? extends Character> list, Character value, int index,
			boolean isSelected, boolean cellHasFocus) {
		JLabel renderer = (JLabel) DEFAULT_RENDERER.getListCellRendererComponent(list, "Character " + value.getId(), index, isSelected, cellHasFocus);
		return renderer;
	}


}
