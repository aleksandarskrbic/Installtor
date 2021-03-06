package configurator.gui.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import configurator.Constants;
import configurator.controller.EscapeDispose;
import localization.LanguageManager;

public class AboutDialog extends EscapeDispose {

	private JPanel aboutPanel;
	private JPanel versionPanel;
	private JPanel teamPanel;
	private JPanel statusbar;
	private JTabbedPane tabbedPane;

	public AboutDialog(JFrame parent) {
		super(parent, true);

		setTitle(LanguageManager.getInstance().getRes().getString("about"));
		setSize(new Dimension(690, 400));
		setIconImage(new ImageIcon("images/about.png").getImage());
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		init();
		groupComponents();
	}

	private void init() {
		aboutPanel = new JPanel(new BorderLayout());
		versionPanel = new JPanel(new BorderLayout());
		teamPanel = new JPanel(new BorderLayout());
		tabbedPane = new JTabbedPane();
		statusbar = new JPanel();

		JLabel txt = new JLabel("Copyright(C) 2017 Tim9.1. " + LanguageManager.getInstance().getRes().getString("all_rr"));
		statusbar.add(txt);

		ImageIcon img = new ImageIcon("images/logo.png");
		JLabel imgLabel = new JLabel("", img, JLabel.CENTER);
		aboutPanel.add(imgLabel, BorderLayout.CENTER);
		JTextArea teamMembers = new JTextArea("Pavle Jankovic - Team Lead \n\nNemanja Simic \n\n"
				+ "Aleksandar Skrbic\n\nAleksandar Vujasinovic");
		teamMembers.setEditable(false);
		teamMembers.setFont(new Font("Arial", Font.BOLD, 25));
		
		teamPanel.add(teamMembers, BorderLayout.CENTER);
		
		img = new ImageIcon("images/logo_version.resized.png");
		imgLabel = new JLabel("", img, JLabel.CENTER);
		versionPanel.add(imgLabel, BorderLayout.CENTER);
		
		JTextArea versionTA = new JTextArea("Version: " + Constants.PROGRAM_VERSION + "\n\nOperating System: "+ System.getProperty("os.name") + 
				"\n\nJava version: " + System.getProperty("java.version"));
		versionTA.setEditable(false);
		versionTA.setFont(new Font("Arial", Font.BOLD, 25));
		versionTA.setOpaque(false);
		
		versionPanel.add(versionTA, BorderLayout.WEST);

	}

	private void groupComponents() {
		tabbedPane.add(LanguageManager.getInstance().getRes().getString("logo"), aboutPanel);
		tabbedPane.add(LanguageManager.getInstance().getRes().getString("versions"), versionPanel);
		tabbedPane.add(LanguageManager.getInstance().getRes().getString("team"), teamPanel);

		add(tabbedPane, BorderLayout.CENTER);
		add(statusbar, BorderLayout.SOUTH);
	}

}
