package configurator.model.parameter;

public class NumberParameter extends AbstractParameter {
	
	private static final long serialVersionUID = -2652199596215941839L;
	
	protected Integer value;
	
	protected boolean isLimited = false;
	
	protected Integer min;
	
	protected Integer max;
	
	public boolean isLimited() {
		return isLimited;
	}

	public void setLimited(boolean isLimited) {
		this.isLimited = isLimited;
	}

	public Integer getMin() {
		return min;
	}

	public void setMin(Integer min) {
		this.min = min;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

	public NumberParameter(String name) {
		super(ParameterType.NUMBER, name);
	}

	@Override
	public void setValue(Object value) {
		this.value = (Integer) value;
	}

	@Override
	public Integer getValue() {
		return value;
	}
}
