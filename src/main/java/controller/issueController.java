/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import Client.AdminClient;
import entity.Issue;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author ayush
 */
@Named(value = "issueController")
@RequestScoped
public class issueController {
    AdminClient ac;
    Response res;
    List<Issue> issues;
    GenericType<List<Issue>> gIssues;
    List<Issue> pendingIssues;
    List<Issue> resolvedIssues;
    
    /**
     * Creates a new instance of issueController
     */
    
    @PostConstruct
    public void init(){
        ac=new AdminClient();
        issues=new ArrayList<>();
        gIssues=new GenericType<List<Issue>>(){};
        pendingIssues=new ArrayList<>();
        resolvedIssues=new ArrayList<>();
        loadPendingIssues();
        loadResolvedIssues();
    }
    public void loadPendingIssues(){
        res=ac.getAllIssues(Response.class);
        issues=res.readEntity(gIssues);
        pendingIssues=issues.stream().filter(issue->"Pending".equals(issue.getStatus())).collect(Collectors.toList());
    }
    public List<Issue> getAllPendingIssues(){
        return pendingIssues;
    }
    public void loadResolvedIssues(){
        res=ac.getAllIssues(Response.class);
        issues=res.readEntity(gIssues);
        resolvedIssues=issues.stream().filter(issue->"Resolved".equals(issue.getStatus())).collect(Collectors.toList());
    }
    public List<Issue> getAllResolvedIssues(){
        return resolvedIssues;
    }
    public String resolveIssue(String iid){
        ac.resolveIssue(iid);
        return "displayIssue.jsf?faces-redirect=true";
    }
}
