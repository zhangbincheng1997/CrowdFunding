pragma solidity ^0.4.0;

contract CrowdFunding {
    
    struct Fund {
        // 已筹人数
        uint number;
        // 已筹数量
        uint coin;
    }
    
    // 众筹地址列表
    address[] funds;
    // 众筹地址映射
    mapping(address=>Fund) fundPool;
    
    // 是否存在
    function isExist(address addr) returns (bool) {
        for(uint i = 0; i < funds.length; i++)
            if(funds[i] == addr)
                return true;
        return false;
    }
    
    // 获取数量
    function getFundCount() returns (uint) {
        return funds.length;
    }
    
    // 获取信息
    function getFundInfo(uint i) returns (address, uint, uint) {
        address addr = funds[i];
        Fund f = fundPool[addr];
        return (addr, f.number, f.coin);
    }
    
    // 发起众筹
    function raiseFund(address addr) {
        if(isExist(addr))
            return;
        funds.push(addr);
        fundPool[addr] = Fund(0, 0);
    }
    
    // 发送金币
    function sendCoin(address addr) payable {
        if(!isExist(addr))
            throw;
        fundPool[addr].number++;
        fundPool[addr].coin += msg.value;
        addr.transfer(msg.value); // 如果钱包部署失败，暂时注释这行
    }
}