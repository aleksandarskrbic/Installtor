package configurator.gui.model;

import java.util.Enumeration;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

import configurator.model.Element;
import configurator.model.Project;
import configurator.model.parameter.AbstractParameter;

public class ProjectTreeModel extends DefaultTreeModel {

	private static final long serialVersionUID = -2675112470505543672L;

	public ProjectTreeModel(TreeNode root) {
		super(root);
	}
	
	@Override
	public void reload() {
		super.reload();
	}
	
	
	public Project getParentProject(Element e) {
		for(Object o : getNode(e).getUserObjectPath()) {
			if(o instanceof Project)
				return (Project) o;
		}

		return null;
	}
	
	public void removeElement(Element e) {
		DefaultMutableTreeNode node = getNode(e);
		if(node != null) {
			node.removeFromParent();
			reload();
		}
	}
	
	public DefaultMutableTreeNode getNode(Element userObject) {
		return getNode(getRoot(), userObject);
	}
	
	private DefaultMutableTreeNode getNode(DefaultMutableTreeNode root, Element userObject) {
		Enumeration children = root.children();
		while(children.hasMoreElements()) {
			DefaultMutableTreeNode childNode = (DefaultMutableTreeNode) children.nextElement();
			if(childNode.getUserObject().equals(userObject))
				return childNode;
			else if(childNode.getChildCount() > 0) {
				DefaultMutableTreeNode node =  getNode(childNode, userObject);
				if(node != null)
					return node;
			}
		}
		return null;
	}

	public void removeProject(Project p) {
		getRoot().remove(getNode(p));
		reload();
	}
	
	public void addProject(Project p) {
		addProjectNode(p);
		reload();
	}
	
	public void addProjects(List<Project> ps) {
		for(Project p : ps)
			addProjectNode(p);
		reload();
	}
	
	private void addProjectNode(Project p) {
		DirectoryMutableTreeNode projectNode = new DirectoryMutableTreeNode(p);
		getRoot().add(projectNode);
	}
	
	public void addNode(Element parent, Element e) {
		DefaultMutableTreeNode parentNode = getNode(parent);
		if(parentNode != null) {
			if(e instanceof AbstractParameter)
				parentNode.add(new DefaultMutableTreeNode(e));
			else
				parentNode.add(new DirectoryMutableTreeNode(e));
		}
	}
	
	public void addNode(Element e) {
		getRoot().add(new DirectoryMutableTreeNode(e));
	}
	
	@Override
	public DefaultMutableTreeNode getRoot() {
		return (DefaultMutableTreeNode) super.getRoot();
	}
	
}