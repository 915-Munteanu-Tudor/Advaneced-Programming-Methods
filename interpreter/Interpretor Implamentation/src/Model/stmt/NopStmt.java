package Model.stmt;

import Model.PrgState;
import Model.adt.IDict;
import Model.types.IType;

public class NopStmt implements IStmt {
    public NopStmt() {}

    public PrgState execute(PrgState state){
        //return null;
        return state;
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> symtable) throws RuntimeException {
        return symtable;
    }

    @Override
    public IStmt createCopy() {
        return new NopStmt();
    }

    @Override
    public String toString(){
        return "no operation\n";
    }
}
