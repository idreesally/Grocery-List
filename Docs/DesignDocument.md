# Design Document

**Author**: Team 3

## 1 Design Considerations

### 1.1 Assumptions

We assume the user is using an android device and will be able to install an apk file on their device.

### 1.2 Constraints

User does not have the ability to sort lists by different sorting methods and also does not have the ability to share lists with other users or open lists across other platforms.

### 1.3 System Environment

Software system must be at least Android 4.4 KitKat.

## 2 Architectural Design

The architecture provides the high-level design view of a system and provides a basis for more detailed design work. These subsections describe the top-level components of the system you are building and their relationships.

### 2.1 Component Diagram

The GroceryListManager is the main component of the application that requires information from GroceryList to manage different grocery lists a user will have. Since the GroceryList component will manage the list of items, it requires information from the component ItemType. Furthermore, all the items will be stored in the database component called ItemDatabase.  

![](https://github.com/qc-se-spring2018/370Spring18Team3/blob/master/GroupProject/Docs/images/componentDiagram.png)


### 2.2 Deployment Diagram

All the components will be deployed on Android devices.

![](https://github.com/qc-se-spring2018/370Spring18Team3/blob/master/GroupProject/Docs/images/DeploymentDiagram.png)

## 3 Low-Level Design

### 3.1 Class Diagram

The internal structure of a software component is expressed as a UML class diagram that represents the static class structure for the component and their relationships.

![](https://github.com/qc-se-spring2018/370Spring18Team3/blob/master/GroupProject/Docs/images/ClassDiagram.png)

## 4 User Interface Design

On the main screen of the application, all created shopping lists will be displayed in a list format. Clicking the garbage icon in the top right hand corner will prompt you to delete all the lists (or may have the option to select multiple lists to delete). Clicking the + icon will prompt you to create a new list, which takes you to the row of images at the bottom. You can hold on a list, which will prompt an "Edit List" popup with 3 options: Edit name, Add item, and Delete.

When creating a new list or editing a list, you will see the screen in the bottom row where you can Enter a list name or edit your list name. You can also delete all items, add an item, or tap on the quantity of an item to edit it. When adding an item, you can either search by hitting the search icon and typing in the name of the item or you can find the item by first going through the Item Type and then finding the Item.

![](https://github.com/qc-se-spring2018/370Spring18Team3/blob/master/GroupProject/Docs/images/ShoppingListsMockup.png)
