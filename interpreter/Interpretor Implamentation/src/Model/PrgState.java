package Model;
import Exceptions.InterpreterException;
import Model.adt.*;
import Model.stmt.IStmt;
import Model.value.IValue;
import Model.value.StringValue;

import java.io.BufferedReader;

public class PrgState {

    IStack<IStmt> exeStack;
    IDict<String, IValue> symTable;
    IList<IValue> out;
    IHeap<IValue> heapTable;
    IDict<StringValue, BufferedReader> fileTbl;
    IStmt originalProgram; //optional field, but good to have

    public PrgState(IStack<IStmt>stk, IDict<String, IValue> symtbl, IList<IValue> ot, IDict<StringValue, BufferedReader> fileTable, IHeap<IValue> ht, IStmt stmt){
        exeStack = stk;
        symTable = symtbl;
        heapTable = ht;
        out = ot;
        fileTbl = fileTable;
        originalProgram =stmt.createCopy();
        stk.push(stmt);
    }


    public void typeCheck() throws InterpreterException {
        originalProgram.typecheck(new Dict<>());
    }

    public Boolean isNotCompleted() {
        return !exeStack.isEmpty();
    }

//    public PrgState oneStep() throws InterpreterException {
//        if (exeStack.isEmpty()) {
//            throw new InterpreterException("Stack is empty");
//        }
//        IStmt currentStatement = exeStack.pop();
//        return currentStatement.execute(this);
//    }

    @Override
    public String toString() {
        return
                " --------Execution Stack-------- \n" +
                exeStack.toString() + '\n' +
                " -------- Symbol  Table -------- \n" +
                symTable.toString() + '\n' +
                " -------- File  Table -------- \n" +
                fileTbl.toString() + '\n' +
                " --------  Heap  Table  -------- \n" +
                heapTable.toString() + '\n' +
                " -------- Output Console -------- \n" +
                out.toString() + "\n" +
                " ------------------------------- \n\n\n";
    }

    public IStack<IStmt> getExeStack() {
        return exeStack;
    }

    public void setExeStack(IStack<IStmt> exeStack) {
        this.exeStack = exeStack;
    }

    public void setSymTable(IDict<String, IValue> symTable) {
        this.symTable = symTable;
    }

    public IDict<String, IValue> getSymTable() {
        return symTable;
    }

    public IList<IValue> getOut() {
        return out;
    }

    public void setOut(IList<IValue> out) {
        this.out = out;
    }

    public IStmt getOriginalProgram() {
        return originalProgram;
    }

    public void setOriginalProgram(IStmt originalProgram) {
        this.originalProgram = originalProgram;
    }

    public IDict<StringValue, BufferedReader> getFileTbl() {
        return fileTbl;
    }

    public void setFileTbl(IDict<StringValue, BufferedReader> fileTbl) {
        this.fileTbl = fileTbl;
    }

    public IHeap<IValue> getHeapTable() {
        return heapTable;
    }

    public void setHeapTable(IHeap<IValue> heapTable) {
        this.heapTable = heapTable;
    }
}