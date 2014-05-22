package stclient.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import stclient.app.entities.ModelEntity;

/**
 * Created by pavel on 25.04.14.
 */
public class CollectionOrderRenderer {

    static private ExpListAdapter expListAdapter;
    static private OrderAdapter orderAdapter;

    static private ModelEntity model = ModelEntity.getInstance();

    public static void onChangeOrderQuantity(int itemId){
        changeQuantityInLeftTable(itemId);
        renderOrderTable();
    }

    public static void setExpListAdapter(ExpListAdapter expListAdapter) {
        CollectionOrderRenderer.expListAdapter = expListAdapter;
    }

    public static void setOrderAdapter(OrderAdapter adapter){
        CollectionOrderRenderer.orderAdapter = adapter;
    }

    private static void changeQuantityInLeftTable(int itemId){

        expListAdapter.notifyDataSetChanged();

    }

    /**
     * Перерисовка правой части экрана сбора заказа
     */
    public static void renderOrderTable(){

        orderAdapter.notifyDataSetChanged();

    }
}
