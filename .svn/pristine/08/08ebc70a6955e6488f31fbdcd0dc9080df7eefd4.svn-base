package configurator;

import java.io.File;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import configurator.gui.MainFrame;
import configurator.gui.dialog.WorkspaceChooserDialog;
import configurator.storage.ProjectStorage;

public class ConfiguratorApp {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			try {
				UIManager.setLookAndFeel(PropertiesManager.getInstance().get("theme", UIManager.getSystemLookAndFeelClassName()));
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
				e.printStackTrace();
			}

			WorkspaceChooserDialog wcd = new WorkspaceChooserDialog(null);
			wcd.setVisible(true);
			File workspace = wcd.getWorkspace();
			
			if(workspace != null) {
				ProjectStorage.init(wcd.getWorkspace());
				MainFrame.getInstance().setVisible(true);
			}
		});
	}
}
