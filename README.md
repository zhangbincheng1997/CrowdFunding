# CrowdFunding

> * 部署Tomcat服务器  
Window->Preferences->Server->Runtime Environments->Add，选择Tomcat版本和安装目录。  
> * 创建Maven工程  
1、File->New->Maven Project->Use default Workspace location，选择GroupId为org.apache.maven.archetypes和ArtifactId为maven-archetype-webapp的工程。  
2、如果创建失败，可以尝试打开Window->Preferences->Maven->Archetypes->Add Remote Catalog，在Catalog File输入http://repo1.maven.org/maven2/archetype-catalog.xml ，在Description输入maven catalog，重新创建工程。  
> * 在Project下创建lib文件夹  
1、在该文件夹里放入servlet-api.jar（位于Tomcat安装目录的lib文件夹下）。  
2、右键Build Path->Add To Build Path。  
> * 在WEB-INF下创建classes文件夹  
Project->Build Path->Configure Build Path->Source->Browse，选择该文件夹。  
> * 打开pom.xml，添加Web3j依赖：  
```
<dependency>
	<groupId>org.web3j</groupId>
	<artifactId>core</artifactId>
	<version>2.1.0</version>
</dependency>
```
> * Project->Maven->Update Project。如果更新后出现问题，在Java Compiler下取消Enable project specific settings选项。  
> * 运行服务器，开心地耍吧~~~  
