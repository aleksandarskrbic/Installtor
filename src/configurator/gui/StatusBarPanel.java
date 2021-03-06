package configurator.gui;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import localization.LanguageManager;

public class StatusBarPanel extends JPanel {

	private static final long serialVersionUID = 3406790438384751403L;
	
	private final JLabel environment;
	
	private final JLabel userName;
	
	public StatusBarPanel() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBorder(new BevelBorder(BevelBorder.LOWERED));
		
		environment = new JLabel(LanguageManager.getInstance().getRes().getString("config"));
		userName = new JLabel(System.getenv().get("USERNAME"));
		
		groupComponents();
	}
	
	private void groupComponents() {
		add(Box.createGlue());
		add(environment);
		add(Box.createGlue());
		add(userName);
	}
	
	public void updateLanguage() {
		environment.setText(LanguageManager.getInstance().getRes().getString("config"));
	}

	public void updateStatus(String status) {
		if(status.isEmpty())
			return;
		
	}
	
	public void updateEnvironment(String env) {
		if(env.isEmpty())
			return;
		
		environment.setText(env);
	} 
	
	
	public void updateUserName(String name) {
		if(name.isEmpty())
			return;
		
		userName.setText(name);
	}
	
}
