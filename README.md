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

Manualy you can from _Maven Projects_ (`View > Tool Windows > Maven Projects`) run `Plugins > tomcat7 > tomcat7:redeploy`

Or you can create configuration for deployment to running _Tomcat Server_:
1. `Run > Edit Configurations...`
2. Press `Alt + Insert` and from _Add New Configuration_ menu select _Maven_
3. Name your configuration _Maven Redeploy_
4. In field _Command Line_ type: `clean package tomcat7:redeploy`
5. Confirm by clicking on _Add_/_Apply_ button

Or you can create configuration, which runs _Tomcat_ and deploys application:
1. `Run > Edit Configurations...`
2. Press `Alt + Insert` and from _Add New Configuration_ menu select _Tomcat Server > Local_
3. Name your configuration as _DWA on Tomcat_
4. Locate your _Tomcat_ installation with button _Configure..._
5. In _Deployment_ tab click _+_ button and select _Artifact_
6. Select _dwa:war_
7. Back in _Server_ tab select in _On Update action_ option _Redeploy_
8. Select your favorite browser in _Open browser_ part
9. Confirm your configuration

### From CLI

```bash
mvn clean package tomcat7:redeploy
```
