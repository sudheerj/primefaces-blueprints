package com.packtpub.pf.blueprint.persistence.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author Ramkumar Pillai   <psramkumar@gmail.com>
 * @version ${VERSION}
 * @since 10/20/13
 */

@Data
@Entity
@Table
public class Location implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NaturalId
    @Basic(optional = false)
    @Column(name = "franchisee_no")
    Long franchiseeNo;
    @Basic(optional = false)
    double longitude;
    @Basic(optional = false)
    double latitude;
    @Basic(optional = false)
    String street1;
    @Basic(optional = false)
    String city;
    String state;
    @Basic(optional = false)
    String zipcode;
    String country;
}
