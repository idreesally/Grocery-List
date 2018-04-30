# design-discussion.md

#### Team 3:
1.	Nhi Tran
2.	Ume Abbas
3.	Kraig Tapang
4.	Mohammad Abdel Qader
5.	Idrees Ally

---

#### Design 1:
![Screen Shot](https://i.imgur.com/FWVYMEc.png)
The first thing we discussed about Design 1 were the multiple inheritance relationships  (represented by a line with a hollow triangle) and discussed if they were correctly implemented. During the group discussion we discussed that an inheritance relationship indicates that of the two related classes, the subclass is considered to be a specialized form of the the superclass and that the generalization of an inheritance relationship is otherwise known as a "is a" relationship.  The inheritance relationship of the ```ItemType``` class being derived from the ```Item``` superclass in this diagram is correctly defined. We also discussed the necessity of the ```User``` class and ```GroceryStore``` class and ultimately decided to omit those classes from team design.

---

#### Design 2:
![Screen Shot](https://i.imgur.com/xdwoTrV.png)
For Design 2 we questioned if the strong composition relationship between the ```GroceryListManager``` class and ```Item``` class was correctly defined. After reviewing the other diagrams, we suggested to discard that relationship in the team design. We agreed that the multiplicities were correct, encapsulation was implemented, and the simplicity of the UML diagram as a whole was readable which played an influence to the team design.

---

#### Design 3:
![Screen Shot](https://i.imgur.com/2NJML5D.png)
What stood out to the group was the amount of subclasses derived from the ```GroceryCategory``` class. We agreed that sometimes less is more, and decided to scrap those subclasses and deemed them unnecessary. We justified this decision by checking if a subclass would have at least one method, attribute, or relation that another subclass wouldn't have.  We also noted that no multiplicities were stated and the visibility of all attributes and methods were set to public, we briefly discussed how we would implement encapsulation in the team design.

---

#### Design 4:
![Screen Shot](https://i.imgur.com/EAlWEdd.png)
We discussed that the ```GroceryListManager``` class and ```GroceryList``` class both needed at least one variable or some type of data structure each to store the various grocery lists and the items of each grocery list. We also proposed to merge the ```CheckListItem``` class and the ```Item``` class together for the team design.

---

#### Design 5:
![Screen Shot](https://i.imgur.com/7zG1jzj.png)
Design 5 included a database diagram which sparked up ideas of how  we could implement a relational database where the  ```ShoppingList``` class could fetch each item. We decided to withhold including a database diagram for the team design as it was inconsistent with the individual designs. The relationships and dependencies were also not completely clear between the ```FoodList``` class and the ```ShoppingList``` class in the diagram, however, the listed functions were done properly.

---

#### Team Design:
![Screen Shot](https://i.imgur.com/nNq0m3F.png)
After analyzing all the designs as a group and discussed the positives and negatives of each, we were able to come up with a team design that was easy to understand, cleaner than our individual designs, and showed consistency amongst each individual design. We finished off the group discussion by going over the requirements one by one and verified each condition was met.

---

#### Summary:
The teams design has been constructed from the following:
1.	Analysis of the individual designs.
2.	Listing the sets of pros and cons.
3.	Deciding the classes that are important for the teams design.  
4.	Discussions over the relationships among the classes.
5.	Collaboratively working on the functionalities(functions) and data structures for each class.  

Lessons Learnt:
1.	We learnt some major differences between the two hierarchies:
	*	Whole-part relations
	*	Inheritance
2.	Multiplicities played a pivotal role in giving us a better understanding of the relations among the classes.
3.	We discussed the purpose of each class, and were able to relate the dependencies among them.
4.	By Listing pros and cons we were able to extract irrelevant classes from our design.
