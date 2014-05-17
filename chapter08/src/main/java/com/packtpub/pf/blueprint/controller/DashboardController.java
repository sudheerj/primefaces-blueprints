package com.packtpub.pf.blueprint.controller;

import com.packtpub.pf.blueprint.JobStatus;
import com.packtpub.pf.blueprint.persistence.entity.Customer;
import com.packtpub.pf.blueprint.persistence.entity.PrintJobs;
import com.packtpub.pf.blueprint.service.DAOService;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by psramkumar on 5/4/14.
 */

@ManagedBean
@SessionScoped
public class DashboardController implements Serializable {

    private static final long serialVersionUID = -1890125026548028469L;

    @Setter @Getter private DashboardModel model;

    @PostConstruct
    public void init() {
        System.out.println("Creating Dashboards");
        if(customer != null) {
            for(int i=0;i<20;i++){
                PrintJobs p = new PrintJobs();
                p.setCustomer(customer);
                p.setJobRefId("jobid" + i);
                p.setPageEnd(0);
                p.setJobName("Jobname" + i);
                p.setStatus(JobStatus.SUBMITTED);
                p.setNoOfPrints(2);
                p.setCreateDate(new Date());
                p.setJobDescription("Description jaofdklj"+i);
                p.setFileName("one.txt");
                ds.addOrUpdateEntity(p);
            }

            populatePrintJobList();
        }
    }

    public void handleReorder(DashboardReorderEvent event) {
        FacesMessage message = new FacesMessage();
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        message.setSummary("Reordered: " + event.getWidgetId());
        message.setDetail("Item index: " + event.getItemIndex() + ", Column index: " + event.getColumnIndex() + ", Sender index: " + event.getSenderColumnIndex());

        addMessage(message);
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    private DAOService ds = new DAOService();


    @ManagedProperty(value = "#{customerController.customer}")
    @Setter
    @Getter
    private Customer customer;

    @Getter @Setter private List<PrintJobs> jobList;

    public void populatePrintJobList(){
        System.out.println("Populating List ");
        if(customer.getEmail().equals("admin@admin.com")){
            jobList = ds.getJobsBySubmittedStatus();
        }else{
            jobList = ds.getJobsByCustomerId(customer);
        }

        System.out.println(jobList);

        model = new DefaultDashboardModel();

        if(jobList != null && !jobList.isEmpty()){

            for(int i=0;i<jobList.size();i++) {
                DashboardColumn column = new DefaultDashboardColumn();
                for(int j=0;j<4;j++) {
                    if(i < jobList.size()) {
                        column.addWidget(jobList.get(i).getJobRefId());
                        i++;
                    }
                }
                model.addColumn(column);
            }
        }

    }

}
