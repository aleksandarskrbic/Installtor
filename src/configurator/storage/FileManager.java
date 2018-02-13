/**
 * 
 */
package configurator.storage;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

import configurator.model.Project;

/**
 * @author Nemanja Simic
 *
 */
public interface FileManager {

	void saveWorkspace(List<Project> projects, Path location);
	
	List<Project> openWorkspace(Path location);
	
	void saveProject(Project data, File file);
	
	Project openProject(File file);
}
