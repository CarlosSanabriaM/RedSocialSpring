package com.uniovi.tests.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
	
}