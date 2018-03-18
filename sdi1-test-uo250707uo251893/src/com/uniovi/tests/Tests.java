package com.uniovi.tests;

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
import com.uniovi.tests.pageobjects.PO_LoginView;
import com.uniovi.tests.pageobjects.PO_PrivateView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_SignupView;
import com.uniovi.tests.pageobjects.PO_View;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Tests {
	
	// Descomentar uno de los dos paths en función del SO:
	//static String PathFirefox = "C:\\Users\\Alex\\Desktop\\sts-bundle\\Firefox46.win\\FirefoxPortable.exe";  // Windows
	static String PathFirefox = "/Applications/Firefox_46.0.app/Contents/MacOS/firefox-bin"; 			   // Mac
	
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
	private static String user2Email = "user2@gmail.com";
	private static String user2Password = "1234";
	
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
		PO_PrivateView.clickLinkAndCheckElement(driver, "aAdminRestart", "text", "Base de datos reiniciada");
		
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
		PO_PrivateView.logoutAndCheckWasOk(driver);
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
		
		PO_PrivateView.logoutAndCheckWasOk(driver);
	}
	
	
	/**
	 * 2.2 [InInVal] Inicio de sesión con datos inválidos (usuario no existente en la aplicación).
	 */
	@Test
	public void PR04() {
		PO_LoginView.goToLoginFillFormAndCheckWasWrong(driver, 
				"notExists@gmail.com", "123456", PO_Properties.getSPANISH());
	}

	/**
	 * 3.1 [LisUsrVal] Acceso al listado de usuarios desde un usuario en sesión.
	 */
	@Test
	public void PR05() {
		// Iniciamos sesión, pinchamos en "Usuarios" -> "Ver Todos" en el menú de navegación
		// (para asegurarnos de que dicho enlace también funciona, aunque ya estemos en dicho listado)
		// y comprobamos que aparece el texto "Todos los usuarios"
		PO_LoginView.goToLoginFillFormAndCheckWasOk(driver, user1Email, user1Password);
		
		PO_PrivateView.clickDropdownMenuOptionAndCheckElement(driver, 
				"aDropdownUsersMenu", "aUserList", "text", "Todos los usuarios");
		
		PO_PrivateView.logoutAndCheckWasOk(driver);
	}
	
	/**
	 * 3.2 [LisUsrInVal] Intento de acceso con URL desde un usuario no identificado al listado 
	 * de usuarios desde un usuario en sesión. Debe producirse un acceso no permitido a vistas privadas.
	 */
	@Test
	public void PR06() {
		// Acceder al listado de usuarios sin estar logeados nos lleva a la página de login.
		driver.navigate().to("http://localhost:8090/user/list");
		PO_View.checkElement(driver, "text", "Identifícate");
	}

	/**
	 * 4.1 [BusUsrVal] Realizar una búsqueda valida en el listado de usuarios desde un usuario en sesión.
	 */
	@Test
	public void PR07() {
		PO_LoginView.goToLoginFillFormAndCheckWasOk(driver, user1Email, user1Password);
		
		// Realizamos una busqueda por el texto "Mar" y comprobamos que 
		// sólo salen dos usuarios, cuyos nombres son María y Marta
		PO_PrivateView.searchText(driver, "Mar");
		PO_PrivateView.checkNumUsers(driver, 2);
		PO_PrivateView.checkElement(driver, "text", "María");
		PO_PrivateView.checkElement(driver, "text", "Marta");
		
		PO_PrivateView.logoutAndCheckWasOk(driver);
	}
	
	/**
	 * 4.2 [BusUsrInVal] Intento de acceso con URL a la búsqueda de usuarios desde 
	 * un usuario no identificado. Debe producirse un acceso no permitido a vistas privadas.
	 */
	@Test
	public void PR08() {
		// Acceder a la busqueda de usuarios sin estar logeados nos lleva a la página de login.
		driver.navigate().to("http://localhost:8090/user/list?searchText=Mar");
		PO_View.checkElement(driver, "text", "Identifícate");
	}
	
	/**
	 * 5.1 [InvVal] Enviar una invitación de amistad a un usuario de forma valida.
	 */
	@Test
	public void PR09() {
		// Iniciar sesión como user1 y mandar una invitación de amistad a user2
		PO_LoginView.goToLoginFillFormAndCheckWasOk(driver, user1Email, user1Password);
		PO_PrivateView.sendInvitation(driver, "user2@gmail.com");
		PO_PrivateView.logoutAndCheckWasOk(driver);
		
		// Iniciar sesión como user2 y comprobar que tenemos una invitación de Pedro (nombre de user1)
		PO_LoginView.goToLoginFillFormAndCheckWasOk(driver, user2Email, user2Password);
		PO_PrivateView.clickDropdownMenuOptionAndCheckElement(driver, 
				"aDropdownUsersMenu", "aUserFriendRequestList", "text", "Pedro");
		PO_PrivateView.logoutAndCheckWasOk(driver);
	}
	
	/**
	 * 5.2 [InvInVal] Enviar una invitación de amistad a un usuario al que ya le habíamos 
	 * invitado la invitación previamente. No debería dejarnos enviar la invitación, 
	 * se podría ocultar el botón de enviar invitación o notificar que ya había sido enviada previamente.
	 */
	@Test
	public void PR10() {
		PO_LoginView.goToLoginFillFormAndCheckWasOk(driver, user1Email, user1Password);
		
		PO_PrivateView.checkCantSendInvitation(driver, "user2@gmail.com");
		
		PO_PrivateView.logoutAndCheckWasOk(driver);
	}
	
	/**
	 * 6.1 [LisInvVal] Listar las invitaciones recibidas por un usuario, realizar 
	 * la comprobación con una lista que al menos tenga una invitación recibida.
	 */
	@Test
	public void PR11() {
		PO_LoginView.goToLoginFillFormAndCheckWasOk(driver, user1Email, user1Password);
		PO_PrivateView.clickDropdownMenuOptionAndCheckElement(driver, 
				"aDropdownUsersMenu", "aUserFriendRequestList", "text", "Nombre7");
		PO_PrivateView.logoutAndCheckWasOk(driver);
	}
	
	/**
	 * 7.1 [AcepInvVal] Aceptar una invitación recibida.
	 */
	@Test
	public void PR12() {
		PO_LoginView.goToLoginFillFormAndCheckWasOk(driver, user1Email, user1Password);
		
		PO_PrivateView.clickDropdownMenuOptionAndCheckElement(driver, 
				"aDropdownUsersMenu", "aUserFriendRequestList", "text", "Nombre7");
		PO_PrivateView.acceptInvitationAndCheckWasOk(driver, "Nombre7 Apellido7");
		
		PO_PrivateView.logoutAndCheckWasOk(driver);
	}
	
	/**
	 * 8.1 [ListAmiVal] Listar los amigos de un usuario, realizar la 
	 * comprobación con una lista que al menos tenga un amigo.
	 */
	@Test
	public void PR13() {
		PO_LoginView.goToLoginFillFormAndCheckWasOk(driver, user1Email, user1Password);
		
		PO_PrivateView.clickDropdownMenuOptionAndCheckElement(driver, 
				"aDropdownUsersMenu", "aUserFriendList", "text", "Marta");
		
		PO_PrivateView.logoutAndCheckWasOk(driver);
	}
	
	/**
	 * 9.1 [PubVal] Crear una publicación con datos válidos. 
	 */
	@Test
	public void PR14() {
		// Iniciamos sesión, pinchamos en "Publicaciones" -> "Nueva Publicación" en el menú de navegación
		// y comprobamos que aparece el texto "Nueva publicación"
		PO_LoginView.goToLoginFillFormAndCheckWasOk(driver, user1Email, user1Password);
		
		PO_PrivateView.clickDropdownMenuOptionAndCheckElement(driver, 
				"aDropdownPostsMenu", "aPostAdd", "text", "Nueva publicación");
		
		// Esperamos a que cargue y rellenamos el formulario para crear una publicación
		String title = "Título nueva publicación";
		String text = "Texto nueva publicación";
		PO_PrivateView.fillFormAddPost(driver, title, text);
		
		// Nos envia directamente al listado de publicaciones del usuario, 
		// así que buscamos el titulo de la nueva publicación que hemos creado
		PO_PrivateView.checkElement(driver, "text", title);
		
		PO_PrivateView.logoutAndCheckWasOk(driver);
	}
	/**
	 * 10.1 [LisPubVal] Acceso al listado de publicaciones desde un usuario en sesión.
	 */
	@Test
	public void PR15() {
		// Iniciamos sesión, pinchamos en "Publicaciones" -> "Ver mis Publicaciones" 
		// en el menú de navegación y comprobamos que aparece el texto "Mis publicaciones"
		PO_LoginView.goToLoginFillFormAndCheckWasOk(driver, user2Email, user2Password);
		
		PO_PrivateView.clickDropdownMenuOptionAndCheckElement(driver, 
				"aDropdownPostsMenu", "aPostList", "text", "Mis publicaciones");
				
		// Comprobamos tambien que el usuario user2 tiene 4 publicaciones
		PO_PrivateView.checkNumPosts(driver, 4);
		
		PO_PrivateView.logoutAndCheckWasOk(driver);
	}
	
	/**
	 * 11.1 [LisPubAmiVal] Listar las publicaciones de un usuario amigo 
	 */
	@Test
	public void PR16() {
		PO_LoginView.goToLoginFillFormAndCheckWasOk(driver, user1Email, user1Password);
		
		// Comprobamos que se accede correctamente al listado de publicaciones Marta,
		// que es amiga de user1
		PO_PrivateView.listFriendPostsAndCheckWasOk(driver, "Marta Almonte");
		
		// Comprobamos también que Marta tiene 4 publicaciones
		PO_PrivateView.checkNumPosts(driver, 4);
		
		PO_PrivateView.logoutAndCheckWasOk(driver);
	}

	/**
	 * 11.2 [LisPubAmiInVal] Utilizando un acceso vía URL tratar de listar 
	 * las publicaciones de un usuario que no sea amigo del usuario identificado en sesión.
	 */
	@Test
	public void PR17() {
		PO_LoginView.goToLoginFillFormAndCheckWasOk(driver, user1Email, user1Password);
		
		driver.navigate().to("http://localhost:8090/post/list/4");
		PO_View.checkElement(driver, "text", "¡Se ha producido un error!");
		PO_PrivateView.clickLinkAndCheckElement(driver, "aIndex", "text", "¡Bienvenidos a Red Social!");
		
		PO_PrivateView.logoutAndCheckWasOk(driver);
	}	
	
	/**
	 * 12.1 [PubFot1Val] Crear una publicación con datos válidos y una foto adjunta.
	 */
	@Test
	public void PR18() {
		// Iniciamos sesión, pinchamos en "Publicaciones" -> "Nueva Publicación" en el menú de navegación
		// y comprobamos que aparece el texto "Nueva publicación"
		PO_LoginView.goToLoginFillFormAndCheckWasOk(driver, user1Email, user1Password);
		
		PO_PrivateView.clickDropdownMenuOptionAndCheckElement(driver, 
				"aDropdownPostsMenu", "aPostAdd", "text", "Nueva publicación");
		
		// Esperamos a que cargue y rellenamos el formulario para crear una publicación con una foto adjunta
		String title = "Título nueva publicación 2";
		String text = "Texto nueva publicación 2";

		PO_PrivateView.fillFormAddPostWithImage(driver, title, text);
		
		// Nos envia directamente al listado de publicaciones del usuario, 
		// así que buscamos el titulo de la nueva publicación que hemos creado
		// y comprobamos que tiene una imágen
		PO_PrivateView.checkElement(driver, "text", title);
		PO_PrivateView.checkElement(driver, "free", "//img[contains(@alt, 'Imagen')]");
		
		PO_PrivateView.logoutAndCheckWasOk(driver);
	}
	
	/**
	 * 12.1 [PubFot2Val] Crear una publicación con datos válidos y sin una foto adjunta.
	 */
	@Test
	public void PR19() {
		// Iniciamos sesión, pinchamos en "Publicaciones" -> "Nueva Publicación" en el menú de navegación
		// y comprobamos que aparece el texto "Nueva publicación"
		PO_LoginView.goToLoginFillFormAndCheckWasOk(driver, user1Email, user1Password);
		
		PO_PrivateView.clickDropdownMenuOptionAndCheckElement(driver, 
				"aDropdownPostsMenu", "aPostAdd", "text", "Nueva publicación");
		
		// Esperamos a que cargue y rellenamos el formulario para crear una publicación
		String title = "Título nueva publicación 3";
		String text = "Texto nueva publicación 3";
		PO_PrivateView.fillFormAddPost(driver, title, text);
		
		// Nos envia directamente al listado de publicaciones del usuario, 
		// así que buscamos el titulo de la nueva publicación que hemos creado
		PO_PrivateView.checkElement(driver, "text", title);
		
		PO_PrivateView.logoutAndCheckWasOk(driver);
	}
	
	/**
	 * 13.1 [AdInVal] Inicio de sesión como administrador con datos válidos. 
	 */
	@Test
	public void PR20() {
		PO_AdminLoginView.goToAdminLoginFillFormAndCheckWasOk(driver, adminEmail, adminPassword);
		PO_PrivateView.logoutAndCheckWasOk(driver);
	}
	
	/**
	 * 13.2 [AdInInVal] Inicio de sesión como administrador con datos inválidos 
	 * (usar los datos de un usuario que no tenga perfil administrador).
	 */
	@Test
	public void PR21() {
		PO_AdminLoginView.goToAdminLoginFillFormAndCheckWasWrong(
				driver, user1Email, user1Password, "Error.admin.login.role" ,PO_Properties.getSPANISH());
	}
	
	/**
	 * 14.1 [AdLisUsrVal] Desde un usuario identificado en sesión como 
	 * administrador listar a todos los usuarios de la aplicación. 
	 */
	@Test
	public void PR22() {
		PO_AdminLoginView.goToAdminLoginFillFormAndCheckWasOk(driver, adminEmail, adminPassword);
		
		PO_PrivateView.clickDropdownMenuOptionAndCheckElement(driver, 
				"aDropdownUsersMenu", "aUserList", "text", "Todos los usuarios");

		PO_PrivateView.logoutAndCheckWasOk(driver);
	}
	
	/**
	 * 15.1 [AdBorUsrVal] Desde un usuario identificado en sesión como 
	 * administrador eliminar un usuario existente en la aplicación.  
	 */
	@Test
	public void PR23() {
		PO_AdminLoginView.goToAdminLoginFillFormAndCheckWasOk(driver, adminEmail, adminPassword);
		
		// Eliminamos al user6 y comprobamos que ya no aparece y nos deslogeamos como administradores
		PO_PrivateView.deleteUserAndCheckWasOk(driver, "user5@gmail.com");
		PO_PrivateView.logoutAndCheckWasOk(driver);
		
		// Nos conectamos como user1, que era amigo de user6, 
		// y comprobamos que ya no aparece en su listado de amigos
		PO_LoginView.goToLoginFillFormAndCheckWasOk(driver, user1Email, user1Password);
		PO_PrivateView.checkUserIsNotFriend(driver, "user5@gmail.com");
		
		PO_PrivateView.logoutAndCheckWasOk(driver);
	}
	
	/**
	 * 15.2 [AdBorUsrInVal] Intento de acceso vía URL al borrado de un usuario existente en la aplicación. 
	 * Debe utilizarse un usuario identificado en sesión pero que no tenga perfil de administrador. 
	 */
	@Test
	public void PR24() {
		PO_LoginView.goToLoginFillFormAndCheckWasOk(driver, user1Email, user1Password);
		
		driver.navigate().to("http://localhost:8090/user/delete/2");
		PO_View.checkElement(driver, "text", "¡Se ha producido un error!");
		PO_PrivateView.clickLinkAndCheckElement(driver, "aIndex", "text", "¡Bienvenidos a Red Social!");
		
		PO_PrivateView.logoutAndCheckWasOk(driver);
	}

}
