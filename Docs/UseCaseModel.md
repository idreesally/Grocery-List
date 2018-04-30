# Use Case Model

**Author**:  Team 3

## 1 Use Case Diagram


![grocerylistdiagram](https://user-images.githubusercontent.com/36058949/38170099-3595e7b6-354a-11e8-8e93-b98940b2233e.png)

## 2 Use Case Descriptions


##### Create Grocery List
	● Requirements:   Allows the user to create a grocery list.

	● Pre-Condition:  There is a 'create' button.
				      There is a database to store the grocery list.
				      There is a UI layout to display the grocery lists from the database.

	● Post-Condition: The grocery list is stored in a database.
    				  The UI layout is updated showing existing grocery lists from the database.

	● Scenario:       Tap on the floating 'create'  icon. Enter a title in the field of the popup. Tap on the 'save' icon.


##### Delete Grocery List
	● Requirements:   Allows the user to delete an existing grocery list.

	● Pre-Condition:  There is a 'delete' button.
				      The grocery list to be deleted exists in the database.
				      There is a UI layout to display the grocery lists from the database.

	● Post-Condition: The grocery list is removed from the database.
    				  The UI layout is updated showing existing grocery lists from the database.

	● Scenario:       Tap on the 'delete' icon located on the right of grocery list row.

##### Open Grocery List
	● Requirements:   Allows the user to open a selected grocery list and view all items within that grocery list.

	● Pre-Condition:  There is an existing grocery list in the database to open.
				      The grocery list is displayed in a clickable card view.       
				      There is a UI layout to display the items within the grocery list.

	● Post-Condition: The grocery lists opens to show its items.
    				  The UI layout is updated showing existing items from database related to the grocery list that was opened.

	● Scenario:       Tap on the row of the grocery list you are trying to open.


##### Rename Grocery List
	● Requirements:   Allows the user to rename the title of an existing grocery list.

	● Pre-Condition:  There is a 'edit' button.
				      There is an existing grocery list in the database to rename.
				      There is a editable text field for the user to input a new name.

	● Post-Condition: The grocery list name is update in the database.
					  The UI layout is updated showing the updated existing grocery lists in the database.

	● Scenario:       Tap on the 'delete' icon located on the right of grocery list row.


##### Add Item
	● Requirements:   Allows the user to add an item to an existing grocery list.

	● Pre-Condition:  There is an 'add' button.
				      There is an existing grocery list and database to store the item.

	● Post-Condition: The item is added to the database.
					  The UI layout is updated showing the updated existing grocery lists from the database.

	● Scenario:       Tap on the floating 'add'  icon. Enter an item name in the field of the popup. Tap on the 'save' icon.


##### Delete Item
	● Requirements:   Allows the user to remove an item from an existing grocery list.

	● Pre-Condition:  There is a 'delete' button.
				      The item to be deleted exists in the database.

	● Post-Condition: The item is removed from the database.

	● Scenario:       Tap on the 'delete' icon located on the right of the item row.



##### Specify Quantity

	● Requirements:   Allows the user to specify the quantity of an item in an existing grocery list.

	● Pre-Condition:  There is a 'edit' button.
				      The specified item exists in the database.
                      The specified item has a integer quantity attribute.

	● Post-Condition: The item name is updated in the database.
    				  The UI layout is updated showing the updated existing grocery lists in the database.

	● Scenario:       Tap on the 'edit' icon located on the right of item row. Enter a quantity in the quantity field of the popup.



##### Check Item

	● Requirements:   Allows the user to check off an item within a grocery list.

	● Pre-Condition:  There is a 'unchecked box' button located next to the item.

	● Post-Condition: There is a checked box located next to the item.

	● Scenario:       Tap on the checkbox on the left of the item row.



##### Uncheck Item

	● Requirements:   Allows the user to uncheck an item within a grocery list.

	● Pre-Condition:  There is a 'checked box' button located next to the item.

	● Post-Condition: There is a unchecked box  located next to the item.

	● Scenario:       Tap on the checkbox on the left of the item row.


##### Check All Items

	● Requirements:   Allows the user to check all items within a grocery list.

	● Pre-Condition:  There is a 'check all items' button in the settings menu.

	● Post-Condition: There is a chcked box located next to every item.

	● Scenario:       Tap on the 'options' icon on the top right. Tap on 'check off all items'.


##### Uncheck All Items

	● Requirements:   Allows the user to unceck all items within a grocery list.

	● Pre-Condition:  There is a 'uncheck all items' button in the settings menu.

	● Post-Condition: There is an unchcked box located next to every item.

	● Scenario:       Tap on the 'options' icon on the top right. Tap on 'uncheck all items'.  


##### Delete All Checked Items

	● Requirements:   Allows the user to delete all checked items withing a grocery list.

	● Pre-Condition:  There is a 'delete all checked items' button in the setting menu.

	● Post-Condition: All items that had a checked next box to is removed.

	● Scenario:       Tap on the 'options' icon on the top right. Tap on 'delete checked items'. Tap on 'yes' on the confirmation popup.
