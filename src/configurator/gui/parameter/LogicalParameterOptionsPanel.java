package configurator.gui.parameter;

import javax.swing.JCheckBox;
import javax.swing.JLabel;

import configurator.model.parameter.LogicalParameter;
import localization.LanguageManager;
import net.miginfocom.swing.MigLayout;

public class LogicalParameterOptionsPanel extends ParameterOptionsEditor<LogicalParameter> {
	
	private JCheckBox cbxDefaultVal = null;
	
	public LogicalParameterOptionsPanel() {
		super();

		initComponents();
		groupComponents();
		updateDefValueText();
	}

	private void updateDefValueText() {
		this.cbxDefaultVal.setText(cbxDefaultVal.isSelected() ? "True" : "False");
	}

	private void initComponents() {
		this.cbxDefaultVal = new JCheckBox();
		this.cbxDefaultVal.addActionListener(e -> updateDefValueText());
	}

	private void groupComponents() {
		setLayout(new MigLayout("", "5[]10[grow]5", "10[]10"));
		add(new JLabel(LanguageManager.getInstance().getRes().getString("def_value")));
		add(cbxDefaultVal, "growx");
	}

	@Override
	public LogicalParameter getParameter() {
		LogicalParameter par = new LogicalParameter(null);
		par.setValue(cbxDefaultVal.isSelected());
		return par;
	}

	@Override
	void setParameter(LogicalParameter p) {
		p.setValue(cbxDefaultVal.isSelected());
	}
	
}
