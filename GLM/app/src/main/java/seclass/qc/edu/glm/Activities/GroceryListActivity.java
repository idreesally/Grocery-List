package seclass.qc.edu.glm.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import seclass.qc.edu.glm.Data.AndroidDatabaseManager;
import seclass.qc.edu.glm.Data.DatabaseHandler;
import seclass.qc.edu.glm.Model.GroceryItem;
import seclass.qc.edu.glm.Model.GroceryList;
import seclass.qc.edu.glm.R;
import seclass.qc.edu.glm.UI.GroceryListAdapter;

import static java.util.Collections.*;

public class GroceryListActivity extends AppCompatActivity {


    //VARIABLES
    private RecyclerView recyclerView;
    private GroceryListAdapter groceryListAdapter;
    private List<GroceryList> groceryList;
    private List<GroceryList> listItems;
    private List<GroceryItem> list;
    ListView listView;
    private DatabaseHandler db;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;

    private EditText groceryItem;
    private EditText groceryItemQuantity;
    private Button saveButton;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        if (id == R.id.action_reset_database) {
            db.resetDataBase(this);
            startActivity(new Intent(GroceryListActivity.this, GroceryListActivity.class));
        }

        if (id == R.id.action_groceryLists){
            startActivity(new Intent(GroceryListActivity.this, GroceryListActivity.class));
        }

        if (id == R.id.action_databaseManager){
            Intent dbmanager = new Intent(GroceryListActivity.this, AndroidDatabaseManager.class);
            startActivity(dbmanager);
        }

        if (id == R.id.action_search_activity){
            Intent dbmanager = new Intent(GroceryListActivity.this, SearchItemActivity.class);
            startActivity(dbmanager);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Grocery List Manager");
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPopDialog();

            }
        });

        db = new DatabaseHandler(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewID);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        list = new ArrayList<>();

        groceryList = new ArrayList<>();
        listItems = new ArrayList<>();

        // Get items from database
        groceryList = db.getAllGroceryLists();

        for (GroceryList c : groceryList) {
            GroceryList groceryList = new GroceryList();
            groceryList.setName(c.getName());
            groceryList.setId(c.getId());
            groceryList.setDateListAdded("Added on: " + c.getDateListAdded());

            listItems.add(groceryList);
        }




        //NHI'S SORT
        list = new ArrayList<>();
        //sort the list names in ascending order in the groceryList
        Collections.sort(groceryList, new Comparator<GroceryList>(){
            public int compare(GroceryList obj1, GroceryList obj2) {
                return obj1.getName().compareToIgnoreCase(obj2.getName()); // To compare string names
            }
        });





//        groceryListAdapter = new GroceryListAdapter(this, listItems);
//        recyclerView.setAdapter(groceryListAdapter);
//        groceryListAdapter.notifyDataSetChanged();

        // fix 1 : Displays the lists in the GroceryList Activity
        groceryListAdapter = new GroceryListAdapter(this, groceryList);
        recyclerView.setAdapter(groceryListAdapter);
        groceryListAdapter.notifyDataSetChanged();
    }

    private void createPopDialog() {

        dialogBuilder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.popup, null);
        groceryItem = (EditText) view.findViewById(R.id.groceryList);
        saveButton = (Button) view.findViewById(R.id.saveButton);


        dialogBuilder.setView(view);
        dialog = dialogBuilder.create();
        dialog.show();


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveGroceryToDB(v);
            }
        });

    }




    private void saveGroceryToDB(View v) {

        GroceryList groceryList = new GroceryList();

        String newGrocery = groceryItem.getText().toString();


        groceryList.setName(newGrocery);


        //Save to DB
        MainActivity.groceryList_id = db.createGroceryList(groceryList);

//        DatabaseHelper.TestDB = db.createGroceryList(groceryList);

        Snackbar.make(v, "List Created!", Snackbar.LENGTH_LONG).show();

        // Log.d("Item Added ID:", String.valueOf(db.getGroceriesCount()));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
                //start a new activity
                startActivity(new Intent(GroceryListActivity.this, GroceryListActivity.class));
                finish();
            }
        }, 300);


    }

}
