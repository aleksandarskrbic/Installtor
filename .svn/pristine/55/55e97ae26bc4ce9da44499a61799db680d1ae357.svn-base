package configurator.gui.parameter;

import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import configurator.model.Project;
import configurator.model.Wizard;
import configurator.model.parameter.AbstractParameter;
import configurator.model.parameter.ParameterType;
import localization.LanguageManager;
import net.miginfocom.swing.MigLayout;

public class ParameterEditorPanel extends JPanel {

	private static final long serialVersionUID = -2482843874094247013L;
	private final static int STATE_EDIT = 0;
	private final static int STATE_CREATE = 1;
	
	private int state = -1;
	
	private JComboBox<Project> cmbProjects = null;
	private JComboBox<Wizard> cmbWizards = null;
	private JTextField txtName = null;
	private JTextArea txtDescription = null;
	private JCheckBox cbxMandatory = null;
	private JComboBox<ParameterTypeEntry> cmbType = null;
	
	private JPanel optionsPanel = null;

	private Project editProject;
	private AbstractParameter editParameter;
	
	private JLabel proj;
	private JLabel wiz;
	private JLabel name;
	private JLabel desc;
	private JLabel type;
	/**
	 * 
	 * Edit parametra.
	 * 
	 */
	public ParameterEditorPanel(AbstractParameter editParameter) {
		super();
		
		this.editParameter = editParameter;
		state = STATE_EDIT;
		
		initComponents();
		groupComponents();
		
		
		createEntries();
		setParameter();
	}
	
	public ParameterEditorPanel() {
		super();
		
		state = STATE_CREATE;
		
		initComponents();
		groupComponents();
		createEntries();
	}
	
	private void setParameter() {
		txtName.setText(editParameter.getName());
		txtDescription.setText(editParameter.getDescription());
		
		cmbProjects.addItem(editProject);
		cmbType.setSelectedItem(editParameter.getType());
		
		ParameterTypeEntry typeEntry = (ParameterTypeEntry) cmbType.getSelectedItem();
		typeEntry.getOptionsEditor().setParameter(editParameter);
	}

	
	private void initComponents() {
		cmbProjects = new JComboBox<Project>();
		cmbWizards = new JComboBox<Wizard>();
		
		cmbProjects.addActionListener(l -> {
			if (getSelectedProject() != null)
				addWizards(getSelectedProject().getWizards());
		    });
		
		txtName = new JTextField(30);
		// TODO: izvuci iz resursa
		if((state == STATE_EDIT) && (editParameter.getName().equals("Source") || editParameter.getName().equals("Destination")))
			txtName.setEditable(false);
		
		txtDescription = new JTextArea(3, 20);
		cbxMandatory = new JCheckBox(LanguageManager.getInstance().getRes().getString("mandatory"));
		cmbType = new JComboBox<ParameterTypeEntry>();
		cmbType.addItemListener(e -> changeOptionsPanel());

		optionsPanel = new JPanel();
		optionsPanel.setLayout(new CardLayout());
		
		proj = new JLabel(LanguageManager.getInstance().getRes().getString("proj"));
		wiz = new JLabel(LanguageManager.getInstance().getRes().getString("wiz"));
		desc = new JLabel(LanguageManager.getInstance().getRes().getString("desc"));
		name = new JLabel(LanguageManager.getInstance().getRes().getString("name"));
		type = new JLabel(LanguageManager.getInstance().getRes().getString("type"));
	}

	
	private void groupComponents() {
		setLayout(new MigLayout("", "5[]10[grow]5", "10[]5[]5[]5[]5[]5[]5[grow]5"));

		add(proj, "cell 0 0");
		add(cmbProjects, "cell 1 0");
		add(wiz, "cell 0 1");
		add(cmbWizards, "cell 1 1");
		
		add(name, "cell 0 2");
		add(txtName, "cell 1 2, growx");
		add(desc, "cell 0 3, aligny top");
		add(new JScrollPane(txtDescription), "cell 1 3, growx");
		add(cbxMandatory, "cell 0 4, span 2 1");
		add(type, "cell 0 5");
		add(cmbType, "cell 1 5");
		add(optionsPanel, "cell 0 6, span 2 1, grow");
	}

	private void createEntries() {
		List<ParameterTypeEntry> entries = new ArrayList<ParameterTypeEntry>();
		entries.add(new ParameterTypeEntry(LanguageManager.getInstance().getRes().getString("text"), ParameterType.TEXT));
		entries.add(new ParameterTypeEntry(LanguageManager.getInstance().getRes().getString("number"), ParameterType.NUMBER));
		entries.add(new ParameterTypeEntry(LanguageManager.getInstance().getRes().getString("logical"), ParameterType.LOGICAL));
		entries.add(new ParameterTypeEntry(LanguageManager.getInstance().getRes().getString("loc_on_disk"), ParameterType.PATH));
		entries.add(new ParameterTypeEntry(LanguageManager.getInstance().getRes().getString("img"), ParameterType.IMAGE));
		entries.add(new ParameterTypeEntry(LanguageManager.getInstance().getRes().getString("multi_line"), ParameterType.MULTILINE));

		for (ParameterTypeEntry e : entries) {
			cmbType.addItem(e);
			optionsPanel.add(String.valueOf(e.hashCode()), e.getOptionsEditor());
			
			if(state == STATE_EDIT) {
				if(e.getType().equals(editParameter.getType()))
					cmbType.setSelectedItem(e);
			}
		}
		
	}
	
	private void changeOptionsPanel() {
		CardLayout cardLayout = (CardLayout) optionsPanel.getLayout();
		ParameterTypeEntry selectedEntry = (ParameterTypeEntry) cmbType.getSelectedItem();
		cardLayout.show(optionsPanel, String.valueOf(selectedEntry.hashCode()));
	}
	
	public AbstractParameter generateParameter() {
		String name = txtName.getText().trim();
		String description = txtDescription.getText().trim();

		ParameterTypeEntry selected = (ParameterTypeEntry) cmbType.getSelectedItem();
		ParameterOptionsEditor view = selected.getOptionsEditor();
		AbstractParameter parameter = view.getParameter();
		
		parameter.setName(name);
		parameter.setDescription(description);
		
		return parameter;
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

	public void addWizards(List<Wizard> wizards) {
		System.out.println(wizards);
		cmbWizards.removeAllItems();
		for (Wizard wiz : wizards)
			cmbWizards.addItem(wiz);
	}
	public void setSelectedWizard(Wizard wiz) {
		cmbProjects.setSelectedItem(wiz);
	}
	public Wizard getSelectedWizard() {
		return (Wizard) cmbWizards.getSelectedItem();
	}
}
