package Model.stmt;


import Exceptions.InterpreterException;
import Exceptions.TypeException;
import Model.PrgState;
import Model.adt.IDict;
import Model.types.IType;
import Model.types.IntType;
import Model.value.IValue;
import Model.value.IntValue;

public class CountDownStmt implements IStmt {
    String var;

    public CountDownStmt(String var) {
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState state) throws InterpreterException {

        if (state.getSymTable().isDefined(var)) {
            IValue foundIndex = state.getSymTable().lookup(var);
            if (foundIndex.getType().equals(new IntType())) {
                int found = ((IntValue) foundIndex).getValue();
                if (state.getLatchTbl().exists(found)) {
                    if (state.getLatchTbl().get(found) > 0) {
                        state.getLatchTbl().update(found, state.getLatchTbl().get(found) - 1);
                    }
                    state.getOut().add(new IntValue(state.getId()));
                } else {
                    throw new InterpreterException("Latch does not exist");
                }
            } else {
                throw new InterpreterException("Variable not of type int");
            }
        } else {
            throw new InterpreterException("Variable not defined");
        }
        return null;
    }

    @Override
    public IStmt createCopy() {
        return new CountDownStmt(var);
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> table) throws InterpreterException {
        IType variableType = table.lookup(var);
        if (variableType.equals(new IntType())) {
            return table;
        }
        throw new TypeException("Variable not of type int");
    }

    @Override
    public String toString() {
        return "countDown (" + var + ")";
    }
}
