package Repo;
import Model.PrgState;
import  java.io.*;
import java.util.LinkedList;
import java.util.List;

public interface IRepo {
    void addPrg(PrgState newPrg);
    PrgState getCrtPrg();
    List<PrgState> getProgramList();
    void setProgramList(List<PrgState> programStateList);



}
