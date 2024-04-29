public interface Phase1SLL<T> {
  /** 
   *  Accessor for head node
   *  @return the head node
   */
  public NodeSL<T> getHead();
  
  /** 
   *  Accessor for tail node
   *  @return the tail node
   */
  public NodeSL<T> getTail();

  /** 
   *  Determines whether a list is empty
   *  @return T/F is the list empty?
   */
  public boolean isEmpty();

  /** 
   *  Inserts the given item at the head of the list
   *  @param v item to insert 
   */
  public void addFirst(T v);
  
  /** Converts to a string representation */
  public String toString();

}