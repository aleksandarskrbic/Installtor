package configurator.model.parameter;

public class LimitedNumberParameter extends NumberParameter {
	
	private static final long serialVersionUID = 8523484761610894274L;

	public LimitedNumberParameter(String name) {
		super(name);
	}

	@Override
	public void setValue(Object v) {
		Integer value = (Integer) v;
		if(min != null && value < min)
			throw new IllegalArgumentException("Value has to be greater than lower bound " + min);
		if(max != null && value > max)
			throw new IllegalArgumentException("Value has to be lower than upper bound " + max);
		
		this.value = value;
	}

}
