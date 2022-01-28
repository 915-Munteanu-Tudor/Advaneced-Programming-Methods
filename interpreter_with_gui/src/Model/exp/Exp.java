package Model.exp;
import Model.adt.IDict;
import Model.adt.IHeap;
import Model.types.IType;
import Model.value.IValue;
import Exceptions.*;


public abstract class Exp {


    public abstract IType typecheck(IDict<String, IType> symTable) throws InterpreterException;
    public abstract IValue eval(IDict<String,IValue> symTable, IHeap<IValue> heapTable) throws InterpreterException;
    public abstract String toString();
}
