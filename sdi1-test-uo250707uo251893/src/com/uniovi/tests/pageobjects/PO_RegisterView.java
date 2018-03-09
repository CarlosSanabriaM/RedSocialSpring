package com.uniovi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_RegisterView extends PO_NavView {

	/**
	 * Espera a que se cargue el formulario y lo rellena
	 * @param driver
	 * @param dnip
	 * @param namep
	 * @param lastnamep
	 * @param passwordp
	 * @param passwordconfp
	 */
	static public void fillForm(WebDriver driver, String dnip, String namep, 
			String lastnamep, String passwordp, String passwordconfp) {
		
		//Si ya está cargado no espera, y si no está cargado espera a que se cargue
		checkElement(driver, "class", "btn"); // espero porque se cargue el formulario (el boton es lo ultimo que se carga!)
		
		WebElement dni = driver.findElement(By.name("dni"));
		dni.click();
		dni.clear();
		dni.sendKeys(dnip);
		
		WebElement name = driver.findElement(By.name("name"));
		name.click();
		name.clear();
		name.sendKeys(namep);
		
		WebElement lastname = driver.findElement(By.name("lastName"));
		lastname.click();
		lastname.clear();
		lastname.sendKeys(lastnamep);
		
		WebElement password = driver.findElement(By.name("password"));
		password.click();
		password.clear();
		password.sendKeys(passwordp);
		
		WebElement passwordConfirm = driver.findElement(By.name("passwordConfirm"));
		passwordConfirm.click();
		passwordConfirm.clear();
		passwordConfirm.sendKeys(passwordconfp);
		
		// Pulsar el boton de Alta.
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}
	
	/**
	 * Espera a que se cargue el formulario, lo rellena y comprueba que se produce el error indicado en el idioma indicado
	 * @param driver
	 * @param dnip
	 * @param namep
	 * @param lastnamep
	 * @param passwordp
	 * @param passwordconfp
	 * @param key
	 * @param language
	 */
	public static void fillFormAndCheckErrorKey(WebDriver driver, String dnip, String namep, 
			String lastnamep, String passwordp, String passwordconfp, String key, int language) {
		
		// Rellenamos el formulario de signup
		PO_RegisterView.fillForm(driver, dnip, namep, lastnamep, passwordp, passwordconfp);
		// COmprobamos que se da el error indicado
		PO_RegisterView.checkKey(driver, key, language);
	}
	
	/**
	 * Espera a que se cargue el formulario, lo rellena y comprueba que se autologea correctamente
	 * @param driver
	 * @param dnip
	 * @param namep
	 * @param lastnamep
	 * @param passwordp
	 * @param passwordconfp
	 */
	public static void fillFormAndCheckWasOk(WebDriver driver, String dnip, String namep, 
			String lastnamep, String passwordp, String passwordconfp) {
		
		// Esperamos a que cargue el formulario y lo rellenamos.
		PO_RegisterView.fillForm(driver, dnip, namep, lastnamep, passwordp, passwordconfp);
		// Comprobamos que entramos en la sección privada
		PO_View.checkElement(driver, "text", "Notas del usuario");
	}

	public static void goToSignup(WebDriver driver) {
		// Vamos al formulario de registro
		PO_HomeView.clickLinkAndCheckTextAppears(driver, "signup", "class", "btn btn-primary");
	}

}
