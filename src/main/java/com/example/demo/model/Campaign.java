package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.web3j.tuples.generated.Tuple5;

import java.math.BigInteger;

@Data
@NoArgsConstructor
public class Campaign {

    private String beneficiary;
    private String content;
    private BigInteger fundingGoal;
    private BigInteger numFunders;
    private BigInteger amount;

    public Campaign(Tuple5<String, String, BigInteger, BigInteger, BigInteger> tuple) {
        this.beneficiary = tuple.component1();
        this.content = tuple.component2();
        this.fundingGoal = tuple.component3();
        this.numFunders = tuple.component4();
        this.amount = tuple.component5();
    }
}