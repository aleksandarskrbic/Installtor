package installator.gui;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import configurator.Constants;
import installator.builder.WizardBuilder;
import localization.LanguageManager;

public class InstallatorMainFrame extends JFrame {
	
	private static final long serialVersionUID = -8018419067704296549L;

	private static InstallatorMainFrame instance = null;
	
	private ButtonsPanel controlButtonsPanel = null;
	
	private JPanel mainPanel;
	
	private InstallatorMainFrame() {
		
	}
	
	public static InstallatorMainFrame getInstance() {
		if (instance == null) {
			instance = new InstallatorMainFrame();
			instance.init();
		}
		
		return instance;
	}
	
	public static boolean hasInstance() {
		return instance != null;
	}
	
	private void init() {
		setSize(500, 300);
		setTitle(LanguageManager.getInstance().getRes().getString("ui") + " " + Constants.PROGRAM_VERSION);
		setSize(new Dimension(800,600));
		setResizable(false);
		setIconImage(new ImageIcon("images/favicon.png").getImage());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		controlButtonsPanel = new ButtonsPanel();
		mainPanel = WizardBuilder.getInstance().getPanel(0);
		groupComponents();
	}
	
	private void groupComponents() {
		add(mainPanel, BorderLayout.CENTER);
		add(controlButtonsPanel, BorderLayout.SOUTH);
	}
	
	public void setContent(JPanel panel) {
		remove(mainPanel);
		mainPanel = panel;
		add(mainPanel);
		pack();
		validate();
	}
	
	public void changeLanguage() {
		ResourceBundle res = LanguageManager.getInstance().getRes();
		
		UIManager.put("OptionPane.yesButtonText", res.getString("yes"));
		UIManager.put("OptionPane.noButtonText", res.getString("no"));
		UIManager.put("FileChooser.cancelButtonText", res.getString("cancel"));
		UIManager.put("FileChooser.cancelButtonToolTipText", res.getString("cancel"));
		UIManager.put("FileChooser.openButtonText", res.getString("open"));
		UIManager.put("FileChooser.openButtonToolTipText", res.getString("open"));
		UIManager.put("FileChooser.openDialogTitleText", res.getString("open"));
		UIManager.put("FileChooser.filesOfTypeLabelText", res.getString("file_type"));
		UIManager.put("FileChooser.fileNameLabelText", res.getString("file_name"));
		UIManager.put("FileChooser.lookInLabelText", res.getString("look_in"));
		
		setTitle(res.getString("ui") + " " + Constants.PROGRAM_VERSION);
		
		pack();
	}
}
