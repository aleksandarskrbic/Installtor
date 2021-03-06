package configurator.gui.model;

import javax.swing.table.DefaultTableModel;

import configurator.gui.EditorPanel.EditType;
import configurator.gui.ProjectExplorerPanel;
import configurator.model.Project;
import configurator.model.Wizard;
import configurator.model.parameter.AbstractParameter;
import configurator.model.parameter.ImageParameter;
import configurator.model.parameter.LogicalParameter;
import configurator.model.parameter.MultiLineTextParameter;
import configurator.model.parameter.NumberParameter;
import configurator.model.parameter.PathParameter;
import configurator.model.parameter.TextParameter;
import localization.LanguageManager;

public class EditorModel extends DefaultTableModel {

	private static final long serialVersionUID = -6348617449059246626L;
	
	private Project activeProject;
	
	private AbstractParameter activeParameter;
	
	private Wizard activeWizard;
	
	private Object[][] data;
	
	private String[] columnNames;
	
	private EditType type;
	
	private String projName;
	
	private String projDesc;
	
	private String wizName;
	
	private String wizDesc;
	
	private String paramName;
	
	private String paramDesc;
	
	private String typeStr;
	
	private String name;
	
	private String desc;
	
	private String value;
	
	private String location;
	
	private String limited;
	
	public EditorModel(EditType type) {
		this.type = type;
		projName = LanguageManager.getInstance().getRes().getString("proj_name");
		projDesc = LanguageManager.getInstance().getRes().getString("proj_desc");
		wizName = LanguageManager.getInstance().getRes().getString("wiz_name");
		wizDesc = LanguageManager.getInstance().getRes().getString("wiz_desc");
		paramName = LanguageManager.getInstance().getRes().getString("param_name");
		paramDesc = LanguageManager.getInstance().getRes().getString("param_desc");
		
		typeStr = LanguageManager.getInstance().getRes().getString("type");
		name = LanguageManager.getInstance().getRes().getString("name");
		desc = LanguageManager.getInstance().getRes().getString("desc");
		value = LanguageManager.getInstance().getRes().getString("value");
		location = LanguageManager.getInstance().getRes().getString("location");
		limited = LanguageManager.getInstance().getRes().getString("limited");
		
		initModel(type);
		setDataVector(data, columnNames);
		activeProject = ProjectExplorerPanel.getSelectedProject();
		activeParameter = ProjectExplorerPanel.getSelectedParameter();
		activeWizard = ProjectExplorerPanel.getSelectedWizard();
	}
	
	private void initModel(EditType currentEdit) {
		switch(currentEdit) {
			case EMPTY: {
				columnNames = new String[] {"", "", "", ""};
				data = new String[][] {
					{ "", "", "", "" }
				};
				break;
			}
			
			case PROJECT: {
				columnNames = new String[] {projName, projDesc};
				Project project = ProjectExplorerPanel.getSelectedProject();
				if(project == null) {
					initModel(EditType.EMPTY);
					type = EditType.EMPTY;
					return;
				}
				data = new String[][] {
					{ project.getName(), project.getDescription() }
				};
				break;
			}
			
			case PARAMETER: {
				columnNames = new String[] {paramName, paramDesc};
				AbstractParameter param = ProjectExplorerPanel.getSelectedParameter();
				if(param == null) {
					initModel(EditType.EMPTY);
					type = EditType.EMPTY;
					return;
				}
				initParamTable(param);
				break;
			}
			
			case WIZARD: {
				columnNames = new String[] {wizName, wizDesc};
				Wizard wizard = ProjectExplorerPanel.getSelectedWizard();
				if(wizard == null) {
					initModel(EditType.EMPTY);
					type = EditType.EMPTY;
					return;
				}
				data = new String[][] {
					{ wizard.getName(), wizard.getDescription() }
				}; 
				break;
			}
			
			default: ;
		}
	}

	public void initParamTable(AbstractParameter param) {
		switch(param.getType()) {
			case NO_TYPE: {
				data = new String[][] {
					{ typeStr, param.getType().toString() },
					{ name, param.getName() },
					{ desc, param.getDescription() },
				};
				break;
			}
			
			case TEXT: {
				TextParameter p = (TextParameter)param;
				data = new String[][] {
					{ typeStr, p.getType().toString() },
					{ name, p.getName() },
					{ desc, p.getDescription() },
					{ "Text",  p.getValue() }
				};
				break;
			}
			
			case NUMBER: {
				NumberParameter p = (NumberParameter)param;
				data = new String[][] {
					{ typeStr, p.getType().toString() },
					{ name, p.getName() },
					{ desc, p.getDescription() },
					{ value , p.getValue()+"" },
					{ limited, p.isLimited()+"" },
					{ "Min", p.getMin()+"" },
					{ "Max", p.getMax()+""}
				};
				break;
			}
			
			case LOGICAL: {
				LogicalParameter p = (LogicalParameter)param;
				data = new String[][] {
					{ typeStr, p.getType().toString() },
					{ name, p.getName() },
					{ desc, p.getDescription() },
					{ value, p.getValue()+"" }
				};
				break;
			}
			
			case PATH: {
				PathParameter p = (PathParameter)param;
				data = new String[][] {
					{ typeStr, p.getType().toString() },
					{ name, p.getName() },
					{ desc, p.getDescription() },
					{ location, p.getValue().toAbsolutePath().toString() },
					{ "Tip lokacije", p.getType().toString() }
				};
				break;
			}
			
			case IMAGE: {
				ImageParameter p = (ImageParameter)param;
				data = new String[][] {
					{ typeStr, p.getType().toString() },
					{ name, p.getName() },
					{ desc, p.getDescription() },
					{ location, p.getImagePath() }
				};
				break;
			}
			
			case MULTILINE: {
				MultiLineTextParameter p = (MultiLineTextParameter)param;
				data = new String[][] {
					{ typeStr, p.getType().toString() },
					{ name, p.getName() },
					{ desc, p.getDescription() },
					{ "Text", (String)p.getValue() }
				};
				break;
			}
			
			default: break;
		}
		
	}
	
	public void updateLanguage() {
		projName = LanguageManager.getInstance().getRes().getString("proj_name");
		projDesc = LanguageManager.getInstance().getRes().getString("proj_desc");
		wizName = LanguageManager.getInstance().getRes().getString("wiz_name");
		wizDesc = LanguageManager.getInstance().getRes().getString("wiz_desc");
		paramName = LanguageManager.getInstance().getRes().getString("param_name");
		paramDesc = LanguageManager.getInstance().getRes().getString("param_desc");
		
		typeStr = LanguageManager.getInstance().getRes().getString("type");
		name = LanguageManager.getInstance().getRes().getString("name");
		desc = LanguageManager.getInstance().getRes().getString("desc");
		value = LanguageManager.getInstance().getRes().getString("value");
		location = LanguageManager.getInstance().getRes().getString("location");
		limited = LanguageManager.getInstance().getRes().getString("limited");
	}	

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}

	public Project getActiveProject() {
		return activeProject;
	}

	public void setActiveProject(Project activeProject) {
		this.activeProject = activeProject;
	}

	public AbstractParameter getActiveParameter() {
		return activeParameter;
	}

	public void setActiveParameter(AbstractParameter activeParameter) {
		this.activeParameter = activeParameter;
	}

	public Wizard getActiveWizard() {
		return activeWizard;
	}

	public void setActiveWizard(Wizard activeWizard) {
		this.activeWizard = activeWizard;
	}

	public EditType getType() {
		return type;
	}
	
}
