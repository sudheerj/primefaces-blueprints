package com.packtpub.pf.blueprint.persistence.entity;

import com.packtpub.pf.blueprint.JobStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by psramkumar on 4/29/14.
 */

@Entity
@Table
@Data
public class PrintJobs implements java.io.Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String jobRefId;

    private String jobName;

    private String jobDescription;

    private String fileName;

    private int noOfPrints;

    private JobStatus status;

    @Temporal(TemporalType.DATE)
    private Date createDate;

    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;


}
