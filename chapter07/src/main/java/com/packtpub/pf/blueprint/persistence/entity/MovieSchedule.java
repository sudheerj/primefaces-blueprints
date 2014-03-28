package com.packtpub.pf.blueprint.persistence.entity;

import lombok.Data;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.ScheduleEvent;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Ramkumar Pillai   <psramkumar@gmail.com>
 * Date: 3/6/14
 * Time: 11:20 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table
@Data
public class MovieSchedule implements java.io.Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;
    private boolean allDay;
    private String userName;

    public MovieSchedule(String title, Date startDate, Date endDate, boolean allDay, String userName) {
        this.title = title;
        this.allDay = allDay;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userName = userName;
    }

    public MovieSchedule() {

    }

    public ScheduleEvent toScheduleEvent() {
        DefaultScheduleEvent se = new DefaultScheduleEvent(title, startDate, endDate, allDay);
        System.out.println("______________ ID : "+id);
        se.setData(id);
        return se;
    }

    public void fromScheduleEvent(ScheduleEvent se, String un) {
        this.title = se.getTitle();
        this.startDate = se.getStartDate();
        this.endDate = se.getEndDate();
        this.allDay = se.isAllDay();
        this.userName = un;

    }
}
