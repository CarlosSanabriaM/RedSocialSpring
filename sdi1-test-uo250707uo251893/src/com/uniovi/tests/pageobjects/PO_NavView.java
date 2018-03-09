package com.uniovi.tests.pageobjects;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_NavView extends PO_View {

	/**
	 * Clicka una de las opciones principales (a href) y comprueba que se vaya a la
	 * vista con el elemento de tipo criterio con el texto textoDestino
	 * 
	 * @param driver: apuntando al navegador abierto actualmente.
	 * @param idLink: id del elemento html "a" que se va a pinchar
	 * @param criterio: "id" or "class" or "text" or "@attribute" or "free". Si el valor
	 *           		de criterio es free es una expresion xpath completa.
	 * @param textoDestino: texto correspondiente a la búsqueda de la página destino.
	 */
	public static void clickLinkAndCheckSomethingAppears(WebDriver driver, String idLink, String criterio, String textoDestino) {
		// Obtenemos el elemento "a" que tiene el id indicado
		List<WebElement> elementos = PO_NavView.checkElement(driver, "id", idLink);
		// Tiene que haber un sólo elemento.
		assertTrue(elementos.size() == 1);
		// Ahora lo clickamos
		elementos.get(0).click();
		// Esperamos a que sea visible un elemento concreto
		elementos = PO_NavView.checkElement(driver, criterio, textoDestino);
		// Tiene que haber un sólo elemento.
		assertTrue(elementos.size() == 1);
	}
	
	/**
	* Selecciona el enlace de idioma correspondiente al texto textLanguage
	* @param driver: apuntando al navegador abierto actualmente.
	* @param textLanguage: el id del enlace de idioma ("btnEnglish" o "btnSpanish")
	*/
	public static void changeIdiom(WebDriver driver, String textLanguage) {
		// Clickamos en la opción Idioma para desplegar del dropdown menu
		List<WebElement> elementos = PO_NavView.checkElement(driver, "id", "btnLanguage");
		elementos.get(0).click();
		// Clickamos en el idioma indicado
		elementos = PO_NavView.checkElement(driver, "id", textLanguage);
		elementos.get(0).click();
	}
	
}