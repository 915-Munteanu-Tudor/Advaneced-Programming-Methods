package Model.exp;
import Exceptions.InterpreterException;
import Model.adt.IDict;
import Model.types.IType;
import Model.value.IValue;
import Model.value.IntValue;

public class ConstExp extends Exp{
    IntValue number;

    public ConstExp(IntValue number) {
        this.number = number;
    }

    @Override
    public IType typecheck(IDict<String, IType> symTable) throws InterpreterException {
        return number.getType();
    }

    public IValue eval(IDict<String,IValue> symTable) {
        return this.number;
    }

    public String toString() {
        return number.toString();
    }
}
