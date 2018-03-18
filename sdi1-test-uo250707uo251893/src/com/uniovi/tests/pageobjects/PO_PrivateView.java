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
	 */
	public static void logoutAndCheckWasOk(WebDriver driver) {
		clickLinkAndCheckElement(driver, "aLogout", "text", "Identifícate");
	}
	
	/**
	 * Rellena el formulario de crear una publicación con los datos indicados
	 * 
	 * @param driver: apuntando al navegador abierto actualmente. 
	 * @param titlep: valor a introducir en el campo title.
	 * @param textp: valor a introducir en el campo text.
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
	 */
	public static void checkNumUsers(WebDriver driver, int numUsers) {
		List<WebElement> elementos = checkElement(driver, "free", "//tbody/tr");
		assertTrue(elementos.size() == numUsers);
	}
	
	/**
	 * Comprueba que el numero de posts en la vista actual coincida con el indicado
	 * 
	 */
	public static void checkNumPosts(WebDriver driver, int numPosts) {
		// Cada post está dentro de un div con class="panel panel-default"
		List<WebElement> elementos = checkElement(driver, "class", "panel panel-default");
		assertTrue(elementos.size() == numPosts);
	}
	
	/**
	 * Estando en la página /user/list, realiza la búsqueda del texto indicado
	 * 
	 * @param driver: apuntando al navegador abierto actualmente. 
	 * @param searchText: texto a buscar en el listado de usuarios.
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
	 * Pulsa el botón de eliminar del usuario con el email indicado, siempre que 
	 * se esté en la vista que tiene la lista de usuarios para el administrador y 
	 * dicho email aparezca en la página actual
	 * 
	 * @param driver: apuntando al navegador abierto actualmente. 
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
	 * Va al listado de amigos y comprueba que no aparece el email indicado
	 * @param driver
	 * @param userEmail
	 */
	public static void checkUserIsNotFriend(WebDriver driver, String userEmail) {
		PO_PrivateView.clickDropdownMenuOptionAndCheckElement(driver, 
				"aDropdownUsersMenu", "aUserFriendList", "text", "Tus Amigos");
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, userEmail, getTimeout());
	}
	
	/**
	 * Manda una invitación de amistad al usuario con el email indicado
	 */
	public static void sendInvitation(WebDriver driver, String userEmail) {
		List<WebElement> elementos = PO_View.checkElement(driver, "free",
				"//td[contains(text(), '"+ userEmail +"')]/following-sibling::td/a[contains(@href, '/user/invitate/')]");
		elementos.get(0).click();
	}

}