package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.web3j.tuples.generated.Tuple2;

import java.math.BigInteger;

@Data
@NoArgsConstructor
public class Funder {

    private String addr;
    private BigInteger amount;

    public Funder(Tuple2<String, BigInteger> tuple) {
        this.addr = tuple.component1();
        this.amount = tuple.component2();
    }
}