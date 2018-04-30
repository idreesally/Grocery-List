package seclass.qc.edu.glm.UI;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import seclass.qc.edu.glm.Activities.GroceryItemActivity;
import seclass.qc.edu.glm.Data.DatabaseHandler;
import seclass.qc.edu.glm.Model.GroceryList;
import seclass.qc.edu.glm.R;

public class GroceryListAdapter extends RecyclerView.Adapter<GroceryListAdapter.ViewHolder> {

    private Context context;
    private List<GroceryList> groceryLists;
    private AlertDialog.Builder alertDialogBuilder;
    private AlertDialog dialog;
    private LayoutInflater inflater;

    //USING THESE TO KEEP TRACK OF LISTS AND ITEMS... IS THERE A BETTER WAY?! there has to be...
    public static long groceryListPosition_ID;
    public static String groceryListPosition_String;


    public GroceryListAdapter(Context context, List<GroceryList> groceryLists) {
        this.context = context;
        this.groceryLists = groceryLists;
    }

    //inflate our list here and then create a view, which we can attach data too
    @Override
    public GroceryListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row, parent, false);

        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(GroceryListAdapter.ViewHolder holder, int position) {
        GroceryList groceryList = groceryLists.get(position);


        holder.groceryListName.setText(groceryList.getName());
        holder.dateAdded.setText(groceryList.getDateListAdded());
    }

    @Override
    public int getItemCount() {
        return groceryLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView groceryListName;
        public TextView dateAdded;
        public Button editButton;
        public Button deleteButton;

        public ViewHolder(View view, Context ctx) {
            super(view);

            context = ctx;

            groceryListName = (TextView) view.findViewById(R.id.name);
            dateAdded = (TextView) view.findViewById(R.id.quantity);

            editButton = (Button) view.findViewById(R.id.editButton);
            deleteButton = (Button) view.findViewById(R.id.deleteButton);

            editButton.setOnClickListener(this);
            deleteButton.setOnClickListener(this);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //go to next screen/ DetailsActivity
                    int position = getAdapterPosition();

//                    groceryListPosition_ID = position;

                    GroceryList groceryList = groceryLists.get(position);


                    groceryListPosition_ID = groceryList.getId();

                    groceryListPosition_String = (String) groceryListName.getText();

                    Intent intent = new Intent(context, GroceryItemActivity.class);
                    intent.putExtra("name", groceryList.getName());
                    intent.putExtra("id", groceryList.getId());
                    intent.putExtra("date", groceryList.getDateListAdded());
                    context.startActivity(intent);

                }
            });
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.editButton:
                    int position = getAdapterPosition();
                    GroceryList groceryList = groceryLists.get(position);
                    editItem(groceryList);
                    break;

                case R.id.deleteButton:
                    position = getAdapterPosition();
                    groceryList = groceryLists.get(position);
                    deleteItem(groceryList.getId());
                    break;
            }
        }

        public void deleteItem(final int id) {
            alertDialogBuilder = new AlertDialog.Builder(context);

            inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.confirmation_dialog, null);

            Button noButton = (Button) view.findViewById(R.id.noButton);
            Button yesButton = (Button) view.findViewById(R.id.yesButton);

            alertDialogBuilder.setView(view);
            dialog = alertDialogBuilder.create();
            dialog.show();

            noButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            yesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //delete the item.
                    DatabaseHandler db = new DatabaseHandler(context);
                    //delete item


                    //NEED TO FIX THIS, set to false for now or else crashes, but we need to make it working while set to true.
                    db.deleteGroceryList(id, false);
                    groceryLists.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());

                    dialog.dismiss();
                }
            });

        }

        public void editItem(final GroceryList grocery) {

            alertDialogBuilder = new AlertDialog.Builder(context);

            inflater = LayoutInflater.from(context);
            final View view = inflater.inflate(R.layout.popup, null);

            final EditText groceryItem = (EditText) view.findViewById(R.id.groceryList);
            final TextView title = (TextView) view.findViewById(R.id.tile);

            title.setText("Rename");
            Button saveButton = (Button) view.findViewById(R.id.saveButton);

            alertDialogBuilder.setView(view);
            dialog = alertDialogBuilder.create();
            dialog.show();

            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseHandler db = new DatabaseHandler(context);

                    //Update item
                    grocery.setName(groceryItem.getText().toString());

                    if (!groceryItem.getText().toString().isEmpty()) {
                        db.updateGroceryList(grocery);
                        notifyItemChanged(getAdapterPosition(),grocery);
                    }else {
                        Snackbar.make(view, "Add Grocery and Quantity", Snackbar.LENGTH_LONG).show();
                    }

                    dialog.dismiss();
                }
            });

        }
    }
}
