# practica-dbcs
Proyecto de Desarrollo Basado en Componentes y Servicios. Uso de Angular, SpringBoot, Docker, MySQL y Maven para la creación de un servicio con autenticación y gestión de usuarios y gestión de pedidos.

## Sistema de gestión de usuarios y pedidos basado en microservicios.

Este proyecto consiste en la gestión de una tienda
en la que el usuario debe iniciar sesión para así estar autenticado y poder gestionar tanto los
usuarios como los pedidos que se crean en el sistema.

El sistema cuenta con un API Gateway para la comunicación entre los microservicios 
entre sí y la WebApp(Angular) mediante KONG/KONGA que debe estar configurado con las
correspondientes rutas y protocolos. Se hace uso de Docker para la creación de las imágenes y
contenedores. 

El Front-end está desarrollado con Angular y el Back-end con SpringBoot, 
la base de datos utilizada es MySQL. La autenticación de usuarios se realiza mediante JWT.

### Docker containers
* Cliente Angular.
* Base de datos MySQL de usuarios.
* Base de datos MySQL de pedidos.
* API de gestión de usuarios.
* API de autenticación de usuarios.
* API de gestión de pedidos.

### Funcionalidades

* Inicio y cierre de sesión.
* Creación y eliminación de usuarios.
* Edición de los datos del usuario.
* Filtrar los usuarios por estado de habilitado.
* Habilitar varios usuarios a la vez.
* Buscar usuarios por correo electrónico.
* Creación y eliminación de pedidos.
* Edición de los datos y productos del pedido.
