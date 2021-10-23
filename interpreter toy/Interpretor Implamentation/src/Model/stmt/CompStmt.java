package Model.stmt;

import Model.PrgState;
import Model.adt.IDict;
import Model.adt.IStack;
import Model.types.IType;

public class CompStmt implements IStmt{
    final IStmt stmt1;
    final IStmt stmt2;

    public CompStmt(IStmt st1, IStmt st2) {
        this.stmt1 = st1;
        this.stmt2 = st2;
    }

    @Override
    public PrgState execute(PrgState state) throws RuntimeException {
        IStack<IStmt> stk = state.getExeStack();
        stk.push(stmt2);
        stk.push(stmt1);
        state.setExeStack(stk);
        //return null;
        return state;
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> symtable) throws RuntimeException {
        return stmt2.typecheck(stmt1.typecheck(symtable));
    }

    @Override
    public String toString(){ return this.stmt1.toString() + '\n' + this.stmt2.toString(); }

    @Override
    public IStmt createCopy() {
        return new CompStmt(stmt1, stmt2);
    }
}
