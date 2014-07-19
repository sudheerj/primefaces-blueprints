package com.packtpub.pf.blueprint.controller;

import com.packtpub.pf.blueprint.persistence.entity.Category;
import com.packtpub.pf.blueprint.persistence.entity.Order;
import com.packtpub.pf.blueprint.persistence.entity.Product;
import com.packtpub.pf.blueprint.service.ProductService;
import org.apache.log4j.Logger;
import org.primefaces.event.DragDropEvent;
import org.primefaces.event.FlowEvent;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Ramkumar Pillai    <psramkumar@gmail.com>
 * Date: 2/8/14
 * Time: 7:09 PM
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@SessionScoped
public class StoreController implements java.io.Serializable {

    private static final Logger _log = Logger.getLogger(StoreController.class);

    private List products = new ArrayList<>();

    public List getProducts() {
        return products;
    }

    public void populateProductForCategory(Category c) {
        ProductService ps = new ProductService();
        this.products = ps.getProductsForCategory(c);

    }

    private List<Product> cartProducts = new ArrayList<>();

    public List<Product> getCartProducts() {
        return cartProducts;
    }

    public void addToCart(Product p) {
        addItemNow(p);
    }

    public void onProdDrop(DragDropEvent ddEvent) {
        Product p = ((Product) ddEvent.getData());
        addItemNow(p);
    }

    private void addItemNow(Product p) {
        cartProducts.add(p);
        productTotal += p.getPrice();
        _log.info("Product Name: " + p.getName() + " is added Successfully, Total is: " + productTotal);
    }

    private double productTotal = 0;

    public double getProductTotal() {
        return productTotal;
    }

    private Order order = new Order();

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }


    public String getStringTotal() {
        return String.format("%.2f", productTotal);
    }

    public void checkoutOrder() {
        ProductService ps = new ProductService();
        String prodDetail = "";
        for (Product p : cartProducts) {
            prodDetail += p.getName() + ", ";
        }
        order = new Order();
        order.setOrderDetails(" Product: " + prodDetail);
        ps.addOrUpdateOrder(order);
        _log.info("Clearing after Check out..");
        cartProducts = new ArrayList<>();
        productTotal = 0;
        order = new Order();
    }

    private boolean skip;

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public String onFlowProcess(FlowEvent event) {
        _log.info("Current wizard step:" + event.getOldStep());
        _log.info("Next step:" + event.getNewStep());

        if (skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }
}
