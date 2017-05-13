package hw2;

/**
 * An interface for a position, which is a holder object storing a
 * single element.
 * @author Roberto Tamassia, Michael Goodrich
 */
//begin#fragment All
public interface Position<E> {
  /** Return the element stored at this position. 
 * @throws InvalidPositionException */
  E element() throws InvalidPositionException;
}
//end#fragment All
