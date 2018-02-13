package configurator.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import configurator.Constants;
import configurator.controller.MainFrameController;
import localization.LanguageManager;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = -2221074717390598212L;

	private WorkspacePanel workspacePanel;

	private MenuBar menu;

	private MainToolbar toolbar;

	private StatusBarPanel statusBar;
	
	private static MainFrame instance = null;

	private MainFrame() {
	}

	private void groupComponents() {
		setJMenuBar(menu);
		add(toolbar, BorderLayout.NORTH);
		add(workspacePanel, BorderLayout.CENTER);
		add(statusBar, BorderLayout.SOUTH);
	}

	public static boolean hasInstance() {
		return instance != null;
	}
	
	public static MainFrame getInstance() {
		if (instance == null) {
			instance = new MainFrame();
			instance.init();
			instance.changeLanguage();
		}

		return instance;
	}

	public WorkspacePanel getWorkspace() {
		return workspacePanel;
	}
	
	private void init() {
		setTitle(LanguageManager.getInstance().getRes().getString("ui") + " " + Constants.PROGRAM_VERSION);
		setSize(new Dimension(800, 600));
		setIconImage(new ImageIcon("images/favicon.png").getImage());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		menu = new MenuBar();
		toolbar = new MainToolbar();
		workspacePanel = new WorkspacePanel();
		statusBar = new StatusBarPanel();
		groupComponents();
		
		
		MainFrameController controller = new MainFrameController();
		addWindowListener(controller);
		addComponentListener(controller);
	}

	public MenuBar getMenu() {
		return menu;
	}

	public MainToolbar getToolbar() {
		return toolbar;
	}

	public StatusBarPanel getStatusBar() {
		return statusBar;
	}

	public void changeLanguage() {
		UIManager.put("OptionPane.yesButtonText", LanguageManager.getInstance().getRes().getString("yes"));
		UIManager.put("OptionPane.noButtonText", LanguageManager.getInstance().getRes().getString("no"));
		UIManager.put("FileChooser.cancelButtonText", LanguageManager.getInstance().getRes().getString("cancel"));
		UIManager.put("FileChooser.cancelButtonToolTipText", LanguageManager.getInstance().getRes().getString("cancel"));
		UIManager.put("FileChooser.openButtonText", LanguageManager.getInstance().getRes().getString("open"));
		UIManager.put("FileChooser.openButtonToolTipText", LanguageManager.getInstance().getRes().getString("open"));
		UIManager.put("FileChooser.openDialogTitleText", LanguageManager.getInstance().getRes().getString("open"));
		UIManager.put("FileChooser.filesOfTypeLabelText", LanguageManager.getInstance().getRes().getString("file_type"));
		UIManager.put("FileChooser.fileNameLabelText", LanguageManager.getInstance().getRes().getString("file_name"));
		UIManager.put("FileChooser.lookInLabelText", LanguageManager.getInstance().getRes().getString("look_in"));
		
		toolbar.updateLanguage();
		menu.updateLanguage();
		workspacePanel.getEditorPanel().getToolbar().updateLanguage();
		workspacePanel.getEditorPanel().getModel().updateLanguage();
		statusBar.updateLanguage();
		setTitle(LanguageManager.getInstance().getRes().getString("ui") + " " + Constants.PROGRAM_VERSION);
	}
}
