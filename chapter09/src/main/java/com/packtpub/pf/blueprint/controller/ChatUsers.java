
package com.packtpub.pf.blueprint.controller;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
/**
 * Created with IntelliJ IDEA.
 * User: Ramkumar Pillai
 * Date: 1/8/14
 * Time: 9:00 AM
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@ApplicationScoped
public class ChatUsers implements Serializable {
    
    private Set<String> users;
    
    @PostConstruct
    public void init() {
        users = new HashSet<>();
    }

    public Set<String> getUsers() {
        return users;
    }
    
    public void remove(String user) {
        this.users.remove(user);
    }
    
    public void add(String user) {
        this.users.add(user);
    }
        
    public boolean contains(String user) {
        return this.users.contains(user);
    }
}
