package com.viethoa.mvvm.Features.Models;

import java.io.Serializable;

/**
 * Created by VietHoa on 21/05/16.
 */
public class User implements Serializable {

    private int id;
    private String token;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
