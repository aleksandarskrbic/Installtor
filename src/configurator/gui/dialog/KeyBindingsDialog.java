package configurator.gui.dialog;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTable;

import configurator.controller.EscapeDispose;
import configurator.gui.MenuBar;
import configurator.gui.model.NonEditableTableModel;
import localization.LanguageManager;

public class KeyBindingsDialog extends EscapeDispose {

	private JTable table = null;
	private String[] columns = { "Action", "KeyBinding" };
	private Object[][] data = {
			{ LanguageManager.getInstance().getRes().getString("new_proj"), new MenuBar().getMiNewProjectAccelerator() },
			{ LanguageManager.getInstance().getRes().getString("new_param"), new MenuBar().getMiNewParameterAccelerator() },
			{ LanguageManager.getInstance().getRes().getString("new_wiz"), new MenuBar().getMiNewWizardAccelerator() },
			{ LanguageManager.getInstance().getRes().getString("save_proj"), new MenuBar().getMiSaveProjectAccelerator() },
			{ LanguageManager.getInstance().getRes().getString("open_proj"), new MenuBar().getMiOpenProjectAccelerator() },
			{ LanguageManager.getInstance().getRes().getString("save_ws"), new MenuBar().getMiSaveWorkspaceAccelerator() },
			{ LanguageManager.getInstance().getRes().getString("open_ws"), new MenuBar().getMiOpenWorkspaceAccelerator() },
			{ LanguageManager.getInstance().getRes().getString("help"), new MenuBar().getMiHelpContentAccelerator() },
			{ LanguageManager.getInstance().getRes().getString("search"), new MenuBar().getMiFindAccelerator() },
			{ LanguageManager.getInstance().getRes().getString("del_sel"), new MenuBar().getMiDeleteSelectionAccelerator() },
			{ LanguageManager.getInstance().getRes().getString("edit_sel"), new MenuBar().getMiEditSelectionAccelerator() } };

	public KeyBindingsDialog(JFrame frame) {
		super(frame, true);
		setTitle(LanguageManager.getInstance().getRes().getString("keyb"));
		setSize(300, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		setLocationRelativeTo(frame);
		setResizable(false);

		table = new JTable();
		table.setModel(new NonEditableTableModel(data));
		add(table);
	}

}
