package configurator.gui.parameter;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import configurator.model.parameter.ImageParameter;
import configurator.model.parameter.ParameterType;
import localization.LanguageManager;

public class ImageParameterOptionsPanel extends ParameterOptionsEditor<ImageParameter> {
	
	private JFileChooser chooser;
	private JButton btn;
	private String path;

	public ImageParameterOptionsPanel() {
		super();
		
		init();
	}
	
	private void init() {
		setLayout(new FlowLayout());
		btn = new JButton(LanguageManager.getInstance().getRes().getString("select_img"));
		chooser = new JFileChooser();
		btn.addActionListener(action -> selectImage());
		add(btn);
	}
	
	private void selectImage() {
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
			path = chooser.getSelectedFile().getAbsolutePath();
			
	}
	
	@Override
	public void setParameter(ImageParameter p) {
		p.setValue(path);
	}

	@Override
	public ImageParameter getParameter() {
		ImageParameter par = new ImageParameter(ParameterType.IMAGE, "");
		par.setValue(path);
		par.setDescription(path);
		return par;
	}

}
