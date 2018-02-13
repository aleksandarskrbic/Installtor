package installator;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import configurator.PropertiesManager;
import configurator.model.Project;
import installator.gui.InstallatorMainFrame;
import installator.gui.SelectProjectDialog;

public class InstallatorApp {
	
	public static void main(String[] args) {

		new SelectProjectDialog().setVisible(true);
		final Project project = SelectProjectDialog.getProject();
		
		try {
			UIManager.setLookAndFeel(PropertiesManager.getInstance().get("theme", UIManager.getSystemLookAndFeelClassName()));
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		if(project != null) {
			SwingUtilities.invokeLater(() -> 
			InstallatorMainFrame.getInstance().setVisible(true));			
		}
	}

}
