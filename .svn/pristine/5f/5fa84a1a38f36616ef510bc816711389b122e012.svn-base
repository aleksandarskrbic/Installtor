/**
 * 
 */
package installator.builder;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import configurator.model.Wizard;
import configurator.model.parameter.AbstractParameter;
import configurator.model.parameter.ImageParameter;
import configurator.model.parameter.LogicalParameter;
import configurator.model.parameter.MultiLineTextParameter;
import configurator.model.parameter.NumberParameter;
import configurator.model.parameter.ParameterType;
import configurator.model.parameter.PathParameter;
import configurator.model.parameter.TextParameter;

/**
 * Obradjivac wizarda
 * Generise GUI elemente za parametre koji se nalaze u wizardu
 * 
 * @author Nemanja Simic
 *
 */
public class ComponentGenerator {

	private List<JComponent> components;
	
	private final Wizard wizard;
	
	public ComponentGenerator(Wizard wizard) {
		components = new ArrayList<>();
		this.wizard = wizard;
		identifyParameters();
	}
	
	private void identifyParameters() {
		for (AbstractParameter p : wizard.getParameters()) {
			generateComponent(p);
		}
	}
	
	private void generateComponent(AbstractParameter p) {
		ParameterType type = p.getType();

		if (type == ParameterType.TEXT) {
			components.add(generateTextComponent((TextParameter)p));
		} else if (type == ParameterType.LOGICAL) {
			components.add(generateBooleanComponent((LogicalParameter)p));
		} else if (type == ParameterType.IMAGE) {
			components.add(generateImageComponent((ImageParameter)p));
		} else if (type == ParameterType.NUMBER) {
			components.add(generateNumberComponent((NumberParameter)p));
		} else if (type == ParameterType.PATH) {
			components.add(generatePathComponent((PathParameter)p));
		} else if (type == ParameterType.MULTILINE) {
			components.add(generateMultiline((MultiLineTextParameter)p));
		}
	}
	
	private JComponent generatePathComponent(PathParameter p) {
		final JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		final JTextField path = new JTextField(p.getValue().toString());
		panel.add(new JLabel(p.getName()));
		panel.add(path);
		
		return panel;
	}

	private JComponent generateNumberComponent(NumberParameter p) {
		final JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.add(new JLabel(p.getName() + ": "));
		panel.add(new JLabel(p.getValue().toString()));
		
		return panel;
	}

	private JComponent generateImageComponent(ImageParameter p) {
		final JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		final JLabel image = new JLabel();
		image.setIcon(new ImageIcon(p.getImagePath()));
		panel.add(new JLabel(p.getName()));
		panel.add(image);
		
		return panel;
	}

	private JComponent generateBooleanComponent(LogicalParameter p) {
		final JCheckBox component = new JCheckBox(p.getName());
		component.setSelected(p.getValue());
		return component;
	}

	private JComponent generateTextComponent(TextParameter p) {
		final JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		final JTextField component = new JTextField();
		component.setText(p.getValue());
		component.setName(p.getName());
		
		panel.add(new JLabel(p.getName()));
		panel.add(component);
		return panel;
	}
	
	private JComponent generateMultiline(MultiLineTextParameter p) {
		final JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		final JTextArea component = new JTextArea();
		
		component.setText(p.getValue().toString());
		component.setName(p.getName());
		
		panel.add(new JLabel(p.getName()));
		panel.add(component);
		return panel;
	}
	
	public List<JComponent> getComponents() {
		return components;
	}
}
