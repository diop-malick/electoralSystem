# Electoral systems Application for Proportional representation

Application de caclcul de résultats d'élections/Scrutin proportionnel plurinominal.

# Architecture

Client / serveur -> 4 Modules  :

	* net.webapp.electoralSystem-core			(DAO, métier)
	* net.webapp.electoralSystem-api			(REST web service)
	* net.webapp.electoralSystem-ui-web		(front web Spring MVC & thymeleaf)
	* net.webapp.electoralSystem-ui-desktop	(front Desktop Swing - Client Web pour le web service)
	* electoralSystem-ui-client-cors			(front web HTML/JS pour tester la Gestion des accès inter-domaines (CORS) )
	
# Stack Technique

* Plateform : JDK 1.8
* Object Lifecycle & IOC : Spring 4
* Test : JUnit
* DAO : Spring Data JPA / Hibernate
* Web Service : Spring MVC
* Security	: Spring Security
* UI : Spring MVC (web) / Swing (desktop)

# Outils utilisés

* IDE : Eclipse Mars (+ Spring Tools Suite)
* Application Server : Tomcat 7
* SCM : Git
* Build : Maven 3
* SGBD : WampServer (MySQL 5) - Workbench

# Link

* http://tahe.developpez.com/tutoriels-cours/intro-java-spring/serge-tahe-introduction-au-langage-java-et-a-l-ecosysteme-spring/
* https://onedrive.live.com/?authkey=%21APlXAeatN_9zGO4&id=f53a919f43d27eb5%2125280&cid=F53A919F43D27EB5

* cors configure
https://spring.io/guides/gs/rest-service-cors/
https://spring.io/blog/2015/06/08/cors-support-in-spring-framework
	
# accée :
api				:  localhost:8080
ui-client-cors	:  localhost:8081/client.html
	