package configurator.gui.parameter;

import javax.swing.JPanel;

import configurator.model.parameter.AbstractParameter;

/**
 * 
 * JPanel koji sluzi za uredjivanje posebnih vrsta parametra.
 *
 */
public abstract class ParameterOptionsEditor<T extends AbstractParameter> extends JPanel {
	
	public ParameterOptionsEditor() {
		super();
	}
	
	/**
	 * Ucitava parametar i sprema njegove atribute za uredjivanje.
	 */
	abstract void setParameter(T p);
	
	/**
	 * Generise novi parametar od unijetih vrijednost.
	 */
	abstract T getParameter();
	
}
