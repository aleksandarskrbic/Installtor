package configurator.gui.dialog;

import java.awt.BorderLayout;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import configurator.controller.EscapeDispose;
import net.miginfocom.swing.MigLayout;

public class HelpContentDialog extends EscapeDispose {

	private JPanel panelTree = null;
	private JPanel panelContent = null;
	private JScrollPane scrollPaneTree = null;
	private JScrollPane scrollPaneContent = null;
	private JButton btnClose = null;
	private JButton btnNext = null;
	private JButton btnPrev = null;
	private JTree tree = null;
	private AbstractAction escapeAction = null;

	public HelpContentDialog(JFrame parent) {
		super(parent, true);
		setTitle("Help Content");
		setIconImage(new ImageIcon("images/support-icon.png").getImage());
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		initComponents();
		groupComponents();
	}

	public void createTree() {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
		DefaultMutableTreeNode gettingStarted = new DefaultMutableTreeNode("Getting started");
		DefaultMutableTreeNode userGuide = new DefaultMutableTreeNode("User guide");
		DefaultMutableTreeNode docs = new DefaultMutableTreeNode("Documentation");

		root.add(gettingStarted);
		root.add(userGuide);
		root.add(docs);

		tree = new JTree(root);
	}

	private void initComponents() {
		panelTree = new JPanel();
		panelContent = new JPanel();
		btnClose = new JButton("Close");
		createTree();
		btnClose.addActionListener(e -> dispose());
		btnNext = new JButton();
		btnNext.setIcon(new ImageIcon("images/Actions-go-next-icon.png"));

		btnPrev = new JButton();
		btnPrev.setIcon(new ImageIcon("images/Actions-go-previous-icon.png"));

		scrollPaneTree = new JScrollPane(panelTree, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneContent = new JScrollPane(panelContent, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	}

	private void groupComponents() {
		setLayout(new MigLayout("", "10[grow]10[200]30[grow]", "20[grow]10[]10[]"));

		panelTree.setLayout(new BorderLayout());
		panelTree.add(tree, BorderLayout.CENTER);

		panelContent.setLayout(new BorderLayout());
		panelContent.add(new JTextArea("Ovde ce biti sadrzaj"), BorderLayout.CENTER);

		add(scrollPaneTree, "cell 0 0 , growx , growy");
		add(scrollPaneContent, "cell 1 0,span, growx, growy ");
		add(btnPrev, "cell 0 1, left, growx");
		add(btnNext, "cell 0 1,left, growx");
		add(btnClose, "cell 3 1  , right, growy");
	}
}
