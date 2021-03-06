package configurator.gui.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import configurator.controller.EscapeDispose;
import configurator.gui.modeleditor.ProjectEditorPanel;
import configurator.model.Project;
import configurator.storage.ProjectStorage;
import localization.LanguageManager;

public class EditProjectDialog extends EscapeDispose {

	private static final long serialVersionUID = -7848407778695551351L;

	private static final String DIALOG_TITLE = "Edit Project";

	private final Project project;

	private final JButton saveButton;

	private final JButton cancelButton;

	private final ProjectEditorPanel editorPanel;

	public EditProjectDialog(JFrame parent, final Project project) {
		super(parent, true);

		setTitle(DIALOG_TITLE);
		setSize(new Dimension(350, 400));
		setIconImage(new ImageIcon("images/project_small.pmg").getImage());
		setLocationRelativeTo(parent);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		this.project = project;
		saveButton = new JButton(LanguageManager.getInstance().getRes().getString("save"));
		cancelButton = new JButton(LanguageManager.getInstance().getRes().getString("cancel"));
		editorPanel = new ProjectEditorPanel(project);

		setupComponents();
		groupComonents();
	}

	private void setupComponents() {
		saveButton.addActionListener(e -> save());
		cancelButton.addActionListener(e -> dispose());
	}

	private void save() {
		if (editorPanel.getProjName().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, LanguageManager.getInstance().getRes().getString("field_name_error"),
					LanguageManager.getInstance().getRes().getString("error"), JOptionPane.ERROR_MESSAGE);
			return;
		}

		project.setName(editorPanel.getProjName());
		project.setDescription(editorPanel.getProjDesc());

		try {
			ProjectStorage.getInstance().updateElement(project);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		dispose();
	}

	private void groupComonents() {
		setLayout(new BorderLayout());
		add(editorPanel, BorderLayout.CENTER);
		add(getControlsPanel(), BorderLayout.SOUTH);
	}

	private JPanel getControlsPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		panel.add(cancelButton);
		panel.add(saveButton);

		return panel;
	}

}
