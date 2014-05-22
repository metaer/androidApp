package stclient.app.entities;

import stclient.app.Helper;
import stclient.app.interfaces.Orders;
import stclient.app.SerializableObject;

public class OrdersEntity extends SerializableObject implements Orders {

    private OrderEntity[] orders;

    private int idCurrentOrder;

    public OrdersEntity(OrderEntity[] orders, int idCurrentOrder){
        this.orders = orders;
        this.idCurrentOrder = idCurrentOrder;
    }

    public OrderEntity getCurrentOrder(){
        return Helper.findById(orders,idCurrentOrder);
    }

    public OrderEntity[] getOrders() {
        return orders;
    }

    public void setOrders(OrderEntity[] orders) {
        this.orders = orders;
    }

//    private OrderEntity getOrderById(int id){
//        for (OrderEntity order : orders){
//            if (order.getId() == id)
//                return order;
//        }
//        return null;
//    }
}
