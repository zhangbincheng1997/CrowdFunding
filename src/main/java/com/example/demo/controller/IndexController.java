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
import org.web3j.tuples.generated.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class IndexController {

    @Autowired
    private CrowdFunding crowdFunding;

    @RequestMapping("campaigns")
    @ResponseBody
    public Result campaigns() {
        try {
            BigInteger count = crowdFunding.numCampaigns().send();
            List<Campaign> clist = new ArrayList<>();
            for (int i = 0; i < count.intValue(); i++) {
                Tuple6<String, String, BigInteger, BigInteger, BigInteger, BigInteger> tuple =
                        crowdFunding.campaigns(BigInteger.valueOf(i)).send();
                Campaign c = new Campaign(tuple);
                c.setId(i);
                clist.add(c);
            }
            return Result.success(clist);
        } catch (Exception e) {
            log.error("", e);
            return Result.error(Status.ETH_ERROR);
        }
    }

    @RequestMapping("funders")
    @ResponseBody
    public Result funders(@RequestParam("campaignID") int campaignID) {
        try {
            BigInteger count = crowdFunding.numFunders(BigInteger.valueOf(campaignID)).send();
            List<Funder> flist = new ArrayList<>();
            for (int i = 0; i < count.intValue(); i++) {
                Tuple3<String, BigInteger, BigInteger> tuple =
                        crowdFunding.funders(BigInteger.valueOf(campaignID), BigInteger.valueOf(i)).send();
                Funder f = new Funder(tuple);
                f.setId(i);
                flist.add(f);
            }
            return Result.success(flist);
        } catch (Exception e) {
            log.error("", e);
            return Result.error(Status.ETH_ERROR);
        }
    }
}
