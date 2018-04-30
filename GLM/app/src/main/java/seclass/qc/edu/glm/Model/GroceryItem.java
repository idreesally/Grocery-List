package seclass.qc.edu.glm.Model;
import java.util.Comparator;
import java.util.ArrayList;

public class GroceryItem {

    int id;
    String item_name;
    int quantity;
    boolean checked;
    String created_at;

    public GroceryItem() {
        checked = false;
    }

    public GroceryItem(String item_name, boolean checked) {
        this.item_name = item_name;
        this.checked = checked;
    }

    public GroceryItem(int id, String item_name) {
        this.id = id;
        this.item_name = item_name;
    }

    public String getStringQuantity() {
        return Integer.toString(quantity);
    }


    public void setId(long id) {
        this.id = (int) id;
    }

    public void setName(String item_name) {
        this.item_name = item_name;
    }

    public void setCreatedAt(String created_at){
        this.created_at = created_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.item_name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isChecked() {
        if(checked == true) return true;
        else return false;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

}


