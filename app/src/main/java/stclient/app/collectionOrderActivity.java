package stclient.app;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.TextView;

import stclient.app.entities.ModelEntity;
import stclient.app.entities.OrderItemEntity;

import android.widget.ListView;

import java.util.ArrayList;


public class collectionOrderActivity extends ActionBarActivity {

    private ModelEntity model;

    TextView txt_help_gest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_order);

        initData();

        initInterface();

    }

    private void initInterface(){
        //Наполняем раскрывающиеся группы
        fillExpGroups(model.getReadyGroups());

        //Наполняем спиннер для выбора клиентов
        fillClientSpinner();

        //Наполняем заказ (правая часть)
        fillOrder();
    }

    private void initData(){
        Context context = getApplicationContext();

        ModelEntity.setContext(context);
        DataBase.setContext(context);

        //Заполняем модель с данными для отображения
        loadModel(Config.useDemoData);
    }

    private void loadModel(){
        loadModel(false);
    }

    private void loadModel(boolean useDemo){

        if (useDemo){
            model = ModelEntity.getInstance();
            model.setupDemoData();
        }
        else{
            ModelEntity.setInstance(DataBase.getCollectionOrderModel());
            model = ModelEntity.getInstance();
            if (model == null){
                loadModel(true);
            }
        }

    }

    private void fillOrder(){
        ArrayList<OrderItemEntity> items = model.getOrders().getCurrentOrder().getOrderItems();

        OrderAdapter arrAdd = new OrderAdapter(this,
                R.id.orderListView, items);

        CollectionOrderRenderer.setOrderAdapter(arrAdd);

        ListView orderView = (ListView) findViewById(R.id.orderListView);
        orderView.setAdapter(arrAdd);
    }

    private void fillClientSpinner(){
        Spinner spinner = (Spinner) findViewById(R.id.spinnerClients);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> clAdapter = ArrayAdapter.createFromResource(this, R.array.clients_names, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        clAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(clAdapter);
    }

    private void fillExpGroups(ArrayList<ReadyGroup> readyGroups){

        // Находим наш list
        ExpandableListView listView = (ExpandableListView)findViewById(R.id.exListView);

        //Создаем адаптер и передаем context и список с данными
        ExpListAdapter adapter = new ExpListAdapter(getApplicationContext(), readyGroups);

        CollectionOrderRenderer.setExpListAdapter(adapter);

        listView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.collection_order, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    public void toggle_contents(View v){
//        txt_help_gest.setVisibility( txt_help_gest.isShown()
//                ? View.GONE
//                : View.VISIBLE );
//    }

}