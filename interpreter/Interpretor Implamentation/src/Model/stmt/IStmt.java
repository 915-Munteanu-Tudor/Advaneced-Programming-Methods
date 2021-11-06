package Model.stmt;

import Exceptions.AssignmentException;
import Exceptions.InterpreterException;
import Model.PrgState;
import Model.adt.IDict;
import Model.types.IType;

public interface IStmt {
    PrgState execute(PrgState state) throws InterpreterException;
    IDict<String, IType> typecheck(IDict<String, IType> symtable) throws InterpreterException;
    IStmt createCopy();

}
