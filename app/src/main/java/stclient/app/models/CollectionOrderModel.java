package stclient.app.models;

import java.util.ArrayList;
import java.util.HashMap;

import stclient.app.ReadyGroup;
import stclient.app.entities.GroupEntity;
import stclient.app.entities.ItemEntity;
import stclient.app.entities.ModelEntity;
import stclient.app.entities.OrderItemEntity;

/**
 * Created by pavel on 24.04.14.
 */
public class CollectionOrderModel {

    public static ArrayList<ReadyGroup> getReadyGroups(GroupEntity[] groups, ItemEntity[] items){
        ArrayList<ReadyGroup> readyGroups = new ArrayList<ReadyGroup>();

        for (GroupEntity group : groups){

            ArrayList<HashMap<String,Object>> currentChildren = getCurrentChildren(items, group.getId());

            readyGroups.add(new ReadyGroup(group.getGroupName(),currentChildren));

        }

        return readyGroups;
    }


    private static ArrayList<HashMap<String,Object>> getCurrentChildren(ItemEntity[] items, int id){

        ArrayList<HashMap<String,Object>> children = new ArrayList<HashMap<String,Object>>();

        for (ItemEntity item : items){
            if (item.getGroupId() == id){

                int quantityOrder = 0;

                OrderItemEntity orderItem = ModelEntity.getInstance().getOrders().getCurrentOrder().getOrderItemByTrueItem(item);

                if (orderItem != null){
                    quantityOrder = orderItem.getQuantity();
                }


                HashMap<String,Object> child = new HashMap<String,Object>();
                child.put("item",item);
                child.put("quantityOrder",quantityOrder);

                children.add(child);
            }

        }

        return children;
    }
}
