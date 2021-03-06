package configurator.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTree;

public class WorkspacePanel extends JPanel {

	private static final long serialVersionUID = 172789143148395431L;

	private final ProjectExplorerPanel projectTree;

	private final JSplitPane splitPane;

	private EditorPanel editorPanel;

	public WorkspacePanel() {
		setLayout(new BorderLayout());

		projectTree = new ProjectExplorerPanel();
		editorPanel = new EditorPanel(EditorPanel.EditType.EMPTY);
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

		groupComponents();
	}

	private void groupComponents() {
		splitPane.add(projectTree);
		splitPane.add(editorPanel);
		add(splitPane, BorderLayout.CENTER);
	}

	public void setEditorPanel(EditorPanel editor) {
		splitPane.remove(editorPanel);
		editorPanel = editor;
		splitPane.add(editorPanel);
		validate();
	}

	public void setSplitPaneLocation(int location) {
		splitPane.setDividerLocation(location);
	}

	public EditorPanel getEditorPanel() {
		return editorPanel;
	}

	public JTree getProjectTree() {
		return projectTree.getTree();
	}
	
	public ProjectExplorerPanel getExplorerPanel() {
		return projectTree;
	}
	
}
