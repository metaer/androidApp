package stclient.app.entities;

import stclient.app.CollectionOrderRenderer;
import stclient.app.SerializableObject;

/**
 * Created by pavel on 23.04.14.
 */
public class OrderItemEntity extends SerializableObject{

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {

        quantity = quantity < 0 ? 0 : quantity;

        if (this.quantity != quantity){
            this.quantity = quantity;
            onChangeQuantityOrderItem();
        }
    }

    /**
     * Изменяет количество на величину amount (чтобы уменьшить кол-во - amount должен быть отрицательным)
     * @param amount
     */
    public void changeQuantity(int amount){
        if (amount != 0){
            quantity += amount;
            if (quantity < 0){
                quantity = 0;
            }
            onChangeQuantityOrderItem();
        }
    }

    //TODO сделать нормальную последовательность описание методов, объявления свойств

    private void onChangeQuantityOrderItem(){

        ModelEntity model = ModelEntity.getInstance();

        model.updateReadyGroups(this);
        CollectionOrderRenderer.onChangeOrderQuantity(getItem().getId());
        model.save();
    }

    public ItemEntity getItem() {

        return item;
    }

    public void setItem(ItemEntity item) {
        this.item = item;
    }

    private ItemEntity item;

    private int quantity;

    public OrderItemEntity(ItemEntity item, int quantity){
        this.item = item;
        this.quantity = quantity;
    }
}
