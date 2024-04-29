# Assignment 3: Linked List Implementation

For this assignment you will develop your own implementation of a singly linked list (SLL).  There are a lot of files in this assignment, so take a deep breath and let's walk through them. The hope is that breaking things into pieces (hence the many files) will help you to start with the basic properties of an SLL and expand outwards until you have built a fully operational implementation of this data structure.

## Files Provided

We have included the following classes that you need to reference, but DO NOT need to make changes to:
- `NodeSL.java`, the class defining a very important object: the individual nodes that make up an SLL
- `MissingElementException.java` and `SelfInsertException.java`, 2 classes that describe specific types of exceptions relevant to linked lists. You will use these in **Phase 3**
- 3 interfaces labeled `Phase1SLL`, `Phase2SLL`, and `Phase4SLL` (there is no interface for Phase 3). You will implement each interface in its corresponding phase
- 1 test class, `SLLTest.java`. These are tests we are providing to help guide your SLL development. Feel free to add additional tests as you see fit, but it's not specifically required.

The only class you need to edit is `SLL.java`. This is where you will develop your code to implement a linked list.

## Vocabulary

- SLL: Singly Linked List
- Stub: A short method definition that has the correct call signature and the minimal implementation required for clean compilation. When you implement an interface, you will need to make sure that every method in the interface is present in your implementation class. Otherwise, your code will fail to compile. Additionally, you'll need to make sure you return something of the correct type. For example, a `stub` of a method that returns an integer could just `return 0;`. As you develop your code, you will replace this with your actual implementation of the method.
- Copy Style Copying: A method for making a deep copy (see class slides)
- Transfer Style Copying: A method for changing a data structure where the original structure is modified to add/remove information 

## Phase 1: Implementing a Basic List Structure

The goal of Phase 1 is to implement a minimal set of methods to create a linked list with a few elements in it, verify that it has the correct structure, and print the elements to the console.

### Adding Stubs

Your `SLL` class will need to implement ALL of the methods outlined in the interface `Phase1SLL.java`. If any are mising (or named incorrectly), it won't compile. Therefore, your first step should be to write stubs for each method in the Phase 1 interface.

### Class Variables and Constructor

The interface does not include the class variables, so think about what properties you want your `SLL` objects to have.

Next, flesh out the constructor stub to implement the creation of an empty list. An empty list will have `head` and `tail` references and set them both to `null`.

### Displaying Lists

The method `toString` will allow you to print lists. Write a method that will assemble and return a `String` variable that contains the `data` from each node in the list in order. 

For example, suppose we have a list of three `NodeSL` objects like `C`-->`B`-->`A`. We want to print a String that reads "[C, B, A]".

To implement the general case:
* Define a variable to hold your accumulating string as you traverse the list
* Initialize your string with a bracket ("[")
* Next, traverse your list, looping through all elements except the last element and adding them to the string with trailing commas (e.g., "B,")
* Finally, add the last element along with a closing bracket ("]")

We also want to account for a special case: if the list is empty, you won't find anything to print because there won't be any nodes to traverse. Instead, you will just need to add a closing bracket to your string (to get "[]"), and then it will be ready to print.

#### Example Code

Below is a `for` loop that will traverse the list, stopping just before the last element.  You can use it as a building block to construct your `toString` method:

`for (Node item = this.head; item != list.tail; item = item.next) {
     // item is a node in the list
   }`

Once your `toString` method is completed, you will be able to display the contents of your list in the usual way:

   System.out.println(list);

### Filling in the Stubs

Next, flesh out your stubs by adding the code needed to implement these methods:
* A method to check whether a list `isEmpty` (think about what this means)
* Accessors to `getHead` and `getTail` of the list
* The method `addFirst` to add a new element at the front of the list.  

In all cases, make sure you can handle the special case where the list is initially empty.

### Checking Your Work

Once you have written stubs for all the required methods, your program should compile, and you can run the Phase1 tests in `TestMethods.java`. Make sure you pass the tests before moving on to Phase 2.

## Phase 2:  More List Manipulators

Now that you've gotten a basic list up and running, you will want to add methods that let you manipulate the contents in different ways.  We strongly suggest drawing a picture of each scenario before you try to implement it (you can include this in your `Implementation.md`). Make sure you think through what links will need to be updated in each case, and think about special cases that should be handled.

### Phase 2 Stubs

Here you will work through the methods outlined in `Phase2SLL.java`.
* Start by adding stubs for all of the methods
* Then work on `removeFirst`
* Next do `addLast` and `removeLast`
* After that, work on `addAfter` and `removeAfter`
* Finally, add a `size` method.  It needs to loop through all the elements to count how many there are.

Tackle one at a time, and try to get it passing all the relevant tests before going on to the next one.  **No, really!**  You want to catch any bugs in one method before going on to the next, otherwise you'll never be sure whether a problem is in the new thing you are working on or something earlier.

While working on this stage, it's pretty easy to accidentally write code that goes into an infinite loop.  If your program seems frozen, **that's a sign**.  While developing, you may want to include a print statement inside every loop so you can easily detect this sort of bug.  Only remove it (or comment out) when you are sure that things are working properly.  If you do find that your code is looping infinitely, try drawing a picture of what is happening to understand why.

## Phase 3:  Exceptions

There is no interface for this phase.  Instead of adding new methods, you will make sure that the existing ones handle certain erroneous conditions by throwing an appropriate exception. 

We have defined two exception classes for you. One is `MissingElementException.java` and the other is `SelfInsertException.java`.  For each, read the documentation provided in the class and figure out if any of your existing methods should throw that exception, and under which circumstances.

Note that you can throw an exception like so:
   `throw new MissingElementException();`

You should also throw these exceptions as needed in the copy methods you develop in Phase 4.

## Phase 4:  Whole-List Operations

The first three phases were about building up and editing single lists.  In this phase, you will add methods that split and combine lists like we talked about in class.  You will write methods to implement both copy style and transfer style approaches to create copies of a list.  As in the previous phase, you should make sure you are running tests for each method as you move through Phase 3.

### Copy Style

Begin by writing a **copy constructor**.  As its name indicates, this is a constructor that takes a linked list object as its sole parameter and returns a *deep copy* of its structure.  This will be a completely separate memory structure with the same number of elements, holding the same values.  You can make it by looping through the original structure and copying one node at a time. Feel free to use methods you have already written to help you implement this.

Next, use your copy constructor to implement slice and splice methods:
* `subseqByCopy(here,n)` returns a copy of a subsequence of the list (slice).  The subsequence starts at `here` and contains `n` nodes.  If the original list is too short to provide the requested number of nodes, it should throw an exception. `this` should remain unchanged.
* `spliceByCopy(list,afterHere)` copies the nodes of `list` and adds them to `this` following the node `afterHere`.  If `afterHere` is null it adds them at the head of `this`.  The contents of `list` remain unchanged.

### Transfer Style

For transfer style, you will write `subseqByTransfer` and `spliceByTransfer`.  These should transfer nodes as a group from one list to another just by updating a few links, and will not require looping.
* `subseqByTransfer(afterHere,toHere)` extracts a subsequence out of the original list and returns it as a new list (thus shortening the original list).  The extracted sequence begins with the element following `afterHere` and goes up to and including `toHere`.  The original list should skip from `afterHere` to the element that originally followed `toHere`.  If `afterHere` is `null` it should extract a sequence from the head of the list onwards.
* `spliceByTransfer(list,afterHere)` moves all the elements of `list` into `this` just after the node `afterHere`.  The input argument `list` should be left empty after the transfer.
