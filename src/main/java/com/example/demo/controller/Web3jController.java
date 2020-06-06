package com.example.demo.controller;

import com.example.demo.base.Result;
import com.example.demo.base.Status;
import com.example.demo.model.Campaign;
import com.example.demo.model.Funder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.web3j.model.CrowdFunding;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tuples.generated.Tuple5;
import org.web3j.utils.Convert;

import java.math.BigInteger;

@Slf4j
@Controller
public class Web3jController {

    @Autowired
    private CrowdFunding crowdFunding;

    @RequestMapping("sponsor")
    @ResponseBody
    public Result sponsor(@RequestParam("content") String content, @RequestParam("goal") int goal) {
        try {
            crowdFunding.sponsor(content, BigInteger.valueOf(goal)).send();
            return Result.success("");
        } catch (Exception e) {
            log.error("", e);
            return Result.error(Status.ETH_ERROR);
        }
    }

    @RequestMapping("contribute")
    @ResponseBody
    public Result contribute(@RequestParam("campaignID") int campaignID, @RequestParam("coin") int coin) {
        try {
            crowdFunding.contribute(BigInteger.valueOf(campaignID), BigInteger.valueOf(coin).multiply(Convert.Unit.ETHER.getWeiFactor().toBigInteger())).send();
            return Result.success("");
        } catch (Exception e) {
            log.error("", e);
            return Result.error(Status.ETH_ERROR);
        }
    }

    @RequestMapping("count")
    @ResponseBody
    public Result count() {
        try {
            BigInteger count = crowdFunding.numCampaigns().send();
            return Result.success(count);
        } catch (Exception e) {
            log.error("", e);
            return Result.error(Status.ETH_ERROR);
        }
    }

    @RequestMapping("campaign")
    @ResponseBody
    public Result campaign(@RequestParam("campaignID") int campaignID) {
        try {
            Tuple5<String, String, BigInteger, BigInteger, BigInteger> tuple = crowdFunding.campaigns(BigInteger.valueOf(campaignID)).send();
            Campaign c = new Campaign(tuple);
            return Result.success(c);
        } catch (Exception e) {
            log.error("", e);
            return Result.error(Status.ETH_ERROR);
        }
    }

    @RequestMapping("funder")
    @ResponseBody
    public Result funder(@RequestParam("campaignID") int campaignID, @RequestParam("funderID") int funderID) {
        try {
            Tuple2<String, BigInteger> tuple = crowdFunding.funders(BigInteger.valueOf(campaignID), BigInteger.valueOf(funderID)).send();
            Funder f = new Funder(tuple);
            return Result.success(f);
        } catch (Exception e) {
            log.error("", e);
            return Result.error(Status.ETH_ERROR);
        }
    }
}
