package Model.stmt;

import Exceptions.InterpreterException;
import Model.PrgState;
import Model.adt.IDict;
import Model.adt.IHeap;
import Model.exp.Exp;
import Model.types.IType;
import Model.value.IValue;
import Model.value.RefValue;

public class WriteHeapStmt implements IStmt{
    String varName;
    Exp expression;

    public WriteHeapStmt(String varName, Exp expression) {
        this.varName = varName;
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws InterpreterException {
        IDict<String, IValue> symTbl = state.getSymTable();
        IHeap<IValue> heapTbl = state.getHeapTable();
        if (symTbl.isDefined(varName)) {
            IValue val = symTbl.lookup(varName);
            if (val instanceof RefValue) {
                int refAddr = ((RefValue) val).getAddress();
                if (heapTbl.exists(refAddr)) {
                    IValue val1 = expression.eval(symTbl, heapTbl);
                    if (val1.getType().equals(heapTbl.get(refAddr).getType())) {
                        heapTbl.put(refAddr, val1);
                    } else{
                        throw new InterpreterException("Expression not of the same type as variable");
                    }
                } else{
                    throw new InterpreterException("Value doesn't exist on heap");
                }
            } else {
                throw new InterpreterException("Value is not a reference");
            }
        } else{
            throw new InterpreterException("Variable not declared");
        }

        state.setSymTable(symTbl);
        state.setHeapTable(heapTbl);
        return state;
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> symtable) throws InterpreterException {
        return null;
    }

    @Override
    public String toString() {
        return "writeHeap(" + varName + "," + expression.toString() + ")";
    }

    @Override
    public IStmt createCopy() {
        return new WriteHeapStmt(varName, expression);
    }
}
