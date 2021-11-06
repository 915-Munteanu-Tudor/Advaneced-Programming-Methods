package Controller;
import Model.PrgState;
import Model.adt.IStack;
import Model.stmt.IStmt;
import Repo.IRepo;
import Exceptions.*;

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
//                if (displayAll)
//                    System.out.println(displayState(prg));
            } catch (Exception e){
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
