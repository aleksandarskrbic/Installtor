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

public class NewProjectDialog extends EscapeDispose {

	private static final long serialVersionUID = 321938249115965564L;

	private JButton btnCreate = null;

	private JButton btnCancel = null;

	private ProjectEditorPanel editorPanel = null;

	public NewProjectDialog(JFrame parent) {
		super(parent, true);

		setTitle(LanguageManager.getInstance().getRes().getString("new_proj"));
		setSize(new Dimension(350, 400));
		setIconImage(new ImageIcon("images/project_small.pmg").getImage());
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		
		initComponents();
		groupComonents();
	}

	private void initComponents() {
		
		editorPanel = new ProjectEditorPanel();
		btnCancel = new JButton(LanguageManager.getInstance().getRes().getString("cancel"));
		btnCancel.addActionListener(e -> dispose());
		btnCreate = new JButton(LanguageManager.getInstance().getRes().getString("create"));
		btnCreate.addActionListener(e -> create());
	}

	private void create() {
		String name = editorPanel.getProjName().trim();
		if (name.isEmpty()) {
			JOptionPane.showMessageDialog(this, LanguageManager.getInstance().getRes().getString("field_name_error"),
					LanguageManager.getInstance().getRes().getString("error"), JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (!editorPanel.pathsSelected()) {
			JOptionPane.showMessageDialog(this, LanguageManager.getInstance().getRes().getString("not_choosen_path"),
					LanguageManager.getInstance().getRes().getString("error"), JOptionPane.ERROR_MESSAGE);
			return;
		}

		String description = editorPanel.getProjDesc().trim();

		Project p = new Project(name, editorPanel.getSrcPath(), editorPanel.getDstPath());
		
		if (!description.isEmpty())
			p.setDescription(description);
		try {
			ProjectStorage.getInstance().addProject(p);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage(), LanguageManager.getInstance().getRes().getString("error"),
					JOptionPane.ERROR_MESSAGE);
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
		final JPanel panel = new JPanel();

		panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panel.add(btnCancel);
		panel.add(btnCreate);

		return panel;
	}

}
