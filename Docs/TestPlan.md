# Test Plan

**Author**: Team 3

## 1 Testing Strategy

### 1.1 Overall strategy

**Unit Testing**
<br>
Our strategic approach for testing our application will begin at the component level consisting of unit tests. As we develop each unit test, we will exercise focusing on only one specific aspect of code at a time so that each failure can quickly lead us to a bug in the code. Additionally, we are expecting unit tests to make up the bulk of our tests, as we want to thoroughly cover all test cases. We are aiming for our unit tests to give us rapid feedback on failures and serve as a safety net to give use the ability to refactor code without breaking existing behavior.

**Integration Testing**
<br>
From the component level we will move outwards into integration testing after exercising our unit tests. We will concentrate on testing the interactions amongst the different modules of our application with reference to the relationships of our UML class diagram, specifically focusing on how the modules integrate and communicate with other components.

**System Testing**
<br>
When the modules of our application are successfully integrated and pass integration tests, system tests will be performed with focus on the functionality, design, behavior, and anticipated expectations of the application as whole.

**Regression Testing**
<br>
Lastly, we intend to implement automated UI tests using the Espresso framework to verify our application still works as the code changes and grows.


### 1.2 Test Selection

**White-box testing** will consist of unit tests for each test case, integration tests using a bottom up approach, and will be performed by our Software Engineers who implement and understand the source code.
<br>
**Black-box testing** will be done by the Project Manager and Quality Assurance Manager whose focus will be on validation of the functional requirements, design, behavior, and anticipated expectations of an end user.


### 1.3 Adequacy Criterion

We will derive our set of test obligations structurally from our code and functionally from our application specifications, diagrams, and group discussions. We will assess the quality of our test cases by seeing if every test obligation in the criterion is satisfied by at least one of the test cases in the test suite.


### 1.4 Bug Tracking

[Trello](https://trello.com/370spring18team3) will be used to track all bugs, requests, tasks and progress.


### 1.5 Technology

The following frameworks will be used for testing:
<br>
[JUnit](https://junit.org) will be used for our unit tests.
<br>
[Roboeletric](http://robolectric.org/) will additionally be experimented with for unit tests.
<br>
[Espresso](https://developer.android.com/training/testing/espresso/index.html) will be used for automated UI tests.


## 2 Test Cases


The test cases table will continue to be populated and filled in during development.

|Purpose|Steps|Expected Result|Actual Result|Pass/Fail Information|Comments|
|:----  |:---|:--------------|:------------|:-------|:----|
|Create a grocery list |Tap on the floating 'create'  icon. Enter a title in the field of the popup. Tap on the 'save' icon.|The grocery list with specified name is created and shown in the GroceryListManager UI.|The grocery list with specified name is created and shown in the GroceryListManager UI|Pass|__________|
|Open a grocery list |Tap on the row of the grocery list you are trying to open.|The tapped grocery list will expand listing all the items inside it.|The tapped grocery list will expand listing all the items inside it.|Pass|__________|
|Delete a selected grocery list |Tap on the 'delete' icon located on the right of grocery list row.|The specified grocery list is removed from the GroceryList UI.|The specified grocery list is removed from the GroceryList UI.|Pass|__________|
|Rename a selected grocery list |Tap on the 'edit' icon located on the right of grocery list row. Enter a name if the field of the popup.|The name of the specified grocery list is updated with the specified new name.|The name of the specified grocery list is updated with the specified new name.|Pass|__________|
|Add an item to a grocery list |Tap on the floating 'add'  icon. Enter an item name in the field of the popup. Tap on the 'save' icon.|The item is added to the grocery list and shown in the GroceryList UI.|The item is added to the grocery list and shown in the GroceryList UI.|Pass|__________|
|Select an item in a grocery list |Tap on the row of the item to select it.|The selected item is highlighted in the GroceryList UI.|N/A|N/A|__________|
|Delete a selected item in a grocery list |Tap on the 'delete' icon located on the right of the item row.|The item is removed from the grocery list and is removed in GroceryList UI.|N/A|N/A|__________|
|Specify a selected quantity for an item in a grocery list |Tap on the 'edit' icon located on the right of item row. Enter a quantity in the quantity field of the popup.|The quantity for an item is updated with the new specified quantity.|The quantity for an item is updated with the new specified quantity.|Pass|No edit icon was located must hover over then then click to edit|
|Check off a selected item in a grocery list |Tap on the checkbox on the left of the item row.|The item is checked off on the grocery list.|The item is checked on the grocery list.|Pass|__________|
|Uncheck off a selected item in a grocery list |Tap on the checkbox on the left of the item row.|The item is unchecked on the grocery list.|The item is unchecked on the grocery list.|Pass|__________|
|Delete a checked item in a grocery list |Tap on the 'options' icon on the top right. Tap on 'delete checked items'. Or tap on the 'delete' icon located on the right of the item row.|The checked item is removed from the grocery list.|N/A|N/A|N/A|
|Check off all items in a grocery list | Tap on the 'options' icon on the top right. Tap on 'check off all items'.|All items are checked off on the grocery list.|N/A|N/A|N/A|
|Uncheck off all item in a grocery list |Tap on the 'options' icon on the top right. Tap on 'uncheck all items'.|All items are are unchecked on the grocey list.|N/A|N/A|N/A|
|Delete all checked items in a grocery list | Tap on the 'options' icon on the top right. Tap on 'delete checked items'. Tap on 'yes' on the confirmation popup.|All checked items are removed from the grocery list.|N/A|N/A|N/A|
