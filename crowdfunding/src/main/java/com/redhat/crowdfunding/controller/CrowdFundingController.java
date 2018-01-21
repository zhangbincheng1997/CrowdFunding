package com.redhat.crowdfunding.controller;

import com.alibaba.fastjson.JSON;
import com.redhat.crowdfunding.model.Fund;
import com.redhat.crowdfunding.model.Record;
import com.redhat.crowdfunding.pool.CrowdFundingServicePool;
import com.redhat.crowdfunding.service.CrowdFundingService;
import com.redhat.crowdfunding.service.CrowdFundingServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author littleredhat
 */
@Controller
public class CrowdFundingController {

    private static Logger logger = LoggerFactory.getLogger(CrowdFundingController.class);

    @RequestMapping("list")
    @ResponseBody
    public int list(HttpServletRequest request, HttpServletResponse response) {
        try {

            // 获取对象
            CrowdFundingService service = CrowdFundingServicePool.borrowObject(); // 对象池加载服务 加快返回响应速度
            // 获取数量
            int totalNum = service.getFundCount();
            // 归还对象
            CrowdFundingServicePool.returnObject(service);

            return totalNum;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @RequestMapping("getFunds")
    @ResponseBody
    public String getFunds(HttpServletRequest request, HttpServletResponse response) {
        int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        logger.info("getFunds request : " + pageIndex + " " + pageSize);

        try {

            // 获取对象
            CrowdFundingService service = CrowdFundingServicePool.borrowObject(); // 对象池加载服务 加快返回响应速度
            // 获取列表
            List<Fund> data = service.getFunds(pageIndex, pageSize);
            // 归还对象
            CrowdFundingServicePool.returnObject(service);

            String res = JSON.toJSONString(data);
            logger.info("getFunds response : " + res);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @RequestMapping("getRecords")
    @ResponseBody
    public String getRecords(HttpServletRequest request, HttpServletResponse response) {
        int fundIndex = Integer.parseInt(request.getParameter("fundIndex"));
        logger.info("getRecords request : " + fundIndex);

        try {

            // 获取对象
            CrowdFundingService service = CrowdFundingServicePool.borrowObject(); // 对象池加载服务 加快返回响应速度
            // 获取列表
            List<Record> data = service.getRecords(fundIndex);
            // 归还对象
            CrowdFundingServicePool.returnObject(service);

            String res = JSON.toJSONString(data);
            logger.info("getRecords response : " + res);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @RequestMapping("raiseFund")
    @ResponseBody
    public boolean raiseFund(HttpServletRequest request, HttpServletResponse response) {
        String desc = request.getParameter("desc");
        int goal = Integer.parseInt(request.getParameter("goal"));
        // sender
        String password = request.getParameter("password");
        String content = request.getParameter("content");
        logger.info("raiseFund request : " + desc + " " + goal + " " + password + " " + content); // test log

        try {

            // 这里不使用对象池 因为合约依赖客户端上传的钱包
            CrowdFundingService service = new CrowdFundingServiceImpl(password, content);
            // 发起众筹
            service.raiseFund(desc, goal);

            logger.info("raiseFund response : " + "success");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @RequestMapping("sendCoin")
    @ResponseBody
    public boolean sendCoin(HttpServletRequest request, HttpServletResponse response) {
        int fundIndex = Integer.parseInt(request.getParameter("fundIndex"));
        int coin = Integer.parseInt(request.getParameter("coin"));
        // sender
        String password = request.getParameter("password");
        String content = request.getParameter("content");
        logger.info("sendCoin request : " + fundIndex + " " + coin + " " + password + " " + content); // test log

        try {

            // 这里不使用对象池 因为合约依赖客户端上传的钱包
            CrowdFundingService service = new CrowdFundingServiceImpl(password, content);
            // 发送金币
            service.sendCoin(fundIndex, coin);

            logger.info("sendCoin response : " + "success");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
