package Model.exp;

import Model.adt.IDict;
import Model.types.IType;
import Model.value.IValue;

public class ValueExp extends Exp {
    final IValue value;

    public ValueExp(IValue val) {this.value = val;}
    @Override
    public IType typecheck(IDict<String, IType> symTable) throws RuntimeException {
        return value.getType();
    }

    @Override
    public IValue eval(IDict<String, IValue> symTable) throws RuntimeException {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
