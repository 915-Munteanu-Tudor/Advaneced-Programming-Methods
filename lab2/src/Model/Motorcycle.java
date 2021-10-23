package Model;

public class Motorcycle implements Vechicles{

    private int repair_cost;
    public String name;

    public Motorcycle(int i, String nm)
    {
        this.repair_cost = i;
        this.name = nm;
    }

    @Override
    public int get_repair_cost()
    {
        return repair_cost;
    }

    @Override
    public String get_name() {
        return name;
    }

    public void setRepair_cost(int repair_cost)
    {
        this.repair_cost = repair_cost;
    }

    @Override
    public boolean check_repair_cost(int price)
    {
        return (this.repair_cost > price);
    }

    @Override
    public String toString()
    {
        return "Motorcycle(repairCost=" + this.repair_cost + ", name=" + this.name + " )";
    }
}
