package configurator.gui;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import configurator.controller.Actions;
import localization.LanguageManager;

public class MainToolbar extends JToolBar {

	private static final long serialVersionUID = -8794537362874546085L;

	private JButton btnNew;

	private JButton btnWiz;

	private JButton btnParam;

	private JButton btnEditSelection;
	
	private JButton btnDeleteSelection = null;

	public MainToolbar() {
		super(SwingUtilities.HORIZONTAL);
		setFloatable(false);

		initComponents();
		groupComponents();
		addListeners();
	}

	private void initComponents() {

		btnNew = new JButton();
		btnNew.setToolTipText(LanguageManager.getInstance().getRes().getString("new_proj"));
		btnNew.setIcon(new ImageIcon("images/project_small.png"));

		btnWiz = new JButton();
		btnWiz.setToolTipText(LanguageManager.getInstance().getRes().getString("new_wiz"));
		btnWiz.setIcon(new ImageIcon("images/wiz_small.png"));

		btnParam = new JButton();
		btnParam.setToolTipText(LanguageManager.getInstance().getRes().getString("new_param"));
		btnParam.setIcon(new ImageIcon("images/parameter_small.png"));

		btnEditSelection = new JButton();
		btnEditSelection.setToolTipText(LanguageManager.getInstance().getRes().getString("edit_sel"));
		btnEditSelection.setIcon(new ImageIcon("images/window-system-icon.png"));
		
		btnDeleteSelection = new JButton();
		btnDeleteSelection.setToolTipText(LanguageManager.getInstance().getRes().getString("del_sel"));
		btnDeleteSelection.setIcon(new ImageIcon("images/delete_selection.png"));

	}

	private void groupComponents() {
		add(btnNew);
		add(btnWiz);
		add(btnParam);
		addSeparator();
		add(btnEditSelection);
		add(btnDeleteSelection);

	}

	private void addListeners() {
		btnNew.addActionListener(e -> Actions.createProject());
		btnWiz.addActionListener(e -> Actions.createWizard());
		btnParam.addActionListener(e -> Actions.createParameter());

		btnEditSelection.addActionListener(l -> Actions.editSelection());
		btnDeleteSelection.addActionListener(l -> Actions.deleteSelection());

	}

	public void updateLanguage() {
		btnNew.setToolTipText(LanguageManager.getInstance().getRes().getString("new_proj"));
		btnWiz.setToolTipText(LanguageManager.getInstance().getRes().getString("new_wiz"));
		btnParam.setToolTipText(LanguageManager.getInstance().getRes().getString("new_param"));

		btnEditSelection.setToolTipText(LanguageManager.getInstance().getRes().getString("edit_param"));
		btnDeleteSelection.setToolTipText(LanguageManager.getInstance().getRes().getString("del_sel"));
	}

}
