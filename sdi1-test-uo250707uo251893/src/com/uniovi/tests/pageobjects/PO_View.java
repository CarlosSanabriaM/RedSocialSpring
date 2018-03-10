package com.uniovi.tests.pageobjects;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.uniovi.tests.util.SeleniumUtils;

public class PO_View {
	
	protected static PO_Properties p = new PO_Properties("messages");
	protected static int timeout = 2;

	public static int getTimeout() {
		return timeout;
	}

	public static void setTimeout(int timeout) {
		PO_View.timeout = timeout;
	}

	public static PO_Properties getP() {
		return p;
	}

	public static void setP(PO_Properties p) {
		PO_View.p = p;
	}
	
	/**
	 * Espera por la visibilidad de un texto correspondiente a la propiedad key 
	 * en el idioma locale en la vista actualmente cargandose en driver..
	 * 
	 * @param driver: apuntando al navegador abierto actualmente.
	 * @param key: clave del archivo de propiedades.
	 * @param locale: Retorna el índice correspondient al idioma. 0 p.SPANISH y 1 p.ENGLISH.
	 * @return Se retornará la lista de elementos resultantes de la búsqueda.
	 */
	static public List<WebElement> checkKey(WebDriver driver, String key, int locale) {
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString(key, locale), getTimeout());
		return elementos;
	}
	
	/**
	 *  Espera por la visibilidad de un elemento/s en la vista actualmente cargandose en driver..
	 * 
	 * @param driver: apuntando al navegador abierto actualmente.
	 * @param criterio: "id" or "class" or "text" or "@attribute" or "free". 
	 * 					Si el valor de criterio es free es una expresion xpath completa. 
	 * @param text: texto correspondiente al criterio.
	 * @return Se retornará la lista de elementos resultantes de la búsqueda.
	 */
	static public List<WebElement> checkElement(WebDriver driver, String criterio, String text) {
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, criterio, text, getTimeout());
		return elementos;		
	}
	
	/**
	 * Clicka en un enlace (a href) y comprueba que se vaya a una vista
	 * en la que haya un texto correspondiente al criterio.
	 * 
	 * @param driver: apuntando al navegador abierto actualmente.
	 * @param idLink: id del elemento html "a" que se va a clickar
	 * @param criterio: "id" or "class" or "text" or "@attribute" or "free". Si el valor
	 *           		de criterio es free es una expresion xpath completa.
	 * @param textoDestino: texto correspondiente al criterio.
	 */
	public static void clickLinkAndCheckElement(WebDriver driver, String idLink, String criterio, String textoDestino) {
		// Obtenemos el elemento "a" que tiene el id indicado
		List<WebElement> elementos = PO_View.checkElement(driver, "id", idLink);
		// Tiene que haber un sólo elemento.
		assertTrue(elementos.size() == 1);
		// Ahora lo clickamos
		elementos.get(0).click();
		
		// Esperamos a que sea visible un elemento concreto
		elementos = PO_NavView.checkElement(driver, criterio, textoDestino);
		// Tiene que haber un sólo elemento.
		assertTrue(elementos.size() == 1);
	}
	
}
