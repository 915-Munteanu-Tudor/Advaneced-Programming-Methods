package Model.stmt;


import Model.PrgState;
import Model.adt.IDict;
import Model.adt.IList;
import Model.adt.IStack;
import Model.exp.Exp;
import Model.types.IType;
import Model.value.IValue;

public class PrintStmt implements IStmt{

    final Exp expression;

    public PrintStmt(Exp exp){
        this.expression = exp;
    }

    @Override
    public String toString() {
        return "print(" + expression.toString() + ")";
    }

    public PrgState execute(PrgState state){
        IStack<IStmt> stk = state.getExeStack();
        IList<IValue> output = state.getOut();
        output.add(expression.eval(state.getSymTable()));
        state.setExeStack(stk);
        state.setOut(output);
        //return state;
        return state;
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> symtable) throws RuntimeException {
        expression.typecheck(symtable);
        return symtable;
    }

    @Override
    public IStmt createCopy() {
        return new PrintStmt(expression);
    }
}
