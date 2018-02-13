package configurator.model.parameter;

import java.io.Serializable;

import configurator.model.Element;

public abstract class AbstractParameter extends Element implements Serializable {
	
	private static final long serialVersionUID = 7200837452813130698L;

	private ParameterType type = ParameterType.NO_TYPE;

	private String description = null;
	
	public AbstractParameter(ParameterType type, String name) {
		super("Parameter");
		
		this.type = type;
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ParameterType getType() {
		return type;
	}
	
	@Override
	public String toString() {
		return name;
	}

	public abstract void setValue(Object value);
	public abstract Object getValue();
}
