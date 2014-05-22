package stclient.app.entities;

import stclient.app.Helper;
import stclient.app.SerializableObject;

public class StoreEntity extends SerializableObject{
    private ItemEntity[] items;

    StoreEntity(ItemEntity[] items){
        this.items = items;
    }

    public ItemEntity[] getItems() {
        return items;
    }

    public void setItems(ItemEntity[] items) {
        this.items = items;
    }

    public ItemEntity findItemById(int id){
        return Helper.findById(items, id);
    }
}
