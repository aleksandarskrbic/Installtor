package configurator.gui.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import configurator.controller.EscapeDispose;
import configurator.gui.ProjectExplorerPanel;
import configurator.gui.parameter.ParameterEditorPanel;
import configurator.model.Project;
import configurator.model.Wizard;
import configurator.model.parameter.AbstractParameter;
import configurator.storage.ProjectStorage;
import localization.LanguageManager;

public class NewParameterDialog extends EscapeDispose {

	private static final long serialVersionUID = -6330855496758999544L;

	private ParameterEditorPanel parameterEditorPanel = null;

	private JButton btnAdd = null;

	private JButton btnCancel = null;

	public NewParameterDialog(JFrame parent) {
		super(parent, true);

		
		setSize(new Dimension(350, 400));
		setLocationRelativeTo(parent);
		setTitle(LanguageManager.getInstance().getRes().getString("new_param"));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		
		initComponents();
		groupComponents();
	}

	private void initComponents() {

		parameterEditorPanel = new ParameterEditorPanel();

		parameterEditorPanel.addProjects(ProjectStorage.getInstance().getProjects());

		Project selectedProject = ProjectExplorerPanel.getSelectedProject();
		if (selectedProject != null)
			parameterEditorPanel.setSelectedProject(selectedProject);

		btnAdd = new JButton(LanguageManager.getInstance().getRes().getString("add"));
		btnAdd.addActionListener(e -> create());
		btnCancel = new JButton(LanguageManager.getInstance().getRes().getString("cancel"));
		btnCancel.addActionListener(e -> dispose());
	}

	private void create() {
		AbstractParameter parameter = parameterEditorPanel.generateParameter();

		Wizard selectedWizard = (Wizard) parameterEditorPanel.getSelectedWizard();

		if (selectedWizard == null) {
			JOptionPane.showMessageDialog(this, LanguageManager.getInstance().getRes().getString("no_wiz_s"),
					LanguageManager.getInstance().getRes().getString("error"), JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (parameter.getName().isEmpty()) {
			JOptionPane.showMessageDialog(this, LanguageManager.getInstance().getRes().getString("field_name_error"),
					LanguageManager.getInstance().getRes().getString("error"), JOptionPane.ERROR_MESSAGE);
			return;
		}

		try {
			selectedWizard.addParameter(parameter);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage(), LanguageManager.getInstance().getRes().getString("error"),
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		ProjectStorage.getInstance().updateElement(parameter);
		dispose();
	}

	private void groupComponents() {
		setLayout(new BorderLayout());

		add(parameterEditorPanel, BorderLayout.CENTER);
		add(getControlPanel(), BorderLayout.SOUTH);
	}

	private JPanel getControlPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		panel.add(btnCancel);
		panel.add(btnAdd);

		return panel;
	}

}
