package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.web3j.tuples.generated.Tuple6;
import org.web3j.utils.Convert;

import java.math.BigInteger;
import java.text.SimpleDateFormat;

@Data
@NoArgsConstructor
public class Campaign {

    private int id;
    private String beneficiary;
    private String content;
    private BigInteger goal;
    private BigInteger amount;
    private BigInteger numFunders;
    private String time;

    public Campaign(Tuple6<String, String, BigInteger, BigInteger, BigInteger, BigInteger> tuple) {
        this.beneficiary = tuple.component1();
        this.content = tuple.component2();
        this.goal = tuple.component3();
        this.amount = tuple.component4().divide(Convert.Unit.ETHER.getWeiFactor().toBigInteger());
        this.numFunders = tuple.component5();
        this.time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(tuple.component6().multiply(BigInteger.valueOf(1000)));
    }
}