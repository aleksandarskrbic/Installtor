package localization;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.function.Supplier;

public class SupportedLanguage {
	private Supplier<String> nameSupplier = null;
	private Locale locale = null;
	private ResourceBundle bundle = null;

	public SupportedLanguage(Supplier<String> nameSupplier, Locale locale) {
		this.nameSupplier = nameSupplier;
		this.locale = locale;
		this.bundle = ResourceBundle.getBundle("MessageResources.MessageResources", locale);
	}

	public String getDisplayName() {
		return nameSupplier.get();
	}

	public Locale getLocale() {
		return locale;
	}
	
	@Override
	public String toString() {
		return getDisplayName();
	}

	public ResourceBundle getBundle() {
		return bundle;
	}

}