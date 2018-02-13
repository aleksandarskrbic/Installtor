package configurator.model.parameter;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathParameter extends AbstractParameter {
	
	private static final long serialVersionUID = -4803607541101225551L;

	private String location = null;
	
	private PathTypeOption option = PathTypeOption.NO_OPTION;
	
	public PathParameter(String name) {
		super(ParameterType.PATH, name);
	}

	public void setOption(PathTypeOption option) {
		this.option = option;
	}
	
	public PathTypeOption getOption() {
		return option;
	}
	
	@Override
	public void setValue(Object value) {
		this.location = ((Path) value).toString();
	}

	@Override
	public Path getValue() {
		return Paths.get(location);
	}
	
	private void writeObject(ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
		out.writeObject(location.toString());
		}
	
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject();
		location = (String) in.readObject();
	}
}
