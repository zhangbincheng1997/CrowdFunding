pragma solidity ^0.4.0;

// 官方文档众筹实现
// https://solidity.readthedocs.io/en/develop/types.html#structs

// 独立实现
contract CrowdFunding {

    // 众筹项目
    struct Fund {
        // 众筹地址
        address owner;
        // 众筹描述
        string desc;
        // 众筹目标
        uint goal;

        // 已筹金币
        uint coins;
        // 是否结束
        bool finished;

        // 捐赠人数
        uint recordCounts;
        // 捐赠记录
        mapping(uint => Record) records;
        // 原本使用 Record[] records 数组定义
        // 但是貌似目前版本尚不支持
        // 于是将 数组 拆分成 长度 + 映射
        // https://solidity.readthedocs.io/en/develop/types.html#mappings
    }

    // 捐赠记录
    struct Record {
        // 捐赠成员
        address member;
        // 捐赠金币
        uint coin;
        // 捐赠时间
        uint time;
    }

    // 众筹地址列表
    Fund[] funds;

    // 获取众筹项目数量
    function getFundCount() public view returns (uint) {
        return funds.length;
    }

    // 获取众筹项目信息
    // 参数：项目编号
    // 返回：众筹地址 众筹描述 众筹目标 已筹金币 是否结束
    function getFundInfo(uint fundIndex) public view returns (address, string, uint, uint, bool) {
        Fund storage fund = funds[fundIndex];
        return (fund.owner, fund.desc, fund.goal, fund.coins, fund.finished);
    }

    // 获取众筹捐赠人数
    function getRecordCount(uint fundIndex) public view returns (uint) {
        return funds[fundIndex].recordCounts;
    }

    // 获取众筹捐赠记录
    // 参数：项目编号 记录编号
    // 返回：捐赠成员 捐赠金币 捐赠时间
    function getRecordInfo(uint fundIndex, uint recordIndex) public view returns (address, uint, uint) {
        Record storage record = funds[fundIndex].records[recordIndex];
        return (record.member, record.coin, record.time);
    }

    // 为自己发起众筹
    function raiseFund(string info, uint goal) public {
        funds.push(Fund(msg.sender, info, goal, 0, false, 0));
    }

    // 为别人发送金币
    // https://solidity.readthedocs.io/en/latest/miscellaneous.html#modifiers
    // payable for functions: Allows them to receive Ether together with a call.
    function sendCoin(uint fundIndex) public payable {
        // 默认属性
        // msg.sender 转账账户
        // msg.value 转账数目
        // 智能合约默认单位 wei
        // 1 ether = 10^18 wei

        // 引用拷贝
        Fund storage fund = funds[fundIndex];
        require(!fund.finished);

        // 转账 失败自动退出
        fund.owner.transfer(msg.value);

        // 更新众筹信息
        fund.coins += msg.value;
        fund.records[fund.recordCounts++] = Record(msg.sender, msg.value, now);
        fund.finished = fund.coins >= fund.goal * 1 ether ? true : false;
    }

    // 回退函数 防止抛出异常
    // https://solidity.readthedocs.io/en/latest/contracts.html#fallback-function
    // if you want your contract to receive Ether, you have to implement a fallback function.
    function() public payable { }
}
