package com.druzbanarodov.relativlayoutjava.adapter;

public class InvitesUser {

    private String name;
    private int idRes;

    public InvitesUser(String name,int idRes){
        this.name = name;
        this.idRes = idRes;


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdRes() {
        return idRes;
    }

    public void setIdRes(int idRes) {
        this.idRes = idRes;
    }
}
