package stclient.app.entities;

import java.io.Serializable;

import stclient.app.SerializableObject;

public class ClientEntity extends SerializableObject{

    private int id;

    private String name;

    private String address;

    private float debt;

    public ClientEntity(int id, String name, String address, float debt){
        this.id = id;
        this.name = name;
        this.address = address;
        this.debt = debt;
    }

    public float getDebt() {
        return debt;
    }

    public void setDebt(float debt) {
        this.debt = debt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {

        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
