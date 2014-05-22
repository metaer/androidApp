package stclient.app.entities;

/**
 * Created by pavel on 24.04.14.
 */
public class test {
    private static test ourInstance = new test();

    public static test getInstance() {
        return ourInstance;
    }

    private test() {
    }
}
