package stclient.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import stclient.app.entities.ItemEntity;
import stclient.app.entities.OrderItemEntity;

/**
 * Created by pavel on 21.04.14.
 */
public class OrderAdapter extends ArrayAdapter<OrderItemEntity>{

    private ArrayList<OrderItemEntity> objects;

    public OrderAdapter(android.content.Context context, int resource, ArrayList<OrderItemEntity> objects) {
        super(context, resource, objects);
        this.objects = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        //TODO поменять названия TextView2 и пр. на нормальные

        View view = View.inflate(getContext(),R.layout.order_item,null);

        //TODO Создать общий метод на следующие 3 пары строк

        TextView name = (TextView) view.findViewById(R.id.textView);
        name.setText(String.valueOf(objects.get(position).getItem().getName()));

        TextView quantity = (TextView) view.findViewById(R.id.textView2);
        quantity.setText(String.valueOf(objects.get(position).getQuantity()));

        TextView sum = (TextView) view.findViewById(R.id.textView3);
        sum.setText(String.valueOf(objects.get(position).getQuantity() * objects.get(position).getItem().getPrice()));

        return view;
    }
    
}