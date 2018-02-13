package configurator.model.parameter;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageParameter extends AbstractParameter {
	
	private static final long serialVersionUID = -3732918002364933580L;
	
	private String path;
	
	private transient JLabel image;

	public ImageParameter(ParameterType type, String name) {
		super(ParameterType.IMAGE, name);
	}

	@Override
	public void setValue(Object value) {
		this.path = (String) value;
		ImageIcon img = new ImageIcon(this.path);
		this.image = new JLabel("", img, JLabel.CENTER);
	}

	@Override
	public Object getValue() {
		if (this.image != null) {
			return image;
		}
		
		return null;
	}
	
	public String getImagePath() {
		return path;
	}
	
	private void writeObject(ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
		out.writeObject(image.getText());
		}
	
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject();
		image = new JLabel((String) in.readObject());
	}

}
