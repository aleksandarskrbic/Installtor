package configurator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import configurator.gui.EditorPanel;
import configurator.gui.MainFrame;
import configurator.gui.model.EditorModel;

public class EditorController implements ActionListener {

	private final EditorModel editorModel;
	
	public EditorController(EditorModel model) {
		editorModel = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		
		if(button.getName().equals("edit")) {
			Actions.editSelection();
		} else if(button.getName().equals("delete")) {
			Actions.deleteSelection();
		}
		
		MainFrame.getInstance().getWorkspace().setEditorPanel(new EditorPanel(editorModel.getType()));
	}
}
