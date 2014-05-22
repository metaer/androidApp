package stclient.app.entities;

import java.util.ArrayList;

import stclient.app.CollectionOrderObserver;
import stclient.app.Helper;
import stclient.app.interfaces.HasId;
import stclient.app.SerializableObject;

public class OrderEntity extends SerializableObject implements HasId{

    private int id;

    private ArrayList<OrderItemEntity> orderItems;

    private String comment;

    private int idClient;

    public OrderEntity(int id, ArrayList<OrderItemEntity> orderItems, String comment, int idClient){
        this.id = id;
        this.orderItems = orderItems;
        this.comment = comment;
        this.idClient = idClient;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getComment() {

        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ArrayList<OrderItemEntity> getOrderItems() {

        return orderItems;
    }

    public ItemEntity[] getTrueItems(){

        ItemEntity[] trueItems = new ItemEntity[orderItems.size()];

        int i = 0;

        for (OrderItemEntity item : orderItems){
            trueItems[i] = item.getItem();
            i++;
        }

        return trueItems;
    }

    public OrderItemEntity getOrderItemById(int id){
        ItemEntity trueItem = Helper.findById(getTrueItems(), id);
        return getOrderItemByTrueItem(trueItem);
    }

    public OrderItemEntity getOrderItemByTrueItem(ItemEntity trueItem){
        for (OrderItemEntity orderItem : orderItems){
            if (orderItem.getItem().equals(trueItem))
                return orderItem;
        }

        return null;
    }

    public void setOrderItems(ArrayList<OrderItemEntity> orderItems) {
        this.orderItems = orderItems;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addOrderItem(ItemEntity item, int quantity){
        orderItems.add(new OrderItemEntity(item,quantity));
    }

    public void remove(OrderItemEntity orderItem){
        int id = orderItem.getItem().getId();
        orderItems.remove(orderItem);
        CollectionOrderObserver.onRemoveOrderItem();
    }
}