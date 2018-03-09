package com.uniovi.tests;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.uniovi.tests.pageobjects.PO_AdminLoginView;
import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_LoginView;
import com.uniovi.tests.pageobjects.PO_PrivateView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_SignupView;
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.tests.util.SeleniumUtils;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NotaneitorTests {
	
	// Descomentar uno de los dos paths en función del SO:
//	static String PathFirefox = "C:\\Path\\FirefoxPortable.exe"; 								// Windows
	static String PathFirefox = "/Applications/Firefox_46.0.app/Contents/MacOS/firefox-bin"; 	// Mac
	
	static WebDriver driver = getDriver(PathFirefox);
	static String URL = "http://localhost:8090";

	public static WebDriver getDriver(String PathFirefox) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}
	
	// Credenciales de inicio de sesión de varios usuarios
	private static String adminEmail = "admin@gmail.com";
	private static String adminPassword = "1234";
	
	private static String user1Email = "user1@gmail.com";
	private static String user1Password = "1234";
	
	/**
	 * Antes de cada prueba se navega al URL home de la aplicaciónn
	 */
	@Before
	public void setUp() {
		driver.navigate().to(URL);
	}

	/**
	 * Después de cada prueba se borran las cookies del navegador
	 */
	@After
	public void tearDown() {
		driver.manage().deleteAllCookies();
	}

	/**
	 * Antes de la primera prueba entramos como administrador y 
	 * reiniciamos la BD con los usuarios de prueba
	 */
	@BeforeClass
	static public void begin() {
		driver.navigate().to(URL);
		
		// Entramos como administrador y pinchamos en la opción de Reiniciar BD
		PO_AdminLoginView.goToAdminLoginFillFormAndCheckWasOk(driver, adminEmail, adminPassword);
		PO_PrivateView.clickLinkAndCheckSomethingAppears(driver, "aAdminRestart", "text", "Base de datos reiniciada");
		
		// Ahora nos desconectamos
		PO_PrivateView.logoutAndCheckWasOk(driver);
	}

	/**
	 * Al finalizar la última prueba cerramos el navegador
	 */
	@AfterClass
	static public void end() {
		driver.quit();
	}
	
	/**
	 * 1.1 [RegVal] Registro de Usuario con datos válidos.
	 */
	@Test
	public void PR01() {
		PO_SignupView.goToSignup(driver);
		PO_SignupView.fillFormAndCheckWasOk(driver, 
				"newUser@gmail.com", "NewUserName", "NewUserLastName", "1234", "1234");
	}
	
	/**
	 * 1.2 [RegInval] Registro de Usuario con datos inválidos (repetición de contraseña invalida).
	 */
	@Test
	public void PR02() {
		PO_SignupView.goToSignup(driver);
		PO_SignupView.fillFormAndCheckErrorKey(driver, 
				"newUser2@gmail.com", "NewUserName2", "NewUserLastName2", "1234", "12345", 
				"Error.signup.passwordConfirm.coincidence", PO_Properties.getSPANISH());
	}
	
	/**
	 * 2.1 [InVal] Inicio de sesión con datos válidos.
	 */
	@Test
	public void PR03() {
		PO_LoginView.goToLoginFillFormAndCheckWasOk(driver, user1Email, user1Password);
	}
	
	
	/**
	 * 2.2 [InInVal] Inicio de sesión con datos inválidos (usuario no existente en la aplicación).
	 */
	@Test
	public void PR04() {
		PO_LoginView.goToLoginFillFormAndCheckWasWrong(driver, 
				"notExists@gmail.com", "123456", PO_Properties.getSPANISH());
	}
	
/*	
	//PR01. Acceder a la página principal /
	@Test
	public void PR01() {
		PO_HomeView.checkWelcome(driver, PO_Properties.getSPANISH());
	}

	// PR02. OPción de navegación. Pinchar en el enlace Registro en la página home
	@Test
	public void PR02() {
		PO_HomeView.clickLinkAndCheckSomethingAppears(driver, "signup", "class", "btn btn-primary");
	}

	// PR03. OPción de navegación. Pinchar en el enlace Identificate en la página
	// home
	@Test
	public void PR03() {
		PO_HomeView.clickLinkAndCheckSomethingAppears(driver, "login", "class", "btn btn-primary");
	}
	
	// PR04. OPción de navegación. Cambio de idioma de Español a Ingles y vuelta a
	// Español
	@Test
	public void PR04() {
		PO_HomeView.checkChangeIdiom(driver, "btnSpanish", "btnEnglish", PO_Properties.getSPANISH(),
				PO_Properties.getENGLISH());
	}
	
	// PR05. Prueba del formulario de registro. registro con datos correctos
	@Test
	public void PR05() {
		PO_SignupView.goToSignup(driver);
		PO_SignupView.fillFormAndCheckWasOk(driver, "77777778A", "Josefo", "Perez", "77777", "77777");
	}
	
	//PR06. Prueba del formulario de registro. DNI repetido en la BD, Nombre corto, .... pagination
	// pagination-centered, Error.signup.dni.length
	@Test
	public void PR06() {
		// Vamos al formulario de registro
		PO_HomeView.clickLinkAndCheckSomethingAppears(driver, "signup", "class", "btn btn-primary");
		
		// Rellenamos el formulario y comprobamos el error de DNI repetido. 
		PO_SignupView.fillFormAndCheckErrorKey(driver, "99999990A", "Josefo", "Perez", "77777", "77777", 
				"Error.signup.dni.duplicate", PO_Properties.getSPANISH());
		
		// Rellenamos el formulario y comprobamos el error de Nombre corto
		PO_SignupView.fillFormAndCheckErrorKey(driver, "99999990B", "Jose", "Perez", "77777", "77777",  
				"Error.signup.name.length", PO_Properties.getSPANISH());
		
		// Rellenamos el formulario y comprobamos el error de Apellidos cortos
		PO_SignupView.fillFormAndCheckErrorKey(driver, "99999990B", "Josefo", "Per", "77777", "77777",   
				"Error.signup.lastName.length", PO_Properties.getSPANISH());
		
		// Rellenamos el formulario y comprobamos el error de Contraseña corta
		PO_SignupView.fillFormAndCheckErrorKey(driver, "99999990B", "Josefo", "Perez", "777", "777",   
				"Error.signup.password.length", PO_Properties.getSPANISH());

		// Rellenamos el formulario y comprobamos el error de Contraseña corta
		PO_SignupView.fillFormAndCheckErrorKey(driver, "99999990B", "Josefo", "Perez", "77777", "88888",   
				"Error.signup.passwordConfirm.coincidence", PO_Properties.getSPANISH());
	}

	// PR07. Loguearse con exito desde el ROLE de Estudiante, 99999990A, 123456
	@Test
	public void PR07() {
		PO_LoginView.goToLoginFillFormAndCheckWasOk(driver, "99999990A", "123456");
	}
	
	// PR08. Loguearse con exito desde el ROLE de Profesor, 99999993D, 123456
	@Test
	public void PR08() {
		PO_LoginView.goToLoginFillFormAndCheckWasOk(driver, "99999993D", "123456");
	}
	
	// PR09. Loguearse con exito desde el ROLE de Administrador, 99999988F, 123456
	@Test
	public void PR09() {
		PO_LoginView.goToLoginFillFormAndCheckWasOk(driver, "99999988F", "123456");
	}
	
	// PR07. Loguearse sin exito desde el ROLE de Estudiante, 99999990A, 111111 (contraseña mal)
	@Test
	public void PR10() {
		//PO_LoginView.goToLoginFillFormAndCheckWasWrong(driver, "99999990A", "111111");
	}
	
	// PR07. Loguearse con exito desde el ROLE de Estudiante y desconectarse, 99999990A, 123456
	@Test
	public void PR11() {
		PO_LoginView.goToLoginFillFormAndCheckWasOk(driver, "99999990A", "123456");
		
		// Ahora nos desconectamos
		PO_PrivateView.logoutAndCheckWasOk(driver);
	}
	
	// PR12. Loguearse, comprobar que se visualizan 4 filas de notas y desconectarse
	// usando el rol de estudiante.
	@Test
	public void PR12() {
		PO_LoginView.goToLoginFillFormAndCheckWasOk(driver, "99999990A", "123456");

		// Contamos el número de filas de notas (han de ser 4).
		PO_PrivateView.checkNumMarks(driver, 4);

		// Ahora nos desconectamos
		PO_PrivateView.logoutAndCheckWasOk(driver);
	}	
	
	//PR13. Loguearse como estudiante y ver los detalles de la nota con Descripcion = Nota A2.
	@Test
	public void PR13() {
		PO_LoginView.goToLoginFillFormAndCheckWasOk(driver, "99999990A", "123456");
		
		// Vemos los detalles de la nota 'Nota A2'
		PO_PrivateView.checkMarkDetails(driver, "Nota A2");

		// Ahora nos desconectamos
		PO_PrivateView.logoutAndCheckWasOk(driver);
	}
	
	// P14. Loguearse como profesor y Agregar Nota A2.
	@Test
	public void PR14() {
		PO_LoginView.goToLoginFillFormAndCheckWasOk(driver, "99999993D", "123456");
		
		//Pinchamos en la opción Añadir notas del navbar
		PO_PrivateView.clickDropdownMenuOption(driver, "marks-menu", "mark/add");
				
		// Ahora vamos a rellenar la nota.
		PO_PrivateView.fillFormAddPost(driver, 3, "Nota Nueva 1", "8");
		
		//Nos vamos a la última página de la paginación
		PO_PrivateView.clickNavigationMenuOption(driver, 3);
		// Comprobamos que aparece la nota en la pagina
		PO_View.checkElement(driver, "text", "Nota Nueva 1");
		
		// Ahora nos desconectamos
		PO_PrivateView.clickLinkAndCheckSomethingAppears(driver, "logout", "text", "Identifícate");
	}

	// PRN. Loguearse como profesor, vamos a la ultima página y Eliminamos la Nota
	// PRN. Ver la lista de Notas.
	@Test
	public void PR15() {
		PO_LoginView.goToLoginFillFormAndCheckWasOk(driver, "99999993D", "123456");
		
		//Pinchamos en la opción Listar notas del navbar
		PO_PrivateView.clickDropdownMenuOption(driver, "marks-menu", "mark/list");
		//Nos vamos a la última página de la paginación
		PO_PrivateView.clickNavigationMenuOption(driver, 3);
		// Esperamos a que aparezca la Nueva nota en la ultima pagina
		// Y Pinchamos en el enlace de borrado de la Nota "Nota Nueva 1"
		PO_PrivateView.deleteMark(driver, "Nota Nueva 1");
		
		// Volvemos a la última pagina (la paginacion tal cual esta nos lleva a la primera)
		PO_PrivateView.clickNavigationMenuOption(driver, 3);
		// Y esperamos a que NO aparezca la ultima "Nueva Nota 1"
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "Nota Nueva1", PO_View.getTimeout());
		
		// Ahora nos desconectamos
		PO_PrivateView.logoutAndCheckWasOk(driver);
	}
*/

}
