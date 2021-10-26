package Model;
import Model.adt.*;
import Model.stmt.IStmt;
import Model.value.IValue;

public class PrgState {

    IStack<IStmt> exeStack;
    IDict<String, IValue> symTable;
    IList<IValue> out;
    IStmt originalProgram; //optional field, but good to have

    public PrgState(IStack<IStmt>stk, IDict<String, IValue> symtbl, IList<IValue> ot, IStmt stmt){
        exeStack = stk;
        symTable = symtbl;
        out = ot;
        originalProgram = stmt.createCopy();
        stk.push(stmt);
    }

    public void typeCheck() throws RuntimeException{
        originalProgram.typecheck(new Dict<>());
    }

    public Boolean isNotCompleted() {
        return !exeStack.isEmpty();
    }

    public PrgState oneStep() throws RuntimeException {
        if (exeStack.isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        IStmt currentStatement = exeStack.pop();
        return currentStatement.execute(this);
    }

    @Override
    public String toString() {
        return
                " --------Execution Stack-------- \n" +
                exeStack.toString() + '\n' +
                " -------- Symbol  Table -------- \n" +
                symTable.toString() + '\n' +
                " -------- Output Console -------- \n" +
                out.toString() + "\n" +
                " ------------------------------- \n\n\n";
    }

    public IStack<IStmt> getExeStack() {
        return exeStack;
    }

    public void setExeStack(IStack<IStmt> exeStack) {
        this.exeStack = exeStack;
    }

    public void setSymTable(IDict<String, IValue> symTable) {
        this.symTable = symTable;
    }

    public IDict<String, IValue> getSymTable() {
        return symTable;
    }

    public IList<IValue> getOut() {
        return out;
    }

    public void setOut(IList<IValue> out) {
        this.out = out;
    }

    public IStmt getOriginalProgram() {
        return originalProgram;
    }

    public void setOriginalProgram(IStmt originalProgram) {
        this.originalProgram = originalProgram;
    }


}