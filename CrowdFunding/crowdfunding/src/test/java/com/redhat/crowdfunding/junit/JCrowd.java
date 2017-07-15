package com.redhat.crowdfunding.junit;

import java.util.concurrent.ExecutionException;

import org.junit.Test;

import com.redhat.crowdfunding.service.CrowdFundingServiceImpl;

/**
 * @author littleredhat
 */
public class JCrowd {

	@Test
	public void getFunds() {
		CrowdFundingServiceImpl service = new CrowdFundingServiceImpl();
		try {
			System.out.println(service.getFunds());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
}
