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
