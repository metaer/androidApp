package stclient.app;
//TODO interfaces с мал. буквы
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import stclient.app.interfaces.HasId;

public class Helper {
    public static <T extends HasId> T findById(T[] array, int id){
        for (T element : array){
            if (element.getId() == id)
                return element;
        }
        return null;
    }

    public static byte[] objectToByteArray( Serializable o ) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream( baos );

        try {
            oos.writeObject( o );
        }
        catch (IOException e){
            System.out.print(e.getMessage());
        } finally {
            oos.close();
        }

        baos.close();

        return baos.toByteArray();
    }

    public static Object byteArrayToObject(byte[] arr) throws IOException {

        ByteArrayInputStream bais = new ByteArrayInputStream(arr);


        ObjectInputStream ois = new ObjectInputStream( bais );

        try{
            Object o = (Object) ois.readObject();
            return o;
        }
        catch (Exception e){
            System.out.print(e.getMessage());
        }
        finally {
            bais.close();
            ois.close();
        }
        return null;
    }

    /**
     * tnre Throws New Runtime Exception
     * @param e
     */
    public static void tnre(Exception e){
        throw new RuntimeException(e.getMessage());
    }


}