package configurator.model.parameter;

public class MultiLineTextParameter extends AbstractParameter {

	private static final long serialVersionUID = 1L;
	
	private String value;

	public MultiLineTextParameter(String name) {
		super(ParameterType.MULTILINE, name);

	}

	@Override
	public void setValue(Object value) {
		this.value = (String) value;
		
	}

	@Override
	public Object getValue() {
		return value;
	}


}
