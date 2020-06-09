package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.utils.Convert;

import java.math.BigInteger;
import java.text.SimpleDateFormat;

@Data
@NoArgsConstructor
public class Funder {

    private int id;
    private String addr;
    private BigInteger amount;
    private String time;

    public Funder(Tuple3<String, BigInteger, BigInteger> tuple) {
        this.addr = tuple.component1();
        this.amount = tuple.component2().divide(Convert.Unit.ETHER.getWeiFactor().toBigInteger());
        this.time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(tuple.component3().multiply(BigInteger.valueOf(1000)));
    }
}