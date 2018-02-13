package localization;

import java.util.Locale;
import java.util.ResourceBundle;

import configurator.PropertiesManager;
import configurator.gui.MainFrame;
import installator.gui.InstallatorMainFrame;

public class LanguageManager {
	private static LanguageManager languageManager = null;

	private ResourceBundle res;
	private Locale locale;

	public SupportedLanguage LANG_SERBIAN_CYR;
	public SupportedLanguage LANG_SERBIAN_LAT;
	public SupportedLanguage LANG_ENG;

	public static SupportedLanguage LANG_DEFAULT;

	private static final String PROP_LANG = "lang";
	
	private LanguageManager() {
		addLanguages();
		
		setDefaultLanguage(LANG_ENG);
		setLocale(LANG_DEFAULT.getLocale());
		setRes(LANG_DEFAULT.getBundle());
	}
	
	public void changeLanguage(SupportedLanguage language) {
		setLocale(language.getLocale());
		setRes(language.getBundle());

		if(MainFrame.hasInstance())
			MainFrame.getInstance().changeLanguage();
		if(InstallatorMainFrame.hasInstance())
			InstallatorMainFrame.getInstance().changeLanguage();
			
		PropertiesManager.getInstance().put(PROP_LANG, language.getLocale().toString());
	}
	
	/**
	 * Postavlja defaultni jezik na language ako ne postoji vec sacuvan
	 * @param language
	 */
	private void setDefaultLanguage(SupportedLanguage language) {
		String savedLang = PropertiesManager.getInstance().get(PROP_LANG);
		if(savedLang == null)
			LANG_DEFAULT = language;
		else if(savedLang.equals(LANG_SERBIAN_CYR.getLocale().toString()))
			LANG_DEFAULT = LANG_SERBIAN_CYR;
		else if(savedLang.equals(LANG_SERBIAN_LAT.getLocale().toString()))
			LANG_DEFAULT = LANG_SERBIAN_LAT;
		else if(savedLang.equals(LANG_ENG.getLocale().toString()))
			LANG_DEFAULT = LANG_ENG;
		else
			LANG_DEFAULT = language;
	}

	private void addLanguages() {
		LANG_ENG = new SupportedLanguage(
				() -> res.getString("eng"), new Locale("en", "US"));
		
		LANG_SERBIAN_CYR = new SupportedLanguage(
				() -> res.getString("srb_cr"), new Locale("sr", "RS", "cr"));
		
		
		LANG_SERBIAN_LAT = new SupportedLanguage(
				() -> res.getString("srb"), new Locale("sr", "RS", "la"));
	}
	
	public SupportedLanguage[] getSupportedLanguages() {
		SupportedLanguage[] ls = {LANG_ENG, LANG_SERBIAN_LAT, LANG_SERBIAN_CYR};
		return ls;
	}
	
	public void setRes(ResourceBundle res) {
		this.res = res;
	}
	
	public ResourceBundle getRes() {
		return res;
	}
		
	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public static LanguageManager getInstance() {
		if (languageManager == null)
			languageManager = new LanguageManager();

		return languageManager;
	}
}
