package Model.exp;

import Exceptions.InterpreterException;
import Model.adt.IDict;
import Model.types.IType;
import Model.value.IValue;

public class ValueExp extends Exp {
    final IValue value;

    public ValueExp(IValue val) {this.value = val;}
    @Override
    public IType typecheck(IDict<String, IType> symTable) throws InterpreterException {
        return value.getType();
    }

    @Override
    public IValue eval(IDict<String, IValue> symTable) throws InterpreterException {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
