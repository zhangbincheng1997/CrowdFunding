pragma solidity ^0.4.0;


/**
 * 用户注册合约，所有用户在使用该众包合约之前先必须在该合约中进行注册，默认最大限制为100000人
 * */
contract Register{
    
    enum Type { 
        REQUESTER, WORKER
    }
    
    struct UserA {
        address addr;
        string userName;
        bytes32 passwd;
        address uscAddr;
        uint registerTime;
        Type userType;
        uint reputation;
    }
    
	// 已经注册的用户 username => UserA
    mapping (string => UserA) public reigiteredUsers;
    // 已经发布的任务
    address[] postTaskPool;
    // 系统管理员
    address owner;
    
    // 已经注册的用户数量
    uint public numRegistrants;
    // 最大允许注册用户数量
	uint public quota;
	// 平均信誉
	uint public reputationAvg;
    // 已经注册的用户列表
    UserA[] registeredUserList;
    
	function UserRegister(){
	    numRegistrants = 0;
	    quota = 1000000;
	    owner = msg.sender;
	}
    
    // 用户注册，每个注册用户产生UserSummary合约，用户密码通过哈希保存在区块链
	// Java端不需要阻塞等待，执行就可以
    function register(address _userAddr, string _userName, string _passwd, string _desc, string _userType) public returns(bool){
        if(numRegistrants >= quota)
		    throw;
		
		if(reigiteredUsers[_userName].addr != address(0))
		    throw;
		    
        UserA user;
		// UserSummary-----------------------------
		UserSummary summary = new UserSummary(_userAddr, _desc, this);
		bytes32 passwdHash = keccak256(_passwd);  // keccak256加密
		
		// 发布者
		if (CrowdBCUtils.equal(_userType,"REQUESTER"))  {
		    registeredUserList.push(UserA({addr:_userAddr,userName:_userName,passwd:passwdHash,uscAddr:summary,registerTime:now,userType:Type.REQUESTER,reputation:70}));
		    reigiteredUsers[_userName]=UserA({addr:_userAddr,userName:_userName,passwd:passwdHash,uscAddr:summary,registerTime:now,userType:Type.REQUESTER,reputation:70});
		}
		// 工作者
		else if (CrowdBCUtils.equal(_userType,"WORKER"))  {
            registeredUserList.push(UserA({addr:_userAddr,userName:_userName,passwd:passwdHash,uscAddr:summary,registerTime:now,userType:Type.WORKER,reputation:70}));
            reigiteredUsers[_userName]=UserA({addr:_userAddr,userName:_userName,passwd:passwdHash,uscAddr:summary,registerTime:now,userType:Type.WORKER,reputation:70});
        }else {
            throw;
        }   
		
		numRegistrants++;
		
		return true;
    }
    
    // 用户登录确认
    function login(string _userName, string _passwd) returns (bool) {
        UserA user = reigiteredUsers[_userName];
        
        if(user.addr == address(0))
            throw;
        
        if(user.passwd != keccak256(_passwd))
            throw;
        
        return true;
    }
    
    // 判断用户是否注册
    function checkRegister(string _userName) returns (bool) {
        UserA user = reigiteredUsers[_userName];
        
        if(user.addr == address(0))
            return false;
        
        return true;
    }
    
    // 更新用户密码
    function updatePassword(string _userName, string _oldPasswd, string _newPasswd) returns(bool){
        UserA user = reigiteredUsers[_userName];
		if(user.addr == address(0))
		    throw;
		
		if (keccak256(_oldPasswd) != user.passwd) {
		    throw;
		}
		
		user.passwd = keccak256(_newPasswd);
		
		return true;
    }
	
	// 这里可以获取用户信息
    
    // 更新最大用户数 可以删除--------------------
    function updateRegisterNum(uint _num) returns(bool){
        if(msg.sender != owner)
            throw;
            
        numRegistrants  =_num;
        
        return true;
    }
    
    // 更新网络平均信誉值，被其他合约调用
    function updateReputationAvg() {
        uint total;
        
        for(uint i = 0; i <= registeredUserList.length; i++) {
            total += registeredUserList[i].reputation;
        }
        
        reputationAvg = total / registeredUserList.length;
    }
    
	// 获取平均信息 被其他合约调用
    function getReputationAvg() public returns(uint) {    
        return reputationAvg;
    }
    
	// 获取用户信誉，被其他合约调用
    function getUserReputation(string _userName) public returns (uint) {
        UserA user = reigiteredUsers[_userName];
		if(user.addr == address(0))
		    throw;
		
		return user.reputation;
    }
    // 获取用户地址，被其他合约调用
    function getUserAddrByName(string _userName) public returns(address) {
        UserA user = reigiteredUsers[_userName];
		if(user.addr == address(0))
		    throw;
		
        return user.addr;
    }
    
    // 更新用户信誉值，被其他合约调用
    function updateUserReputation(string _userName, uint _rep) public returns (bool) {
        for(uint i =0; i < postTaskPool.length; i++) {
            if(msg.sender == postTaskPool[i]) {
                
                UserA user = reigiteredUsers[_userName];
        		if(user.addr == address(0))
        		    throw;
                
                user.reputation = _rep;
                return true;
            }
        }
    }
    
	// 通过用户名获取UserSummary，被其他合约调用也可以被Java调用
    function getUserUSCContractByName(string _userName) public returns (address) {
        UserA user = reigiteredUsers[_userName];
		if(user.addr == address(0))
		    throw;
		
		return user.uscAddr;
		    
    }
    // 添加任务，被其他合约调用
    function updatePostTaskPool(address _rwrcAddr) public {
        
        postTaskPool.push(_rwrcAddr);
    }
    
    function () {
	    throw;
	}
}

//用户汇总合约，包含用户众包过程中的任务信息
contract UserSummary {
    // 合约人
    address owner;
    // 完成的任务数量
    uint finishTaskNum;
    // 任务部署数量
    uint taskDeployNum;
    // 简介
    string profile;
    
    struct TaskA {
        string desc;
        uint reward;
        uint deadline;
        uint subTaskNum;// 子任务数量
        uint minReputation;
        address addr;
    }
    // 任务列表
    TaskA[] taskList;
    // 任务长度
    uint taskId = 0;
    
	// Register合约地址
    address registerAddr;
    Register reg = Register(registerAddr);
    
	// 构造函数
    function UserSummary(address _userAddr, string desc, address _registerAddr) {
        owner = _userAddr;
        finishTaskNum = 0;
        taskDeployNum = 0;
        profile = desc;
        registerAddr = _registerAddr;
    }
    
    // 任务上传，需要工作者压金或者压信誉值
	function postTask(string _userName, string _passwd, string _desc, uint _reward, uint _deadline, uint _subTaskNum, uint _minReputation, string _solutionDesc) returns (bool){
	    
		if(msg.sender != owner)
	        throw;
	    // 密码错误
	    if(!reg.login(_userName, _passwd))
	        throw;
	    // 不够钱发布
	    if(owner.balance < _reward)
	        throw;
	    // 创建RWRC
	    RequesterWorkerRelationship rwrc = new RequesterWorkerRelationship(owner, _desc, _reward, _deadline, _subTaskNum, _minReputation, _solutionDesc, registerAddr);
	    // 时间过了截止时间
		if(!rwrc.lockUntil(owner, _reward, (_deadline - now))) {
	        // 删除合约  刚创建就删除
			delete rwrc;
	        throw;
	    }
	    // 加入
	    taskList.push(TaskA({desc:_desc, reward:_reward, deadline:_deadline, subTaskNum:_subTaskNum, minReputation:_minReputation, addr:rwrc}));
	    // 更新到任务池里
		reg.updatePostTaskPool(rwrc);
	    
		return true;
	}
	
	function addFinishTaskNum() {
	    // 更新完成任务数量
	    finishTaskNum++;
	}
	
	function updateTaskDeployNum (uint _taskDeployNum) {
	    // 更新部署任务数量
	    taskDeployNum = _taskDeployNum;
	}
	
	function updateProfile(string _profile) {
	    // 更新简介
	    profile = _profile; 
	}
	
	function () {
	    throw;
	}
}

//任务管理合约，一个发布的任务对应一个该合约，任务的接收、评估等操作都在该合约中进行
contract RequesterWorkerRelationship {
    // 该合约工作者
    struct Worker {
        address addr;
        string userName;
        uint deposit;// 存款
        uint solutionPostTime;// 解决时间
        uint reputation;// 信誉
    }
    
	enum Status {
	    Pending, Unclaimed, Claimed, Evaluating, Cancelled, Completed 
	}
	
	// Register地址
	address registerAddr;
	Register reg = Register(registerAddr);
	
	// address 映射 工作者
    mapping(address => Worker) receivedWorkerPool;
    // 该合约工作者
	Worker[] receivedWorkerList;
	// 答案池
    mapping(address => string) solutionPool;
    // ？？？？？
    mapping(address => mapping(uint => uint)) locker;
    // 锁钱事件
	event Locked(address indexed _addr, uint _timestamp, uint _value);
	// 转账事件
    event Transfer(address indexed from, address indexed to, uint value);
    // 任务描述
    string taskDesc;
    // 解决办法描述
    string solutionDesc;
    // 发布者地址
    address requesterAddr;
    // 工作者任务数量
    uint subTaskNum;
    // 已经接收任务的数量
    uint receivedWorkerNum;
    // 已经完成任务的数量
    uint finishedTaskNum;
    // 截止日期
    uint deadline;
    // 任务发布时间
    uint256 taskPostTime;
    // 奖励
    uint reward;
    // 最小信誉
    uint minReputation;
    // 任务状态
    Status status;
    
	// 构造函数
    function RequesterWorkerRelationship(address _reuqesterAddr, string _desc, uint _reward, uint _deadline, uint _subTaskNum, uint _minReputation, string _solutionDesc, address _registerAddr){
        requesterAddr = _reuqesterAddr;
        taskDesc = _desc;
        reward = _reward;
        deadline = _deadline;
        taskPostTime = now;
        subTaskNum = _subTaskNum;
        minReputation = _minReputation;
        status = Status.Unclaimed;
        solutionDesc = _solutionDesc;
        registerAddr = _registerAddr;
    }
    
    // 判断工作者是否具备接收任务的资格，按照其信誉值
    function checkWorkerQualification(string _workeUserName) returns (bool) {
        
        uint reputation = reg.getUserReputation(_workeUserName);
        if(reputation <= minReputation)
            return false;
            
        return true;
    }
    
    // 判断工作者是否具备接收任务的资格，按照其信誉值
    function checkWorkerQualification(uint _reputation) returns (bool) {
        
        if(_reputation <= minReputation)
            return false;
            
        return true;
    }
    
    // 工作者接收任务，用户需要压押金或者信誉值
	function receiveTask(string _workeUserName, address _workerAddr, uint _deposit) returns (bool) {
	    // 不允许接收任务
		if(status != Status.Pending && status != Status.Unclaimed)
	        throw;
	    // 超出人数
        if(receivedWorkerNum >= subTaskNum)
            throw;
        
        uint workReputation = reg.getUserReputation(_workeUserName);
        // 工作者不够信誉
		if(!checkWorkerQualification(workReputation))
             throw;
       
        // 工作者押coin和信誉值中选择一种，优先考虑coin，信誉值统一减1
        if(_deposit !=0) {
			// 押金币
            if(lockUntil(_workerAddr, _deposit, (deadline - now))) {
                receivedWorkerPool[_workerAddr] = Worker({addr: _workerAddr, userName:_workeUserName, deposit:_deposit, solutionPostTime:0, reputation:workReputation});
                receivedWorkerList.push(Worker({addr: _workerAddr, userName:_workeUserName, deposit:_deposit, solutionPostTime:0, reputation:workReputation}));
                
            } else {
                throw;
            }
        } else {
		// 押信誉
            if(workReputation >= 1) {
                reg.updateUserReputation(_workeUserName, workReputation - 1);
    	        receivedWorkerPool[_workerAddr] = Worker({addr: _workerAddr, userName:_workeUserName, deposit:0, solutionPostTime:0, reputation:workReputation});
                receivedWorkerList.push(Worker({addr: _workerAddr, userName:_workeUserName, deposit:0, solutionPostTime:0, reputation:workReputation}));
                
            } else {
                throw;
            }
        }
        
        receivedWorkerNum++;
        // 工作者够了
	    if(receivedWorkerNum >= subTaskNum) {
	        status = Status.Claimed;
	    }
	    
	    return true;
	}
	// 取消任务，取消是不再需要工作者了
	function cancelTask() {
	    
	    receivedWorkerNum = subTaskNum;
	    
	    status = Status.Cancelled;
	}
	 
	// 提交任务结果，提交结果在外面通过云端保存，区块链只保存指针信息
	function submitSolution(string _workName, string _pointer) returns(bool) {
        address _workerAddr = reg.getUserAddrByName(_workName);
	    if(_workerAddr == address(0))
            throw;
	            
	    if(msg.sender != _workerAddr)
	        throw;
	    // 时间过了截止时间
	    if(now >= deadline)
	        throw;
	    
	    Worker worker = receivedWorkerPool[_workerAddr];
	    // 记录解决时间
		worker.solutionPostTime = now;
	    // workerAddr的解决指针    
	    solutionPool[_workerAddr] = _pointer;
	    // 状态转为评估状态
	    status = Status.Evaluating;
	    
	    return true;
	}
	
	// 结果评估，根据指针获取结果，并与任务发布时的要求进行比对
	function validateSolution(address _workerAddr) returns (string) {
	    
	    string _pointer =  solutionPool[_workerAddr];
	    
	    if(CrowdBCUtils.equal(getSolutionByPointer(_pointer), solutionDesc)) {
	        return 'H';
	    } else {
	        return 'L';
	    }
	}
	
	function getSolutionByPointer(string _pointer) returns (string) {
	    // 模拟数据，这里如果需要真正解决，需要用到其他高级方式
		return "aaa";
	}
	
	// 工人获取报酬
	function claimRewardByWorker(string _workerName) returns (bool){
		// ????这里？？？ 少了个！吗
		if(reg.checkRegister(_workerName))
	        throw;
	        
        address _workerAddr = reg.getUserAddrByName(_workerName);
	    if(_workerAddr == address(0))
            throw;
            
	    if(msg.sender != _workerAddr)
	        throw;
	    
	    uint rewardAvg = reward / subTaskNum;
	    
	    return evaluateSingleTask(_workerName, rewardAvg);
	}
	
	// 时间到的时候由发布者主动进行评价
	function evaluateTaskByRequester() returns(bool){
	    if(msg.sender != requesterAddr)
	        return false;
	    
		uint rewardAvg = reward / receivedWorkerNum;
		// 发布者主动评估
		for(uint i = 0; i <= receivedWorkerList.length; i++) {
		    Worker _worker = receivedWorkerList[i];
		    evaluateSingleTask(_worker.userName, rewardAvg);
		}
		
		status = Status.Completed;
		
	    return true;
	}
	
	// 单个子任务进行评估，评估完成后进行信誉值更新和转账
	function evaluateSingleTask(string _workerName, uint reward) private returns (bool) {
	    
	    address _workerAddr = reg.getUserAddrByName(_workerName);
	    
	    if(msg.sender != _workerAddr && msg.sender != requesterAddr)
	        return false;
	        
	    uint rep = reg.getUserReputation(_workerName);
	    // 存款，如果是押金币则取出为正数，如果是押信誉则取出为0
	    uint sendReward = receivedWorkerPool[_workerAddr].deposit;
	    
		// 高质量，大于平均  +2
	    if(CrowdBCUtils.equal(validateSolution(_workerAddr), 'H') && rep >= reg.getReputationAvg()) {
	        
	        reg.updateUserReputation(_workerName, rep + 2);
	        sendReward += reward;
	        
	    } 
		// 低质量  -1
		else if(CrowdBCUtils.equal(validateSolution(_workerAddr), 'L') && rep >= reg.getReputationAvg()) {
	        
	        reg.updateUserReputation(_workerName, rep - 1);
	        
	    } 
		// 低质量  0信誉？
		else if(CrowdBCUtils.equal(validateSolution(_workerAddr), 'L') && rep == reg.getReputationAvg()) {
	        
	        reg.updateUserReputation(_workerName, 0);
	        
	    } 
		// 其他？？？
		else {
	        
	        reg.updateUserReputation(_workerName, rep + 2);
	        
	    }
	    // 启动转账事件，在Java开启监听，并不是内置
	    Transfer(requesterAddr, _workerAddr, sendReward);
	    // 更新平均信誉
	    reg.updateReputationAvg();
	    // 找到该工作者的UserSummary
	    UserSummary usc = UserSummary(reg.getUserUSCContractByName(_workerName));
	    // 增加完成数量
		usc.addFinishTaskNum();
	    
	    return true;
	}
	
	// 
	function lockUntil(address addr, uint value, uint _timestamp) returns (bool _success) {
        // 钱不够
		if(addr.balance < value) 
            return false;
        // 截止时间正确
        if(_timestamp > now) { //only lock into the future.
            // 锁钱
			locker[addr][_timestamp] += value;
            // 锁钱事件
			Locked(addr, _timestamp, value);
            return true;
		// 截止时间过了
        } else {
			// 回退
            bool isSuc = addr.send(value); //send back. Perhaps replace with exception?
            if(!isSuc)
                throw;
            return false;
        }
    }
    
	function () {
	    throw;
	}
}


library CrowdBCUtils {
    function compare(string _a, string _b) returns (int) {
        bytes memory a = bytes(_a);
        bytes memory b = bytes(_b);
        uint minLength = a.length;
        if (b.length < minLength) minLength = b.length;
        //@todo unroll the loop into increments of 32 and do full 32 byte comparisons
        for (uint i = 0; i < minLength; i ++)
            if (a[i] < b[i])
                return -1;
            else if (a[i] > b[i])
                return 1;
        if (a.length < b.length)
            return -1;
        else if (a.length > b.length)
            return 1;
        else
            return 0;
    }
    /// @dev Compares two strings and returns true iff they are equal.
    function equal(string _a, string _b) returns (bool) {
        return compare(_a, _b) == 0;
    }
    /// @dev Finds the index of the first occurrence of _needle in _haystack
    function indexOf(string _haystack, string _needle) returns (int)
    {
    	bytes memory h = bytes(_haystack);
    	bytes memory n = bytes(_needle);
    	if(h.length < 1 || n.length < 1 || (n.length > h.length)) 
    		return -1;
    	else if(h.length > (2**128 -1)) // since we have to be able to return -1 (if the char isn't found or input error), this function must return an "int" type with a max length of (2^128 - 1)
    		return -1;									
    	else
    	{
    		uint subindex = 0;
    		for (uint i = 0; i < h.length; i ++)
    		{
    			if (h[i] == n[0]) // found the first char of b
    			{
    				subindex = 1;
    				while(subindex < n.length && (i + subindex) < h.length && h[i + subindex] == n[subindex]) // search until the chars don't match or until we reach the end of a or b
    				{
    					subindex++;
    				}	
    				if(subindex == n.length)
    					return int(i);
    			}
    		}
    		return -1;
    	}	
    }
}
