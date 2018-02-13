package configurator.controller;

import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import configurator.gui.MainFrame;
import localization.LanguageManager;

public class MainFrameController implements WindowListener, ComponentListener {

	public MainFrameController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	/***
	 * Aktivira se kada pokusamo da zatvorimo aplikaciju
	 */
	@Override
	public void windowClosing(WindowEvent arg0) {
		JFrame frame = (JFrame) arg0.getComponent();
		int code = JOptionPane.showConfirmDialog(frame, LanguageManager.getInstance().getRes().getString("exit_msg")
				,LanguageManager.getInstance().getRes().getString("close_msg"),JOptionPane.YES_NO_OPTION);
			if (code != JOptionPane.YES_OPTION){
			
				frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
			}
			else{
				frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			}
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}


	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentResized(ComponentEvent e) {
		Dimension dimension = MainFrame.getInstance().getSize();
		MainFrame.getInstance().getWorkspace().setSplitPaneLocation(dimension.width / 5 + 50);
		MainFrame.getInstance().getWorkspace().getProjectTree().setMaximumSize(new Dimension(dimension.width / 4, dimension.height));
		MainFrame.getInstance().getWorkspace().getEditorPanel().setMinimumSize(new Dimension(dimension.width / 2, dimension.height));
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
}
