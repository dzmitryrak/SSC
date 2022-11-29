package com.itechart.base;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

//TODO add usage into bvt.xml or delete
public class Retry implements IRetryAnalyzer {

    private static final int MAX_RETRY = 3;
    private int attempt = 1;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (!iTestResult.isSuccess()) {
            if (attempt < MAX_RETRY) {
                attempt++;
                iTestResult.setStatus(ITestResult.FAILURE);
                System.out.println("Retrying once again");
                return true;
            } else {
                iTestResult.setStatus(ITestResult.FAILURE);
            }
        } else {
            iTestResult.setStatus(ITestResult.SUCCESS);
        }
        return false;
    }

}

