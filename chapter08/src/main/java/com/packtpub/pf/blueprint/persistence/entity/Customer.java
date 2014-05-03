package com.packtpub.pf.blueprint.persistence.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by psramkumar on 4/21/14.
 */

@Data
@Entity
@Table
public class Customer implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;


}
