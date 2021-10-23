package Controller;
import Model.Vechicles;
import Repository.IRepo;
import Repository.Repo;

public class Controller {
    private Repo repo;

    public Controller(Repo r){
        super();
        this.repo = r;
    }

    public void add(Vechicles v) throws Exception
    {
        try {
            repo.add(v);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void remove(int pos) throws Exception
    {
        try {
            repo.remove(pos);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public Vechicles[] get_all() throws Exception
    {
        if (repo.get_vechicles().length == 0){
            throw new Exception("The array is empty!\n");
        }
        return repo.get_vechicles();

    }

    public int get_index()
    {
        return repo.get_current_index();
    }

    public Vechicles[] filter(int price) throws Exception
    {
        try{
            return repo.filter(price);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
