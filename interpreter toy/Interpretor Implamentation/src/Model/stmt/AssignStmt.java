package Model.stmt;

import Model.PrgState;
import Model.adt.IDict;
import Model.adt.IStack;
import Model.exp.Exp;
import Model.types.IType;
import Model.value.IValue;

public class AssignStmt implements IStmt{

    final String id;
    final Exp expression;

    public AssignStmt(String id, Exp exp){
        this.id = id;
        this.expression = exp;
    }

    public String getId(){return id;}

    public Exp getExpression(){return expression;}

    @Override
    public String toString(){
        return this.id + "=" + this.expression.toString();
    }

    @Override
    public PrgState execute(PrgState state) throws RuntimeException {
        IStack<IStmt> stack = state.getExeStack();
        IDict<String, IValue> stbl = state.getSymTable();
        if (stbl.isDefined(id)){
            IValue val = expression.eval(stbl);
            if (val.getType().equals(stbl.lookup(id).getType())){
                stbl.update(id,val);
            } else {
                throw new RuntimeException("Type of expression and type of variable do not match");
            }
        } else {
            throw new RuntimeException("Variable is not declared");
        }
        state.setExeStack(stack);
        state.setSymTable(stbl);
        //return null;
        return state;
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> symTable) throws RuntimeException {
        IType vart = symTable.lookup(id);
        IType expt = expression.typecheck(symTable);
        if (vart.equals(expt)) {
            return symTable;
        } else {
            throw new RuntimeException("Not the same type on assignment");
        }
    }

    @Override
    public IStmt createCopy() {
        return new AssignStmt(id,expression);
    }

}
