package com.packtpub.pf.blueprint.persistence.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Ramkumar Pillai <psramkumar@gmail.com>
 * Date: 2/6/14
 * Time: 9:37 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table
public class Product implements java.io.Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private double price;

    private String description;

    @Temporal(TemporalType.DATE)
    private Date createDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Product() {
        this.createDate = new Date();
        this.category = new Category();
    }

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
        this.createDate = new Date();
    }

    public Product(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.createDate = new Date();
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", createDate=" + createDate +
                ", category=" + category +
                '}';
    }
}
