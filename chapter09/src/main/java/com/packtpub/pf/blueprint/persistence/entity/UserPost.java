package com.packtpub.pf.blueprint.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Ramkumar Pillai     <psramkumar@gmail.com>
 * Date: 3/9/14
 * Time: 7:32 PM
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPost implements java.io.Serializable  {

    @Id
    @GeneratedValue
    private Long id;

    private String postText;

    private String postType;

    private String postDescription;

    @Temporal(TemporalType.DATE)
    private Date createDate;

    @ManyToOne(fetch = FetchType.EAGER)
    private Profile user;
}
