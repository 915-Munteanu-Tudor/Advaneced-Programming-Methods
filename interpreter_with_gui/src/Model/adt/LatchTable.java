package Model.adt;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class LatchTable <T> implements ILatchTable<T>{
    AtomicInteger val;
    Map<Integer, T> latchTbl;

    public LatchTable() {
        this.val = new AtomicInteger(0);
        this.latchTbl = new HashMap<>();
    }

    @Override
    public int allocate(T value) {
        latchTbl.put(val.incrementAndGet(), value);
        return val.get();
    }

    @Override
    public void update(int address, T value) {
        latchTbl.put(address, value);
    }

    @Override
    public Map<Integer, T> getContent() {
        return latchTbl;
    }

    @Override
    public boolean exists(int address) {
        return latchTbl.containsKey(address);
    }

    @Override
    public void setContent(Map<Integer, T> map) {
        latchTbl = map;
    }

    @Override
    public T get(int addr) {
        return latchTbl.get(addr);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(var elem: latchTbl.keySet()) {
            if (elem != null)
                s.append(elem).append(" -> ").append(latchTbl.get(elem).toString()).append('\n');
        }
        return s.toString();
    }
}
