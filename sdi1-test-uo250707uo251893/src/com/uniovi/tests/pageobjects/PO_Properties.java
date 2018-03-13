package com.uniovi.tests.pageobjects;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;

public class PO_Properties {
	
	private static String Path;
	static int SPANISH = 0;
	static int ENGLISH = 1;	
	static Locale[] idioms = new Locale[] {new Locale("ES"), new Locale("EN")};

	public PO_Properties(String Path) {
		PO_Properties.Path = Path;
	}

	public String getString(String prop, int locale) {
		ResourceBundle bundle = ResourceBundle.getBundle(Path, idioms[locale]);
		String value = bundle.getString(prop);
		try {
			// Transformamos la cadena leída en formato ISO-8859-1 a UTF8
			value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return value;
	}
    
    public static int getSPANISH() {
		return SPANISH;
	}

	public static int getENGLISH() {
		return ENGLISH;
	}
	
}
