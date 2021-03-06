package configurator.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import configurator.controller.ProjectTreeController;
import configurator.gui.EditorPanel.EditType;
import configurator.gui.model.DirectoryMutableTreeNode;
import configurator.gui.model.ProjectTreeModel;
import configurator.model.Project;
import configurator.model.Wizard;
import configurator.model.parameter.AbstractParameter;
import configurator.storage.ProjectStorage;

public class ProjectExplorerPanel extends JPanel {
	
	private static final long serialVersionUID = 7570976587844087705L;
	
	private static Project selectedProject = null;
	
	private static AbstractParameter selectedParameter = null;
	
	private static Wizard selectedWizard = null;
	
	private final ProjectTreeModel ptm;
	
	public static ProjectTreeController ptc; 
	
	private final JTree projectTree;
	
	private final JScrollPane scrollPane;
	
	public ProjectExplorerPanel() {
		setLayout(new BorderLayout());
		setMinimumSize(new Dimension(200, 100));
		
		DirectoryMutableTreeNode root = new DirectoryMutableTreeNode("Workspace");
		projectTree = new JTree(root);
		ptm = new ProjectTreeModel(root);
		ptc = new ProjectTreeController(ptm, projectTree);
		init();
		
		scrollPane = new JScrollPane(projectTree, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		groupComponents();
	}

	private void init() {
		ProjectStorage.getInstance().addProjectListener(ptc);
		projectTree.setModel(ptm);
		projectTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		projectTree.addTreeSelectionListener(tsl -> setSelected());
		projectTree.addMouseListener(ptc);
		ptc.reloadProjects();
	}
	
	private void setSelected() {
		TreePath selectionPath = projectTree.getSelectionModel().getSelectionPath();
		
		selectedProject = null;
		selectedParameter = null;
		selectedWizard = null;

		if(selectionPath == null) return;
		
		Object[] path = selectionPath.getPath();
		for(Object item : path) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) item;
			Object userObject = node.getUserObject();
			if(userObject instanceof Project)
			{
				selectedProject = (Project) userObject;
				MainFrame.getInstance().getWorkspace().setEditorPanel(new EditorPanel(EditType.PROJECT));
			}
			if (userObject instanceof Wizard) {
				selectedWizard = (Wizard) userObject;
				MainFrame.getInstance().getWorkspace().setEditorPanel(new EditorPanel(EditType.WIZARD));
			}
			if(userObject instanceof AbstractParameter) {
				selectedParameter = (AbstractParameter) userObject;
				MainFrame.getInstance().getWorkspace().setEditorPanel(new EditorPanel(EditType.PARAMETER));				
			}
			
		}
		System.out.printf("%s %s %s\n", selectedProject, selectedWizard, selectedParameter);
		System.out.printf("\n%s\n", selectedProject.getEdited() ? "EDITED" : "SAVED");
	}
	
	private void groupComponents() {
		add(scrollPane, BorderLayout.CENTER);
	}
	public static Wizard getSelectedWizard() {
		return selectedWizard;
	}
	
	public static Project getSelectedProject() {
		return selectedProject;
	}
	
	public static AbstractParameter getSelectedParameter() {
		return selectedParameter;
	}
	public static Object getSelection() {
		if (selectedProject == null)
			return null;
		if (selectedWizard == null)
			return selectedProject;
		if (selectedParameter == null)
			return selectedWizard;
		if (selectedParameter != null)
			return selectedParameter;
		return null;
	}
	
	public JTree getTree() {
		return projectTree;
	}
}
