package configurator.gui;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import configurator.controller.Actions;
import localization.LanguageManager;

public class ProjectContextMenu extends JPopupMenu {

	public enum MenuType { PROJECT_MENU, PARAMETER_MENU, WIZARD_MENU }

	private static final long serialVersionUID = 8856767540513887864L;
	
	private JMenuItem editParam;
	private JMenuItem deleteParam;
	
	private JMenu addMenu;
	private JMenuItem addParameter;
	private JMenuItem addWizard;
	
	private JMenuItem editProject;
	private JMenuItem deleteProject;
	
	private JMenu addMenuWiz;
	private JMenuItem addParameterWiz;
	private JMenuItem editWizard;
	private JMenuItem deleteWizard;

	public ProjectContextMenu(MenuType type) {
		if(type == MenuType.PROJECT_MENU) {
			// project selected
			initProjectContext();
		} else if(type == MenuType.PARAMETER_MENU) {
			// param selected
			initParamContext();
		} else if(type == MenuType.WIZARD_MENU) {
			// wizard selected
			initWizardContext();
		}
	}

	private void initParamContext() {
		editParam = new JMenuItem(LanguageManager.getInstance().getRes().getString("edit"));
		deleteParam = new JMenuItem(LanguageManager.getInstance().getRes().getString("delete"));
		
		add(editParam);
		add(deleteParam);

		
		editParam.addActionListener(e -> Actions.editSelection());
		deleteParam.addActionListener(e -> Actions.deleteSelection());

	}

	private void initProjectContext() {
		addMenu = new JMenu(LanguageManager.getInstance().getRes().getString("add"));
		addParameter = new JMenuItem(LanguageManager.getInstance().getRes().getString("par"));
		addWizard = new JMenuItem(LanguageManager.getInstance().getRes().getString("wiz"));
		
		editProject = new JMenuItem(LanguageManager.getInstance().getRes().getString("edit"));
		deleteProject = new JMenuItem(LanguageManager.getInstance().getRes().getString("delete"));
		
		addMenu.add(addParameter);
		addMenu.add(addWizard);
		add(addMenu);
		add(editProject);
		add(deleteProject);

		addParameter.addActionListener(e -> Actions.createParameter());
		addWizard.addActionListener(e -> Actions.createWizard());
		editProject.addActionListener(e -> Actions.editSelection());
		deleteProject.addActionListener(e -> Actions.deleteSelection());
	}
	
	private void initWizardContext() {
		addMenuWiz = new JMenu(LanguageManager.getInstance().getRes().getString("add"));
		addParameterWiz = new JMenuItem(LanguageManager.getInstance().getRes().getString("par"));
		editWizard = new JMenuItem(LanguageManager.getInstance().getRes().getString("edit"));
		deleteWizard = new JMenuItem(LanguageManager.getInstance().getRes().getString("delete"));
		
		addMenuWiz.add(addParameterWiz);
		add(addMenuWiz);
		add(editWizard);
		add(deleteWizard);

		addParameterWiz.addActionListener(e -> Actions.createParameter());
		editWizard.addActionListener(e -> Actions.editSelection());
		deleteWizard.addActionListener(e -> Actions.deleteSelection());
	}
}