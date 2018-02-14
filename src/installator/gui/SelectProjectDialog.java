package installator.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

import configurator.Constants;
import configurator.PropertiesManager;
import configurator.controller.EscapeDispose;
import configurator.model.Project;
import configurator.storage.BinarySerializer;
import configurator.storage.FileManager;
import installator.builder.WizardBuilder;
import localization.LanguageManager;
import localization.SupportedLanguage;
import net.miginfocom.swing.MigLayout;

public class SelectProjectDialog extends EscapeDispose {
	
	private static final long serialVersionUID = 1938553829436322021L;
    
	private JLabel select;

	private JTextField pathField;

	private JButton selectBtn;

	private JButton confirm;

	private JButton cancel;

	private JFileChooser chooser;
	
	private String projectPath;
	
	private JComboBox<SupportedLanguage> selectLang;
	
	private static Project project;
	
	public SelectProjectDialog() {
		super(null, true);
		
		try {
			UIManager.setLookAndFeel(PropertiesManager.getInstance().get("theme", UIManager.getSystemLookAndFeelClassName()));
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		initComponents();
		groupComponents();
	}
	
	private void initComponents() {
		setSize(420, 140);
		setTitle(LanguageManager.getInstance().getRes().getString("ui") + " " + Constants.PROGRAM_VERSION);
		setLocationRelativeTo(null);
		setResizable(false);
		
		chooser = new JFileChooser();
		chooser.setFileFilter(new FileNameExtensionFilter("Uni Project", "uni"));

		select = new JLabel(LanguageManager.getInstance().getRes().getString("select") + ":");
		pathField = new JTextField();
		selectBtn = new JButton(LanguageManager.getInstance().getRes().getString("select_button"));
		confirm = new JButton(LanguageManager.getInstance().getRes().getString("confirm_button"));
		cancel = new JButton(LanguageManager.getInstance().getRes().getString("cancel"));

		selectBtn.addActionListener(l -> openChooser());
		confirm.addActionListener(l -> openApp());
		cancel.addActionListener(l -> Runtime.getRuntime().exit(ABORT));
		
		selectLang = new JComboBox<>(LanguageManager.getInstance().getSupportedLanguages());
		
		selectLang.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SupportedLanguage selected = (SupportedLanguage) selectLang.getSelectedItem();
				LanguageManager.getInstance().changeLanguage(selected);
				updateLanguage();
			}
		});
		
	}

	private void groupComponents() {
		setLayout(new MigLayout("", "10[grow]10[]10[]10", "10[][]10[]10"));

		add(select, "cell 0 0, growx");
		add(pathField, "cell 0 1, spanx 2, growx");
		add(selectBtn, "cell 2 1, align right");
		add(cancel, "cell 1 2");
		add(confirm, "cell 2 2");
		add(selectLang, "cell 0 2");
	}

	private void openChooser() {
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			final Path p = chooser.getSelectedFile().toPath();
			pathField.setText(chooser.getSelectedFile().getPath().toString());
			projectPath = p.toString();
		}
	}

	private void openApp() {
		if (pathField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, LanguageManager.getInstance().getRes().getString("select"),
					LanguageManager.getInstance().getRes().getString("no_proj"), JOptionPane.OK_OPTION);
			return;
		}
		
		final FileManager loader = new BinarySerializer();
		// TODO: obezbijediti lose unose!
		project = loader.openProject(new File(projectPath));
		WizardBuilder.getInstance().build();
		dispose();
	}
	
	public void updateLanguage() {
		select.setText(LanguageManager.getInstance().getRes().getString("select") + ":");
		selectBtn.setText(LanguageManager.getInstance().getRes().getString("select_button"));
		confirm.setText(LanguageManager.getInstance().getRes().getString("confirm_button"));
		cancel.setText(LanguageManager.getInstance().getRes().getString("cancel"));
	}
	
	public static Project getProject() {
		return project;
	}

}
