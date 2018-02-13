package configurator.gui;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

import configurator.controller.Actions;
import configurator.controller.ChangeLookAndFeelAction;
import configurator.controller.menubarActions.ShowViewComponents;
import configurator.gui.dialog.AboutDialog;
import configurator.gui.dialog.HelpContentDialog;
import configurator.gui.dialog.KeyBindingsDialog;
import localization.LanguageManager;
import localization.SupportedLanguage;

public class MenuBar extends JMenuBar {

	private static final long serialVersionUID = 2833631704397290865L;

	private JMenu file = null;
	private JMenu edit = null;
	private JMenu help = null;
	private JMenu window = null;

	private JMenu miNew = null;
	private JMenuItem miNewProject = null;
	private JMenuItem miNewWizard = null;
	private JMenuItem miNewParameter = null;
	private JMenuItem miOpenProject = null;
	private JMenuItem miSaveProject = null;
	private JMenuItem miOpenWorkspace = null;
	private JMenuItem miSaveWorkspace = null;
	private JMenuItem miExit = null;

	private JMenuItem miAbout = null;
	private JMenuItem miHelpContent = null;
	private JMenuItem miKeyBindings = null;

	private JMenuItem miEditSelection = null;
	private JMenuItem miDeleteSelection = null;

	private JMenuItem miFind = null;
	private JMenuItem miClearHighlights = null;

	private JMenu changeTheme = null;
	private JMenu appearance = null;
	private JMenu changeLanguage = null;
	private JMenuItem srb = null;
	private JMenuItem eng = null;
	private JMenuItem srbCr = null;
	private ChangeLookAndFeelAction miTheme = null;

	private JCheckBoxMenuItem showHideStatusBar;
	private JCheckBoxMenuItem showHideMainToolbar;
	private JCheckBoxMenuItem showHideEditorToolbar;

	private ButtonGroup languageGroup;

	private ArrayList<KeyStroke> keyBindings = new ArrayList<KeyStroke>();

	public MenuBar() {
		initMenuComponents();
		setMnemonics();
		setAccelerators();
		setListeners();

	}

	private void initMenuComponents() {

		languageGroup = new ButtonGroup();

		showHideEditorToolbar = new JCheckBoxMenuItem(LanguageManager.getInstance().getRes().getString("editor_tb"));
		showHideMainToolbar = new JCheckBoxMenuItem(LanguageManager.getInstance().getRes().getString("main_tb"));
		showHideStatusBar = new JCheckBoxMenuItem(LanguageManager.getInstance().getRes().getString("statusbar"));

		showHideEditorToolbar.setSelected(false);
		showHideMainToolbar.setSelected(true);
		showHideStatusBar.setSelected(true);

		file = new JMenu();
		edit = new JMenu();
		window = new JMenu();
		help = new JMenu();

		// file
		miNew = new JMenu();
		miNew.setIcon(new ImageIcon("images/new-file-icon.png"));
		miNewProject = new JMenuItem(new ImageIcon("images/project_small.png"));
		miNewWizard = new JMenuItem(new ImageIcon("images/Wizard-icon.png"));
		miNewParameter = new JMenuItem(new ImageIcon("images/parameter_small.png"));
		miOpenProject = new JMenuItem(new ImageIcon("images/Open-file-icon1.png"));
		miSaveProject = new JMenuItem(new ImageIcon("images/Save-icon1.png"));
		miOpenWorkspace = new JMenuItem(new ImageIcon("images/Open-file-icon1.png"));
		miSaveWorkspace = new JMenuItem(new ImageIcon("images/Save-icon1.png"));
		miExit = new JMenuItem(new ImageIcon("images/exit-to-app-16.png"));

		// edit
		miEditSelection = new JMenuItem(LanguageManager.getInstance().getRes().getString("edit_sel"));
		miEditSelection.setIcon(new ImageIcon("images/window-system-icon.png"));
		
		miDeleteSelection = new JMenuItem(LanguageManager.getInstance().getRes().getString("del_sel"));
		miDeleteSelection.setIcon(new ImageIcon("images/delete_selection.png"));

		miFind = new JMenuItem(LanguageManager.getInstance().getRes().getString("find"));
		miFind.setIcon(new ImageIcon("images/search.png"));
		miClearHighlights = new JMenuItem(LanguageManager.getInstance().getRes().getString("clear_highlights"));

		// window
		appearance = new JMenu();

		changeTheme = new LookAndFeelMenu();

		changeLanguage = new JMenu(LanguageManager.getInstance().getRes().getString("cl"));
		srb = new JRadioButtonMenuItem((LanguageManager.getInstance().getRes().getString("srb")));
		srbCr = new JRadioButtonMenuItem(LanguageManager.getInstance().getRes().getString("srb_cr"));
		eng = new JRadioButtonMenuItem(LanguageManager.getInstance().getRes().getString("eng"));
		
		eng.setIcon(new ImageIcon("images/if_flag-united-kingdom_748024.png"));
		srb.setIcon(new ImageIcon("images/if_flag-serbia_748111.png"));
		srbCr.setIcon(new ImageIcon("images/if_flag-serbia_748111.png"));
		
		languageGroup.add(srb);
		languageGroup.add(srbCr);
		languageGroup.add(eng);
		
		// help
		miAbout = new JMenuItem();
		miAbout.setIcon(new ImageIcon("images/about.png"));
		miHelpContent = new JMenuItem();
		miHelpContent.setIcon(new ImageIcon("images/support-icon.png"));
		miKeyBindings = new JMenuItem();

		miNew.add(miNewProject);
		miNew.addSeparator();
		miNew.add(miNewWizard);
		miNew.addSeparator();
		miNew.add(miNewParameter);

		file.add(miNew);
		file.addSeparator();
		file.add(miOpenProject);
		file.add(miSaveProject);
		file.addSeparator();
		// file.add(miOpenWorkspace);
		file.add(miSaveWorkspace);
		file.addSeparator();
		file.add(miExit);

		edit.addSeparator();
		edit.add(miEditSelection);
		edit.addSeparator();
		edit.add(miDeleteSelection);
		edit.addSeparator();
		
		edit.add(miFind);
		edit.add(miClearHighlights);

		window.add(appearance);
		window.addSeparator();

		appearance.add(showHideEditorToolbar);
		appearance.add(showHideMainToolbar);
		appearance.add(showHideStatusBar);

		window.add(changeTheme);
		window.addSeparator();
		changeLanguage.add(eng);
		changeLanguage.add(srb);
		changeLanguage.add(srbCr);
		window.add(changeLanguage);
		
		help.add(miAbout);
		help.addSeparator();
		help.add(miHelpContent);
		help.add(miKeyBindings);

		add(file);
		add(edit);
		add(window);
		add(help);
	}

	private void setListeners() {

		// file
		miNewProject.addActionListener(e -> Actions.createProject());
		miNewParameter.addActionListener(e -> Actions.createParameter());
		miNewWizard.addActionListener(e -> Actions.createWizard());

		miOpenProject.addActionListener(e -> Actions.openProject());
		miSaveProject.addActionListener(e -> Actions.saveProject());
		miOpenWorkspace.addActionListener(e -> Actions.openWorkspace());
		miSaveWorkspace.addActionListener(e -> Actions.saveWorkspace());
		miExit.addActionListener(e -> MainFrame.getInstance().dispose());

		// edit
		miEditSelection.addActionListener(l -> Actions.editSelection());
		miDeleteSelection.addActionListener(l -> Actions.deleteSelection());
		
		miFind.addActionListener(l -> Actions.find());
		miClearHighlights.addActionListener(l -> Actions.clearHighlights());

		// help
		miHelpContent.addActionListener(e -> new HelpContentDialog(MainFrame.getInstance()).setVisible(true));
		miAbout.addActionListener(e -> new AboutDialog(MainFrame.getInstance()).setVisible(true));
		miKeyBindings.addActionListener(e -> new KeyBindingsDialog(MainFrame.getInstance()).setVisible(true));

		// window
		showHideMainToolbar.addActionListener(new ShowViewComponents());
		showHideStatusBar.addActionListener(new ShowViewComponents());
		showHideEditorToolbar.addActionListener(new ShowViewComponents());

		SupportedLanguage langEng = LanguageManager.getInstance().LANG_ENG;
		SupportedLanguage langSrbLat = LanguageManager.getInstance().LANG_SERBIAN_LAT;
		SupportedLanguage langSrbCyr = LanguageManager.getInstance().LANG_SERBIAN_CYR;
		
		eng.setSelected(LanguageManager.LANG_DEFAULT == langEng);
		eng.addActionListener(e -> LanguageManager.getInstance().changeLanguage(langEng));
		
		srb.setSelected(LanguageManager.LANG_DEFAULT == langSrbLat);
		srb.addActionListener(e -> LanguageManager.getInstance().changeLanguage(langSrbLat));
		
		srbCr.setSelected(LanguageManager.LANG_DEFAULT == langSrbCyr);
		srbCr.addActionListener(e -> LanguageManager.getInstance().changeLanguage(langSrbCyr));

	}

	private void setMnemonics() {
		file.setMnemonic('F');
		edit.setMnemonic('E');
		help.setMnemonic('H');
		window.setMnemonic('W');
	}

	private void setAccelerators() {
		miNewProject
				.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		miNewWizard.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		miNewParameter
				.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		miHelpContent.setAccelerator(KeyStroke.getKeyStroke("F12"));
		miSaveProject.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		miOpenProject.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		miSaveWorkspace
				.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		miOpenWorkspace
				.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		miFind.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
		
		miDeleteSelection.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE,0));
		miEditSelection.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
		

		keyBindings.add(miNewProject.getAccelerator());
		keyBindings.add(miNewParameter.getAccelerator());
		keyBindings.add(miNewWizard.getAccelerator());
		keyBindings.add(miHelpContent.getAccelerator());
		keyBindings.add(miSaveProject.getAccelerator());
		keyBindings.add(miOpenProject.getAccelerator());
		keyBindings.add(miSaveWorkspace.getAccelerator());
		keyBindings.add(miOpenWorkspace.getAccelerator());
		keyBindings.add(miFind.getAccelerator());
		keyBindings.add(miEditSelection.getAccelerator());
		keyBindings.add(miDeleteSelection.getAccelerator());
	}

	public void updateLanguage() {
		file.setText(LanguageManager.getInstance().getRes().getString("file"));
		edit.setText(LanguageManager.getInstance().getRes().getString("edit"));
		window.setText(LanguageManager.getInstance().getRes().getString("window"));
		help.setText(LanguageManager.getInstance().getRes().getString("help"));
		miNew.setText(LanguageManager.getInstance().getRes().getString("new"));

		showHideEditorToolbar.setText(LanguageManager.getInstance().getRes().getString("editor_tb"));
		showHideMainToolbar.setText(LanguageManager.getInstance().getRes().getString("main_tb"));
		showHideStatusBar.setText(LanguageManager.getInstance().getRes().getString("statusbar"));

		miNewProject.setText(LanguageManager.getInstance().getRes().getString("proj"));
		miNewWizard.setText(LanguageManager.getInstance().getRes().getString("wiz"));
		miNewParameter.setText(LanguageManager.getInstance().getRes().getString("par"));

		miOpenProject.setText(LanguageManager.getInstance().getRes().getString("open_proj"));
		miSaveProject.setText(LanguageManager.getInstance().getRes().getString("save_proj"));
		miOpenWorkspace.setText(LanguageManager.getInstance().getRes().getString("open_ws"));
		miSaveWorkspace.setText(LanguageManager.getInstance().getRes().getString("save_ws"));
		miExit.setText(LanguageManager.getInstance().getRes().getString("exit"));

		appearance.setText(LanguageManager.getInstance().getRes().getString("appear"));

		changeTheme.setText(LanguageManager.getInstance().getRes().getString("ct"));
		changeLanguage.setText(LanguageManager.getInstance().getRes().getString("cl"));
		srbCr.setText(LanguageManager.getInstance().LANG_SERBIAN_CYR.getDisplayName());
		srb.setText(LanguageManager.getInstance().LANG_SERBIAN_LAT.getDisplayName());
		eng.setText(LanguageManager.getInstance().LANG_ENG.getDisplayName());

		miAbout.setText(LanguageManager.getInstance().getRes().getString("about"));
		miHelpContent.setText(LanguageManager.getInstance().getRes().getString("helpc"));
		miKeyBindings.setText(LanguageManager.getInstance().getRes().getString("keyb"));

		miFind.setText(LanguageManager.getInstance().getRes().getString("find"));
		miClearHighlights.setText(LanguageManager.getInstance().getRes().getString("clear_highlights"));
	}
	
	public KeyStroke getMiDeleteSelectionAccelerator() {
		return miDeleteSelection.getAccelerator();
	}
	public KeyStroke getMiEditSelectionAccelerator() {
		return miEditSelection.getAccelerator();
	}

	public KeyStroke getMiFindAccelerator() {
		return miFind.getAccelerator();
	}

	public KeyStroke getMiNewProjectAccelerator() {
		return miNewProject.getAccelerator();
	}

	public KeyStroke getMiNewWizardAccelerator() {
		return miNewWizard.getAccelerator();
	}

	public KeyStroke getMiNewParameterAccelerator() {
		return miNewParameter.getAccelerator();
	}

	public KeyStroke getMiOpenProjectAccelerator() {
		return miOpenProject.getAccelerator();
	}

	public KeyStroke getMiSaveProjectAccelerator() {
		return miSaveProject.getAccelerator();
	}

	public KeyStroke getMiHelpContentAccelerator() {
		return miHelpContent.getAccelerator();
	}

	public JCheckBoxMenuItem getShowHideEditorToolbar() {
		return showHideEditorToolbar;
	}

	public JCheckBoxMenuItem getShowHideMainToolbar() {
		return showHideMainToolbar;
	}

	public JCheckBoxMenuItem getShowHideStatusBar() {
		return showHideStatusBar;
	}

	public KeyStroke getMiSaveWorkspaceAccelerator() {
		return miSaveWorkspace.getAccelerator();
	}

	public KeyStroke getMiOpenWorkspaceAccelerator() {
		return miOpenWorkspace.getAccelerator();
	}

}