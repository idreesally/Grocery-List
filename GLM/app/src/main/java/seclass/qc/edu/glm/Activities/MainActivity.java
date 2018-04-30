package seclass.qc.edu.glm.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import seclass.qc.edu.glm.Data.AndroidDatabaseManager;
import seclass.qc.edu.glm.Data.DatabaseHandler;
import seclass.qc.edu.glm.Model.GroceryList;
import seclass.qc.edu.glm.R;

public class MainActivity extends AppCompatActivity {

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private EditText groceryList;
    private Button saveButton;
    private DatabaseHandler db;

    public static long groceryList_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Grocery List Manager");
        setContentView(R.layout.activity_main);
        //initialize database
        db = new DatabaseHandler(this);

        byPassActivity();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPopupDialog();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_reset_database) {
            db.resetDataBase(this);
            startActivity(new Intent(MainActivity.this, GroceryListActivity.class));
        }

        if (id == R.id.action_groceryLists){
            startActivity(new Intent(MainActivity.this, GroceryListActivity.class));
        }
        if (id == R.id.action_databaseManager){
            Intent dbmanager = new Intent(MainActivity.this, AndroidDatabaseManager.class);
            startActivity(dbmanager);
        }

        if (id == R.id.action_search_activity){
            Intent dbmanager = new Intent(MainActivity.this, SearchItemActivity.class);
            startActivity(dbmanager);
        }

        return super.onOptionsItemSelected(item);
    }

    private void createPopupDialog() {
        dialogBuilder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.popup, null);
        groceryList = (EditText) view.findViewById(R.id.groceryList);
        final TextView title = (TextView) view.findViewById(R.id.tile);
        saveButton = (Button) view.findViewById(R.id.saveButton);

        title.setText("Enter Grocery List Name");
        dialogBuilder.setView(view);
        dialog = dialogBuilder.create();
        dialog.show();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!groceryList.getText().toString().isEmpty()) {
                    saveGroceryToDB(v);
                }
            }
        });
    }

    private void saveGroceryToDB(View v) {
        GroceryList grocery = new GroceryList();
        String newGrocery = groceryList.getText().toString();
        grocery.setName(newGrocery);
        //save to DB

        groceryList_id = db.createGroceryList(grocery);
        Snackbar.make(v, "Grocery List Created!", Snackbar.LENGTH_LONG).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
                //start new activity
                startActivity(new Intent(MainActivity.this, GroceryListActivity.class));
            }
        }, 300);
    }

    public void byPassActivity() {
        //if database is not empty, go to GroceryListActivity
        if (db.getGroceryListCount() > 0) {
            startActivity(new Intent(MainActivity.this, GroceryListActivity.class));
            finish();
        }
    }


}
