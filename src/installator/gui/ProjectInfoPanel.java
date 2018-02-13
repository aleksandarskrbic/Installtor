package installator.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import configurator.model.Project;
import configurator.model.Wizard;
import configurator.model.parameter.AbstractParameter;
import localization.LanguageManager;

public class ProjectInfoPanel extends JPanel {

	private JPanel panel;

	private Project project;

	public ProjectInfoPanel() {
		project = SelectProjectDialog.getProject();
		setSize(new Dimension(600, 400));
		setLayout(new FlowLayout(FlowLayout.CENTER, 10, 30));
		initComponents();
	}

	private void initComponents() {
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JLabel title = new JLabel(LanguageManager.getInstance().getRes().getString("proj_wiz_param"));
		title.setFont(new Font("Serif", Font.BOLD, 29));
		title.setForeground(Color.DARK_GRAY);
		panel.add(title);
		
		JLabel name = new JLabel(project.getName());
		name.setFont(new Font("Serif", Font.BOLD, 24));
		name.setForeground(Color.DARK_GRAY);
		panel.add(name);

		for (Wizard w : project.getWizards()) {
			JLabel label = new JLabel("   " + w.getName());
			label.setFont(new Font("Serif", Font.BOLD, 18));
			label.setForeground(Color.BLUE);
			panel.add(label);
			for (AbstractParameter p : w.getParameters()) {
				panel.add(new JLabel("       - " + p.getName()));
			}
		}
		add(panel);
	}
}