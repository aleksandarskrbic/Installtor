package configurator.gui.dialog;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;

import configurator.controller.Actions;
import configurator.controller.EscapeDispose;
import localization.LanguageManager;
import net.miginfocom.swing.MigLayout;

public class FindDialog extends EscapeDispose {

	private static final long serialVersionUID = -394412103514950489L;

	private static JTextField findTxt;

	private JButton findBtn = null;

	public FindDialog(JFrame parent) {
		super(parent, true);
		setTitle(LanguageManager.getInstance().getRes().getString("find"));
		setSize(375, 80);
		setResizable(false);
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		initComponents();
		groupComponents();
	}

	private void initComponents() {

		findTxt = new JTextField();
		findBtn = new JButton(LanguageManager.getInstance().getRes().getString("find"));
		findBtn.addActionListener(l -> Actions.search());
		findTxt.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					Actions.search();
				}
			}
		});
	}

	private void groupComponents() {
		setLayout(new MigLayout("", "5[grow]10[]5", "5[]5"));

		add(findTxt, "cell 0 0, growx");
		add(findBtn, "cell 1 0");
	}

	public static String getFindTxt() {
		return findTxt.getText();
	}

}
