package Model.stmt;

import Exceptions.AssignmentException;
import Exceptions.InterpreterException;
import Model.PrgState;
import Model.adt.IDict;
import Model.adt.IStack;
import Model.exp.Exp;
import Model.types.IType;
import Model.types.IntType;
import Model.types.StringType;
import Model.value.IValue;
import Model.value.IntValue;
import Model.value.StringValue;

import java.io.BufferedReader;
import java.io.IOException;

public class readFileStmt implements IStmt{
    Exp expression;
    String varName;

    public readFileStmt(Exp expression, String varName) {
        this.expression = expression;
        this.varName = varName;
    }

    @Override
    public PrgState execute(PrgState state) throws InterpreterException {
        IStack<IStmt> stack = state.getExeStack();
        IDict<String, IValue> stbl = state.getSymTable();
        IDict<StringValue, BufferedReader> fileTbl = state.getFileTbl();
        if (stbl.isDefined(varName)) {
            IValue val = stbl.lookup(varName);
            if (val.getType().equals(new IntType())) {
                IValue val1 = expression.eval(stbl, state.getHeapTable());
                if (val1.getType().equals(new StringType())) {
                    StringValue strVal = (StringValue) val1;
                    BufferedReader rd = fileTbl.lookup(strVal);
                    try {
                        String line = rd.readLine();
                        IntValue valForVar;
                        if (line == null) {
                            valForVar = new IntValue();
                        }
                        else {
                            valForVar = new IntValue(Integer.parseInt(line));
                        }
                        stbl.update(varName, valForVar);
                    } catch (IOException exception) {
                        throw new InterpreterException("Cannot read from file");
                    }
                } else {
                    throw new AssignmentException("Cannot read, the expression is not of string type");
                }
            } else {
                throw new AssignmentException("Variable is not of int type");
            }
        } else {
            throw new AssignmentException("Variable is not declared");
        }
        state.setFileTbl(fileTbl);
        state.setSymTable(stbl);
        state.setExeStack(stack);
        return state;
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> symtable) throws InterpreterException {
        return null;
    }

    @Override
    public String toString() {
        return "readFile(" + expression.toString() + ", " + varName.toString() + ")";
    }

    @Override
    public IStmt createCopy() {
        return new readFileStmt(expression,varName);
    }
}
