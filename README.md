# electoral systems Application for Proportional representation

Application de caclcul de résultats d'élections/Scrutin proportionnel plurinominal

# Architecture

Client / serveur -> 4 Modules  :

	net.webapp.electoralSystem-core			(dao, métier)
	net.webapp.electoralSystem-api			(REST web service)
	net.webapp.electoralSystem-ui-web		(front web Sprng MVC)
	net.webapp.electoralSystem-ui-desktop		(front Desktop Swing)
	
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
	