package com.qa.hubspot.utils;

import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.Field;
import net.rcarz.jiraclient.Issue;
import net.rcarz.jiraclient.Issue.FluentCreate;
import net.rcarz.jiraclient.JiraClient;
import net.rcarz.jiraclient.JiraException;

public class JiraServiceProvider {

	public JiraClient jira;
	public String project;
	public JiraServiceProvider(String JiraUrl,String username,String password,String project)
	{
		BasicCredentials creds = new BasicCredentials(username,password);
		jira = new JiraClient(JiraUrl,creds);
		this.project =project;
		
	}
	
	public void createJiraTicket(String issueType,String summary,String description,String reporterName)
	{
		try {
			FluentCreate fluentcreate = jira.createIssue(project, issueType);
			
			fluentcreate.field(Field.SUMMARY, summary); 
			fluentcreate.field(Field.DESCRIPTION, description);
			Issue  newIssue =fluentcreate.execute();
			
			System.out.println("new issue created in jira with id:" +newIssue);
		} catch (JiraException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
