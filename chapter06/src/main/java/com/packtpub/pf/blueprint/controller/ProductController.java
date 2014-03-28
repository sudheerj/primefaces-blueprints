package com.packtpub.pf.blueprint.controller;

import com.packtpub.pf.blueprint.persistence.entity.Category;
import com.packtpub.pf.blueprint.persistence.entity.Discount;
import com.packtpub.pf.blueprint.persistence.entity.Order;
import com.packtpub.pf.blueprint.persistence.entity.Product;
import com.packtpub.pf.blueprint.service.ProductService;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Ramkumar Pillai   <psramkumar@gmail.com>
 * Date: 1/27/14
 * Time: 8:48 PM
 * To change this template use File | Settings | File Templates.
 */

@ManagedBean
@SessionScoped
public class ProductController implements java.io.Serializable {

    private static final Logger _log = Logger.getLogger(ProductController.class);

    //Category Related Methods and Members
    private Category category;
    private List categories = new ArrayList<>();

    public List getCategories() {
        return categories;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void changeCategory(Category category) {
        this.category = category;
        _log.info("Category Changed to :" + category);
    }

    public void updateCategory() {

        ps.addCategory(category);
        categories = ps.getAllCategory();
        _log.info("Successfully Updated Category : " + category);
    }

    public void addNewCategory() {
        _log.info("About to Create New Category...");
        this.category = new Category();
    }

    public void removeCategory(long id) {

        ps.removeCategory(id);
        categories = ps.getAllCategory();
        _log.info("Successfully Deleted Category : " + id);
    }

    //Category Related Ends

    //Discount Related starts..

    private Discount discount;
    private List<Discount> discounts = new ArrayList<>();

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public void changeDiscount(Discount discount) {
        this.discount = discount;
        _log.info("Discount Changed to :" + discount);
    }

    public void updateDiscount() {

        ps.addDiscount(discount);
        discounts = ps.getAllDiscounts();
        _log.info("Successfully Updated Discount : " + discount);
    }

    public void addNewDiscount() {
        _log.info("About to Create New Discount...");
        this.discount = new Discount();
        this.discount.setCreateDate(new Date());
    }

    public void removeDiscount(long id) {

        ps.removeDiscount(id);
        discounts = ps.getAllDiscounts();
        _log.info("Successfully Deleted Discounts : " + id);
    }

    //Discount Related Ends here

    private long categoryId = 0;
    private Product product;
    private List products = new ArrayList<>();

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public List getProducts() {
        return this.products;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product prod) {
        this.product = prod;
    }

    public void addNewProduct() {
        this.product = new Product();
        this.categoryId = 0L;
    }

    public void changeProduct(Product product) {
        this.product = product;
        this.categoryId = product.getCategory().getId();
        _log.info("Product Changed to :" + product);
    }

    public void updateProduct() {

        product.setCategory(ps.getCategory(categoryId));
        ps.addProduct(product);
        this.products = ps.getAllProducts();
        _log.info("Successfully Updated Product : " + product);
    }

    public void removeProduct(long id) {

        ps.removeProduct(id);
        this.products = ps.getAllProducts();
        _log.info("Successfully Deleted Product : " + id);
    }


    private List orders = new ArrayList<>();

    public List getOrders() {
        return this.orders;
    }

    private Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }


    public void changeOrder() {
        ps.addOrUpdateOrder(order);
    }

    private List<String> statusTypes = new ArrayList<>();

    public List<String> getStatusTypes() {
        return statusTypes;
    }


    @PostConstruct
    public void init() {
        _log.info("Initializing now here : " + System.currentTimeMillis());
        populateCategory();
        populateOpenOrders();
        statusTypes.add(ps.OPEN_STATUS);
        statusTypes.add("PAID");
        statusTypes.add("PENDING");
        statusTypes.add("CANCELED");
        statusTypes.add("CLOSED");
    }

    private void populateCategory() {
        List<String> categs = new ArrayList<>();
        categs.add("Electronics");
        categs.add("Collectibles and Art");
        categs.add("Home and Garden");
        categs.add("Sporting Goods");
        categs.add("Toys and Hobbies");
        categs.add("Deals and Gifts");
        categs.add("Cars");
        categs.add("Motor Cycles");
        categs.add("Boat");
        categs.add("Aero Planes");
        categs.add("Kitchen Collections");

        for (String c : categs) {
            ps.addCategory(new Category(c));
        }

        Product p = new Product("SmSng-Kybd", 10.8);
        p.setCategory(ps.getCategory(1L));
        ps.addProduct(p);
        p = new Product("SmSng-RAM 16GB", 89.8);
        p.setCategory(ps.getCategory(1L));
        ps.addProduct(p);
        p = new Product("SmSng-Mouse", 43.8);
        p.setCategory(ps.getCategory(1L));
        ps.addProduct(p);
        p = new Product("Dell-Monitor", 34.8);
        p.setCategory(ps.getCategory(1L));
        ps.addProduct(p);
        p = new Product("Logicth-Kybd", 102.8);
        p.setCategory(ps.getCategory(1L));
        ps.addProduct(p);
        p = new Product("SmSng-SSD-512GB", 10.8);
        p.setCategory(ps.getCategory(1L));
        ps.addProduct(p);

        Order o = new Order();
        o.setCreateDate(new Date());
        o.setStatus(ps.OPEN_STATUS);
        o.setTotal(200.99);
        o.setOrderDetails("Name: Ramkumar \n Address: Abc Street, Dallas TX, 75252, \n Product : 2 Pak 8GB DDR3 RAM, 1 2TB SSD SMSNG, 1 Clng Fan");
        ps.addOrUpdateOrder(o);

        categories = ps.getAllCategory();
        products = ps.getAllProducts();
    }

    private void populateOpenOrders() {
        this.orders = ps.getAllOrders();
    }

    private ProductService ps = new ProductService();
}
