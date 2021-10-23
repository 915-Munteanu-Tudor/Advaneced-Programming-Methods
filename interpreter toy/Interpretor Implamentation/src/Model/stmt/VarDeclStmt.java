package Model.stmt;


import Model.PrgState;
import Model.adt.IDict;
import Model.adt.IStack;
import Model.types.IType;
import Model.value.IValue;

public class VarDeclStmt implements IStmt{
    String name;
    IType type;

    public VarDeclStmt(String name, IType type){
        this.name = name;
        this.type = type;
    }


    @Override
    public PrgState execute(PrgState state) throws RuntimeException {
        IStack<IStmt> stk = state.getExeStack();;
        IDict<String, IValue> stbl = state.getSymTable();
        if (stbl.isDefined(name)) {
            throw new RuntimeException("Variable already declared!");
        } else {
            IValue def_val = type.defaultValue();
            stbl.add(name,def_val);
        }
        state.setSymTable(stbl);
        state.setExeStack(stk);
        //return null;
        return state;
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> symtable) throws RuntimeException {
        symtable.add(name,type);
        return symtable;
    }

    @Override
    public String toString() {
        return type.toString() + " " + name;
    }

    @Override
    public IStmt createCopy() {
        return new VarDeclStmt(name,type);
    }
}
