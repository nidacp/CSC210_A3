/**
 * Exception for cases when trying to operate on an element
 * that does not exist. For example, trying to remove
 * an element from an empty list, or the one following
 * the last element
 */
public class MissingElementException extends RuntimeException {
  /** default constructor */
  public MissingElementException() {
    super();
  }
}