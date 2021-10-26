package Model.adt;

import java.util.Collection;
import java.util.Map;

public interface IDict<T1,T2>{
    T2 lookup(T1 id);
    boolean isDefined(T1 id);
    void update(T1 v1, T2 v2) throws RuntimeException;
    void add(T1 v1, T2 v2) throws  RuntimeException;
    void remove(T1 id) throws RuntimeException;
    Map<T1, T2> getContent();

    String toString();
    boolean isEmpty();
    Collection<T2> values();
}
