package com.packtpub.pf.blueprint.controller;

import org.apache.log4j.Logger;
import org.primefaces.component.panel.Panel;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Ramkumar Pillai
 * Date: 1/8/14
 * Time: 9:00 AM
 * To change this template use File | Settings | File Templates.
 */

@ManagedBean
@SessionScoped
public class ChatController implements Serializable {
    private static final Logger _log = Logger.getLogger(ChatController.class);

    public void addPanel(ActionEvent event) {
        UIComponent component = FacesContext.getCurrentInstance().getViewRoot().findComponent("myPanelGrid");
        if (component != null) {
            Panel p = new Panel();
            p.setClosable(true);
            p.setHeader("Test");
            p.setVisible(true);
            component.getChildren().add(p);
        }
    }

}
