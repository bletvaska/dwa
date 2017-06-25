# Damn Wulnerable Application


## Installation 

### Tomcat Installation

1. Download and install [Apache Tomcat](http://tomcat.apache.org/)

2. update content of element `<tomcat-users>` in file `TOMCAT/conf/tomcat-users.xml` with following elements:
    ```XML
    <role rolename="manager-gui"/>
    <role rolename="manager-script"/>
    <user name="admin" password="admin"  
        roles="manager-gui,manager-script" />
    ```
3. Test your installation:
    * start _Tomcat_ with `TOMCAT/bin/startup.sh`
    * with your browser open URL [http://localhost:8080](http://localhost:8080)
    * shutdown _Tomcat_ with `TOMCAT/bin/shutdown.sh`


### Maven Installation

1. Download and install [Apache Maven](https://maven.apache.org/)

2. Test the environment with:
    ```bash
    mvn --version
    ```


### Import project to IDE

_DWA_ is _Maven Project_, so you can use any IDE, which is able to work with _Maven_. Anyway - the preffered IDE is [IntelliJ IDEA](https://www.jetbrains.com/idea/).

1. Import project as _Maven Project_

2. Execute file `Initialization.java` from your IDE or from CLI:


## Deploying Project to Tomcat Server

### From IntelliJ IDEA

From _Maven Projects_ (`View > Tool Windows > Maven Projects`) run `Plugins > tomcat7 > tomcat7:redeploy`

### From CLI

```bash
mvn clean package tomcat7:redeploy
```
