import java.util.LinkedList;
import java.util.List;

import org.w3c.dom.Node;


/**
 * Class to implement a singly linked list
 *
 * @author 
 * @version Spring 2024
 */

 public class SLL<T> implements Phase1SLL<T> {
    public NodeSL<T> head;
    public NodeSL<T> tail;
    public List<NodeSL<T>> nodes;

    /** 
   *  Accessor for head node
   *  @return the head node
   */
  public SLL() {
    nodes=new LinkedList<>();
    // NOT SURE IF THIS IS RIGHT (it just wouldn't let me do a list raw)
    head=null;
    tail=null;
  }


  public NodeSL<T> getHead() {
    /*if(isEmpty()) {
        throw new IndexOutOfBoundsException("Head does not exist.");
    }*/
    return head;
  }
  
  /** 
   *  Accessor for tail node
   *  @return the tail node
   */
  public NodeSL<T> getTail() {
    /*if(isEmpty()) {
        throw new IndexOutOfBoundsException("Tail does not exist.");
    }*/
    return tail;
  }

  /** 
   *  Determines whether a list is empty
   *  @return T/F is the list empty?
   */
  public boolean isEmpty() {
    return head==null;
  }

  /** 
   *  Inserts the given item at the head of the list
   *  @param v item to insert 
   */
  public void addFirst(T v) {
    NodeSL<T> newHead;
    if(isEmpty()) {
        newHead = new NodeSL<T>(v, null);
        tail=newHead;
    }
    else {
        newHead = new NodeSL<T>(v, head);
    }
    head=newHead;
  }
  
  /** Converts to a string representation */
  public String toString() {
    String ret = "[";
    for (NodeSL<T> item = this.head; item != this.tail; item = item.getNext()) {
     // item is a node in the list
     ret+=item.getData();
     ret+=", ";
    }
    if(!isEmpty()) {
        ret+=tail.getData();
    }
    ret+="]";
    return ret;
  }

  // PHASE 2

  /** 
   *  Inserts the given item at the tail of the list
   *  @param item to insert 
   */
  public void addLast(T v) {
    if(isEmpty()) {
        addFirst(v);
        System.out.println("Added first: " + v);
        return;
    }

    NodeSL<T> item = new NodeSL<T>(v, null);
    System.out.println("Previous tail: " + tail.getData());
    tail.setNext(item);
    tail=item;
    System.out.println("New tail: " + tail.getData());
  }

  /** 
   *  Inserts the given item after the specified node
   *  @param here node to insert after
   *  @param v item to insert 
   */
  public void addAfter(NodeSL<T> here, T v) {
    if(here.equals(tail)) {
        addLast(v);
        return;
    }

    System.out.println("Where we want new node to point: " + here.getNext().getData());
    NodeSL<T> item = new NodeSL<T>(v, here.getNext());
    System.out.println("New node: " + item + " pointing at " + item.getNext());
    here.setNext(item);
    System.out.println("Here is now pointing at " + here.getNext());
  }

  /** 
   *  Removes the given item from the head of the list
   *  @return v item removed
   */
  public T removeFirst() {
    try {
      NodeSL<T> ret = head;
      head=head.getNext();
      return ret.getData();
    } catch (Exception e) {
      throw new MissingElementException();
    }
  }

  /** 
   *  Removes the given item from the tail of the list
   *  @return item removed
   */
  public T removeLast() {
    if(this.size()==0) {
      throw new MissingElementException();
    }

    NodeSL<T> ret=tail;

    // if the last item is also the first
    if(head.equals(tail)) {
        head=null;
        return ret.getData();
    }

    // Find the second last node 
        NodeSL<T> second_last = head; 
        while (second_last.getNext().getNext() != null) 
            second_last = second_last.getNext(); 
  
        // Change next of second last 
        second_last.setNext(null); 

    /*System.out.println("Old tail value: " + ret.getData());
    System.out.println("New tail value: " + second_last.getData());*/
    tail=second_last;
    return ret.getData();
  }

  /** 
   *  Removes the node after the given position
   *  @param here marks position to remove after
   *  @return item removed
   */
  public T removeAfter(NodeSL<T> here) {
    if(here==null) {
        return removeFirst();
    }
    if(here==tail) {
        return null;
    }
    System.out.println("Here is " + here.getData());
    if(here.getNext()==tail) {
        return removeLast();
    }

    NodeSL<T> ret=here.getNext();
    here.setNext(ret.getNext());
    System.out.println("    Removing " + ret.getData());
    return ret.getData();
  }

  /**
   *  Returns a count of the number of elements in the list
   *  @return current number of nodes
   */
  public int size(){
    System.out.println("new size() call!");
    try {
      Object x = this.head;
    } catch (Exception e) {
      throw new MissingElementException();
    }

    if(head==null) {
        System.out.println("    Finished with size 0.");
        return 0;
    }
    if(head.equals(tail)) {
        System.out.println("    Finished with size 1.");
        return 1;
    }
    // starts at 1 to include the tail
    int ret = 1;
    for (NodeSL<T> item = this.head; item != this.tail; item = item.getNext()) {
        System.out.println("For loop");
        ret++;
        System.out.println(ret);
    }
    System.out.println("    Finished with size " + ret);
    return ret;
  }

  // PHASE 4

  /** 
   *  Makes a copy of elements from the original list
   *  @param here  starting point of copy
   *  @param n  number of items to copy
   *  @return the copied list
   */
  public SLL<T> subseqByCopy(NodeSL<T> here, int n) {
    SLL<T> copy = new SLL<>();
    System.out.println("Initialized: " + copy);
    NodeSL<T> item = here;

    for(int i=0; i<n; i++) {
      System.out.println("  For loop i="+i+" and copy is " + copy);
      copy.addLast(item.getData());
      item=item.getNext();

      if(item==tail && i==n) {
        System.out.println("n large small for list size.");
        throw new MissingElementException();
      }
    }

    System.out.println(copy + "\n");
    return copy;
  }

  /**
   *  Places copy of the provided list into this after the specified node.
   *  @param list  the list to splice in a copy of
   *  @param afterHere  marks the position in this where the new list should go
   */
  public void spliceByCopy(SLL<T> list, NodeSL<T> afterHere) {
    if(this==list) {
      throw new SelfInsertException();
    }
    if(list.tail==null) {
      return;
    }

    SLL<T> newList = subseqByCopy(list.head, list.size());

    if(afterHere==null) {
      newList.tail.setNext(this.head);
      this.head=newList.head;
      return;
    }

    System.out.println("Splicing in " + list + " into " + this + " after " + afterHere.getData());

    if(afterHere!=tail) {
      newList.tail.setNext(afterHere.getNext());
      System.out.println("  list tail now pointing to " + newList.tail.getNext().getData());
    } else {
      this.tail = newList.tail;
      System.out.println("    Tail changed to " + this.tail.getData());
    }

    afterHere.setNext(newList.head);
    System.out.println("  afterHere now pointing to " + afterHere.getNext().getData());
    System.out.println("New list: " + this);
  }

  /** 
   *  Extracts a subsequence of nodes and returns them as a new list
   *  @param afterHere  marks the node just before the extraction
   *  @param toHere  marks the node where the extraction ends
   *  @return  the new list
   */
  public SLL<T> subseqByTransfer(NodeSL<T> afterHere, NodeSL<T> toHere) {
    SLL<T> ret = new SLL<>();
    if(afterHere==null) {
      ret.head=this.head;
      this.head=toHere.getNext();
    }
    else {
      ret.head=afterHere.getNext();
      afterHere.setNext(toHere.getNext());
    }

    ret.tail=toHere;
    toHere.setNext(null);

    return ret;
  }

  /** 
   *  Takes the provided list and inserts its elements into this
   *  after the specified node.  The inserted list ends up empty.
   *  @param list  the list to splice in.  Becomes empty after the call
   *  @param afterHere  Marks the place where the new elements are inserted
   */
  public void spliceByTransfer(SLL<T> list, NodeSL<T> afterHere) {
    //System.out.println("List starts as " + list);
    //System.out.println("  this starts as " + this);

    if(this==list) {
      throw new SelfInsertException();
    }

    if(afterHere==null) {
      list.tail.setNext(this.head);
      this.head=list.head;
    }
    else {
      list.tail.setNext(afterHere.getNext());
      afterHere.setNext(list.head);
    }

    list= new SLL<>();
    //list.addFirst(null);
    System.out.println("List is now " + list);
    //System.out.println("  this is now " + this);
    //System.out.println("List is now " + list + " \n");
  }
  
 }
