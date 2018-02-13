package configurator.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import configurator.controller.EditorController;
import configurator.gui.model.EditorModel;
import de.javasoft.plaf.synthetica.SyntheticaDefaultTableCellRenderer;

public class EditorPanel extends JPanel {

	public static enum EditType {
		PROJECT, PARAMETER, WIZARD, EMPTY
	};

	/**
	 * 
	 */
	private static final long serialVersionUID = 4417931207628279754L;

	private final EditorToolbar toolbar;

	private final JTable table;

	private final EditorModel model;

	private final EditorController controller;

	/***
	 * 
	 * 
	 */
	public EditorPanel(EditType type) {
		setLayout(new BorderLayout());
		setMinimumSize(new Dimension(200, 100));
		setBorder(new EmptyBorder(7, 7, 7, 7));

		toolbar = new EditorToolbar();
		toolbar.setVisible(false);

		model = new EditorModel(type);
		table = new JTable(model);
		table.setOpaque(false);
		System.out.println(table.getDefaultRenderer(Object.class).getClass().getName());
		Object cellRenderer = table.getDefaultRenderer(Object.class);

		if (cellRenderer instanceof DefaultTableCellRenderer)
			((DefaultTableCellRenderer) cellRenderer).setOpaque(true);
		else if (cellRenderer instanceof SyntheticaDefaultTableCellRenderer)
			((SyntheticaDefaultTableCellRenderer) cellRenderer).setOpaque(true);
		else
			System.err.println("Unknown cell table renderer: " + cellRenderer.getClass().getName());

		table.setForeground(Color.BLACK);

		controller = new EditorController(model);
		toolbar.setListener(controller);
		groupComponents();
	}

	private void groupComponents() {
		add(toolbar, BorderLayout.NORTH);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);

		add(scrollPane, BorderLayout.CENTER);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(new ImageIcon("images/workspace_background.png").getImage(), (getWidth() - 250) / 2,
				(getHeight() - 250) / 2, 250, 250, this);
	}

	public EditorToolbar getToolbar() {
		return toolbar;
	}

	public EditorModel getModel() {
		return model;
	}

}
