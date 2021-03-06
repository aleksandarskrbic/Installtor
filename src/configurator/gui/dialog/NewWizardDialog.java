package configurator.gui.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import configurator.controller.EscapeDispose;
import configurator.gui.ProjectExplorerPanel;
import configurator.gui.modeleditor.WizardEditorPanel;
import configurator.model.Project;
import configurator.model.Wizard;
import configurator.storage.ProjectStorage;
import localization.LanguageManager;

public class NewWizardDialog extends EscapeDispose {

	private static final long serialVersionUID = 185927410277902864L;

	private JButton btnCreate = null;

	private JButton btnCancel = null;

	private WizardEditorPanel wizEditor = null;

	public NewWizardDialog(JFrame parent) {
		super(parent, true);
		setTitle(LanguageManager.getInstance().getRes().getString("new_wiz"));
		setSize(300, 500);
		setIconImage(new ImageIcon("images/Wizard-icon.png").getImage());
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		
		initComponents();
		groupComonents();
	}

	private void initComponents() {

		btnCreate = new JButton(LanguageManager.getInstance().getRes().getString("create"));
		btnCancel = new JButton(LanguageManager.getInstance().getRes().getString("cancel"));
		wizEditor = new WizardEditorPanel();

		btnCancel.addActionListener(e -> dispose());
		btnCreate.addActionListener(l -> create());

		wizEditor.addProjects(ProjectStorage.getInstance().getProjects());

		Project selectedProject = ProjectExplorerPanel.getSelectedProject();
		if (selectedProject != null)
			wizEditor.setSelectedProject(selectedProject);

	}

	private void create() {

		Project selectedProject = wizEditor.getSelectedProject();
		Wizard wizard = new Wizard(wizEditor.getWizardName());

		if (wizEditor.getWizardName().isEmpty()) {
			JOptionPane.showMessageDialog(this, LanguageManager.getInstance().getRes().getString("field_name_error"),
					LanguageManager.getInstance().getRes().getString("error"), JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			selectedProject.addWizard(wizard);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage(), LanguageManager.getInstance().getRes().getString("error"),
					JOptionPane.ERROR_MESSAGE);
		}
		ProjectStorage.getInstance().updateElement(wizard);

		dispose();
	}

	private void groupComonents() {
		setLayout(new BorderLayout());
		add(wizEditor, BorderLayout.CENTER);
		add(getControlsPanel(), BorderLayout.SOUTH);
	}

	private JPanel getControlsPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		panel.add(btnCancel);
		panel.add(btnCreate);

		return panel;
	}
}
