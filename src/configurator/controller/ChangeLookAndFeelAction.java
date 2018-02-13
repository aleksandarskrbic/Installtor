package configurator.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import configurator.PropertiesManager;
import configurator.gui.MainFrame;

public class ChangeLookAndFeelAction extends AbstractAction {

	private LookAndFeelInfo info;

	public ChangeLookAndFeelAction(LookAndFeelInfo info) {
		this.info = info;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			UIManager.setLookAndFeel(info.getClassName());
			SwingUtilities.updateComponentTreeUI(MainFrame.getInstance());
			PropertiesManager.getInstance().put("theme", info.getClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
	}

}
