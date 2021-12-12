package Model.adt;
import Exceptions.AdtException;

import java.util.*;

public interface IStack<T> {

    T pop() throws AdtException;
    void push(T v);
    boolean isEmpty();
//    T top() throws RuntimeException;
    int size();
    String toString();
}

