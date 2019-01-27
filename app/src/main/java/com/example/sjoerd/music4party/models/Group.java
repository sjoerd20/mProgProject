/*
  Represents a single group

  @author      Sjoerd Terpstra

 */

package com.example.sjoerd.music4party.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Group implements Serializable {
    private int loginCode;
    private String groupId;
    private ArrayList<String> users = new ArrayList<>();

    public Group() {
    }

    public Group(int loginCode, String groupId) {
        this.loginCode = loginCode;
        this.groupId = groupId;
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

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }


}