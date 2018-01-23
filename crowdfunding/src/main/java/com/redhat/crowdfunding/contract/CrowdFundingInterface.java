package com.redhat.crowdfunding.contract;

import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.util.List;

/**
 * @author littleredhat
 */
public interface CrowdFundingInterface {

    /********** 管理员 **********/

    // 获取众筹项目数量
    public RemoteCall<Uint256> getFundCount();

    // 获取众筹项目信息
    public RemoteCall<List<Type>> getFundInfo(int fundIndex);

    // 获取众筹捐赠人数
    public RemoteCall<Uint256> getRecordCount(int fundIndex);

    // 获取众筹捐赠记录
    public RemoteCall<List<Type>> getRecordInfo(int fundIndex, int recordIndex);

    /********** 用户 **********/

    // 为自己发起众筹
    public RemoteCall<TransactionReceipt> raiseFund(String desc, int goal);

    // 为别人发送金币
    public RemoteCall<TransactionReceipt> sendCoin(int index, int coin);
}