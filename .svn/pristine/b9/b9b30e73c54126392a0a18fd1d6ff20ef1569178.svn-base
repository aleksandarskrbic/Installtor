package configurator.model;

import java.util.ArrayList;
import java.util.List;

import configurator.model.parameter.AbstractParameter;

public class Wizard extends Element {

	private static final long serialVersionUID = 4570228738243573429L;
	
	private String description = null;

	private List<AbstractParameter> parameterList;

	public Wizard(String name) {
		this.setName(name);
		parameterList = new ArrayList<AbstractParameter>();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<AbstractParameter> getParameters() {
		return parameterList;
	}
	
	public void addParameter(AbstractParameter parameter) {
		boolean exist = parameterList
									.stream()
									.anyMatch(p -> p.getName().equals(parameter.getName()));
		
		if (exist) {
			throw new IllegalArgumentException(
					String.format("Parameter '%s' already exists in wizard '%s'.", parameter.getName(), name));
		}
		
		parameterList.add(parameter);
	}

	public boolean removeParameter(AbstractParameter parameter) {
		return parameterList.remove(parameter);
	}

	public void updateParameter(AbstractParameter oldParameter, AbstractParameter newParameter) {
		int index = parameterList.indexOf(oldParameter);
		parameterList.set(index, newParameter);
	}

	@Override
	public String toString() {
		return name;
	}
}
