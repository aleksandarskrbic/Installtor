package configurator.gui.modeleditor;

import java.nio.file.Path;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import configurator.model.Project;
import localization.LanguageManager;
import net.miginfocom.swing.MigLayout;

/**
 * Ovaj panel sluzi za konstrukciju dijaloga
 * koji edituje ili pravi novi projekat
 */
public class ProjectEditorPanel extends JPanel {

	private static final long serialVersionUID = -5193963604273831251L;
	
	private final JTextField projName;

	private final JTextArea projDesc;
	
	private JLabel name;
	
	private JLabel desc;
	
	private Path source;
	
	private Path dest;
	
	private JTextField srcText;
	
	private JTextField dstText;
	
	private JButton pickSrc;
	
	private JButton pickDst;
	
	private boolean dstSelected = false;
	
	private boolean srcSelected = false;
	
	/***
	 * Konstruktor za novi projekat
	 */
	public ProjectEditorPanel() {
		projName = new JTextField();
		projDesc = new JTextArea(15, 15);
		initComponents();
		groupComonents();
	}

	/***
	 * Konstruktor koji pozivamo ako editujemo postojeci projekat
	 * @param project Projekat koji editujemo
	 */
	public ProjectEditorPanel(final Project project) { 
		this();
		projName.setText(project.getName());
		projDesc.setText(project.getDescription());
	}
	
	private void initComponents() {
		name = new JLabel(LanguageManager.getInstance().getRes().getString("name") + ":");
		desc = new JLabel(LanguageManager.getInstance().getRes().getString("desc") + ":");
		
		srcText = new JTextField(30);
		dstText = new JTextField(30);
		
		pickSrc = new JButton(LanguageManager.getInstance().getRes().getString("src_param"));
		pickDst = new JButton(LanguageManager.getInstance().getRes().getString("dest_param"));

		JFileChooser srcChooser = new JFileChooser();
		JFileChooser dstChooser = new JFileChooser();
		
		pickSrc.addActionListener(l -> chooseSource(srcChooser));
		pickDst.addActionListener(l -> chooseDestination(dstChooser));
	}

	private void groupComonents() {
		setLayout(new MigLayout("", "10[]10[grow]10", "10[]10[]10[]10[grow]10"));
		
		add(name, "cell 0 0");
		add(projName, "cell 1 0, growx");
		add(desc, "cell 0 1, aligny top grow");
		add(new JScrollPane(projDesc), "cell 1 1, grow");
		add(srcText, "cell 1 2");
		add(pickSrc, "cell 0 2");
		add(dstText, "cell 1 3");
		add(pickDst, "cell 0 3");
	}
	
	private void chooseDestination(JFileChooser dstChooser) {
		dstChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		if (dstChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			dest = dstChooser.getSelectedFile().toPath();
			dstText.setText(dest.toAbsolutePath().toString());
			dstSelected = true;
			return;
		}
		dstText.setText("");
		dstSelected = false;
	}

	private void chooseSource(JFileChooser srcChooser) {
		srcChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		if (srcChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			source = srcChooser.getSelectedFile().toPath();
			srcText.setText(source.toAbsolutePath().toString());
			srcSelected = true;
			return;
		}
		srcText.setText("");
		srcSelected = false;
	}
	
	public String getProjName() {
		return projName.getText();
	}

	public String getProjDesc() {
		return projDesc.getText();
	}
	
	public boolean pathsSelected() {
		return srcSelected && dstSelected;
	}

	public Path getSrcPath() {
		return source;
	}

	public Path getDstPath() {
		return dest;
	}

}
