package configurator.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import configurator.gui.ElementFilter;
import configurator.gui.ProjectContextMenu;
import configurator.gui.TreeCellRenderer;
import configurator.gui.model.ProjectTreeModel;
import configurator.model.Element;
import configurator.model.Project;
import configurator.model.Wizard;
import configurator.model.parameter.AbstractParameter;
import configurator.storage.ProjectStorage;
import configurator.storage.ProjectStorageListener;

public class ProjectTreeController implements ProjectStorageListener, MouseListener {
	
	private final ProjectTreeModel model;
	
	private final JTree tree;
	
	List<Object> expandedObjects; // Svi vidljivi (expandovani) projekti, vizardi i param.
	
	private ElementFilter expandFilter = null;
	
	public ProjectTreeController(ProjectTreeModel model, JTree tree) {
		this.model = model;
		this.tree = tree;
		
		expandFilter = e -> false;
	}
	
	@Override
	public void elementUpdated(Element e) {
		saveExpansion();

		// model.setFilter(e1 -> e1.toString().contains("1"));
		reloadProjects();

		expandNode((DefaultMutableTreeNode) model.getNode(e).getParent());
		expandUserObject(e);
		selectUserObject(e);
		
		restoreExpansion();
	}
	
	public void search(String s) {
		tree.setCellRenderer(new TreeCellRenderer(TreeCellRenderer.RenderType.SEARCH));
		TreeCellRenderer renderer = (TreeCellRenderer) tree.getCellRenderer();
		String caseInsensitiveRegex = "(?i)" + s;
		renderer.highlight(caseInsensitiveRegex);
		expandFilter = e -> e.getName().matches(".*" + caseInsensitiveRegex + ".*");
		
		reloadProjects();
	}
	
	public void clearHighlights() {
		TreeCellRenderer renderer = (TreeCellRenderer) tree.getCellRenderer();
		renderer.clearHighlights();
		reloadProjects();
	}
	
	public void reloadProjects() {
		List<Element> expandLater = new ArrayList<Element>();
		
		model.getRoot().removeAllChildren();
		
		for(Project p : ProjectStorage.getInstance().getProjects()) {
			model.addNode(p);
			if(expandFilter.filter(p))
				expandLater.add(p);
			
			for(Wizard w : p.getWizards()) {
				model.addNode(p, w);
				if(expandFilter.filter(w))
					expandLater.add(p);
				
				for(AbstractParameter par : w.getParameters()) {
					model.addNode(w, par);
					if(expandFilter.filter(par))
						expandLater.add(w);
				}
			}
		}
		
		model.reload();
		
		for(Element e : expandLater)
			expandUserObject(e);
	}
	
	@Override
	public void setProjects(List<Project> ps) {
		model.addProjects(ps);
	}
	
	private void expandNode(DefaultMutableTreeNode node) {
		tree.expandPath(new TreePath(node.getPath()));
	}
	
	private void selectNode(DefaultMutableTreeNode node) {
		tree.setSelectionPath(new TreePath(node.getPath()));
	}
	
	private void expandUserObject(Element e) {
		DefaultMutableTreeNode expandable = model.getNode(e);
		if(expandable != null)
			if(expandable.getChildCount() > 0)
				expandNode(expandable);
			else
				expandNode((DefaultMutableTreeNode) expandable.getParent());
	}
	
	private void selectUserObject(Element e) {
		selectNode(model.getNode(e));
	}
	
	private void saveExpansion() {
		expandedObjects = new ArrayList<Object>();
		Enumeration<TreePath> e = tree.getExpandedDescendants(new TreePath(model.getRoot()));
		while(e != null && e.hasMoreElements()) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.nextElement().getLastPathComponent();
			if(node == model.getRoot())
				continue;
			expandedObjects.add((Object) node.getUserObject());
		}
	}
	
	private void restoreExpansion() {
		for(Object item : expandedObjects)
			expandUserObject((Element) item);
	}
	
	public void setExpandFilter(ElementFilter filter) {
		this.expandFilter = filter;
	}
	
	public void removeExpandFilter() {
		expandFilter = e -> false;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		JTree tree = (JTree)e.getSource();
		TreePath path = tree.getPathForLocation(e.getX(), e.getY());
		if(path == null)
			return;
		tree.setSelectionPath(path);
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	/***
	 * Otvara kontekstni meni na desni klik misa
	 * 
	 */
	@Override
	public void mouseClicked(MouseEvent e) {		
		if(SwingUtilities.isRightMouseButton(e)) {
			JTree tree = (JTree)e.getSource();
			TreePath path = tree.getPathForLocation(e.getX(), e.getY());
			if(path == null)
				return;
			tree.setSelectionPath(path);
			DefaultMutableTreeNode obj = (DefaultMutableTreeNode)path.getLastPathComponent();
			if(obj.getUserObject() instanceof Project) {
				ProjectContextMenu contextMenu = new ProjectContextMenu(ProjectContextMenu.MenuType.PROJECT_MENU);		
				contextMenu.show(tree, e.getX(), e.getY());
			} else if(obj.getUserObject() instanceof Wizard) {
				ProjectContextMenu contextMenu = new ProjectContextMenu(ProjectContextMenu.MenuType.WIZARD_MENU);
				contextMenu.show(tree, e.getX(), e.getY());
			} else if(obj.getUserObject() instanceof AbstractParameter) {
				ProjectContextMenu contextMenu = new ProjectContextMenu(ProjectContextMenu.MenuType.PARAMETER_MENU);
				contextMenu.show(tree, e.getX(), e.getY());
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

}
