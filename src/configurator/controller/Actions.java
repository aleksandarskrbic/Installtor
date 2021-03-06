package configurator.controller;

import java.io.File;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import configurator.Constants;
import configurator.gui.EditorPanel;
import configurator.gui.EditorPanel.EditType;
import configurator.gui.MainFrame;
import configurator.gui.ProjectExplorerPanel;
import configurator.gui.TreeCellRenderer;
import configurator.gui.dialog.AboutDialog;
import configurator.gui.dialog.EditParameterDialog;
import configurator.gui.dialog.EditProjectDialog;
import configurator.gui.dialog.EditWizardDialog;
import configurator.gui.dialog.FindDialog;
import configurator.gui.dialog.HelpContentDialog;
import configurator.gui.dialog.NewParameterDialog;
import configurator.gui.dialog.NewProjectDialog;
import configurator.gui.dialog.NewWizardDialog;
import configurator.model.Project;
import configurator.model.Wizard;
import configurator.model.parameter.AbstractParameter;
import configurator.storage.BinarySerializer;
import configurator.storage.FileManager;
import configurator.storage.ProjectFileFilter;
import configurator.storage.ProjectStorage;
import localization.LanguageManager;

public class Actions {

	private static MainFrame mainFrame = MainFrame.getInstance();

	public static void find() {
		FindDialog findDialog = new FindDialog(mainFrame);
		findDialog.setVisible(true);
	}

	public static void search() {
		if (FindDialog.getFindTxt().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, 
					LanguageManager.getInstance().getRes().getString("sf_empty"),
					null, JOptionPane.OK_OPTION);
			return;
		}

		ProjectExplorerPanel.ptc.search(FindDialog.getFindTxt());
	}

	public static void createProject() {
		NewProjectDialog npd = new NewProjectDialog(mainFrame);
		npd.setVisible(true);
	}

	public static void createWizard() {
		new NewWizardDialog(mainFrame).setVisible(true);
	}

	public static void createParameter() {
		new NewParameterDialog(mainFrame).setVisible(true);
	}

	public static void editProject() {
		if (ProjectExplorerPanel.getSelectedProject() == null) {
			JOptionPane.showMessageDialog(null,
					LanguageManager.getInstance().getRes().getString("must_sp"),
					null, JOptionPane.OK_OPTION);
			return;
		}

		EditProjectDialog editProjectDialog = new EditProjectDialog(mainFrame, ProjectExplorerPanel.getSelectedProject());
		editProjectDialog.setVisible(true);
	}

	public static void editWizard() {
		if (ProjectExplorerPanel.getSelectedWizard() == null) {
			JOptionPane.showMessageDialog(null,
					LanguageManager.getInstance().getRes().getString("must_sw"),
					null, JOptionPane.OK_OPTION);
			return;
		}

		EditWizardDialog editWizard = new EditWizardDialog(mainFrame, ProjectExplorerPanel.getSelectedWizard());
		editWizard.setVisible(true);
	}

	public static void editParameter() {
		if (ProjectExplorerPanel.getSelectedParameter() == null) {
			JOptionPane.showMessageDialog(null,
					LanguageManager.getInstance().getRes().getString("must_sparam"),
					null, JOptionPane.OK_OPTION);
			return;
		}

		EditParameterDialog epd = new EditParameterDialog(mainFrame, ProjectExplorerPanel.getSelectedWizard(), ProjectExplorerPanel.getSelectedParameter());
		epd.setVisible(true);
	}

	private static void deleteProject() {
		if (ProjectExplorerPanel.getSelectedProject() == null)
			return;

		int result = JOptionPane.showConfirmDialog(null,
				LanguageManager.getInstance().getRes().getString("delete_p") + " " +
				ProjectExplorerPanel.getSelectedProject().getName(), null,
				JOptionPane.YES_NO_OPTION);

		if (result == JOptionPane.YES_OPTION) {
			ProjectStorage.getInstance().removeProject(ProjectExplorerPanel.getSelectedProject());
		}
	}

	private static void deleteWizard() {
		if (ProjectExplorerPanel.getSelectedWizard() == null)
			return;

		int result = JOptionPane.showConfirmDialog(null,
				LanguageManager.getInstance().getRes().getString("delete_w") + " " +
				ProjectExplorerPanel.getSelectedWizard().getName() + "?", null,
				JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION) {

			if (ProjectExplorerPanel.getSelectedWizard().getName().equals("Location")) {
				JOptionPane.showMessageDialog(null, 
						LanguageManager.getInstance().getRes().getString("delete_w_c"), null, JOptionPane.OK_OPTION);
				return;
			}

			Project proj = ProjectExplorerPanel.getSelectedProject();
			proj.removeWizard(ProjectExplorerPanel.getSelectedWizard());
			ProjectStorage.getInstance().updateElement(proj);
		}
	}

	private static void deleteParameter() {
		if (ProjectExplorerPanel.getSelectedParameter() == null)
			return;

		int result = JOptionPane.showConfirmDialog(null,
				LanguageManager.getInstance().getRes().getString("delete_param")
				+ " " +ProjectExplorerPanel.getSelectedParameter().getName() + "?", null,
				JOptionPane.YES_NO_OPTION);

		if (result == JOptionPane.YES_OPTION) {

			if (ProjectExplorerPanel.getSelectedParameter().getName().equals("Source")
					|| ProjectExplorerPanel.getSelectedParameter().getName().equals("Destination")) {
				JOptionPane.showMessageDialog(null, LanguageManager.getInstance().getRes().getString("cant_delete"),
						LanguageManager.getInstance().getRes().getString("error"), JOptionPane.OK_OPTION);
				return;
			}

			Wizard wiz = ProjectExplorerPanel.getSelectedWizard();
			wiz.removeParameter(ProjectExplorerPanel.getSelectedParameter());
			ProjectStorage.getInstance().updateElement(wiz);
		}
	}

	public static void editSelection() {
		Object o = ProjectExplorerPanel.getSelection();
		if (o == null) {
			JOptionPane.showMessageDialog(null, LanguageManager.getInstance().getRes().getString("select_tree"),
					LanguageManager.getInstance().getRes().getString("error"), JOptionPane.OK_OPTION);
			return;
		} else if (o instanceof Project)
			editProject();
		else if (o instanceof Wizard)
			editWizard();
		else if (o instanceof AbstractParameter)
			editParameter();

		ProjectExplorerPanel.getSelectedProject().setEdited(true);
		final TreeCellRenderer renderer = new TreeCellRenderer(TreeCellRenderer.RenderType.EDIT);
		MainFrame.getInstance().getWorkspace().getProjectTree().setCellRenderer(renderer);
		String caseInsensitiveRegex = "(?i)" + ProjectExplorerPanel.getSelectedProject().getName();
		renderer.highlight(caseInsensitiveRegex);
	}

	public static void deleteSelection() {
		if (ProjectExplorerPanel.getSelection() == null) {
			JOptionPane.showMessageDialog(null, LanguageManager.getInstance().getRes().getString("select_tree"),
					LanguageManager.getInstance().getRes().getString("error"), JOptionPane.OK_OPTION);
			return;
		}
		Object o = ProjectExplorerPanel.getSelection();
		if (o instanceof Project)
			deleteProject();
		else if (o instanceof Wizard)
			deleteWizard();
		else if (o instanceof AbstractParameter)
			deleteParameter();
	}

	public static void openAbout() {
		new AboutDialog(mainFrame).setVisible(true);
	}

	public static void openHelp() {
		new HelpContentDialog(mainFrame).setVisible(true);
	}

	public static void showTable() {
		MainFrame frame = (MainFrame) MainFrame.getInstance();
		frame.getWorkspace().setEditorPanel(new EditorPanel(EditType.PROJECT));
	}

	public static void openProject() {
		final JFileChooser chooser = new JFileChooser(ProjectStorage.getInstance().getWorkspace().getAbsolutePath());
		chooser.setFileFilter(new ProjectFileFilter());

		if (chooser.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
			final File file = chooser.getSelectedFile();
			final FileManager serializer = new BinarySerializer();
			final Project loadedProject = serializer.openProject(file);
			loadedProject.setProjectFile(file);
			try {
				ProjectStorage.getInstance().addProject(loadedProject);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(MainFrame.getInstance(), e.getMessage(),
						LanguageManager.getInstance().getRes().getString("error"), JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public static void saveProject() {
		final JFileChooser chooser = new JFileChooser(ProjectStorage.getInstance().getWorkspace().getAbsolutePath());
		final File file = new File(ProjectExplorerPanel.getSelectedProject().getName());
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setSelectedFile(file);
		chooser.setFileFilter(new ProjectFileFilter());

		if (chooser.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
			File f = chooser.getSelectedFile();
			if (!f.getAbsolutePath().endsWith(Constants.APP_EXTENSION)) {
				f = new File(chooser.getSelectedFile().getAbsolutePath() + Constants.APP_EXTENSION);
			}
			ProjectExplorerPanel.getSelectedProject().setProjectFile(f);
			final FileManager serializer = new BinarySerializer();
			serializer.saveProject(ProjectExplorerPanel.getSelectedProject(), f);

			ProjectExplorerPanel.getSelectedProject().setEdited(false);
			if (MainFrame.getInstance().getWorkspace().getProjectTree().getCellRenderer() instanceof TreeCellRenderer) {
				TreeCellRenderer renderer = (TreeCellRenderer) MainFrame.getInstance().getWorkspace().getProjectTree()
						.getCellRenderer();
				renderer.clearHighlights();
			}
		}
	}

	public static void openWorkspace() {
		if ("e".equals("e"))
			throw new RuntimeException("NOT SUPPORTED!");

		final JFileChooser chooser = new JFileChooser(ProjectStorage.getInstance().getWorkspace().toString());
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setFileFilter(new ProjectFileFilter());

		if (chooser.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
			ProjectStorage.getInstance().removeAllProjects();
			final FileManager serializer = new BinarySerializer();
			final List<Project> projects = serializer.openWorkspace(chooser.getSelectedFile().toPath());
			projects.stream().forEach(p -> ProjectStorage.getInstance().addProject(p));
			// WorkspacePanel.setWorkspaceLocation(chooser.getSelectedFile().toPath());
		}
	}

	public static void saveWorkspace() {
		final FileManager serializer = new BinarySerializer();
		serializer.saveWorkspace(ProjectStorage.getInstance().getProjects(),
				ProjectStorage.getInstance().getWorkspace().toPath());
	}

	public static Object clearHighlights() {
		ProjectExplorerPanel.ptc.clearHighlights();
		return null;
	}
	
	public static void setLookAndFeel() {
		
	}

}
