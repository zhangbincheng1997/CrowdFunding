# CrowdFunding

## 概况说明
众筹系统，实现发送金币和发起众筹功能。前端AJAX请求，后端Web3j处理。

## 准备工作
环境搭建、部署智能合约、Web3j 轻量级的以太坊开发库for Java:
https://github.com/littleredhat1997/Ethereum

## 开发环境
1. IntelliJ IDEAorg.apache.maven.archetypes:maven-archetype-webapp
2. Apache Tomcat 8.0

## 智能合约
1. 中文文档 http://www.tryblockchain.org/
2. 英文文档 https://solidity.readthedocs.io/
3. 在线测试 https://remix.ethereum.org/

## 运行项目
1. 部署 crowdfunding.sol 合约
```
# https://github.com/littleredhat1997/Ethereum
# 部署合约方式一 - wallet 部署
# 部署合约方式二 - geth 部署
# 部署合约方式三 - web3j 部署
```
2. 修改 src/main/resources/config.properties 里的相关数据
3. 启动服务器 http://localhost:8080/crowdfunding/index.html

## 项目重点
1. 合约类 接口 Interface - 实现 Contract
2. 服务类 接口 Service - 实现 ServiceImpl
3. 控制器 SpringMVC Controller
4. 由于以太坊写入速度太慢影响用户体验
	1. 对于需要查询的事务，采用对象池加载合约
	2. 对于需要执行的事务，采用异步请求事务
