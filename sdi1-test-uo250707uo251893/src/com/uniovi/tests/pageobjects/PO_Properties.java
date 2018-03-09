package com.uniovi.tests.pageobjects;

import java.util.Locale;
import java.util.ResourceBundle;

public class PO_Properties {
	
	private static String Path;
	static int SPANISH = 0;
	static int ENGLISH = 1;	
	static Locale[] idioms = new Locale[] {new Locale("ES"), new Locale("EN")};
	//static Properties p = new Properties();
	public PO_Properties(String Path) //throws FileNotFoundException, IOException 
	{
		PO_Properties.Path = Path;
	}
	//
	// locale is de index in idioms array.
	//
    public String getString(String prop, int locale) {	
		ResourceBundle bundle = ResourceBundle.getBundle(Path, idioms[locale]);
		return bundle.getString(prop);
	}
    
    public static int getSPANISH() {
		return SPANISH;
	}

	public static int getENGLISH() {
		return ENGLISH;
	}
	
}
