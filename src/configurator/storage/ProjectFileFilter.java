package configurator.storage;

import java.io.File;

import javax.swing.filechooser.FileFilter;

import configurator.Constants;
import localization.LanguageManager;

public class ProjectFileFilter extends FileFilter {
	
	public ProjectFileFilter() { }
	
	public String getDescription() {
			return LanguageManager.getInstance().getRes().getString("ui") + " (" + Constants.APP_EXTENSION + ")";
	}
		 
	public boolean accept(File f) {
		if (f.isDirectory()) {
			return true;
		} else {
			String filename = f.getName().toLowerCase();
			return filename.endsWith(Constants.APP_EXTENSION);
		}
	}
}
