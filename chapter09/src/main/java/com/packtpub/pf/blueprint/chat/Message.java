/**
 * Created with IntelliJ IDEA.
 * User: Ramkumar Pillai
 * Date: 1/8/14
 * Time: 9:00 AM
 * To change this template use File | Settings | File Templates.
 */

package com.packtpub.pf.blueprint.chat;

public class Message {
    
    private String text;
    private String user;
    private boolean updateList;

    public Message() {
    }

    public Message(String text) {
        this.text = text;
    }
    
    public Message(String text, boolean updateList) {
        this.text = text;
        this.updateList = updateList;
    }

    public Message(String user, String text, boolean updateList) {
        this.text = text;
        this.user = user;
        this.updateList = updateList;
    }
    
    public String getText() {
        return text;
    }

    public Message setText(String text) {
        this.text = text;
        return this;
    }

    public String getUser() {
        return user;
    }

    public Message setUser(String user) {
        this.user = user;
        return this;
    }

    public boolean isUpdateList() {
        return updateList;
    }

    public void setUpdateList(boolean updateList) {
        this.updateList = updateList;
    }
}

