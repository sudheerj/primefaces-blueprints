package com.packtpub.pf.blueprint.controller;

import com.packtpub.pf.blueprint.JobStatus;
import com.packtpub.pf.blueprint.persistence.entity.Customer;
import com.packtpub.pf.blueprint.persistence.entity.Location;
import com.packtpub.pf.blueprint.persistence.entity.PrintJobs;
import com.packtpub.pf.blueprint.service.DAOService;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.FileUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by psramkumar on 5/3/14.
 */
@ManagedBean
@SessionScoped
public class FileUploadController implements Serializable {


    @PostConstruct
    public void init() {
        PrintJobs job = null;
        Customer c = (Customer)ds.loadEntityById(Customer.class, 1L);
        Location l = (Location)ds.loadEntityById(Location.class, 1L);
        for(int i = 0;i<10; i++) {
            job = new PrintJobs();
            job.setCustomer(c);
            job.setLocation(l);
            job.setCreateDate(new Date());
            job.setJobRefId("Job10"+i);
            job.setFileName("abc.txt");
            job.setJobDescription("Description");
            job.setJobName("FirstJob"+i);
            job.setNoOfPrints(2);
            job.setPageRange(true);
            job.setPageStart(3);
            job.setPageEnd(10);
            job.setStatus(JobStatus.SUBMITTED);

            ds.addOrUpdateEntity(job);
        }

        if(customer != null) {
            populatePrintJobList();
        }
    }

    public void handleFileUpload(FileUploadEvent event) {

        //get uploaded file from the event
        UploadedFile uploadedFile = (UploadedFile) event.getFile();

        //create an InputStream from the uploaded file
        InputStream inputStr = null;
        try {
            inputStr = uploadedFile.getInputstream();
        } catch (IOException e) {
            //log error
        }

        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String newFileName = servletContext.getRealPath("") + File.separator + "photocam" + File.separator + uploadedFile.getFileName();


        //create destination File
        File destFile = new File(newFileName);

        //use org.apache.commons.io.FileUtils to copy the File
        try {
            FileUtils.copyInputStreamToFile(inputStr, destFile);
        } catch (IOException e) {
            //log error
        }
    }

    @Getter private Long jobId = 1L;

    @ManagedProperty(value = "#{customerController.customer}")
    @Setter
    @Getter
    private Customer customer;

    @ManagedProperty(value = "#{locationController.location}")
    @Setter
    @Getter
    private Location location;

    public StreamedContent getFileforJobId(Long jobId){

         StreamedContent file = null;

        return file;
    }

    private DAOService ds = new DAOService();

    @Getter @Setter private PrintJobs jobs = new PrintJobs();

    public String savePrintJobs(){
        jobs.setCustomer(customer);
        jobs.setLocation(location);
        jobs.setJobRefId(jobs.getJobName()+"12");
        jobs.setCreateDate(new Date());
        jobs.setStatus(JobStatus.SUBMITTED);
        ds.addOrUpdateEntity(jobs);
        jobs = new PrintJobs();
        populatePrintJobList();
        return "/printjobs.jsf?faces-redirect=true/";
    }

    @Getter @Setter private List<PrintJobs> jobList;

    public void populatePrintJobList(){
        jobList = ds.getJobsByCustomerId(customer);
    }

}
