package SharedResources;

import java.util.LinkedList;

public class SharedBuffer<T> {
    private LinkedList<T> buffer = new LinkedList<>();

    public synchronized void put(T obj) {
        buffer.addLast(obj);
        notifyAll();
    }
    public synchronized T get() throws InterruptedException{
        while (buffer.isEmpty()) {
            wait();
        }
        return buffer.removeFirst(); // Kan behöva justeras
    }

    public int size() {
        return buffer.size();
    }
}
