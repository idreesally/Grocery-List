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
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import seclass.qc.edu.glm.Data.AndroidDatabaseManager;
import seclass.qc.edu.glm.Data.DatabaseHandler;
import seclass.qc.edu.glm.Model.GroceryItem;
import seclass.qc.edu.glm.Model.GroceryList; // GET RID OF THIS
import seclass.qc.edu.glm.R;
import seclass.qc.edu.glm.UI.GroceryItemAdapter;
import seclass.qc.edu.glm.UI.GroceryListAdapter;

public class GroceryItemActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GroceryItemAdapter groceryItemAdapter;
    private List<GroceryItem> groceryItemList;
    private List<GroceryItem> listItems;
    private DatabaseHandler db;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private EditText groceryItem;
    private EditText itemQuantity ;
    private Button saveButton;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_reset_database) {
            db.resetDataBase(this);
            startActivity(new Intent(GroceryItemActivity.this, GroceryListActivity.class));
        }
        if (id == R.id.action_groceryLists){
            startActivity(new Intent(GroceryItemActivity.this, GroceryListActivity.class));
        }
        if (id == R.id.action_databaseManager){
            Intent dbmanager = new Intent(GroceryItemActivity.this, AndroidDatabaseManager.class);
            startActivity(dbmanager);
        }
        if (id == R.id.action_search_activity){
            Intent dbmanager = new Intent(GroceryItemActivity.this, SearchItemActivity.class);
            startActivity(dbmanager);
        }

        return super.onOptionsItemSelected(item);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Sets title to Grocery List that was clicked
        setTitle(GroceryListAdapter.groceryListPosition_String);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPopPopDialog();
            }
        });

        db = new DatabaseHandler(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewID);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        groceryItemList = new ArrayList<>();
        listItems = new ArrayList<GroceryItem>();

        //BUG WAS HERE IN THE NEXT FEW LINES!!!! APPLICATION KEPT CRASHING...
        //it's fixed now, after some changes to DatabaseHandler, but not 2 sure what happened...

//        groceryItemList = db.getAllGroceryItems();


//        groceryItemList = db.getAllGroceryItemsByGroceryList(GroceryListAdapter.groceryListPosition_ID);

        groceryItemList = db.getAllGroceryItemsByGroceryListByName(GroceryListAdapter.groceryListPosition_String);

        for (GroceryItem c : groceryItemList) {
            GroceryItem groceryItems = new GroceryItem();
            groceryItems.setName(c.getName());
            groceryItems.setQuantity(c.getQuantity());
            groceryItems.setId(c.getId());
            groceryItems.setCreatedAt("Added on: " + c.getCreated_at());
            listItems.add(groceryItems);
        }

        GroceryItemAdapter groceryItemAdapter = new GroceryItemAdapter(this, listItems);
        recyclerView.setAdapter(groceryItemAdapter);
        groceryItemAdapter.notifyDataSetChanged();
    }
     // changes for adding quantity to database - farwa
     // changed popup layout to popup_new_item
    private void createPopPopDialog() {
        dialogBuilder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.popup_new_item, null);

        groceryItem = (EditText) view.findViewById(R.id.groceryList);
        final TextView title = (TextView) view.findViewById(R.id.tile);
        itemQuantity = (EditText) view.findViewById(R.id.groceryList2);
        final TextView title2 = (TextView) view.findViewById(R.id.tile2);
        saveButton = (Button) view.findViewById(R.id.saveButton);

        title.setText("Enter Grocery Item Name");
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



  // changes for adding quantity - farwa
    private void saveGroceryToDB(View v) {
        GroceryItem groceryItem1 = new GroceryItem();
        String newGroceryItem = groceryItem.getText().toString();

        //try statement, just in case user doesnt enter a quantity, default will be 1
        int quantity = 1;
        try {
            String newItemQuantity = itemQuantity.getText().toString();
            quantity = Integer.parseInt(newItemQuantity);
        } catch (Exception e){}

        groceryItem1.setName(newGroceryItem);
        groceryItem1.setQuantity(quantity);

        //save to DB
//        db.createGroceryItem(groceryItem1, new long[] { groceryList_id });
        db.createGroceryItem(groceryItem1, new long[] { GroceryListAdapter.groceryListPosition_ID });


        Snackbar.make(v, "Item Created!", Snackbar.LENGTH_LONG).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
                //start a new activity
                startActivity(new Intent(GroceryItemActivity.this, GroceryItemActivity.class));
                finish();
            }
        }, 300);
    }

}
