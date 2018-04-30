package seclass.qc.edu.glm.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.SearchView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import seclass.qc.edu.glm.Data.AndroidDatabaseManager;
import seclass.qc.edu.glm.Data.DatabaseHandler;
import seclass.qc.edu.glm.Model.SearchEntry;
import seclass.qc.edu.glm.R;
import seclass.qc.edu.glm.Util.ParserSupermarket;

public class SearchItemActivity extends AppCompatActivity {

    private static final String TAG = "SearchItemActivity";
    private ListView listFeed;
    private DatabaseHandler db;
    ArrayAdapter<SearchEntry> arrayAdapter;
    String itemToSearchFor = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("SearchItemActivity (Test)");
        setContentView(R.layout.activity_search_item);

        listFeed = (ListView) findViewById(R.id.xmlListView);

        Log.d(TAG, "onCreate: starting Asynctask");
        DownloadData downloadData = new DownloadData();
//        downloadData.execute("http://www.supermarketapi.com/api.asmx/SearchByProductName?APIKEY=ef4f49c73e&ItemName=chips ahoy");
        downloadData.execute("http://www.supermarketapi.com/api.asmx/SearchByProductName?APIKEY=ef4f49c73e&ItemName="+itemToSearchFor);
        Log.d(TAG, "onCreate: done");


        Toolbar toolbar = (Toolbar) findViewById(R.id.search_bar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem item = menu.findItem(R.id.search_product);
        SearchView searchView = (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                itemToSearchFor = query;

                listFeed = (ListView) findViewById(R.id.xmlListView);

                Log.d(TAG, "onCreate: starting Asynctask");
                DownloadData downloadData = new DownloadData();
//              downloadData.execute("http://www.supermarketapi.com/api.asmx/SearchByProductName?APIKEY=ef4f49c73e&ItemName=chips ahoy");
                downloadData.execute("http://www.supermarketapi.com/api.asmx/SearchByProductName?APIKEY=ef4f49c73e&ItemName="+itemToSearchFor);
                Log.d(TAG, "onCreate: done");


                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                arrayAdapter.getFilter().filter(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//
//        if (id == R.id.action_reset_database) {
//            db.resetDataBase(this);
//            startActivity(new Intent(SearchItemActivity.this, GroceryListActivity.class));
//        }
//
//        if (id == R.id.action_groceryLists){
//            startActivity(new Intent(SearchItemActivity.this, GroceryListActivity.class));
//        }
//        if (id == R.id.action_databaseManager){
//            Intent dbmanager = new Intent(SearchItemActivity.this, AndroidDatabaseManager.class);
//            startActivity(dbmanager);
//        }
//
//        if (id == R.id.action_search_activity){
//            Intent dbmanager = new Intent(SearchItemActivity.this, SearchItemActivity.class);
//            startActivity(dbmanager);
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    private class DownloadData extends AsyncTask<String, Void, String>{
        private static final String TAG = "DownloadData";

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d(TAG, "onPostExecute: parameter is " + s);
            ParserSupermarket parseApplications = new ParserSupermarket();
            parseApplications.parse(s);

//            List<SearchEntry> arrayTest = new ArrayList<>();
//            arrayTest =ParserSupermarket.getSupermarketProducts();
//            Log.d(TAG, "onPostExecute: " + arrayTest);


            //ARRAY ADAPTER
            arrayAdapter = new ArrayAdapter<SearchEntry>(
                    SearchItemActivity.this, R.layout.search_item, ParserSupermarket.getSupermarketProducts());
            listFeed.setAdapter(arrayAdapter);
        }




        //The String... parameter takes in array of strings
        @Override
        protected String doInBackground(String... strings) {
            Log.d(TAG, "doInBackground: starts with " + strings[0]);
            String rssFeed = downloadXML(strings[0]);
            if(rssFeed == null){
                Log.e(TAG, "doInBackground: Error downloading");
            }
            return rssFeed;
        }

        private String downloadXML(String urlPath){
            //Using StringBuilder, because we are going to be appending to strings a lot as we read characters
            //from the input stream.
            StringBuilder xmlResult = new StringBuilder();
            //We are using a 'try' block bcuz things can go potentially go wrong.
            //User may not be connected to the internet, the URL we are trying to access is down, etc.
            try {
                URL url = new URL(urlPath); //Is it a valid URL?
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                int response = connection.getResponseCode();
                Log.d(TAG, "downloadXML: The response code was " + response);
                //InputStream inputStream = connection.getInputStream();
                //InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                //BufferedReader reader = new BufferedReader(inputStreamReader);
                //This does the same at the above commented code
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                int charsRead;
                char[] inputBuffer = new char[500];
                //Keep reading until the end of the input stream is reached.
                while (true){
                    charsRead = reader.read(inputBuffer);
                    if(charsRead < 0){
                        break;
                    }
                    if(charsRead > 0){
                        xmlResult.append(String.copyValueOf(inputBuffer,0,charsRead));
                    }
                }
                reader.close();
                return xmlResult.toString();
            } catch (MalformedURLException e){
                Log.e(TAG, "downloadXML: Invalid URL " + e.getMessage());
            } catch (IOException e) {
                Log.e(TAG, "downloadXML: IO Exception reading data: " + e.getMessage());
            } catch (SecurityException e){
                Log.e(TAG, "downloadXML: Security Exception. Needs pemission? " + e.getMessage());
//                e.printStackTrace();
            }
            return null;
        }



    }

}
