#Instituto_BD
- Aplicación de escritorio para gestión de alumnos y consultas.

## Descripción

- Esta es una aplicación que permite gestionar una base de datos de un instituto. Permite la carga de datos de un nuevo alumno así como realizar consultas. Este proyecto fue hecho en base al patrón de diseño, Modelo Vista Controlador (MVC). Proporciona una interfaz gráfica intuitiva para navegar y buscar información específica sobre estudiantes, docentes y cursos. 
La aplicación ha sido desarrollada en Java utilizando JavaFX y Scene Builder para crear una interfaz gráfica atractiva y fácil de usar. La base de datos fue desarrollada en SQL utilizando MySQL.

##Requisitos previos

####Antes de ejecutar la aplicación, asegúrate de tener instalado lo siguiente:

- Java JDK 8 o superior en tu sistema.
- Eclipse IDE (https://eclipseide.org/)
- MySQL (https://dev.mysql.com/downloads/installer/)
- Los plugins y librerías de JavaFX necesarias para ejecutar la aplicación.

####Instalación de las herramientas necesarias para trabajar con JavaFX en eclipse  y realizar interfaces gráficas con SceneBuilder

- Descarga de los paquetes necesarios

- Descargar JavaFX-sdk desde: https://gluonhq.com/products/javafx/
Elegir adecuadamente el sistema operativo y la opción SDK

- Documentación de JAVAFX
https://openjfx.io/

- Guardar el archivo descomprimido en una carpeta bien conocida. Te recomiendo crear una carpeta en Documentos que se llame LibreriasEclipse

- Dentro de la carpeta creada descomprimir el archivo SDK descargado

#####Ahora vamos a eclipse: 
- Vamos a instalar un plugins para trabajar con JavaFX, abrimos el eclipseMarketplace

- Escribimos en el buscador JavaFX y go

- Instalamos la primera opción: e(fx)clipse

- Esperamos sin cerrar el programa (recuerden que sigue instalando en segundo plano)

#####Ahora vamos a instalar la librería que descargamos (javaFX-sdk) como una librería de usuario:

- Vamos a Windows/preferences y escribimos en el buscador "user"

- Elegimos la opción: user Libraries

- Damos new y escribimos JavaFX

- Ahora agregamos la librería que descargamos con add external JARs

- Buscamos en la carpeta LibreriasEclipse que creamos anteriormente y navegamos en la carpeta hasta encontrar la ruta src/lib

- Dentro de la carpeta lib seleccionamos todos los .jar y finalizamos

#####Vamos a descargar e instalar el programa para hacer interfaces gráficas

- Descargar SceneBuilder desde: https://gluonhq.com/products/scene-builder/

- Elegir adecuadamente el sistema operativo e instalarlo en la computadora.

- Abrir el archivo Sample.fxml desde eclipse

- Ir a windows/preferences

- Buscar la opción JavaFX

- En la pestaña SceneBuilder executable cargar la ruta del archivo .exe de SceneBuilder

- Para linux buscarlo en: 
Instalar con sudo dpkg -i archivo.deb
Buscar el instalador en: 
/opt/JavaFXSceneBuilder2.0/JavaFXSceneBuilder2.0

- Para windows buscarlo en: /programFile/…..
(C:\Users\Usuario\AppData\Local\SceneBuilder\SceneBuilder.exe) RUTA



#####Creación de un proyecto

- En eclipse vamos file/new/other

- Elegimos JavaFX proyect

- Damos nombre al programa y next, next

- Elegimos el lenguaje FXML

- Elegimos el tipo de panel: javaFx AnchorPane

#####Necesitamos decirle a Eclipse que vamos a utilizar la librería JavaFX como una librería de usuario en este proyecto (esto hay que hacerlo cada vez que creamos un proyecto)

- Parados sobre el proyecto hacemos botón derecho y elegimos Build Path y Configure Build Path

- Vamos a la pestaña Java Build Path y libraries
- Seleccionamos ClassPath
- Agregamos Add Library
- Seleccionamos user library
- Seleccionamos JavaFX y cerramos
- Movemos hacia arriba con el mouse la librería agregada a ModulePath y cerramos


####Herramientas para acceder a mySQL con Eclipse

- Debemos descargar el conector de mySQL con eclipse (libreria)
https://dev.mysql.com/get/Downloads/Connector-J/mysql-connector-java-5.1.47.zip

- Descomprimimos el archivo en la carpeta LIbreriasEclipse creada anteriormente

- Vamos a Windows/preferences y escribimos en el buscador "user"

- Elegimos la opción: user Libraries

- Damos new y escribimos SqlConector

- Ahora agregamos la librería que descargamos con add external JARs

- Buscamos en la carpeta LibreriasEclipse que creamos anteriormente y navegamos en la carpeta hasta encontrar la ruta /mysql-connector-java-5.1.47/

- Dentro de la carpeta seleccionamos todos los .jar y finalizamos.

#####Necesitamos decirle a Eclipse que vamos a utilizar la librería SqlConector como una librería de usuario en este proyecto (esto hay que hacerlo cada vez que creamos un proyecto)

- Parados sobre el proyecto hacemos botón derecho y elegimos Build Path y Configure Build Path

- Vamos a la pestaña Java Build Path y libraries

- Seleccionamos ClassPath

- Agregamos Add Library

- Seleccionamos user library

- Seleccionamos SqlConector y cerramos

- Movemos hacia arriba con el mouse la librería agregada a ModulePath y cerramos


###Bloque de consultas SQL

    
Consultar todos los datos de las tablas Alumnos, Docentes y Cursos

    SELECT * FROM instituto2.Alumnos;
    SELECT * FROM instituto2.Docentes; 
	SELECT * FROM instituto2.Cursos;

Listado de alumnos del curso Programación

	SELECT A.idAlumno, A.alumnoNombre, A.alumnoApellido
	FROM instituto2.Alumnos A
	INNER JOIN Alumno_Curso AC ON A.idAlumno = AC.idAlumno
	WHERE AC.idCurso = 1;

Cantidad de cursos del alumno Id 13

	SELECT A.alumnoNombre, A.alumnoApellido, COUNT(*) AS cantidad_cursos
	FROM instituto2.Alumno_Curso AC
	INNER JOIN Alumnos A ON A.idAlumno = AC.idAlumno
	WHERE AC.idAlumno = 13;

Cursos del alumno con Id 15

	SELECT C.idCurso, C.cursoNombre, A.alumnoApellido
	FROM instituto2.Cursos C
	INNER JOIN Alumno_Curso AC ON C.idCurso = AC.idCurso
	INNER JOIN Alumnos A ON AC.idAlumno = A.idAlumno
	WHERE AC.idAlumno = 15;

Curso del docente con DNI 95959595

	SELECT C.idCurso, C.cursoNombre, D.docenteApellido
	FROM instituto2.Cursos C
	INNER JOIN Docente_Curso DC ON C.idCurso = DC.idCurso
	INNER JOIN Docentes D ON DC.idDocente = D.idDocente
	WHERE D.docenteDNI = '95959595';

Datos de un alumno filtrado por DNI

	SELECT A.alumnoNombre, A.alumnoApellido, A.alumnoFechaDeIngreso, 	C.cursoNombre
	FROM instituto2.Alumnos A
	INNER JOIN Alumno_Curso AC ON A.idAlumno = AC.idAlumno
	INNER JOIN Cursos C ON AC.idCurso = C.idCurso
	WHERE A.alumnoDNI = '22222222';


###Autor
Cristian D. Burgos