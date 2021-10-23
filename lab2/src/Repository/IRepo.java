package Repository;
import Model.Vechicles;
import java.io.IOException;

public interface IRepo {
    public void add(Vechicles v) throws Exception;
    public void remove(int pos) throws Exception;
    public Vechicles[] filter(int price) throws Exception;
    public Vechicles[] get_vechicles() throws Exception;
    public int get_current_index() throws Exception;
}
