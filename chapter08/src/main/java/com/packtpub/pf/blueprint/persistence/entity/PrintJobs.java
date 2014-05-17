package com.packtpub.pf.blueprint.persistence.entity;

import com.packtpub.pf.blueprint.JobStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by psramkumar on 4/29/14.
 */

@Entity
@Table

public class PrintJobs implements java.io.Serializable {

    @Id
    @GeneratedValue
    @Getter @Setter private Long id;

    @Getter @Setter private String jobRefId;

    @Getter @Setter private String jobName;

    @Getter @Setter private String jobDescription;

    @Getter @Setter private String fileName;

    @Getter @Setter private int noOfPrints;

    @Getter @Setter private int pageStart = 1;

    @Getter @Setter private int pageEnd;

    @Getter @Setter private boolean pageRange;

    @Getter @Setter private JobStatus status;

    @Temporal(TemporalType.DATE)
    @Getter @Setter private Date createDate;

    @OneToOne
    @Getter @Setter private Location location;

    @ManyToOne(fetch = FetchType.EAGER)
    @Getter @Setter private Customer customer;


}
