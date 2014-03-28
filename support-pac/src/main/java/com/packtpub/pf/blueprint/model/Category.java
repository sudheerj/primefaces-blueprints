package com.packtpub.pf.blueprint.model;

/**
 * Created with IntelliJ IDEA.
 * User: Ramkumar Pillai
 * Date: 12/27/13
 * Time: 9:42 AM
 * To change this template use File | Settings | File Templates.
 */

public class Category {
    private Integer id;
    private String name;
    private Integer parentId;
    private boolean enabled;
    private String imagePath;
    private String description;

    private Category() {

    }

    public Category(Integer id, String name, Integer parentId, boolean enabled, String description) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.enabled = enabled;
        this.description = description;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
