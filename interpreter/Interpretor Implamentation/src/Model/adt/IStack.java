package Model.adt;
import java.util.*;

public interface IStack<T> {

    T pop() throws RuntimeException;
    void push(T v);
    boolean isEmpty();
//    T top() throws RuntimeException;
    int size();
    String toString();
}

