package configurator.gui.dialog;

import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import configurator.PropertiesManager;
import configurator.controller.EscapeDispose;
import localization.LanguageManager;
import net.miginfocom.swing.MigLayout;

public class WorkspaceChooserDialog extends EscapeDispose {

	private static final long serialVersionUID = -3646382556163699413L;

	private JLabel selectWsLabel;
	
	private JTextField pathField;
	
	private JButton selectButton;
	
	private JButton confirm;
	
	private JButton cancel;
	
	private JFileChooser chooser;

	private File workspace;
	
	private static final String PROP_WORKSPACE = "workspace";
	
	public WorkspaceChooserDialog(JFrame parent) {
		super(parent, true);
		
		setSize(350, 150);
		setTitle(LanguageManager.getInstance().getRes().getString("select_ws"));
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		initComponents();
		groupComponents();
		pack(); 
	}
	private void initComponents() {
		chooser = new JFileChooser();
		selectWsLabel = new JLabel(LanguageManager.getInstance().getRes().getString("selected_ws"));
		pathField = new JTextField();
		selectButton = new JButton(LanguageManager.getInstance().getRes().getString("select_button"));
		selectButton.setSize(30, pathField.getHeight());
		confirm = new JButton(LanguageManager.getInstance().getRes().getString("confirm_button"));
		cancel = new JButton(LanguageManager.getInstance().getRes().getString("cancel"));
		
		selectButton.addActionListener(l -> openChooser());
		confirm.addActionListener(l -> check());
		cancel.addActionListener(l -> 
			{
				workspace = null;
				dispose();
			});
		
		String savedWorkspace = PropertiesManager.getInstance().get(PROP_WORKSPACE);
		if(savedWorkspace != null) {
			pathField.setText(savedWorkspace);
			workspace = new File(savedWorkspace);
		}
	}

	private void groupComponents() {
		setLayout(new MigLayout("", "10[grow]10[]10[]10", "10[][]20[]"));
	
		add(selectWsLabel, "cell 0 0, growx");
		add(pathField, "cell 0 1, spanx 2, growx");
		add(selectButton, "cell 2 1, align right");
		add(cancel, "cell 1 2");
		add(confirm, "cell 2 2");
	}

	private void openChooser() {
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			this.workspace = chooser.getSelectedFile();
			pathField.setText(workspace.getAbsolutePath());
		}
	}
	
	public File getWorkspace() {
		return workspace;
	}
	
	private void check() {
		if(pathField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, 
					LanguageManager.getInstance().getRes().getString("select_ws"), 
					LanguageManager.getInstance().getRes().getString("no_ws_selected"),
					JOptionPane.OK_OPTION);
			return;
		}
		PropertiesManager.getInstance().put(PROP_WORKSPACE, workspace.getAbsolutePath());
		dispose();
	}
}
