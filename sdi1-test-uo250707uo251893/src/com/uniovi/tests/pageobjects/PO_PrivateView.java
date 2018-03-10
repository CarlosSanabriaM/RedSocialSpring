package com.uniovi.tests.pageobjects;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.uniovi.tests.util.SeleniumUtils;

public class PO_PrivateView extends PO_NavView {
	
	/**
	 * Hacemos click en el botón de Desconectarse y vemos como va al formulario de Login
	 * @param driver
	 */
	public static void logoutAndCheckWasOk(WebDriver driver) {
		clickLinkAndCheckElement(driver, "aLogout", "text", "Identifícate");
	}
	
	/**
	 * Rellena el formulario de crear una publicación
	 * @param driver
	 * @param titlep
	 * @param textp
	 */
	public static void fillFormAddPost(WebDriver driver, String titlep, String textp) {
		//Si ya está cargado no espera, y si no está cargado espera a que se cargue
		checkElement(driver, "id", "buttonSubmit");
		
		// Rellenemos el campo de "Título"
		WebElement title = driver.findElement(By.name("title"));
		title.click();
		title.clear();
		title.sendKeys(titlep);
		
		// Rellenemos el campo "Texto"
		WebElement text = driver.findElement(By.name("text"));
		text.click();
		text.clear();
		text.sendKeys(textp);
		
		By boton = By.id("buttonSubmit");
		driver.findElement(boton).click();
	}
	
	/**
	 * Comprueba que el numero de usuarios en la vista actual coincida con el indicado
	 * @param driver
	 * @param numUsers
	 */
	public static void checkNumUsers(WebDriver driver, int numUsers) {
		List<WebElement> elementos = checkElement(driver, "free", "//tbody/tr");
		assertTrue(elementos.size() == numUsers);
	}

	public static void checkMarkDetails(WebDriver driver, String markDescription) {// TODO . modificar!!
		By enlace = By.xpath("//td[contains(text(), '"+ markDescription +"')]/following-sibling::*[2]");
		driver.findElement(enlace).click();		
		// Esperamos por la ventana de detalle
		checkElement(driver, "text", "Detalles de la nota");
	}
	
//	/**
//	 * Clicka en un dropdown menu de los del navbar y luego clicka
//	 * en una opción de dicho menu con el atributo href indicado por textOption
//	 * 
//	 * @param driver: apuntando al navegador abierto actualmente..
//	 * @param idDropdownMenu: id del dropdown menu que se va a clickar (en realidad, del elemento li
//	 * 			padre del enlace que hay que clickar)
//	 * @param textOption: Texto del href de la opcion del dropdown menu que se quiere clickar
//	 */
//	public static void clickDropdownMenuOption(WebDriver driver, String idDropdownMenu, String hrefMenuOption) {// TODO ELIMINAR???
//		//Clickamos el dropdown menu indicado
//		List<WebElement> elementos = 
//				checkElement(driver, "id", idDropdownMenu);
//		elementos.get(0).click();
//		
//		// Esperamos a aparezca la opción indicada:
//		elementos = checkElement(driver, "@href", hrefMenuOption);
//		// Pinchamos en ella
//		elementos.get(0).click();
//	}
	
//	public static void clickNavigationMenuOption(WebDriver driver, int page) {// TODO ELIMINAR???
//		// Esperamos a que se muestren los enlaces de paginación
//		List<WebElement> elementos = checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
//		// Nos vamos a la pagina indicada
//		elementos.get(page).click();
//	}

	/**
	 * Pulsa el botón de eliminar del usuario con el email indicado, siempre que 
	 * se esté en la vista que tiene la lista de usuarios para el administrador y 
	 * dicho email aparezca en la página actual
	 * @param driver
	 * @param userEmail: email del usuario a eliminar
	 */
	public static void deleteUser(WebDriver driver, String userEmail) {
		// Buscamos una celda que contenga el email indicado. 
		// La celda siguiente de la misma fila contendrá el botón de eliminar, así que lo clickamos.
		List<WebElement> elementos = checkElement(driver, "free",
				"//td[contains(text(), '"+ userEmail +"')]/following-sibling::td/div/button");
		elementos.get(0).click();
	}
	
	/**
	 * Elimina el usuario con el email indicado, y comprueba que ya no aparece
	 * su email en la vista actual (que se ha eliminado correctamente)
	 * @param driver
	 * @param userEmail: email del usuario a eliminar
	 */
	public static void deleteUserAndCheckWasOk(WebDriver driver, String userEmail) {
		deleteUser(driver, userEmail);
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, userEmail, getTimeout());
	}

	/**
	 * Estando en la página /user/list, realiza la búsqueda del texto indicado
	 * @param driver
	 * @param searchText: texto a buscar en el listado de usuarios
	 */
	public static void searchText(WebDriver driver, String searchText) {
		// Obtenemos el input donde se introduce el texto a buscar
		List<WebElement> elementos = PO_PrivateView.checkElement(driver, "id", "inputSearchText");
		WebElement inputST = elementos.get(0); 
		
		// Introducimos el texto a buscar
		inputST.click();
		inputST.clear();
		inputST.sendKeys(searchText);

		// Pulsamos el boton de enviar para realizar la búsqueda
		By boton = By.id("buttonSearchText");
		driver.findElement(boton).click();
	}

	/**
	 * Comprueba que el numero de posts en la vista actual coincida con el indicado
	 * @param driver
	 * @param numPosts
	 */
	public static void checkNumPosts(WebDriver driver, int numPosts) {
		// Cada post está dentro de un div con class="panel panel-default"
		List<WebElement> elementos = checkElement(driver, "class", "panel panel-default");
		assertTrue(elementos.size() == numPosts);
	}

}