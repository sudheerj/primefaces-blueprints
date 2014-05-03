package com.packtpub.pf.blueprint.controller;

import org.apache.commons.io.FileUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

/**
 * Created by psramkumar on 5/3/14.
 */
@ManagedBean
@SessionScoped
public class FileUploadController implements Serializable {


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
        String destPath = "your path here";
        File destFile = new File(destPath);

        //use org.apache.commons.io.FileUtils to copy the File
        try {
            FileUtils.copyInputStreamToFile(inputStr, destFile);
        } catch (IOException e) {
            //log error
        }
    }

}
