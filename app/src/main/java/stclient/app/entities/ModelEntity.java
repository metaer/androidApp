package stclient.app.entities;

import android.content.Context;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import stclient.app.DataBase;
import stclient.app.ReadyGroup;
import stclient.app.SerializableObject;
import stclient.app.models.CollectionOrderModel;

public class ModelEntity extends SerializableObject{

    private static ModelEntity instance = new ModelEntity();

    private ClientEntity[] clients;

    private StoreEntity store;

    private OrdersEntity orders;

    private GroupEntity[] groups;

    private static Context context;

    public ArrayList<ReadyGroup> getReadyGroups() {
        return readyGroups;
    }

    ArrayList<ReadyGroup> readyGroups;

    private int idCurrentOrder;

    private HashMap<String,Object> demoData = new HashMap<>();

    private ModelEntity(){

    }

    public static ModelEntity getInstance() {
        return instance;
    }

    public static void setInstance(ModelEntity instance) {
        ModelEntity.instance = instance;
    }

    public void setupDemoData() {

        initDemo();

        loadClients();
        loadStore();
        loadOrders();
        loadGroups();

        initReadyGroups();
    }

    public void initReadyGroups(){
        this.readyGroups = CollectionOrderModel.getReadyGroups(getGroups(), getStore().getItems());
    }

    public void save(){
        DataBase.saveToFile(this);
    }

    public StoreEntity getStore() {
        return store;
    }

    public void setStore(StoreEntity store) {
        this.store = store;
    }

    public OrdersEntity getOrders() {
        return orders;
    }

    public void setOrders(OrdersEntity orders) {
        this.orders = orders;
    }

    public ClientEntity[] getClients() {
        return clients;
    }

    public void setClients(ClientEntity[] clients) {
        this.clients = clients;
    }

    public GroupEntity[] getGroups() {
        return groups;
    }

    public void setGroups(GroupEntity[] groups) {
        this.groups = groups;
    }

    private void loadClients(){
        clients = (ClientEntity[]) demoData.get("clients");
    }

    private void loadStore(){
        store = (StoreEntity) demoData.get("store");
    }

    private void loadOrders(){
        orders = (OrdersEntity) demoData.get("orders");
    }

    private void loadGroups(){
        groups = (GroupEntity[]) demoData.get("groups");
    }

    private void initDemo(){
        demoData.put("clients",
                new ClientEntity[] {
                        new ClientEntity(1,"Вася","ул. Ленина д. 1", 5000),
                        new ClientEntity(2,"Петя","ул. Ленина д. 21", 7000),
                        new ClientEntity(3,"Вова","ул. Ленина д. 11", 1200)
                }
        );

        demoData.put("store",
                new StoreEntity(
                        new ItemEntity[] {
                                // TODO айдишки стринговые или GUID
                                new ItemEntity(1,"MILD SEVEN",45.3f,40,2),
                                new ItemEntity(2,"BOND",80f,50,2),
                                new ItemEntity(3,"Очаковское",64f,13,1),
                                new ItemEntity(4,"Журавли",87f,420,1),
                        }
                )
        );

        StoreEntity store = (StoreEntity)demoData.get("store");


        ArrayList<OrderItemEntity> orderItems = new ArrayList<>();

        orderItems.add(
                new OrderItemEntity(
                        store.findItemById(1),
                        4
                )
        );

        demoData.put("orders",
                new OrdersEntity(
                        new OrderEntity[] {
                               new OrderEntity(
                                       1,
                                       orderItems,
                                       "Test comment",
                                       1
                               ),
                               new OrderEntity(
                                       2,
                                       orderItems,
                                       "Test comment",
                                       1
                               )
                        },
                        1
                )
        );

        demoData.put("groups",
                new GroupEntity[]{
                    new GroupEntity(1,"Алкоголь"),
                    new GroupEntity(2,"Сигареты"),
                }
        );
    }

    public void updateReadyGroups(OrderItemEntity orderItem){
        //пробегаемся по группам по всем товарам. Там где id товара совпадает с переданным, обновляем количество.

        for (ReadyGroup readyGroup : readyGroups){
            for (HashMap<String, Object> itemMap : readyGroup.getItems()) {
                ItemEntity item = (ItemEntity) itemMap.get("item");
                if (item.getId() == orderItem.getItem().getId()){
                    itemMap.put("quantityOrder",orderItem.getQuantity());
                }
            }
        }
    }

    public Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        ModelEntity.context = context;
    }

}