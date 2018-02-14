package configurator.storage;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import configurator.Constants;
import configurator.model.Project;

public class BinarySerializer implements FileManager {
	
	private ObjectOutputStream outStream;
	
	private ObjectInputStream inStream;
	
	public BinarySerializer() {
	
	}
	
	/***
	 * Saves project to a file
	 * @param data Project which is going to be saved
	 */
	@Override
	public void saveProject(Project data, File file) {
		try {
			outStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
			outStream.writeObject(data);
			outStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/***
	 * Loads project from the file
	 * @return returns loaded project or null if not able to load
	 */
	@Override
	public Project openProject(File file) {
		// or throw exception
		if(!file.isFile())
			return null;
		Project p = null;
		try {
			inStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
			p = (Project) inStream.readObject();
			p.generateID();
			p.getWizards().forEach(w -> { w.generateID();
				w.getParameters().forEach(par -> par.generateID());
			});
			
			inStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return p;
	}

	
	/***
	 *  Saves workspace to the given location
	 *  Workspace contains projects which are being saved.
	 *  @param projects saving those instances to the given location
	 *  @param location given location
	 */
	@Override
	public void saveWorkspace(List<Project> projects, Path location) {
		for(Project p : projects) {
			File f = new File(location.resolve(p.getName() + Constants.APP_EXTENSION).toString());
			try {
				outStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
				outStream.writeObject(p);
				outStream.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}

	/***
	 * Tries to open projects from the given location
	 * @param location current workspace path
	 * @return returns collection of successfully loaded projects
	 */
	@Override
	public List<Project> openWorkspace(Path location) {
		final List<Project> projects = new ArrayList<>();
		final File ws = location.toFile();
		for(final File file : ws.listFiles()) {
			Project p = openProject(file);
			projects.add(p);
		}
		
		return projects;
	}

}
