package configurator.gui.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import configurator.controller.EscapeDispose;
import configurator.gui.modeleditor.WizardEditorPanel;
import configurator.model.Project;
import configurator.model.Wizard;
import configurator.storage.ProjectStorage;
import localization.LanguageManager;

public class EditWizardDialog extends EscapeDispose {

	private static final long serialVersionUID = 8197950949941198943L;

	private static final String DIALOG_TITLE = "Edit Wizard";

	private Wizard wizard = null;
	private Project project = null;
	private JButton saveBtn = null;
	private JButton cancelBtn = null;
	private WizardEditorPanel wizEditorPanel;

	public EditWizardDialog(JFrame parent, Wizard wizard) {
		super(parent, true);

		this.wizard = wizard;

		setTitle(DIALOG_TITLE);
		setSize(300, 500);
		setLocationRelativeTo(parent);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		initCompononets();
		groupComponents();
		setListeners();
	}

	private void setListeners() {
		cancelBtn.addActionListener(e -> dispose());
		saveBtn.addActionListener(e -> save());
	}

	private void save() {
		if (wizEditorPanel.getWizardName().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, LanguageManager.getInstance().getRes().getString("field_name_error"),
					LanguageManager.getInstance().getRes().getString("error"), JOptionPane.OK_OPTION);
			return;
		}

		wizard.setName(wizEditorPanel.getWizardName());
		wizard.setDescription(wizEditorPanel.getWizardDescription());
		try {
			ProjectStorage.getInstance().updateElement(wizard);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		dispose();
	}

	private void initCompononets() {

		saveBtn = new JButton(LanguageManager.getInstance().getRes().getString("save"));
		cancelBtn = new JButton(LanguageManager.getInstance().getRes().getString("cancel"));
		wizEditorPanel = new WizardEditorPanel(wizard, project);
		wizEditorPanel.hideCmbBox();
	}

	private void groupComponents() {
		setLayout(new BorderLayout());
		add(wizEditorPanel, BorderLayout.CENTER);
		add(getControlsPanel(), BorderLayout.SOUTH);
	}

	private JPanel getControlsPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panel.add(saveBtn);
		panel.add(cancelBtn);

		return panel;
	}

}
