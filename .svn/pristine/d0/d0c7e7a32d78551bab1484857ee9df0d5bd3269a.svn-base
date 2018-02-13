package installator.gui;

import javax.swing.JButton;
import javax.swing.JPanel;

import installator.builder.WizardBuilder;
import localization.LanguageManager;
import net.miginfocom.swing.MigLayout;

public class ButtonsPanel extends JPanel {
	
	private JButton btnNext = null;
	private JButton btnCancel = null;
	private JButton btnFinish = null;
	private JButton btnBack = null;
	
	public ButtonsPanel() {
		initComponents();
		groupComponents();
		setVisible(true);
		
	}
	private void initComponents() {
		btnBack = new JButton(LanguageManager.getInstance().getRes().getString("back"));
		btnBack.setName("backBtn");
		btnBack.addActionListener(WizardBuilder.getInstance());
		
		btnNext = new JButton(LanguageManager.getInstance().getRes().getString("next"));
		btnNext.setName("nextBtn");
		btnNext.addActionListener(WizardBuilder.getInstance());
		
		btnCancel = new JButton(LanguageManager.getInstance().getRes().getString("cancel"));
		btnCancel.setName("cancelBtn");
		btnCancel.addActionListener(e -> InstallatorMainFrame.getInstance().dispose());
		
		btnFinish = new JButton(LanguageManager.getInstance().getRes().getString("finish"));
		btnFinish.setName("finishBtn");
		btnFinish.addActionListener(WizardBuilder.getInstance());
	}
	/*
	 * Za sada su svi prikazani, a onda kad se uvede card layout trebace samo da se
	 * do poslednjeg dijaloga finish dugme ne vidi
	 */
	
	private void groupComponents() {
		setLayout(new MigLayout("", "10[]70:350:400[grow ,right]10[right]10[right]10"));
		add(btnCancel,"cell 0 0");
		add(btnBack, "cell 1 0, align right");
		add(btnNext, "cell 2 0, align right");
		add(btnFinish, "cell 3 0, align right");
	}

}
