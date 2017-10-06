# Magellan

*Modern Java EE example suite for WildFly*

## Introduction

Magellan is a basic application, created to support the article [Magellan - A clustering tutorial with WildFly, nginx, Scala(FX) and Gradle](http://gianlucacosta.info/wildfly-clustering); apart from that, its most valuable feature is perhaps the mix of modern technologies employed, demonstrating how they can find a natural place in the Java EE ecosystem.


## Technologies employed

* The elegant [Scala](http://scala-lang.org/)  programming language

* [ScalaFX](http://www.scalafx.org/), to create the rich user interface

* [Nginx](http://nginx.org/), to provide load balancing

* [Gradle](http://gradle.org/) to easily manage project dependencies and the build lifecycle for the many artifacts

* [MoonDeploy](https://github.com/giancosta86/moondeploy) to simplify client deployment


The JavaFX client relies on [WildFly](http://wildfly.org/)'s API, whereas the Java EE application, deployed via the **ear** archive, can run on any Java EE-compiliant server.


## Modules

* **magellan** is the root project, creating the **ear** by assembling the artifacts of its Java EE subprojects. It also builds the standalone JavaFX client.

* **magellan-web**: the subproject creating the **war** archive for the Web tier

* **magellan-ejb**: creates the *EJB-jar* artifacts

* **magellan-common**: creates a plain **jar** file containing the EJB interfaces - therefore, it is referenced by both the Java EE modules and the JavaFX client

* **magellan-app-client**: standalone JavaFX application connecting to the stateful EJB


# Running the project

The binary packages are available in the [releases area](https://github.com/giancosta86/magellan/releases/latest):

* the EAR archive can be deployed to WildFly or any Java EE server

* the JavaFX client application can be downloaded and run directly from within the browser, if [MoonDeploy](https://github.com/giancosta86/moondeploy) is installed: just click on **App.moondeploy** and open it with MoonDeploy


# Building the project

* Simply run **gradle build** to produce:

  * the EAR archive

  * the JAR of the JavaFX application


* To deploy the EAR to 2 different WildFly standalone instances (called *alpha* and *beta* in the reference tutorial), run:

  **gradle deploy -PalphaDeploymentDir="PATH_TO_ALPHA_DEPLOYMENT_DIR" -PbetaDeploymentDir="PATH_TO_BETA_DEPLOYMENT_DIR"**

  where a *deployment dir* is a **standalone/deployments** subdirectory in a WildFly root directory

* To run the JavaFX application, just execute **gradle run**, passing the 2 parameters described above



## Further references

* [Magellan - A clustering tutorial with WildFly, nginx, Scala(FX) and Gradle](http://gianlucacosta.info/wildfly-clustering)

* [MoonDeploy](https://github.com/giancosta86/moondeploy)
