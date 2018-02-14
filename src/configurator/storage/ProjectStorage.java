package configurator.storage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import configurator.model.Element;
import configurator.model.Project;
import configurator.model.Wizard;
import configurator.model.parameter.AbstractParameter;

public class ProjectStorage {

	private static ProjectStorage projectStorage = null;

	private List<Project> projects = null;

	private List<ProjectStorageListener> projectListeners = null;
	private File worpskace = null;
	
	private ProjectStorage(File workspace) {
		projects = new ArrayList<Project>();
		projectListeners = new ArrayList<ProjectStorageListener>();
		
		this.worpskace = workspace;
		loadProjects();
	}

	public void loadProjects() {
		projects = new ArrayList<Project>();
		FileManager serializer = new BinarySerializer();
		List<Project> projects = serializer.openWorkspace(worpskace.toPath());
		for(Project p : projects) {
			this.projects.add(p);
		}
	}

	// Project managment
	public void addProject(Project project) {
		addProject(-1, project);
	}

	private void addProject(int index, Project project) {
		boolean exist = projects.stream().anyMatch(p -> p.getName().equals(project.getName()) && !project.getID().equals(p.getID()));
		// TODO: kada otvaramo projekat koji vec postoji ovo pravi problem
		// detektuke da postoji i ne moze da odradi update projekta
		if (exist)
			throw new IllegalArgumentException(String.format("Project '%s' already exists.", project.getName()));

		System.out.println(index);
		if (index < 0 || index >= projects.size()) {
			projects.add(project);
			triggerElementUpdated(project);
		} else {
			triggerElementUpdated(project);
			projects.set(index, project);
		}
	}

	public void removeProject(Project p) {
		if (!projects.contains(p)) {
			System.err.println("Project does not exist: " + p);
		}
		projects.remove(p);
		triggerElementUpdated(p);
	}

	public void removeAllProjects() {
		for (Project p : projects) {
			removeProject(p);
		}
	}
	
	public void updateElement(Element e) {
		for (Project p : projects) {
			if (p.getID().equals(e.getID())) {
				int index = projects.indexOf(p);
				addProject(index, (Project) e);
				return;
			}

			for (Wizard w : p.getWizards()) {
				if (w.getID().equals(e.getID())) {
					p.updateWizard(w, (Wizard) e);
					triggerElementUpdated(e);
					return;
				}

				for (AbstractParameter par : w.getParameters()) {
					if (par.getID().equals(e.getID())) {
						w.updateParameter(par, (AbstractParameter) e);
						triggerElementUpdated(e);
						return;
					}
				}
			}
		}
	}

	// Listeners
	public void addProjectListener(ProjectStorageListener listener) {
		projectListeners.add(listener);
		listener.setProjects(projects);
	}
	
	private void triggerElementUpdated(Element e) {
		for (ProjectStorageListener pl : projectListeners)
			pl.elementUpdated(e);
	}

	public List<Project> getProjects() {
		return projects;
	}
	
	public File getWorkspace() {
		return worpskace;
	}

	public static void init(File workspace) {
		if (projectStorage == null) {
			if(!workspace.exists())
				workspace.mkdirs();

			projectStorage = new ProjectStorage(workspace);
		}
	}
	
	public static ProjectStorage getInstance() {
		return projectStorage;
	}
	
}
