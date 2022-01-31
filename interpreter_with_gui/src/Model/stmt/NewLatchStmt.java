package Model.stmt;

import Exceptions.InterpreterException;
import Exceptions.TypeException;
import Model.PrgState;
import Model.adt.IDict;
import Model.exp.Exp;
import Model.types.IType;
import Model.types.IntType;
import Model.value.IValue;
import Model.value.IntValue;
import com.sun.jdi.Value;

public class NewLatchStmt implements IStmt{
    String id;
    Exp expression;

    public NewLatchStmt(String id, Exp expression) {
        this.id = id;
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws InterpreterException {
        IValue expressionResult = expression.eval(state.getSymTable(), state.getHeapTable());
        if (expressionResult.getType().equals(new IntType())) {
            IntValue num1 = (IntValue) expressionResult;
            if (state.getSymTable().isDefined(id)) {
                IValue variableValue = state.getSymTable().lookup(id);
                if (variableValue.getType().equals(new IntType())) {
                    int addr = state.getLatchTbl().allocate(num1.getValue());
                    state.getSymTable().update(id, new IntValue(addr));
                }
            } else throw new InterpreterException("Variable not defined");
        } else {
            throw new InterpreterException("Expression not of type int");
        }
        return null;
    }

    @Override
    public IStmt createCopy() {
        return new NewLatchStmt(id, expression);
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> table) throws InterpreterException {
        IType variableType = table.lookup(id);
        IType expressionType = expression.typecheck(table);
        if (variableType.equals(new IntType()) && expressionType.equals(new IntType())) {
            return table;
        }
        throw new TypeException("Latch must be type int");
    }

    @Override
    public String toString() {
        return "newLatch (" + id + ", " + expression.toString() + ")";
    }

}
