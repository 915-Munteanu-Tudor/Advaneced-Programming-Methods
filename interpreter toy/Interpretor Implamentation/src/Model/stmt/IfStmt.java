package Model.stmt;

import Model.PrgState;
import Model.adt.Dict;
import Model.adt.IDict;
import Model.adt.IStack;
import Model.exp.Exp;
import Model.types.BoolType;
import Model.types.IType;
import Model.value.BoolValue;
import Model.value.IValue;

import java.util.Map;

public class IfStmt implements IStmt {
    final Exp expression;
    final IStmt thenStmt;
    final IStmt elseStmt;

    public IfStmt(Exp expression, IStmt thenStmt, IStmt elseStmt) {
        this.expression = expression;
        this.thenStmt = thenStmt;
        this.elseStmt = elseStmt;
    }

    @Override
    public String toString() {
        return "( IF (" + expression.toString() + ") THEN (" + thenStmt.toString() + ") ELSE (" + elseStmt.toString() + "))";
    }

    @Override
    public PrgState execute(PrgState state) throws RuntimeException {
        IStack<IStmt> stk = state.getExeStack();
        IValue cond = expression.eval(state.getSymTable());
        if (!cond.getType().equals(new BoolType())) {
            throw  new RuntimeException("Condition is not a boolean type");
        }
        if (cond.equals(new BoolValue(true))) {
            stk.push(thenStmt);
        } else {
            stk.push(elseStmt);
        }
        state.setExeStack(stk);
        //return null;
        return state;
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> symtable) throws RuntimeException {
        IType expressionType = expression.typecheck(symtable);
        if (expressionType.equals(new BoolType())) {
            thenStmt.typecheck(clone(symtable));
            elseStmt.typecheck(clone(symtable));
            return symtable;
        } else {
            throw new RuntimeException("Condition not of type bool");
        }
    }

    private IDict<String, IType> clone(IDict<String, IType> symtable) {
        IDict<String,IType> newSymTbl = new Dict<>();
        for (Map.Entry<String, IType> entry:symtable.getContent().entrySet()) {
            newSymTbl.add(entry.getKey(), entry.getValue());
        }

        return newSymTbl;
    }

    @Override
    public IStmt createCopy() {
        return new IfStmt(expression,thenStmt,elseStmt);
    }
}
