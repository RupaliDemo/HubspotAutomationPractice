package com.qa.hubspot.listners;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.qa.hubspot.utils.JiraPolicy;
import com.qa.hubspot.utils.JiraServiceProvider;

public class TestJiraListener implements ITestListener{

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		
		JiraPolicy jiraPolicy =result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(JiraPolicy.class);
		
		boolean isTicketReady =jiraPolicy.logTicketReady();
		if(isTicketReady) {
			//raise jira ticket
			System.out.println("is Ticket Ready for Jira:" +isTicketReady);
			
			JiraServiceProvider jiraSp =  new JiraServiceProvider("https://hubspotautomation2.atlassian.net", 
					"rupali083@gmail.com","vzryWP53Br9tr9bIEXDF9A2C", "HUB");
			String issueSummery =result.getMethod().getConstructorOrMethod().getMethod().getName() +"got failed due to some exception or assertion";
			String issueDescription = result.getThrowable().getMessage() +"\n";
			issueDescription.concat(ExceptionUtils.getFullStackTrace(result.getThrowable()));
			jiraSp.createJiraTicket("Bug", issueSummery, issueDescription, "Rupali");
		
			
		}
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
