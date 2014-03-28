package com.packtpub.pf.blueprint.model;

/**
 * Created with IntelliJ IDEA.
 * User: psramkumar
 * Date: 1/6/14
 * Time: 11:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class LineItem {
    private int menuItemId;
    private String displayName;
    private int quantity;
    private double price;
    private String instruction;

    public LineItem(Integer menuItemId, String displayName, int quantity, double price) {
        this.menuItemId = menuItemId;
        this.displayName = displayName;
        this.quantity = quantity;
        this.price = price;
    }

    public int getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(int menuItemId) {
        this.menuItemId = menuItemId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LineItem)) return false;

        LineItem lineItem = (LineItem) o;

        if (menuItemId != lineItem.menuItemId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = menuItemId;
        result = 31 * result + (displayName != null ? displayName.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
