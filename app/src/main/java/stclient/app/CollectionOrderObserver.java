package stclient.app;

import stclient.app.entities.ModelEntity;

/**
 * Created by pavel on 28.04.14.
 */
public class CollectionOrderObserver {
    public static void onRemoveOrderItem(){
        CollectionOrderRenderer.renderOrderTable();
        ModelEntity.getInstance().save();
    }
}
