# CrowdFunding

## 必备工具  
1、Eclipse IDE for Java EE Developers

2、Apache Tomcat

3、Geth

4、Ethereum Wallet

## 运行项目  
1、部署Tomcat服务器

2、创建Maven工程

3、更新Maven工程

4、钱包部署CrowdFunding合约

=>将合约地址复制到工程src/main/java/Consts.java里

=>将管理员密钥复制到工程src/main/resouces/wallet.txt里
 
## 其他注意  
一、创建Maven工程失败
> * Window->Preferences->Maven->Archetypes->Add Remote Catalog
> * 在Catalog File输入http://repo1.maven.org/maven2/archetype-catalog.xml
> * 在Description输入maven catalog，
> * 重新创建工程

二、在WEB-INF下创建classes文件夹  
> * Choose Output folder=> Project->Build Path->Configure Build Path->Source->Browse
> * Remove Output folder=> src/main/java And src/main/resources

三、更新Maven工程异常
> * Java Compiler取消Enable project specific settings选项
