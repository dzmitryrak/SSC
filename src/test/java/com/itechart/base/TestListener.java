package com.itechart.base;

import com.itechart.utils.AllureUtils;
import lombok.extern.log4j.Log4j2;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.concurrent.TimeUnit;

@Log4j2
public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult iTestResult) {
        log.info("-------------------- STARTING TEST {} -----------------------", iTestResult.getName());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        //TODO Api request to set passed status of test case
        log.info("-------------- FINISHED TEST {} Duration: {} ----------------", iTestResult.getName(),
                getExecutionTime(iTestResult));
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        //TODO Api request to set failed status of test case
        AllureUtils.takeScreenshot();
        log.info("-------------- FAILED TEST {} Duration: {} ----------------", iTestResult.getName(),
                getExecutionTime(iTestResult));
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        //TODO Api request to set skipped status of test case
        AllureUtils.takeScreenshot();
        log.info("--------- SKIPPING TEST {} ---------", iTestResult.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {
        //TODO create test run using api

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }

    private long getExecutionTime(ITestResult iTestResult) {
        return TimeUnit.MILLISECONDS.toSeconds(iTestResult.getEndMillis() - iTestResult.getStartMillis());
    }
}
