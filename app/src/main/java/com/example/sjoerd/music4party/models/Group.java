/*
  Represents a single group

  @author      Sjoerd Terpstra

 */

package com.example.sjoerd.music4party.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Group implements Serializable {
    private String groupID;
    private int loginCode;
    private ArrayList<String> users = new ArrayList<>();

    public Group(String groupID, int loginCode) {
        this.groupID = groupID;
        this.loginCode = loginCode;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public int getLoginCode() {
        return loginCode;
    }

    public void setLoginCode(int loginCode) {
        this.loginCode = loginCode;
    }

    public ArrayList<String> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<String> users) {
        this.users = users;
    }
}