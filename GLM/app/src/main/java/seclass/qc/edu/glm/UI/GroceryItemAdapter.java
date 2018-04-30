package seclass.qc.edu.glm.UI;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import seclass.qc.edu.glm.Data.DatabaseHandler;
import seclass.qc.edu.glm.Model.GroceryItem;
import seclass.qc.edu.glm.R;

public class GroceryItemAdapter extends RecyclerView.Adapter<GroceryItemAdapter.ViewHolder>{

    private Context context;
    private List<GroceryItem> groceryItems;
    private AlertDialog.Builder alertDialogBuilder;
    private AlertDialog dialog;
    private LayoutInflater inflater;


    public GroceryItemAdapter(Context context, List<GroceryItem> groceryItems) {
        this.context = context;
        this.groceryItems = groceryItems;
    }

    //Inflating our item list, creating a view, attach data to the view.
    @Override
    public GroceryItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);

        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(GroceryItemAdapter.ViewHolder holder, int position) {

        GroceryItem groceryItem = groceryItems.get(position);

        holder.groceryItemName.setText(groceryItem.getName());
        holder.itemQuantity.setText("Quantity: " + groceryItem.getStringQuantity());

    }

    @Override
    public int getItemCount() {
        return groceryItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        public TextView groceryItemName;
        public TextView itemQuantity;
        public CardView cardView;
        public CheckBox checkBox;
       // public Button editButton;
       // public Button deleteButton;
        public int id;


        public ViewHolder(View view, Context ctx) {

            super(view);

            context = ctx;

            groceryItemName = (TextView) view.findViewById(R.id.name);
            itemQuantity = (TextView) view.findViewById(R.id.quantity);
            checkBox = (CheckBox) view.findViewById(R.id.checkBox);
           // editButton = (Button) view.findViewById(R.id.editButton);
           // deleteButton = (Button) view.findViewById(R.id.deleteButton);

           // editButton.setOnClickListener(this);
           // deleteButton.setOnClickListener(this);
            groceryItemName.setOnClickListener(this);
            itemQuantity.setOnClickListener(this);
            checkBox.setOnClickListener(this);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //go to next screen: GroceryItemActivity
                    int position = getAdapterPosition();

                    GroceryItem groceryItem = groceryItems.get(position);
//                    Intent intent = new Intent(context, GroceryItemActivity.class);
//                    intent.putExtra("name", groceryItem.getName());
//                    intent.putExtra("id", groceryItem.getId());
//                    intent.putExtra("date", groceryItem.getDateItemAdded());
//                    context.startActivity(intent);
                }
            });

            cardView =  (CardView) view.findViewById(R.id.card_view);
            cardView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                /*case R.id.editButton:
                    int position = getAdapterPosition();
                    GroceryItem groceryItem = groceryItems.get(position);

                    editItem(groceryItem);

                    break;
                case R.id.deleteButton:
                    position = getAdapterPosition();
                    groceryItem = groceryItems.get(position);

                    deleteItem(groceryItem.getId());

                    break;*/
                case R.id.name:
                    int position = getAdapterPosition();
                    GroceryItem groceryItem = groceryItems.get(position);
                    editItemName(groceryItem);
                    break;

                case R.id.quantity:
                    position = getAdapterPosition();
                    groceryItem = groceryItems.get(position);
                    editQuantity(groceryItem);
                    break;
                case R.id.checkBox:
                    position = getAdapterPosition();
                    groceryItem = groceryItems.get(position);
                    boolean checked = ((CheckBox) v).isChecked();
                    editChecked(groceryItem, checked);
                    break;
            }
        }

        @Override
        public boolean onLongClick(View v) {
            int position = getAdapterPosition();
            GroceryItem groceryItem = groceryItems.get(position);
            deleteItem(groceryItem.getId());

            return true;
        }

        public void deleteItem(final long id) {
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
                    db.deleteGroceryItem(id);
                    groceryItems.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());

                    dialog.dismiss();

                }
            });

        }

        public void editItemName(final GroceryItem grocery) {

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
                    String newGroceryItemName = groceryItem.getText().toString();
                    grocery.setName(newGroceryItemName);

                    if (!groceryItem.getText().toString().isEmpty()) {
                        grocery.setName(newGroceryItemName);
                        db.updateGroceryItem(grocery);
                        notifyItemChanged(getAdapterPosition(),grocery);
                    } else {
                        Snackbar.make(view, "Add Grocery", Snackbar.LENGTH_LONG).show();
                    }

                    dialog.dismiss();

                }
            });
        }


        public void editQuantity(final GroceryItem grocery){

            alertDialogBuilder = new AlertDialog.Builder(context);

            inflater = LayoutInflater.from(context);
            final View view = inflater.inflate(R.layout.popup2, null);

            final EditText itemQuantity = (EditText) view.findViewById(R.id.groceryList2);
            final TextView title = (TextView) view.findViewById(R.id.tile2);

            title.setText("Enter new quantity");
            Button saveButton = (Button) view.findViewById(R.id.saveButton);


            alertDialogBuilder.setView(view);
            dialog = alertDialogBuilder.create();
            dialog.show();

            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    DatabaseHandler db = new DatabaseHandler(context);

                    //Update item
                    int quantity = 1;
                    try {
                        String newItemQuantity = itemQuantity.getText().toString();
                        quantity = Integer.parseInt(newItemQuantity);
                    } catch (Exception e){}
                    grocery.setQuantity(quantity);


                    if (!itemQuantity.getText().toString().isEmpty()) {
                        grocery.setQuantity(quantity);
                        db.updateGroceryItem(grocery);
                        notifyItemChanged(getAdapterPosition(),grocery);
                    } else {
                        Snackbar.make(view, "Add Grocery and Quantity", Snackbar.LENGTH_LONG).show();
                    }

                    dialog.dismiss();

                }
            });
        }

        public void editChecked(final GroceryItem grocery, boolean checked){
            DatabaseHandler db = new DatabaseHandler(context);
            grocery.setChecked(checked);
        }

       /* public void editItem(final GroceryItem grocery) {

            alertDialogBuilder = new AlertDialog.Builder(context);

            inflater = LayoutInflater.from(context);
            final View view = inflater.inflate(R.layout.popup2, null);

            final EditText groceryItem = (EditText) view.findViewById(R.id.groceryList);
            final EditText itemQuantity = (EditText) view.findViewById(R.id.groceryList2);
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
                    int quantity = 1;
                    String newGroceryItemName = groceryItem.getText().toString();
                    try {
                        String newItemQuantity = itemQuantity.getText().toString();
                        quantity = Integer.parseInt(newItemQuantity);
                    } catch (Exception e){}

                    grocery.setName(newGroceryItemName);
                    grocery.setQuantity(quantity);


                    if (!groceryItem.getText().toString().isEmpty()) {
                        grocery.setName(newGroceryItemName);
                        db.updateGroceryItem(grocery);
                        notifyItemChanged(getAdapterPosition(),grocery);
                    } else {
                        Snackbar.make(view, "Add Grocery and Quantity", Snackbar.LENGTH_LONG).show();
                    }

                    dialog.dismiss();

                }
            });
        }
*/
    }
}