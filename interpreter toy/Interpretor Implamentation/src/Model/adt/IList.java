package Model.adt;

public interface IList<T> {
    public T pop();
    void add(T v);
    String toString();
    boolean empty();
    void clear();
    int size();
    int get(T v);
    void remove(int pos);
}
