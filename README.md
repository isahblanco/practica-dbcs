# Práctica DBCS. Sistema de gestión de usuarios y pedidos basado en microservicios
Práctica de Desarrollo Basado en Componentes y Servicios. Uso de Angular, SpringBoot, Docker, MySQL y Maven para la creación de un servicio con autenticación y gestión de usuarios y gestión de pedidos.

Consiste en la gestión de una tienda
en la que el usuario debe iniciar sesión para así estar autenticado y poder gestionar tanto los
usuarios como los pedidos que se creen en el sistema.

El sistema cuenta con un API Gateway para la comunicación entre los microservicios 
entre sí y la WebApp(Angular) mediante KONG/KONGA, que debe estar configurado con las
correspondientes rutas y protocolos. Se hace uso de Docker para la creación y gestión de las imágenes y
contenedores. 

El Front-end está desarrollado en Angular y el Back-end en SpringBoot, 
la base de datos utilizada es MySQL. La autenticación de usuarios se realiza mediante Json Web Token(JWT).

## Docker containers
* Cliente Angular.
* Base de datos MySQL de usuarios.
* Base de datos MySQL de pedidos.
* API de gestión de usuarios.
* API de autenticación de usuarios.
* API de gestión de pedidos.

## Funcionalidades

* Inicio y cierre de sesión.
* Creación y eliminación de usuarios.
* Edición de los datos del usuario.
* Filtrar los usuarios por estado de habilitado.
* Habilitar varios usuarios a la vez.
* Buscar usuarios por correo electrónico.
* Creación y eliminación de pedidos.
* Edición de los datos y productos del pedido.

## Puesta en marcha

Primero, comprobar credenciales (MYSQL_ROOT_PASSWORD, MYSQL_USER, MYSQL_PASSWORD) para la conexión con la base de datos en el fichero "docker-compose.yml". Desde el terminal ir al directorio practica-dbcs/ y ejecutar el comando **docker-compose**.

Para tener un usuario con el que hacer login, desde una extensión de cliente REST como RESTClient de Mozilla Firefox, añadir un nuevo 
usuario de prueba mediante una operación POST a http://localhost:8080/users con la siguiente estructura en el cuerpo de la petición:
	
		{
			"name": "Paco412",
			"firstName": "Paco",
			"lastName": "Marcones",
			"email": "paco@hotmail.com",
			"password": "pacomarcones",
			"enabled": true,
			"createdAt": ""
		}
    
Para introducir productos en la base de datos, se hace lo mismo que antes pero a http://localhost:8082/orders/products con esta estructura en el cuerpo de la petición:
  
		{
			"sku": "6464SA",
			"name": "Silla ranita",
			"price": 100.00
		}

Después, para configurar KONG/KONGA, ir a la URL http://localhost:1337 y registrarse. En http://localhost:1337/#!/dashboar marcando la opción DEFAULT, completamos los campos **Name:** Kong y **Kong Admin URL:** http://kong:8001.

Una vez hecho esto, en la pestaña **SERVICES** creamos cada servicio de la siguente manera.

Para GestorUsuarios-API:
 - Name: GestorUsuarios-API
 - URL: http://GestorUsuarios-API:8080/users
 
Para AutenticacionUsuarios-API:
  - Name: AutenticacionUsuarios-API
  - URL: http://AutenticacionUsuarios-API:8081/login
  
Para Pedidos-API:
  - Name: Pedidos-API
  - URL: http://Pedidos-API:8082/orders
  
En la pestaña **ROUTES**, crear las siguientes rutas. 

Para el servicio GestorUsuarios-API:
 - Paths: /api/gestor
 - Methods: GET, POST, PUT, DELETE, OPTIONS
 - Protocols: http
 
Para el servicio AutenticacionUsuarios-API:
 - Paths: /api/autenticar
 - Methods: POST, OPTIONS
 - Protocols: http
  
Para Pedidos-API:
 - Paths: /api/pedidos
 - Methods: GET, POST, PUT, DELETE, OPTIONS
 - Protocols: http
 
A continuación, en la pestaña **CONSUMERS** se crea uno de JWT. El "secret" y "key" que genere deben actualizarse en el fichero
practica-dbcs/userAuthentication/src/main/resources/application.properties de la siguente manera:
 
 - jwt.secret=*nuevo secret generado*
 - jwt.kid=*nuevo key generado*
 
y se asigna el consumer como plugin a las rutas **/api/gestor** y **/api/pedidos**.
Finalmente, se crea otro consumer más, **CORS**, con alcance global y la aplicación queda totalmente configurada para usar. Desde un navegador web, escribir la url http://localhost:4200/ o escribir localhost:4200 e iniciar sesión con el email y contraseña del usuario que se introdujo anteriormente.

