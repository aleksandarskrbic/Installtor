package configurator.gui.parameter;

import javax.swing.border.TitledBorder;

import configurator.model.parameter.ParameterType;
import localization.LanguageManager;

/**
 * 
 * Struktura koja u ComboBox-u predstavlja tip parametra. Sadrzi tip i JPanel sa njegovim
 * opcijama.
 *
 */
public class ParameterTypeEntry {
	private ParameterType type = ParameterType.NO_TYPE;
	private String displayName = null;
	private ParameterOptionsEditor optionsEditor = null;

	/**
	 * 
	 * Za ovaj konstruktor optionsPanel mora da odgovara type parametru.
	 * 
	 */
	@Deprecated
	public ParameterTypeEntry(String displayName, ParameterType type, ParameterOptionsEditor optionsPanel) {
		this.type = type;
		this.displayName = displayName;

		this.optionsEditor = optionsPanel;
		this.optionsEditor.setBorder(new TitledBorder(LanguageManager.getInstance().getRes().getString("options")));
	}

	public ParameterTypeEntry(String displayName, ParameterType type) {
		this.type = type;
		this.displayName = displayName;

		switch (type) {
		case TEXT:
			optionsEditor = new TextParameterOptionsPanel();
			break;
		case NUMBER:
			optionsEditor = new NumberParameterOptionsPanel();
			break;
		case LOGICAL:
			optionsEditor = new LogicalParameterOptionsPanel();
			break;
		case PATH:
			optionsEditor = new PathParameterOptionsPanel();
			break;
		case IMAGE:
			optionsEditor = new ImageParameterOptionsPanel();
			break;
		case MULTILINE:
			optionsEditor = new MultiLineTextParameterOptionsPanel();
			break;
		default:
			break;
		}

		this.optionsEditor.setBorder(new TitledBorder(LanguageManager.getInstance().getRes().getString("options")));
	}

	public ParameterType getType() {
		return type;
	}

	public ParameterOptionsEditor getOptionsEditor() {
		return optionsEditor;
	}

	@Override
	public String toString() {
		return displayName;
	}
}
