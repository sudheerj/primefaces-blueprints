package com.packtpub.pf.blueprint.model;

/**
 * Created with IntelliJ IDEA.
 * User: Ramkumar Pillai
 * Date: 12/27/13
 * Time: 9:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class MenuItem {

    private Integer id;
    private String name;
    private String displayName;
    private double price;
    private boolean enabled;
    private Integer categoryId;
    private String imagePath;

    public MenuItem(Integer id, String name, String displayName, double price, boolean enabled, Integer categoryId) {
        this.id = id;
        this.name = name;
        this.displayName = displayName;
        this.price = price;
        this.enabled = enabled;
        this.categoryId = categoryId;
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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuItem)) return false;

        MenuItem menuItem = (MenuItem) o;

        if (enabled != menuItem.enabled) return false;
        if (Double.compare(menuItem.price, price) != 0) return false;
        if (!displayName.equals(menuItem.displayName)) return false;
        if (!id.equals(menuItem.id)) return false;
        if (!name.equals(menuItem.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + displayName.hashCode();
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (enabled ? 1 : 0);
        return result;
    }
}
