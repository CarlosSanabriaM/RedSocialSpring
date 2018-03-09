package com.uniovi.tests.pageobjects;

import org.openqa.selenium.WebDriver;

public class PO_HomeView extends PO_NavView {

	/**
	 * Comprueba que se carga el saludo de bienvenida en el idioma indicado
	 */
	static public void checkWelcome(WebDriver driver, int language) {
		checkElement(driver, "text", p.getString("Index.welcome.message", language));
	}

	static public void checkChangeIdiom(WebDriver driver, String textIdiom1, 
			String textIdiom2, int locale1,int locale2) {
		
		// Esperamos a que se cargue el saludo de bienvenida en Español
		PO_HomeView.checkWelcome(driver, locale1);
		// Cambiamos a segundo idioma
		PO_HomeView.changeIdiom(driver, textIdiom2);
		// COmprobamos que el texto de bienvenida haya cambiado a segundo idioma
		PO_HomeView.checkWelcome(driver, locale2);
		// Volvemos a Español.
		PO_HomeView.changeIdiom(driver, textIdiom1);
		// Esperamos a que se cargue el saludo de bienvenida en Español
		PO_HomeView.checkWelcome(driver, locale1);
	}
	
}
