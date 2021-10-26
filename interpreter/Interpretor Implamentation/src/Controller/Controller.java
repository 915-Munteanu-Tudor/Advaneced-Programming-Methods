package Controller;
import Model.PrgState;
import Model.adt.IStack;
import Model.stmt.IStmt;
import Repo.IRepo;

public class Controller {
    final IRepo repo;
    boolean displayAll;

    public Controller(IRepo repo) {
        this.repo = repo;
        this.displayAll = false;
    }

    public void addProgram(PrgState newPrg) {
        repo.addPrg(newPrg);
    }

    public PrgState oneStep(PrgState state) throws RuntimeException{
        IStack<IStmt> stk = state.getExeStack();
        if (stk.isEmpty()) {
            displayAll = false;
            throw new RuntimeException("Prg state is empty!");
        } else
            displayAll = true;
        IStmt crtStmt = stk.pop();
        return crtStmt.execute(state);
    }

    public void allStep(){
        PrgState prg = repo.getCrtPrg();
        if (displayAll)
            System.out.println(displayState(prg));
        while (!prg.getExeStack().isEmpty()) {
            try {
                prg = oneStep(prg);
                if (displayAll)
                    System.out.println(displayState(prg));
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        System.out.println(getOutput(prg));
    }

    public void setDisplayAll(Boolean displayAll) {
        this.displayAll = displayAll;
    }

    public String displayState(PrgState state) {
        return state.toString();
    }

    public String getOutput(PrgState state) {
        return state.getOut().toString();
    }

    public void typeCheck() {
        repo.getProgramList().get(0).typeCheck();
    }
}
