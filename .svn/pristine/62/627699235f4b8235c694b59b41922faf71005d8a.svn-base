package configurator.model;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import configurator.model.parameter.AbstractParameter;
import configurator.model.parameter.PathParameter;
import configurator.model.parameter.PathTypeOption;

public class Project extends Element {
	
	private static final long serialVersionUID = 4217323380486152095L;

	private String description = null;
	
	private List<Wizard> wizardList = null;
	
	private File projectFile = null;
	
	private boolean edited;
	
	public Project(String name, Path from, Path to) {
		this.name = name;
		this.description = "";
		edited = false;
		wizardList = new ArrayList<Wizard>();
		
		final Wizard wiz = new Wizard("Location");
		wiz.setDescription("Default wizard - installation parameters");
		generateDefaultParams(from, to).forEach(p -> wiz.addParameter(p));
		addWizard(wiz);
	}
	
	public List<Wizard> getWizards() {
		return wizardList;
	}
	
	public void addWizard(Wizard wizard) {
		boolean exist = wizardList
								.stream()
								.anyMatch(wiz -> wiz.getName().equals(wizard.getName()));
		
		if (exist) {
			throw new IllegalArgumentException(
					String.format("Wizard '%s' already exists in project '%s'.", wizard.getName(), name));
		}
		
		wizardList.add(wizard);
	}
	
	public void updateWizard(Wizard oldWiz, Wizard newWiz) {
		int index = wizardList.indexOf(oldWiz);
		wizardList.remove(index);
		wizardList.add(index, newWiz);
	}
	
	public boolean removeWizard(Wizard wiz) {
		return wizardList.remove(wiz);
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	private List<AbstractParameter> generateDefaultParams(Path from, Path to) {
		PathParameter copyFrom = new PathParameter("Source");
		copyFrom.setDescription("Location to copy from");
		PathParameter copyTo = new PathParameter("Destination");
		copyTo.setDescription("Location to copy to");
		
		copyFrom.setOption(PathTypeOption.DIRECTORY);
		copyTo.setOption(PathTypeOption.DIRECTORY);
		
		copyFrom.setValue(from);
		copyTo.setValue(to);
		
		final List<AbstractParameter> parameters = new ArrayList<>();
		parameters.add(copyFrom);
		parameters.add(copyTo);
		
		return parameters;
	}
	
	public void setProjectFile(File projFile) {
		projectFile = projFile;
	}
	
	public File getProjectFile() {
		return projectFile;
	}
	
	public boolean getEdited() {
		return edited;
	}
	
	public void setEdited(boolean value) {
		edited = value;
	}

}
