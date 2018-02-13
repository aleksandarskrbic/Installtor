package configurator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

public abstract class EscapeDispose extends JDialog {

	private static final long serialVersionUID = 4367942537817915964L;
	private AbstractAction escapeAction;

	public EscapeDispose(JFrame parent, boolean tf) {
		super(parent, tf);
		escapeAction = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		};
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
				"ESCAPE_KEY");
		getRootPane().getActionMap().put("ESCAPE_KEY", escapeAction);
	}

	public EscapeDispose(JFrame parent) {
		super(parent);
		escapeAction = new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		};

		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
				"ESCAPE_KEY");
		getRootPane().getActionMap().put("ESCAPE_KEY", escapeAction);
	}

}
