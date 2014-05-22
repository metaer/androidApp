package stclient.app.entities;

import stclient.app.interfaces.HasId;
import stclient.app.SerializableObject;

public class ItemEntity extends SerializableObject implements HasId {

    private int id;

    private String name;

    private float price;

    private int quantity;

    private int groupId;

    public ItemEntity (int id, String name, float price, int quantity, int groupId){
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.groupId = groupId;
    }

//    @Override
//    public String toString() {
//        return "sdfs";
//    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public float getPrice(){
        return price;
    }

    public int getQuantity(){
        return quantity;
    }

    public int getGroupId(){
        return groupId;
    }

}