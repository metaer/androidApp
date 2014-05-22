package stclient.app;

import android.content.ClipData;
import android.widget.BaseExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;

import stclient.app.entities.GroupEntity;
import stclient.app.entities.ItemEntity;
import stclient.app.entities.ModelEntity;
import stclient.app.entities.OrderItemEntity;


/**
 * Created by pavel on 08.04.14.
 */
public class ExpListAdapter extends BaseExpandableListAdapter{
    private ArrayList<ReadyGroup> mGroups;

    private Context mContext;

    public ExpListAdapter (Context context,ArrayList<ReadyGroup> groups){
        mContext = context;
        mGroups = groups;
    }

    @Override
    public int getGroupCount() {
        return mGroups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mGroups.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGroups.get(groupPosition);
    }

    @Override
    public HashMap<String,Object> getChild(int groupPosition, int childPosition) {
        return mGroups.get(groupPosition).getChild(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                             ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.group_view, null);
        }

        if (isExpanded){
            //Изменяем что-нибудь, если текущая Group раскрыта
        }
        else{
            //Изменяем что-нибудь, если текущая Group скрыта
        }

        TextView textGroup = (TextView) convertView.findViewById(R.id.textGroup);

        ReadyGroup group = mGroups.get(groupPosition);

        //System.out.print("\nПозиция: " + String.valueOf(groupPosition) + "\n");

        textGroup.setText(group.getHeader());

        return convertView;

    }

    private void fillTableRow(View convertView, int groupPosition, int childPosition){

        ItemEntity item = (ItemEntity) mGroups.get(groupPosition).getChild(childPosition).get("item");
        int quantityOrderInt = (int) mGroups.get(groupPosition).getChild(childPosition).get("quantityOrder");

                TextView name = (TextView) convertView.findViewById(R.id.name);

        name.setText(item.getName());

        //TODO вынести пары строчек в отдельное место (также используется в др. адаптере)

        TextView quantityStore = (TextView) convertView.findViewById(R.id.quantityStore);
        quantityStore.setText(String.valueOf(item.getQuantity()));

        TextView price = (TextView) convertView.findViewById(R.id.price);
        price.setText(String.valueOf(item.getPrice()));

        EditText quantityOrder = (EditText) convertView.findViewById(R.id.quantityOrder);
        quantityOrder.setText(String.valueOf(quantityOrderInt));
    }

    /**
     * @param plus - если true - устанавливаем лиссенер для кнопки плюс, если фолс - для кнопки минус
     */
    private void setButtonListener(final boolean plus, View convertView, final int groupPosition, final int childPosition){
        Button button = (Button)convertView.findViewById(plus ? R.id.plus : R.id.minus);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ModelEntity model = ModelEntity.getInstance();

                ItemEntity currentItem = (ItemEntity)getChild(groupPosition,childPosition).get("item");

                OrderItemEntity orderItem = model.getOrders().getCurrentOrder().getOrderItemByTrueItem(currentItem);

                int amount = plus ? 1 : -1;

                if (orderItem != null) {
                    orderItem.changeQuantity(amount);
                    if (orderItem.getQuantity() == 0){
                        model.getOrders().getCurrentOrder().remove(orderItem);
                    }
                }
                else{
                    if (plus){
                        model.getOrders().getCurrentOrder().addOrderItem(currentItem,0);
                        orderItem = model.getOrders().getCurrentOrder().getOrderItemByTrueItem(currentItem);
                        orderItem.changeQuantity(amount);
                    }
                }

            }
        });
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(R.layout.child_view, null);

        fillTableRow(convertView, groupPosition, childPosition);

        setButtonListener(true, convertView, groupPosition, childPosition);
        setButtonListener(false, convertView, groupPosition, childPosition);

//        if (childPosition == 0){
//            View headerView = inflater.inflate(R.layout.header_view, null);
//            return headerView;
//        }

        //Если товар первый в списке (childPosition == 1), то добавляем списку в верхний ряд шапку таблицы.
//        if (childPosition == 0){
//            TableLayout table = (TableLayout) convertView.findViewById(R.id.table);
//            table.addView(headerView,0);
//        }

//        TextView textChild = (TextView) convertView.findViewById(R.id.textChild);
//        textChild.setText(mGroups.get(groupPosition).get(childPosition));

//        Button button = (Button)convertView.findViewById(R.id.buttonChild);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(mContext,"button is pressed",5000).show();
//            }
//        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public Context getmContext() {
        return mContext;
    }
}