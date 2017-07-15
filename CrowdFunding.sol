pragma solidity ^0.4.0;

contract CrowdFunding {
    
    struct Fund {
        address addr; // 众筹地址
        uint number; // 已筹人数
        uint coin; // 已筹数量
    }
    
    address[] funds;
    mapping(address=>Fund) fundPool;
    
    // 是否存在
    function isExist(address addr) returns (bool) {
        return fundPool[addr].addr == addr;
    }
    
    // 获取数量
    function getFundCount() returns (uint) {
        return funds.length;
    }
    
    // 获取信息
    function getFundInfo(uint i) returns (address, uint, uint) {
        Fund f = fundPool[funds[i]];
        return (f.addr, f.number, f.coin);
    }
    
    // 发起众筹
    function raiseFund() {
        funds.push(msg.sender);
        fundPool[msg.sender] = Fund(msg.sender, 0, 0);
    }
    
    // 发送金币
    function sendCoin(address addr) payable {
        fundPool[addr].number++;
        fundPool[addr].coin += msg.value;
        addr.transfer(msg.value); // 如果钱包部署失败，暂时注释这行
    }
}