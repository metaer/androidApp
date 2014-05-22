package stclient.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by pavel on 29.04.14.
 */
public class ContextGetter extends Application {

        private static Context context;

        public void onCreate(){
            super.onCreate();
            ContextGetter.context = getApplicationContext();
        }

        public static Context getAppContext() {
            return ContextGetter.context;
        }

}
