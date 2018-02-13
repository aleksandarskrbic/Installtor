/**
 * 
 */
package installator.model;

import configurator.model.Project;

/**
 * @author Nemanja Simic
 *
 */
public class Setup {

	// loaded project
	private final Project activeProject;
	
	// number of wizards generated
	private int wizardCount;
	
	// active wizard index
	private int currentWizard;
	
	// 0 - 100
	private int percentCompleted;

	public Setup(Project project) {
		activeProject = project;
		wizardCount = project.getWizards().size();
		currentWizard = 0;
		percentCompleted = 0;
	}

	public int getWizardCount() {
		return wizardCount;
	}

	public int getCurrentWizard() {
		return currentWizard;
	}

	public void setCurrentWizard(int currentWizard) {
		this.currentWizard = currentWizard;
	}

	public int getPercentCompleted() {
		return percentCompleted;
	}

	public void setPercentCompleted(int percentCompleted) {
		if(percentCompleted < 0 || percentCompleted > 100)
			throw new IllegalArgumentException("Percents must be in range 0 - 100");
		
		this.percentCompleted = percentCompleted;
	}

	public Project getActiveProject() {
		return activeProject;
	}

}
