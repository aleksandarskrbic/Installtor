package configurator.controller.menubarActions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JToolBar;

import configurator.gui.MainFrame;
import configurator.gui.MenuBar;
import configurator.gui.StatusBarPanel;

public class ShowViewComponents implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		JToolBar toolbar = MainFrame.getInstance().getToolbar();
		StatusBarPanel statusBar = MainFrame.getInstance().getStatusBar();
		JToolBar editorToolbar = MainFrame.getInstance().getWorkspace().getEditorPanel().getToolbar();
		MenuBar menuBar = MainFrame.getInstance().getMenu();
		
		if (!menuBar.getShowHideEditorToolbar().isSelected())
			editorToolbar.setVisible(false);
		else editorToolbar.setVisible(true);
		
		if (!menuBar.getShowHideMainToolbar().isSelected())
			toolbar.setVisible(false);
		else toolbar.setVisible(true);
		
		if (!menuBar.getShowHideStatusBar().isSelected())
			statusBar.setVisible(false);
		else statusBar.setVisible(true);
	}

}
