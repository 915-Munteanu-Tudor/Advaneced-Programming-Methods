package View;
import Repository.Repo;
import Controller.Controller;
import Model.*;

import java.awt.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Repo repo = new Repo(2);
        Controller ctrl = new Controller(repo);
        Car c1 = new Car(1234, "qw");
        Truck c2 = new Truck(2345, "wq");
        Motorcycle c3 = new Motorcycle(345, "rt");
        ctrl.add(c3);
        ctrl.add(c1);
        ctrl.add(c2);
        //ctrl.remove(0);

        menu(ctrl);
    }

    public static void menu(Controller ctrl) throws Exception {
        boolean b = true;
        while (b)
        {
            System.out.println("0 - Exit\n");
            System.out.println("1 - Add vechicle\n");
            System.out.println("2 - Remove a vechicle\n");
            System.out.println("3 - Filter vechicles\n");
            System.out.println("4 - Print all the vechicles\n");
            Scanner s = new Scanner(System.in);
            //Scanner sc = new Scanner(System.in);
            System.out.println("Enter the command:");
            String cmd = s.next();
            if (Objects.equals(cmd, "0"))
                break;
            else if (Objects.equals(cmd, "1")){
                System.out.println("What vechicle do you want to add?\n 1.car\n 2.truck\n 3.motorcycle\n");
                System.out.println("Enter the command:");
                String cmd1 = s.next();
                if (Objects.equals(cmd1, "1")){
                    System.out.println("Enter the reparation cost:");
                    int cmd2 = s.nextInt();
                    System.out.println("Enter the name:");
                    String CMD= s.next();
                    Car car = new Car(cmd2,CMD);
                    ctrl.add(car);
                }
                else if(Objects.equals(cmd1, "2")){
                    System.out.println("Enter the reparation cost:");
                    int cmd3 = s.nextInt();
                    System.out.println("Enter the name:");
                    String cmd4 = s.next();
                    Truck truck = new Truck(cmd3,cmd4);
                    ctrl.add(truck);
                }
                else if (Objects.equals(cmd1, "3")){
                    System.out.println("Enter the reparation cost:");
                    int cmd5 = s.nextInt();
                    System.out.println("Enter the name:");
                    String cmd6 = s.next();
                    Motorcycle motorcycle = new Motorcycle(cmd5, cmd6);
                    ctrl.add(motorcycle);
                }
                else
                    System.out.println("Bad command!\n");

            }

            else if (Objects.equals(cmd, "2")){
                System.out.println("What vehicle to remove?");
                System.out.println("Enter it's position:");
                try {
                    int cmd7 = s.nextInt();
                    ctrl.remove(cmd7);
                }
                catch (Exception e){
                    System.out.println("Please enter an integer!\n");
                }



            }

            else if (Objects.equals(cmd, "3"))
                System.out.println(Arrays.toString(ctrl.filter(1000)));

            else if (Objects.equals(cmd, "4")){
                try {
                    System.out.println(Arrays.toString(ctrl.get_all()));
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }

            else
                System.out.println("Bad command!\n");
        }
    }
}