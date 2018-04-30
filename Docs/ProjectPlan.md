# Project Plan

**Author**: Team 3

## 1 Introduction

The project goal is to develop an android application that allows users to create well-planned grocery lists, saving precious time in stores, and ensuring users will never miss an item on their shopping list.

## 2 Process Description

1.
    - **Activity name**: Create List for grocery shopping:
    - **Activity description**: User can Create New List, provide name for the new list, and then the new list is save in database. After than, user can use Update List to add item and item type.
    - **Entrance criteria**: input is the name of list.
    - **Exit criteria**: message creation complete or the list is in database.


2.
    - **Activity name**: View List
    - **Activity description**: User can View List that is in the database. If the list is not existed in the database, it will send out the error message.
    - **Entrance criteria**: input is the name of list.
    - **Exit criteria**: return values of list OR  message list not found.


3.
    - **Activity name**: Update List
    - **Activity description**: User have full access to the existed list to Manage Item in the list, delete all checked items, rename list or check/uncheck item.
    - **Entrance criteria**:  input is the name of list
    - **Exit criteria**: Update complete or the changes have been saved in the database.


4.
    - **Activity name**: Delete All Checked Items
    - **Activity description**: User can delete all the items that have been checked in the list.
    - **Entrance criteria**:  input boolean type checkedItem
    - **Exit criteria**: the checked marks are being removed for all items in list.


5.
    - **Activity name**: Rename List
    - **Activity description**: User can rename the list if necessary.
    - **Entrance criteria**:  input is the list name and existed list in the database.
    - **Exit criteria**: the name of the list is already changed/updated in the database and message successful renamed list.


6.
    - **Activity name**: Check/Uncheck Item
    - **Activity description**: User can check the items that already bought or unchecked if they made the mistake by check the one that they haven't bought.
    - **Entrance criteria**:  input is the boolean type checkedItem
    - **Exit criteria**: the checked marks are shown in the list for the specific items or removed after uncheck.


7.
    - **Activity name**: View Item
    - **Activity description**: User can View Item that is in the database. If the item is not existed in the database, it will send out the error message.
    - **Entrance criteria**: input is the name of item.
    - **Exit criteria**: return values of item OR  message item not found.


8.
    - **Activity name**: Specify/Update Item
    - **Activity description**: User can rename the item and specify quantity of the existed item within the exist list in the database.
    - **Entrance criteria**: input is the name and quantity of item within the existed list.
    - **Exit criteria**: name/quantity has been changed OR message nothings changed if the update is the same one that was existed


9.
    - **Activity name**: Create Item
    - **Activity description**: User can create the new item if the item is not in the database.
    - **Entrance criteria**: input is the list and non-existed item.
    - **Exit criteria**: new item is in the database OR if the item is already in database, send message to announce that the item is already exist


10.
    - **Activity name**: Add Item
    - **Activity description**:
        - User can add item into the list and add item type for the item. However, if the itemType is not existed in the database, it will throw error and request user to create the item type for the item
        - If the item is not existed in the database, it will throw error and request user to Create new item.
    - **Entrance criteria**: input is the existed list and item.
    - **Exit criteria**: items are added to the list or throw error if item is not found in database


11.
    - **Activity name**: Delete Item
    - **Activity description**: User can delete unwanted items in the list.
    - **Entrance criteria**: input is the item that user want to delete.
    - **Exit criteria**: items are removed from the list OR if the item is not in the database, send message unable to non-existed item


12.
    - **Activity name**: Delete List
    - **Activity description**: User can delete unwanted list
    - **Entrance criteria**: input is the existed list in the database.
    - **Exit criteria**: List is being removed from the application OR if the list is not in the database, send message unable to non-existed list

## 3 Team


|     Team Member     |Role                                       |
|:-------------------:|:--------------------------------------------------------------------------------|
|       Nhi Tran      | **Backend Developer** <br> Implement code,based upon the project plan and specifications. |
|     Farwa Abbas     | **Document Manager** <br> Update documentation throughout development.                    |
|     Kraig Tapang    | **Backend Developer** <br> Implement code,based upon the project plan and specifications. |
| Mohammad AbdelQader | **QA Manager** <br> Implement unit, integration, system, and regression tests.            |
|     Idrees Ally     | **Database Developer** <br> Implement code,based upon the proposed database design.      |
