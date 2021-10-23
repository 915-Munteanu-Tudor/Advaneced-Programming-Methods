package Repository;
import Model.Vechicles;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Pattern;

public class Repo implements IRepo{
    private int current_index;
    private int size;
    private Vechicles[] vechicles_repo;

    public Repo(int size)
    {
        this.current_index = 0;
        this.size = size;
        this.vechicles_repo = new Vechicles[size];

    }

    @Override
    public void add(Vechicles v) throws Exception {
        if (current_index >= size)
        {
            size = 2*size;
            Vechicles[] arr = new Vechicles[size];
            arr = Arrays.copyOf(vechicles_repo, size);
            vechicles_repo = Arrays.copyOf(arr, size);
        }
        if (v.get_repair_cost() < 0)
            throw new Exception("Cost cannot be negatuve!\n");
        this.vechicles_repo[current_index] = v;
        current_index++;
    }

    @Override
    public void remove(int pos) throws Exception {
        if (current_index == 0)
            throw new Exception("Array is empty!\n");
        else if (pos < 0 || pos > current_index)
            throw new Exception("This position does not exist\n");
//        for (int i = pos; i < current_index-1; i++) {
//            vechicles_repo[i] = vechicles_repo[i+1];
//        }

        Vechicles[] copyArray = new Vechicles[size];
        System.arraycopy(vechicles_repo, 0, copyArray, 0, pos);
        System.arraycopy(vechicles_repo, pos + 1, copyArray, pos, vechicles_repo.length - pos - 1);
        vechicles_repo = copyArray;
        current_index--;
    }

    @Override
    public Vechicles[] filter(int price) throws Exception {
        Vechicles[] rez = new Vechicles[current_index];
        int i = 0;
        for (int index =0;index<current_index;index++){
            if (vechicles_repo[index].check_repair_cost(price)){
                rez[i] = vechicles_repo[index];
                i++;
            }
        }
        if (rez.length == 0)
            throw new Exception("No vehicles to filter\n");

        return (Vechicles[]) Arrays.stream(rez).filter(Objects::nonNull).toArray(Vechicles[]::new);
    }

    @Override
    public Vechicles[] get_vechicles() throws Exception {
        return (Vechicles[]) Arrays.stream(vechicles_repo).filter(Objects::nonNull).toArray(Vechicles[]::new);
    }

    @Override
    public int get_current_index() {
        return current_index;
    }

}
