package Model.exp;

import Exceptions.InterpreterException;
import Model.adt.IDict;
import Model.adt.IHeap;
import Model.types.IType;
import Model.value.IValue;
import Model.value.RefValue;

public class ReadHeapExp extends Exp{
    Exp expression;

    public ReadHeapExp(Exp exp) {
        this.expression = exp;
    }

    @Override
    public IType typecheck(IDict<String, IType> symTable) throws InterpreterException {
        return null;
    }

    @Override
    public IValue eval(IDict<String, IValue> symTable, IHeap<IValue> heapTable) throws InterpreterException {
        IValue val = expression.eval(symTable,heapTable);
        if (val instanceof RefValue) {
            RefValue rv = (RefValue) val;
            int addr = rv.getAddress();
            if (heapTable.exists(addr)) {
                return heapTable.get(addr);
            } else {
                throw new InterpreterException("Not allocated heap");
            }
        } else {
            throw new InterpreterException("Expression not of refference type");
        }
    }


    @Override
    public String toString() {
        return "readHeap(" + expression.toString() + ")";
    }
}
