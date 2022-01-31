package Model.stmt;

import Exceptions.InterpreterException;
import Exceptions.TypeException;
import Model.PrgState;
import Model.adt.IDict;
import Model.adt.IStack;
import Model.adt.Stack;
import Model.exp.Exp;
import Model.types.BoolType;
import Model.types.IType;

public class ConditionalAssignmentStmt implements  IStmt{
    String v;
    Exp expression1;
    Exp expression2;
    Exp expression3;

    public ConditionalAssignmentStmt(String v, Exp expression1, Exp expression2, Exp expression3) {
        this.v = v;
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.expression3 = expression3;
    }


    @Override
    public PrgState execute(PrgState state) throws InterpreterException {
        IStack<IStmt> stack = state.getExeStack();
        stack.push(new IfStmt(expression1, new AssignStmt(v, expression2), new AssignStmt(v, expression3)));
        state.setExeStack(stack);
        return null;
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> table) throws InterpreterException {
        IType conditionType = expression1.typecheck(table);
        if (conditionType.equals(new BoolType())) {
            IType varType = table.lookup(v);
            IType then = expression2.typecheck(table);
            IType elseType = expression3.typecheck(table);
            if (varType.equals(then) && varType.equals(elseType)) {
                return table;
            }
            throw new TypeException("Variable and expression not of the sam type");
        }
        throw new TypeException("Condition not of type bool");
    }

    @Override
    public IStmt createCopy() {
        return new ConditionalAssignmentStmt(v, expression1, expression2, expression3);
    }

    @Override
    public String toString() {
        return "v = " + expression1.toString() + " ? " + expression2.toString() + " : " + expression3.toString();

    }
}
