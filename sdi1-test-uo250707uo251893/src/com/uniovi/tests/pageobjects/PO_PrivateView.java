package com.uniovi.tests.pageobjects;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class PO_PrivateView extends PO_NavView {
	
	/**
	 * Hacemos click en el botón de Desconectarse y vemos como va al formulario de Login
	 * @param driver
	 */
	public static void logoutAndCheckWasOk(WebDriver driver) {
		clickOption(driver, "logout", "text", "Identifícate");
	}
	
	public static void fillFormAddMark(WebDriver driver, 
			int userOrder, String descriptionp, String scorep) {
		
		//Si ya está cargado no espera, y si no está cargado espera a que se cargue
		checkElement(driver, "class", "btn"); // espero porque se cargue el formulario de añadir nota (el boton es lo ultimo que se carga!)
		
		// Seleccionamos el alumnos userOrder
		new Select(driver.findElement(By.id("user"))).selectByIndex(userOrder);
		
		// Rellenemos el campo de descripción
		WebElement description = driver.findElement(By.name("description"));
		description.click();
		description.clear();
		description.sendKeys(descriptionp);
		
		// Rellenemos el campo de puntuación
		WebElement score = driver.findElement(By.name("score"));
		score.click();
		score.clear();
		score.sendKeys(scorep);
		
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}
	
	public static void checkNumMarks(WebDriver driver, int numNotas) {
		List<WebElement> elementos = checkElement(driver, "free", "//tbody/tr");
		assertTrue(elementos.size() == numNotas);
	}

	public static void checkMarkDetails(WebDriver driver, String markDescription) {
		By enlace = By.xpath("//td[contains(text(), '"+ markDescription +"')]/following-sibling::*[2]");
		driver.findElement(enlace).click();		
		// Esperamos por la ventana de detalle
		checkElement(driver, "text", "Detalles de la nota");
	}
	
	/**
	 * Clicka en un dropdown menu de los del navbar y luego clicka
	 * en una opción de dicho menu con el atributo href indicado por textOption
	 * 
	 * @param driver: apuntando al navegador abierto actualmente..
	 * @param idDropdownMenu: id del dropdown menu que se va a clickar (en realidad, del elemento li
	 * 			padre del enlace que hay que clickar)
	 * @param textOption: Texto del href de la opcion del dropdown menu que se quiere clickar
	 */
	public static void clickDropdownMenuOption(WebDriver driver, String idDropdownMenu, String hrefMenuOption) {
		//Clickamos el dropdown menu indicado
		List<WebElement> elementos = 
				checkElement(driver, "free", "//li[contains(@id,'"+ idDropdownMenu +"')]/a");
		elementos.get(0).click();
		
		// Esperamos a que se despliegue (aparezca) el menú de opciones.
		//elementos = SeleniumUtils.EsperaCargaPagina(driver, "class", "dropdown-menu", getTimeout());
		
		// Esperamos a aparezca la opción indicada:
		elementos = checkElement(driver, "@href", hrefMenuOption);
		// Pinchamos en ella
		elementos.get(0).click();
	}
	
	public static void clickNavigationMenuOption(WebDriver driver, int page) {
		// Esperamos a que se muestren los enlaces de paginación
		List<WebElement> elementos = checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
		// Nos vamos a la pagina indicada
		elementos.get(page).click();
	}

	public static void deleteMark(WebDriver driver, String markDescription) {
		List<WebElement> elementos = checkElement(driver, "free",
				"//td[contains(text(), '"+ markDescription +"')]/following-sibling::*/a[contains(@href, 'mark/delete')]");
		elementos.get(0).click();
	}
	
//	public static void clickLastNavigationMenuOption(WebDriver driver) {
//		// Esperamos a que se muestren los enlaces de paginación
//		WebElement elemento = PO_View.checkElement(driver, "free", "//li[last()]/a[contains(@class, 'page-link')]").get(0);
//		// Nos vamos a la última página
//		elemento.click();
//	}
//	
//	public static void clickFirstNavigationMenuOption(WebDriver driver) {
//		// Esperamos a que se muestren los enlaces de paginación
//		WebElement elemento = PO_View.checkElement(driver, "free", "//li[first()]/a[contains(@class, 'page-link')]").get(0);
//		// Nos vamos a la primera página
//		elemento.click();
//	}
	
}