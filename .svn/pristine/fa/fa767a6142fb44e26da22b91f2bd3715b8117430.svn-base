package configurator.gui.parameter;

import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;

import configurator.model.parameter.PathParameter;
import configurator.model.parameter.PathTypeOption;
import net.miginfocom.swing.MigLayout;

public class PathParameterOptionsPanel extends ParameterOptionsEditor<PathParameter> {
	
	private static final long serialVersionUID = 1423137030063952604L;

	private JComboBox<TypeEntry> cmbFileType = null;
	
	private JFileChooser pathChooser = null;
	
	private JTextField path;
	
	private JButton pickPath;
	
	public PathParameterOptionsPanel() {
		super();
		
		initComponents();
		groupComponents();
	}

	private void initComponents() {
		pathChooser = new JFileChooser();
		path = new JTextField(30);
		pickPath = new JButton("Choose..");
		pickPath.addActionListener(l -> pickFile());
		
		cmbFileType = new JComboBox<TypeEntry>();
		cmbFileType.addItem(new TypeEntry("File or Directory", PathTypeOption.FILE_AND_DIRECTORY));
		cmbFileType.addItem(new TypeEntry("File Only", PathTypeOption.FILE));
		cmbFileType.addItem(new TypeEntry("Directory Only", PathTypeOption.DIRECTORY));
		
	}

	private void pickFile() {
		// TODO: zabraniti da se mijenja kod default parametara tip odabira
		// mora uvijek biti DIRECTORY
		
		pathChooser.setFileSelectionMode(getOption().ordinal());

		if (pathChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
			path.setText(pathChooser.getSelectedFile().getAbsolutePath()); 
	}

	private void groupComponents() {
		setLayout(new MigLayout("", "5[]10[grow]5", "10[]10[]10[]"));
		add(new JLabel("Type:"));
		add(cmbFileType, "span 1 0, growx, wrap");
		add(path, "span 2 0");
		add(pickPath);
	}
	
	private PathTypeOption getOption() {
		return ((TypeEntry) cmbFileType.getSelectedItem()).getOption();
	}
	
	@Override
	public PathParameter getParameter() {
		PathParameter pathPar = new PathParameter(null);
		pathPar.setOption(getOption());
		try {
			pathPar.setValue(Paths.get(pathChooser.getSelectedFile().getAbsolutePath()));			
		} catch (Exception e) {
			pathPar.setValue(Paths.get(path.getText()));
		}
		return pathPar;
	}
	
	private class TypeEntry {
		private String displayName = null;
		private PathTypeOption option = PathTypeOption.NO_OPTION;
		
		public TypeEntry(String displayName, PathTypeOption option) {
			this.displayName = displayName;
			this.option = option;
		}
		
		public PathTypeOption getOption() {
			return option;
		}
		
		@Override
		public String toString() {
			return displayName;
		}
	}

	@Override
	void setParameter(PathParameter p) {
		path.setText(p.getValue().toAbsolutePath().toString());
	}

}
