package configurator.gui.parameter;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import configurator.model.parameter.NumberParameter;
import localization.LanguageManager;
import net.miginfocom.swing.MigLayout;

public class NumberParameterOptionsPanel extends ParameterOptionsEditor<NumberParameter> {
	private final int MIN_VALUE = Integer.MIN_VALUE;
	private final int MAX_VALUE = Integer.MAX_VALUE;

	private JSpinner spnDefaultVal = null;
	private JCheckBox cbxLimit = null;
	private JLabel lblMinVal = null;
	private JSpinner spnMinVal = null;
	private JLabel lblMaxVal = null;
	private JSpinner spnMaxVal = null;

	public NumberParameterOptionsPanel() {
		super();

		initComponents();
		groupComponents();
		updateLimitEnabled();
	}

	private void initComponents() {
		spnDefaultVal = new JSpinner();
		
		cbxLimit = new JCheckBox(LanguageManager.getInstance().getRes().getString("limited_number"));
		cbxLimit.addActionListener(e -> updateLimitEnabled());
		
		
		lblMinVal = new JLabel(LanguageManager.getInstance().getRes().getString("min_val"));
		spnMinVal = new JSpinner();
		spnMinVal.addChangeListener(c -> updateModels());

		lblMaxVal = new JLabel(LanguageManager.getInstance().getRes().getString("max_val"));
		spnMaxVal = new JSpinner();
		spnMaxVal.addChangeListener(c -> updateModels());
	}

	private void updateModels() {
		int val = (int) spnDefaultVal.getValue();
		if(!cbxLimit.isSelected()) {
			spnDefaultVal.setModel(new SpinnerNumberModel(val, MIN_VALUE, MAX_VALUE, 1));
			return;
		}
		
		int minVal = (int) spnMinVal.getValue();
		int maxVal = (int) spnMaxVal.getValue();

		if (val < minVal) val = minVal;
		else if (val > maxVal) val = maxVal;

		spnDefaultVal.setModel(new SpinnerNumberModel(val, minVal, maxVal, 1));
		spnMinVal.setModel(new SpinnerNumberModel(minVal, MIN_VALUE, maxVal, 1));
		spnMaxVal.setModel(new SpinnerNumberModel(maxVal, minVal, MAX_VALUE, 1));
	}
	
	private void updateLimitEnabled() {
		boolean enabled = cbxLimit.isSelected();
		lblMinVal.setEnabled(enabled);
		spnMinVal.setEnabled(enabled);
		lblMaxVal.setEnabled(enabled);
		spnMaxVal.setEnabled(enabled);
		
		updateModels();
	}

	private void groupComponents() {
		setLayout(new MigLayout("", "5[]10[grow]5", "5[]20[]10[]10[]5"));

		add(new JLabel(LanguageManager.getInstance().getRes().getString("def_value")));
		add(spnDefaultVal, "growx, wrap");
		add(cbxLimit, "span 2 1, wrap");
		add(lblMinVal);
		add(spnMinVal, "growx, wrap");
		add(lblMaxVal);
		add(spnMaxVal, "growx");
	}

	@Override
	public NumberParameter getParameter() {
		NumberParameter numPar = new NumberParameter(null);
		numPar.setLimited(false);
		if (cbxLimit.isSelected()) {
			numPar.setLimited(true);
			numPar.setMin((Integer) spnMinVal.getValue());
			numPar.setMax((Integer) spnMaxVal.getValue());
		}
		
		numPar.setValue((Integer) spnDefaultVal.getValue());
		
		return numPar;
	}

	@Override
	void setParameter(NumberParameter p) {
		p.setValue((Integer)spnDefaultVal.getValue());
		
		if(cbxLimit.isSelected()) {
			p.setLimited(cbxLimit.isSelected());
			p.setMin((Integer) spnMinVal.getValue());
			p.setMax((Integer) spnMaxVal.getValue());
		}
	}
	
}
