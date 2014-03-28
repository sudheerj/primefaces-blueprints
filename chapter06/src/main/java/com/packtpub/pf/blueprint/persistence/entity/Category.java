package com.packtpub.pf.blueprint.persistence.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Ramkumar Pillai   <psramkumar@gmail.com>
 * Date: 2/6/14
 * Time: 4:34 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table
public class Category implements java.io.Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Long parentId;

    @OneToMany(mappedBy = "category")
    private Set<Product> products = new HashSet<>();

    public Category() {
        this.parentId = 0L;
    }

    public Category(String name) {
        this.name = name;
        this.parentId = 0L;
    }

    public Category(String name, Long parentId) {
        this.name = name;
        this.parentId = parentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> product) {
        this.products = product;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                '}';
    }
}
