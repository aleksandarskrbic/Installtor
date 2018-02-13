package configurator.gui.modeleditor;

import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import configurator.model.Project;
import configurator.model.Wizard;
import localization.LanguageManager;
import net.miginfocom.swing.MigLayout;

public class WizardEditorPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	
	private final JTextField wizardName;
	private final JTextArea wizardDescription;
	private final JLabel chooseProject;
	private final JLabel desc;
	private final JLabel name;
	private final JComboBox<Project> cmbProjects;
	
	public WizardEditorPanel() {
		wizardName = new JTextField();
		wizardDescription = new JTextArea();
		cmbProjects = new JComboBox<Project>();
		name = new JLabel(LanguageManager.getInstance().getRes().getString("name") + ":");
		chooseProject = new JLabel(LanguageManager.getInstance().getRes().getString("cproj") + ":");
		desc = new JLabel(LanguageManager.getInstance().getRes().getString("desc") + ":");
		groupComponents();
	}
	
	public WizardEditorPanel(Wizard wiz, Project p) {
		this();
		wizardName.setText(wiz.getName());
		if(wiz.getName().equals("Location"))
			wizardName.setEditable(false);
		wizardDescription.setText(wiz.getDescription());
	}
	private void groupComponents() {
		setLayout(new MigLayout("", "10[]10[grow]", "10[]10[]10[grow]"));
		add(name,"cell 0 0");
		add(wizardName,"cell 1 0, growx");
		add(chooseProject,"cell 0 1");
		add(cmbProjects,"cell 1 1, growx");
		add(desc,"cell 0 2");
		add(new JScrollPane(wizardDescription),"cell 1 2, growx , growy");
	}
	
	public String getWizardName() {
		return wizardName.getText();
	}
	
	public String getWizardDescription() {
		return wizardDescription.getText();
	}
	
	public void addProjects(List<Project> projects) {
		for(Project project : projects)
			cmbProjects.addItem(project);
	}
	
	public void setSelectedProject(Project project) {
		cmbProjects.setSelectedItem(project);
	}
	
	public Project getSelectedProject() {
		return (Project) cmbProjects.getSelectedItem();
	}
	public void  hideCmbBox() {
		cmbProjects.setVisible(false);
		chooseProject.setVisible(false);
	}

}
