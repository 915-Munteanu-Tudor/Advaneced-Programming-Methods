package Repo;
import Model.PrgState;

import  java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Repo implements IRepo {

    List<PrgState> myPrgStates;

    public Repo() {
        myPrgStates = new LinkedList<>();
    }

    @Override
    public PrgState getCrtPrg() {
        PrgState curr = myPrgStates.get(0);
        myPrgStates.remove(0);
        return curr;
    }

    @Override
    public List<PrgState> getProgramList() {
        return myPrgStates;
    }

    @Override
    public void setProgramList(List<PrgState> programStateList) {
        this.myPrgStates = programStateList;
    }

    @Override
    public void addPrg(PrgState newPrg) {
        myPrgStates.add(newPrg);
    }
}
