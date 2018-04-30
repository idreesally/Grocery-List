package seclass.qc.edu.glm.Model;

import java.util.Comparator;

public class GroceryList {

    int id;
    String list_name;
    String dateListAdded;



    public GroceryList(String list_name) {
        this.list_name = list_name;
    }

    public GroceryList(int id, String list_name) {
        this.id = id;
        this.list_name = list_name;
    }

    public GroceryList() {

    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String tag_name) {
        this.list_name = tag_name;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.list_name;
    }

    public String getDateListAdded() {
        return dateListAdded;
    }

    public void setDateListAdded(String dateListAdded) {
        this.dateListAdded = dateListAdded;
    }


}
