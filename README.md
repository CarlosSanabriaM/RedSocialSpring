# Red Social (Spring Boot)
Este proyecto se trata de una aplicación web realizada para la asignatura "Sistemas Distribuidos e Internet". Ha sido desarrollada en Java, utilizando el framework Spring Boot. Para desarrollarla se utiliza un patrón Modelo-Vista-Controlador (MVC).


## Autores
* Carlos Sanabria Miranda (@CarlosSanabriaM)
* Alejandro Barrera Sánchez (@wason12)


## Contenido
- **La carpeta *red-social-spring*** contiene la aplicación web.
- **La carpeta *red-social-spring-test*** contiene un proyecto Java JUnit con una serie de pruebas unitarias, empleando el framework Selenium. Estas comprueban el correcto funcionamiento de la aplicación web.
- **La carpeta *hsqldb*** contiene la base de datos (vacía pero lista para usar y probar la aplicación).
- **El pdf *Instrucciones*** explica los requisitos necesarios que han de satisfacer tanto la aplicación como los tests.
- **El pdf *Documentacion*** explica cómo se han implementado la aplicación y los tests.


## Información de la Base de datos
Esta aplicación utiliza una base de datos HSQLDB, que genera una serie de ficheros con la información y el contenido de la BD. Ha sido utilizada debido a su sencillez. Toda la información relativa al funcionamiento de la BD y de sus directorios y ficheros se encuentra en el README de la carpeta hsqldb.

Resumiendo cómo arrancar la BD: Ejecutar el script runServer (en el caso de Windows es el que tiene extensión .bat y en el caso de Mac el que tiene extensión .sh).

Al ejecutar la aplicación, la base de datos será rellenada con datos de prueba. Cada vez que se ejecutan los tests, la base de datos es vaciada y rellenada con dichos datos de prueba.


## Cómo ejecutar la aplicación
Primero es necesario tener arrancada la base de datos, como se explica en el apartado anterior.

Hay varias formas para ejecutar, entre ellas:
1. **Desde línea de comandos:** Situarse en la carpeta *red-social-spring* desde la línea de comandos y ejecutar el comando: `mvn spring-boot:run`. Para parar la ejecución, pulsar `Control + C`.
2. **Utilizando el IDE Eclipse:** Importar el proyecto *red-social-spring* en un workspace. Hacer click derecho en el archivo *RedSocialSpringApplication.java* y seleccionar *Run as -> Java Application*.

Para visualizar la web, abrir un navegador y visitar la siguiente URL: http://localhost:8090/.


## Cómo ejecutar los tests
Para probar la aplicación se utiliza una versión antigua de un navegador Firefox. En concreto, la versión 46. Este Firefox en particular no guarda datos en caché, por lo que es perfecto para probar la aplicación mientras está en desarrollo.

Es necesaria una versión de Firefox 47 o inferior, debido a que a partir de la 48 Mozilla no permite utilizar Selenium.

Una vez tenemos dicho Firefox, hay que acceder al fichero `Tests.java` del proyecto *red-social-spring-test* y modificar la ruta donde se encuentra dicha versión de Firefox. Para ello, hay que cambiar la línea `static String PathFirefox = "/Applications/Firefox.app/Contents/MacOS/firefox-bin";` e indicar ahí la ruta.

Importar el proyecto en un workspace de Eclipse. Hacer click derecho en el archivo *Tests.java* y seleccionar *Run as -> JUnit Test*.
