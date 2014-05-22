package stclient.app.entities;

import java.io.Serializable;

import stclient.app.SerializableObject;

public class GroupEntity extends SerializableObject{

    private int id;

    String groupName;

    public GroupEntity(int id, String groupName){
        this.id = id;
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}