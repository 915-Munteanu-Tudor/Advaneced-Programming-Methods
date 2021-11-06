package View;
import Exceptions.InterpreterException;
import Model.adt.*;
import Model.exp.ArithExp;
import Model.exp.ConstExp;
import Model.exp.ValueExp;
import Model.exp.VarExp;
import Model.stmt.*;
import Model.types.BoolType;
import Model.types.IntType;
import Model.value.BoolValue;
import Model.value.IValue;
import Model.value.IntValue;
import Repo.Repo;
import Repo.IRepo;
import Controller.Controller;
import Model.PrgState;

import java.io.IOException;
import java.util.Scanner;


public class Main {


    public static void main(String[] args) throws InterpreterException {
        PrgState state1, state2, state3;
        Controller ctrl1, ctrl2, ctrl3;
        try {
            IStmt ex3 = new CompStmt(new VarDeclStmt("a", new BoolType()), new CompStmt(new VarDeclStmt("v",
                    new IntType()), new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                    new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new IntValue(2))),
                            new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
                            VarExp("v"))))));
            state3 = new PrgState(new Stack<>(), new Dict<>(), new List<>(), new Dict<>(), ex3);
            IRepo repo3 = new Repo("logExample1.txt");
            repo3.addPrg(state3);
            ctrl3 = new Controller(repo3);


            IStmt ex2 = new CompStmt(new VarDeclStmt("v", new IntType()),
                    new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))),
                            new PrintStmt(new VarExp("v"))));
            state2 = new PrgState(new Stack<>(), new Dict<>(), new List<>(), new Dict<>(), ex2);
            IRepo repo2 = new Repo("logExample2.txt");
            repo2.addPrg(state2);
            ctrl2 = new Controller(repo2);

            IStmt ex1 = new CompStmt(new VarDeclStmt("a", new IntType()), new CompStmt(new VarDeclStmt("b", new IntType()),
                    new CompStmt(new AssignStmt("a", new ArithExp('+', new ValueExp(new IntValue(2)), new ArithExp('*',
                            new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))), new CompStmt(
                            new AssignStmt("b", new ArithExp('+', new VarExp("a"), new ValueExp(new IntValue(1)))),
                            new PrintStmt(new VarExp("b"))))));
            state1 = new PrgState(new Stack<>(), new Dict<>(), new List<>(), new Dict<>(), ex1);
            IRepo repo1 = new Repo("logExample1.txt");
            repo1.addPrg(state1);
            ctrl1 = new Controller(repo1);

            TextMenu menu = new TextMenu();
            menu.addCommand(new ExitCmd("0", "exit"));
            menu.addCommand(new RunExemple("1", ex1.toString(), ctrl1));
            menu.addCommand(new RunExemple("2", ex2.toString(), ctrl2));
            menu.addCommand(new RunExemple("3", ex3.toString(), ctrl3));
            menu.show();
        } catch (InterpreterException exception) {
            System.out.println(exception.getMessage());
        }

    }
}


//        label:
//        while (true) {
//
//            System.out.println("\n0 - Exit\n");
//            System.out.println("1 - Run the first exemple\n");
//            System.out.println("2 - run the second exemple\n");
//            System.out.println("3 - Run the 3rd exemple\n");
//            Scanner s = new Scanner(System.in);
//            System.out.println("Enter the command:");
//            String cmd = s.next();
//            switch (cmd) {
//                case "0":
//                    System.out.println("Exit!");
//                    break label;
//                case "1": {
//                    IDict<String, IValue> symTable = new Dict<String, IValue>();
//                    IList<IValue> out = new List<IValue>();
//                    IStack<IStmt> exeStack = new Stack<>();
//                    PrgState myPrgState = new PrgState(exeStack, symTable, out, ex1);
//
//                    myController.addProgram(myPrgState);
//                    myController.allStep();
//                    break;
//                }
//                case "2": {
//                    IDict<String, IValue> symTable = new Dict<String, IValue>();
//                    IList<IValue> out = new List<IValue>();
//                    IStack<IStmt> exeStack = new Stack<>();
//                    PrgState myPrgState = new PrgState(exeStack, symTable, out, ex2);
//
//                    myController.addProgram(myPrgState);
//                    myController.allStep();
//                    break;
//                }
//                case "3": {
//                    IDict<String, IValue> symTable = new Dict<String, IValue>();
//                    IList<IValue> out = new List<IValue>();
//                    IStack<IStmt> exeStack = new Stack<>();
//                    PrgState myPrgState = new PrgState(exeStack, symTable, out, originalProgram1);
//
//                    myController.addProgram(myPrgState);
//                    myController.allStep();
//                    break;
//                }
//            }
//        }
//    }


