/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zavierdev.crudsiswa.auth;

/**
 *
 * @author tech
 */
public class Session {
    private String id;
    private String username;
    private boolean statusLogin;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean getStatusLogin() {
        return statusLogin;
    }

    public void setStatusLogin(boolean statusLogin) {
        this.statusLogin = statusLogin;
    }
}
