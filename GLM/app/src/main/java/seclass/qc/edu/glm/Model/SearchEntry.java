package seclass.qc.edu.glm.Model;

/**
 * Created by kraigslist on 4/19/2018.
 */

public class SearchEntry {

    private String itemName;
    private String itemDescription;
    private String itemCategory;
    private String itemID;
    private String itemImage;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    @Override
    public String toString() {
        return
                '\n' + "item_name = \n" + itemName + '\n' + '\n' +
//                        "item_description='" + itemDescription + '\n' +
                        "item_category = \n" + itemCategory + '\n';
//                        ", image_URL='" + itemImage + '\n';
    }
}
