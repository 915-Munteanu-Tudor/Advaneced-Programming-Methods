package Model.exp;
import Model.adt.IDict;
import Model.types.IType;
import Model.value.IValue;

public abstract class Exp {


    public abstract IType typecheck(IDict<String, IType> symTable) throws RuntimeException;
    public abstract IValue eval(IDict<String,IValue> symTable) throws RuntimeException;
    public abstract String toString();
}
