package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.model.CrowdFunding;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;

@Slf4j
@Configuration
public class Web3jConfig {

    @Value("${web3j.url}")
    private String URL;

    @Value("${credentials.source}")
    private String SOURCE;

    @Value("${credentials.password}")
    private String PASSWORD;

    @Value("${contract.address}")
    private String ADDRESS;

    @Bean
    public Web3j web3j() {
        log.info("加载web3j====>：" + URL);
        return Web3j.build(new HttpService(URL));
    }

    @Bean
    public Credentials credentials() throws Exception {
        log.info("加载credentials====>：" + SOURCE);
        return WalletUtils.loadCredentials(PASSWORD, SOURCE);
    }

    @Bean
    public CrowdFunding crowdFunding() throws Exception {
        log.info("加载crowdFunding====>：" + ADDRESS);
        return CrowdFunding.load(ADDRESS, web3j(), credentials(), new DefaultGasProvider());
    }
}
