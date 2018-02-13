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
import configurator.gui.parameter.ParameterEditorPanel;
import configurator.model.Wizard;
import configurator.model.parameter.AbstractParameter;
import configurator.storage.ProjectStorage;
import localization.LanguageManager;

public class EditParameterDialog extends EscapeDispose {

	private static final long serialVersionUID = -2320447908421868437L;

	private static final String DIALOG_TITLE = "Edit Parameter";

	private AbstractParameter parameter = null;

	private Wizard wizard = null;

	private JButton btnSave = null;

	private JButton btnCancel = null;

	private ParameterEditorPanel paramEditorPanel = null;


	public EditParameterDialog(JFrame parent, Wizard wizard, AbstractParameter parameter) {
		super(parent,true);
		this.parameter = parameter;
		this.wizard = wizard;

		setLocationRelativeTo(parent);
		setSize(new Dimension(350, 400));
		setTitle(DIALOG_TITLE);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		initComponents();
		groupComponents();
	}


	private void initComponents() {

		paramEditorPanel = new ParameterEditorPanel(parameter);

		btnSave = new JButton(LanguageManager.getInstance().getRes().getString("save"));
		btnSave.addActionListener(e -> save());

		btnCancel = new JButton(LanguageManager.getInstance().getRes().getString("cancel"));
		btnCancel.addActionListener(e -> dispose());
	}

	private void save() {
		AbstractParameter editedParameter = paramEditorPanel.generateParameter();
		if (editedParameter.getName().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, LanguageManager.getInstance().getRes().getString("field_name_error"),
					LanguageManager.getInstance().getRes().getString("error"), JOptionPane.ERROR_MESSAGE);
			return;
		}

		wizard.updateParameter(parameter, editedParameter);
		ProjectStorage.getInstance().updateElement(editedParameter);
		dispose();
	}

	private void groupComponents() {
		setLayout(new BorderLayout());

		add(paramEditorPanel, BorderLayout.CENTER);
		add(getControlPanel(), BorderLayout.SOUTH);
	}

	private JPanel getControlPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		panel.add(btnCancel);
		panel.add(btnSave);

		return panel;
	}

}
