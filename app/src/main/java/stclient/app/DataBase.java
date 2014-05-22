package stclient.app;

import android.content.Context;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;

import stclient.app.entities.ModelEntity;

/**
 * Created by pavel on 29.04.14.
 */
public class DataBase {
    
    final private static String collectionOrderDataFileName = "collectionOrderData.bin"; 

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        DataBase.context = context;
    }

    private static Context context;

    static ModelEntity collectionOrderModel;

    private static byte[] getByteArrayFromSavedFile(String fileName){
        File file = new File(getFilePath(fileName));
        byte[] fileData = new byte[(int) file.length()];
        try{
            DataInputStream dis = new DataInputStream(new DataInputStream(new FileInputStream(file)));
            dis.readFully(fileData);
            dis.close();
        }
        catch (IOException e){
            return null;
        }

        return fileData;
    }

    public static void loadCollectionOrderModel(){

        byte[] byteArray = getByteArrayFromSavedFile(collectionOrderDataFileName);

        try {
            if (byteArray == null){
                ModelEntity.getInstance().setupDemoData();
            }
            else{
                ModelEntity.setInstance((ModelEntity) Helper.byteArrayToObject(byteArray));
            }
            collectionOrderModel = ModelEntity.getInstance();
        }
        catch (IOException e){
            Helper.tnre(e);
        }
    }

    public static ModelEntity getCollectionOrderModel() {
        if ( collectionOrderModel == null){
            loadCollectionOrderModel();
        }

        return collectionOrderModel;
    }

    public static void saveToFile(Serializable object){

        byte[] byteArray = {};

        try{
            byteArray = Helper.objectToByteArray(object);
        }
        catch (IOException e){
            Helper.tnre(e);
        }

        try{
            FileOutputStream fos = new FileOutputStream(getFilePath(collectionOrderDataFileName));
            fos.write(byteArray);
            fos.close();
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    private static String getFilePath(String path){
        return context.getFilesDir() + File.separator + path;
    }
}
