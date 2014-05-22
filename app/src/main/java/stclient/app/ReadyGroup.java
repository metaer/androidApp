package stclient.app;

import android.content.ClipData;

import java.util.ArrayList;
import java.util.HashMap;

import stclient.app.entities.ItemEntity;
import stclient.app.entities.OrderItemEntity;

public class ReadyGroup extends SerializableObject{

    private String header;

    private ArrayList<HashMap<String,Object>> items;

    public ReadyGroup(String header, ArrayList<HashMap<String,Object>> items){

        this.header = header;
        this.items = items;
    }

    public String getHeader() {

        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public ArrayList<HashMap<String,Object>> getItems() {
        return items;
    }

    public void setItems(ArrayList<HashMap<String,Object>> items) {
        this.items = items;
    }

    /**
     * Возвращает кол-во детей
     * @return
     */
    public int size(){
        return this.items.size();
    }

    /**
     * Возвращает ребенка
     * @param position позиция ребенка в группе
     * @return ребенок
     */
    public HashMap<String,Object> getChild(int position){
        return items.get(position);
    }
}
