package configurator.gui;

import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import localization.LanguageManager;

public class EditorToolbar extends JToolBar {

	private static final long serialVersionUID = -4849623958718921435L;

	private final JButton editButton;

	private final JButton deleteButton;
	

	public EditorToolbar() {
		super(SwingConstants.HORIZONTAL);
	
		editButton = new JButton("");
		deleteButton = new JButton("");
		
		
		setFloatable(false);
		setupComponents();
		groupComponents();
	}

	private void setupComponents() {
		editButton.setToolTipText(LanguageManager.getInstance().getRes().getString("edit_sel"));
		deleteButton.setToolTipText(LanguageManager.getInstance().getRes().getString("del_sel"));
		
		editButton.setName("edit");
		deleteButton.setName("delete");
		
		editButton.setIcon(new ImageIcon("images/edit_selection.png"));
		deleteButton.setIcon(new ImageIcon("images/delete_selection.png"));
	}

	private void groupComponents() {
		add(editButton);
		add(deleteButton);
	}
	
	public void setListener(ActionListener listener) {
		editButton.addActionListener(listener);
		deleteButton.addActionListener(listener);
	}
	
	public void updateLanguage() {
		editButton.setToolTipText(LanguageManager.getInstance().getRes().getString("edit_sel"));
		deleteButton.setToolTipText(LanguageManager.getInstance().getRes().getString("del_sel"));
	}
	
}
