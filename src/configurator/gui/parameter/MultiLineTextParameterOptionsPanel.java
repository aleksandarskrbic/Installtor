package configurator.gui.parameter;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import configurator.model.parameter.MultiLineTextParameter;
import localization.LanguageManager;
import net.miginfocom.swing.MigLayout;

public class MultiLineTextParameterOptionsPanel extends ParameterOptionsEditor<MultiLineTextParameter> {
	
	private JTextArea txtDefault = null;
	
	public MultiLineTextParameterOptionsPanel() {
		super();

		init();
	}

	private void init() {
		txtDefault = new JTextArea(5, 10);
		
		setLayout(new MigLayout("", "5[]10[grow]5", "5[]5"));
		
		add(new JLabel(LanguageManager.getInstance().getRes().getString("def_value")));
		add(txtDefault, "growx");
	}

	@Override
	void setParameter(MultiLineTextParameter p) {
		p.setValue(txtDefault.getText().trim());
	}

	@Override
	MultiLineTextParameter getParameter() {
		MultiLineTextParameter multiLine = new MultiLineTextParameter(null);
		multiLine.setValue(txtDefault.getText().trim());

		return multiLine;
	}
	
}
