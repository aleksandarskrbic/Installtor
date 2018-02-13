package configurator.storage;

import java.util.List;

import configurator.model.Element;
import configurator.model.Project;

public interface ProjectStorageListener {
	
	
	/**
	 * Poziva se pri dodavanju novog listenera.
	 * 
	 * @param ps Lista projekata.
	 */
	public void setProjects(List<Project> ps);
	public void elementUpdated(Element e);
}