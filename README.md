# CrowdFunding

## 必备工具
> * Eclipse IDE for Java EE Developers
> * Apache Tomcat
> * Geth
> * Ethereum Wallet

## 创建工程
> * 部署Tomcat服务器  
Window->Preferences->Server->Runtime Environments->Add，选择Tomcat版本和安装目录。  
> * 创建Maven工程  
1、File->New->Maven Project->Use default Workspace location，选择GroupId为org.apache.maven.archetypes和ArtifactId为maven-archetype-webapp的工程。  
2、如果创建时失败，可以尝试打开Window->Preferences->Maven->Archetypes->Add Remote Catalog，在Catalog File输入http://repo1.maven.org/maven2/archetype-catalog.xml ，在Description输入maven catalog，重新创建工程。  
> * 在Project下创建lib文件夹  
1、复制servlet-api.jar（位于Tomcat安装目录的lib文件夹下），放入到lib文件夹。  
2、右键jar包，Build Path->Add To Build Path。  
> * 在WEB-INF下创建classes文件夹  
1、Project->Build Path->Configure Build Path->Source->Browse，设置为classes文件夹。  
2、Remove->src/main/java和src/main/resources的Output folder。  
> * 添加Web3j依赖  
向pom.xml输入：  
```
<dependency>
	<groupId>org.web3j</groupId>
	<artifactId>core</artifactId>
	<version>2.1.0</version>
</dependency>
```
> * 更新Maven工程  
1、Project->Maven->Update Project。  
2、如果更新后异常，在Java Compiler下取消Enable project specific settings选项。  
> * 运行服务器，开心地耍吧~~~  
