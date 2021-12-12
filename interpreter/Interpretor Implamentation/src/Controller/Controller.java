package Controller;
import Model.PrgState;
import Model.adt.IStack;
import Model.stmt.IStmt;
import Model.value.IValue;
import Model.value.RefValue;
import Repo.IRepo;
import Exceptions.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Controller {
    final IRepo repo;
    //boolean displayAll;

    public Controller(IRepo repo) {
        this.repo = repo;
        //this.displayAll = false;
    }

    public void addProgram(PrgState newPrg) {
        repo.addPrg(newPrg);
    }

    private Map<Integer, IValue> unsafeGarbageCollector(List<Integer> symTblAddr, Map<Integer, IValue>heap) {
        return heap.entrySet().stream()
                .filter(e -> symTblAddr.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    List<Integer> getAddrFromSymTbl(Collection<IValue> symTblVals) {
        return  symTblVals.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> {RefValue v1 = (RefValue) v; return v1.getAddress();})
                .collect(Collectors.toList());
    }

    public PrgState oneStep(PrgState state) throws InterpreterException {
        IStack<IStmt> stk = state.getExeStack();
        if (stk.isEmpty()) {
           // displayAll = false;
            throw new InterpreterException("Prg state is empty!");
        }
        //else
        //    displayAll = true;
        IStmt crtStmt = stk.pop();
        return crtStmt.execute(state);
    }

    public void allStep() throws InterpreterException{
        PrgState prg = repo.getCrtPrg();
        repo.logPrgStateExec(prg);
//        if (displayAll)
//            System.out.println(displayState(prg));
        while (!prg.getExeStack().isEmpty()) {
            try {
                PrgState prg1 = oneStep(prg);
                repo.logPrgStateExec(prg1);
                prg.getHeapTable().setContent(unsafeGarbageCollector(
                        getAddrFromSymTbl(prg.getSymTable().getContent().values()),
                                prg.getHeapTable().getContent()));
//                if (displayAll)
//                    System.out.println(displayState(prg));
            } catch (InterpreterException e){
                throw new InterpreterException(e.getMessage());
            }
        }
        //System.out.println(getOutput(prg));
    }

//    public void setDisplayAll(Boolean displayAll) {
//        this.displayAll = displayAll;
//    }

    public String displayState(PrgState state) {
        return state.toString();
    }

    public String getOutput(PrgState state) {
        return state.getOut().toString();
    }

    public void typeCheck() throws InterpreterException {
        repo.getProgramList().get(0).typeCheck();
    }
}
