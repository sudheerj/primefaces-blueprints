package com.packtpub.pf.blueprint.controller;

import com.packtpub.pf.blueprint.persistence.entity.MovieSchedule;
import com.packtpub.pf.blueprint.persistence.entity.User;
import com.packtpub.pf.blueprint.service.DAOService;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Ramkumar Pillai
 * Date: 3/21/14
 * Time: 6:44 PM
 * To change this template use File | Settings | File Templates.
 */

@ManagedBean
@SessionScoped
public class ScheduleController implements Serializable {

    private static final Logger _log = Logger.getLogger(ScheduleController.class);

    @Getter
    @Setter
    private ScheduleModel eventModel;

    @Getter
    @Setter
    private ScheduleEvent event = new DefaultScheduleEvent();

    public ScheduleController() {

    }

    DAOService ds = new DAOService();

    @PostConstruct
    public void createSamples() {
        eventModel = new DefaultScheduleModel();
        MovieSchedule ms = new MovieSchedule("New Movie at legassy", today1Pm(), today6Pm(), false, user.getUsername());
        ds.addOrUpdateEntity(ms);

        ms = new MovieSchedule("Champions League Match", previousDay8Pm(), previousDay11Pm(), false, user.getUsername());
        ds.addOrUpdateEntity(ms);

        ms = new MovieSchedule("Birthday Party", today1Pm(), today6Pm(), false, user.getUsername());
        ds.addOrUpdateEntity(ms);

        ms = new MovieSchedule("Breakfast at Tiffanys", nextDay9Am(), nextDay11Am(), false, user.getUsername());
        ds.addOrUpdateEntity(ms);

        ms = new MovieSchedule("Plant the new garden stuff", theDayAfter3Pm(), fourDaysLater3pm(), false, user.getUsername());
        ds.addOrUpdateEntity(ms);


        List<MovieSchedule> msL = ds.getAllEvents();

        for(MovieSchedule e : msL){
            eventModel.addEvent(e.toScheduleEvent());
        }

    }

    public Date getRandomDate(Date base) {
        Calendar date = Calendar.getInstance();
        date.setTime(base);
        date.add(Calendar.DATE, ((int) (Math.random() * 30)) + 1);    //set random day of month

        return date.getTime();
    }

    public Date getInitialDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), Calendar.FEBRUARY, calendar.get(Calendar.DATE), 0, 0, 0);

        return calendar.getTime();
    }

    private Calendar today() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);

        return calendar;
    }

    private Date previousDay8Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
        t.set(Calendar.HOUR, 8);

        return t.getTime();
    }

    private Date previousDay11Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
        t.set(Calendar.HOUR, 11);

        return t.getTime();
    }

    private Date today1Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 1);

        return t.getTime();
    }

    private Date theDayAfter3Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 2);
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 3);

        return t.getTime();
    }

    private Date today6Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 6);

        return t.getTime();
    }

    private Date nextDay9Am() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.AM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
        t.set(Calendar.HOUR, 9);

        return t.getTime();
    }

    private Date nextDay11Am() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.AM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
        t.set(Calendar.HOUR, 11);

        return t.getTime();
    }

    private Date fourDaysLater3pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 4);
        t.set(Calendar.HOUR, 3);

        return t.getTime();
    }

    @ManagedProperty(value = "#{userController.userNow}")
    @Setter
    @Getter
    private User user;

    public void addEvent(ActionEvent actionEvent) {
        MovieSchedule ms = new MovieSchedule();
        if (event.getId() == null) {
            eventModel.addEvent(event);
        } else {
            eventModel.updateEvent(event);
            Long id = (Long) event.getData();

            _log.info("About to Update Id :" + id);
            if (id != null) {
                Object o = ds.loadEntityById(MovieSchedule.class, id);
                _log.info("We got the Entity here to Update: " + o);
                if (o != null) {
                    ms = (MovieSchedule) o;
                }
            }

        }

        ms.fromScheduleEvent(event, user.getUsername());
        _log.info("Test the Values: " + ms);
        ds.addOrUpdateEntity(ms);
        _log.info("After update Test the Values: " + ms);
        event = new DefaultScheduleEvent();
    }

    public void onEventSelect(SelectEvent selectEvent) {
        event = (ScheduleEvent) selectEvent.getObject();
    }

    public void onDateSelect(SelectEvent selectEvent) {
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
    }

    public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

        addMessage(message);
    }

    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

        addMessage(message);
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}

