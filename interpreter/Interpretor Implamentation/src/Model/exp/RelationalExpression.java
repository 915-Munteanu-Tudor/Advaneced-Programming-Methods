package Model.exp;

import Exceptions.InterpreterException;
import Model.adt.IDict;
import Model.types.IType;
import Model.types.IntType;
import Model.value.BoolValue;
import Model.value.IValue;
import Model.value.IntValue;

public class RelationalExpression extends Exp{
    String operation;
    Exp expression1;
    Exp expression2;

    public RelationalExpression(String operation, Exp expression1, Exp expression2) {
        this.operation = operation;
        this.expression1 = expression1;
        this.expression2 = expression2;
    }

    @Override
    public IType typecheck(IDict<String, IType> symTable) throws InterpreterException {
        return null;
    }

    @Override
    public IValue eval(IDict<String, IValue> symTable) throws InterpreterException {
        IValue val1 = expression1.eval(symTable);
        if (val1.getType().equals(new IntType())) {
            IValue val2 = expression2.eval(symTable);
            if (val2.getType().equals(new IntType())) {
                IntValue intval1 = (IntValue)val1;
                IntValue intval2 = (IntValue)val2;
                int intv1 = intval1.getValue();
                int intv2 = intval2.getValue();
                return switch (operation){
                    case "<" -> new BoolValue(intv1 < intv2);
                    case "<=" -> new BoolValue(intv1 <= intv2);
                    case "==" -> new BoolValue(intv1 == intv2);
                    case "!=" -> new BoolValue(intv1 != intv2);
                    case ">" -> new BoolValue(intv1 > intv2);
                    case ">=" -> new BoolValue(intv1 >= intv2);
                    default -> throw new InterpreterException("Invalid operation");
                };
            } else {
                throw new InterpreterException("Expression 2 not of int type");
            }
        } else {
            throw new InterpreterException("Expression 1 not of int type");
        }
    }

    @Override
    public String toString() {
        return expression1.toString() + " " + operation + " " + expression2.toString();
    }
}
