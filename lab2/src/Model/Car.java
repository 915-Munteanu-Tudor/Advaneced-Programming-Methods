package Model;

public class Car implements Vechicles{
    private int repair_cost;
    public String name;

    public Car(int i, String name)
    {
        this.repair_cost = i;
        this.name = name;
    }


    @Override
    public int get_repair_cost()
    {
        return repair_cost;
    }

    @Override
    public String get_name() {
        return null;
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
        return "Car(repairCost=" + this.repair_cost + ", name=" + this.name + ")";
    }
}
