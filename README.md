# 以太坊众筹系统
:smile: 更新版本，支持插件。

## Hello World
学前班：https://github.com/littleredhat1997/Ethereum

如果不想本地搭建，可以使用公开测试网络：
https://ropsten.infura.io/
https://rinkeby.infura.io
https://kovan.infura.io

## 项目说明
1. 发起众筹
![alt text](docs/1.png "title")
2. 发送金币
![alt text](docs/2.png "title")
3. 捐赠记录
![alt text](docs/3.png "title")
4. 众筹项目
![alt text](docs/4.png "title")
其它注意：钱包要有钱(支付gas) 记得挖矿 等待确认 ......

## 运行插件
Plugins->web3j:generate-sources
resources/CrowdFunding.sol ----> org.web3j.model.CrowdFunding

## 部署合约
test/java/com/example/demo/ContractDeploy.java
```
CrowdFunding contract = CrowdFunding.deploy(web3j, credentials, new DefaultGasProvider()).send();
System.out.println(contract.getContractAddress());
// rewrite: contractAddress ----> application.properties
```

## 启动项目
main/java/com/example/demo/Application.java

localhost:8080/test