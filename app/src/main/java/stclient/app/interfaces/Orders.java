package stclient.app.interfaces;

import stclient.app.entities.OrderEntity;

/**
 * Created by pavel on 23.04.14.
 */
public interface Orders {

    /**
     * Получает текущий заказ
     */
    public OrderEntity getCurrentOrder();

    /**
     * Получить все заказы
     * @return
     */
    public OrderEntity[] getOrders();

    /**
     * Установить заказы
     * @param orders
     */
    public void setOrders(OrderEntity[] orders);

}
