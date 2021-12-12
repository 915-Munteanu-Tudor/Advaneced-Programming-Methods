package Model.stmt;

import Exceptions.InterpreterException;
import Exceptions.TypeException;
import Model.PrgState;
import Model.adt.IDict;
import Model.adt.IStack;
import Model.adt.Stack;
import Model.exp.Exp;
import Model.types.BoolType;
import Model.types.IType;
import Model.value.BoolValue;
import Model.value.IValue;

public class WhileStmt implements IStmt {
    Exp expression;
    IStmt statement;

    public WhileStmt(Exp expression, IStmt statement) {
        this.expression = expression;
        this.statement = statement;
    }

    @Override
    public PrgState execute(PrgState state) throws InterpreterException {
        IStack<IStmt> stk = state.getExeStack();
        IValue expVal = expression.eval(state.getSymTable(), state.getHeapTable());
        if (expVal.getType().equals(new BoolType())) {
            if (expVal.equals(new BoolValue(true))) {
                stk.push(new WhileStmt(expression,statement));
                stk.push(statement);
            }
        } else {
            throw new TypeException("Expression not of type bool");
        }
        state.setExeStack(stk);
        return state;
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> symtable) throws InterpreterException {
        return null;
    }

    @Override
    public String toString() {
        return "while(" + expression.toString() + ") { " + statement.toString() + "}";
    }

    @Override
    public IStmt createCopy() {
        return new WhileStmt(expression,statement);
    }
}
