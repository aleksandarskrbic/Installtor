package configurator.gui.parameter;

import javax.swing.JLabel;
import javax.swing.JTextField;

import configurator.model.parameter.TextParameter;
import localization.LanguageManager;
import net.miginfocom.swing.MigLayout;

public class TextParameterOptionsPanel extends ParameterOptionsEditor<TextParameter> {
	
	private JTextField txtDefault = null;
	
	public TextParameterOptionsPanel() {
		super();

		init();
	}

	private void init() {
		txtDefault = new JTextField(30);
		
		setLayout(new MigLayout("", "5[]10[grow]5", "5[]5"));
		
		add(new JLabel(LanguageManager.getInstance().getRes().getString("def_value")));
		add(txtDefault, "growx");
	}
	
	@Override
	public TextParameter getParameter() {
		TextParameter txtPar = new TextParameter(null);
		txtPar.setValue(txtDefault.getText().trim());

		return txtPar;
	}

	@Override
	void setParameter(TextParameter p) {
		p.setValue(txtDefault.getText().trim());
	}
}
