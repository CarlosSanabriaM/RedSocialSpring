package com.uniovi.tests.pageobjects;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.uniovi.tests.util.SeleniumUtils;

public class PO_NavView extends PO_View {
	
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
	
	/**
	 * Clicka en un dropdown-menu y después clicka en una opción de dicho menú
	 * 
 	 * @param driver: apuntando al navegador abierto actualmente.
	 * @param idLinkDropdownMenu: id del elemento html "a" del dropdown-menu
	 * @param idLinkDropdownMenuOption: id del elemento html "a" de la opción a clickar del dropdown-menu
	 * @param criterio: "id" or "class" or "text" or "@attribute" or "free". Si el valor
	 *           		de criterio es free es una expresion xpath completa.
	 * @param textoDestino: texto correspondiente al criterio.
	 */
	public static void clickDropdownMenuOptionAndCheckElement(WebDriver driver, 
			String idLinkDropdownMenu, String idLinkDropdownMenuOption,
			String criterio, String textoDestino) {
		
		// Primero clickamos en el dropdown-menu que tiene el elemento "a" con el id especificado
		// y esperamos a que aparezca la opción de dicho menú que tenga un elemento "a" con el id especificado
		PO_PrivateView.clickLinkAndCheckElement(driver, idLinkDropdownMenu, "id", idLinkDropdownMenuOption);
		
		// Una vez ha aparecido dicha opción de menú, clickamos en ella 
		// y esperamos a que aparezca un texto correspondiente al criterio
		PO_PrivateView.clickLinkAndCheckElement(driver, idLinkDropdownMenuOption, criterio, textoDestino);
	}
	
	/**
	 * CLicka una de las opciones principales (a href) y comprueba que se vaya a la
	 * vista con el elemento de tipo type con el texto Destino
	 * 
	 * @param driver:
	 *            apuntando al navegador abierto actualmente.
	 * @param textOption:
	 *            Texto de la opción principal.
	 * @param criterio:
	 *            "id" or "class" or "text" or "@attribute" or "free". Si el valor
	 *            de criterio es free es una expresion xpath completa.
	 * @param textoDestino:
	 *            texto correspondiente a la búsqueda de la página destino.
	 */
	public static void clickOption(WebDriver driver, String textOption, String criterio, String textoDestino) {
		// CLickamos en la opción de registro y esperamos a que se cargue el enlace de Registro.
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "@href", textOption,
				getTimeout());
		// Tiene que haber un sólo elemento.
		assertTrue(elementos.size() == 1);
		// Ahora lo clickamos
		elementos.get(0).click();
		// Esperamos a que sea visible un elemento concreto
		elementos = SeleniumUtils.EsperaCargaPagina(driver, criterio, textoDestino, getTimeout());
		// Tiene que haber un sólo elemento.
		assertTrue(elementos.size() == 1);
	}
	
}