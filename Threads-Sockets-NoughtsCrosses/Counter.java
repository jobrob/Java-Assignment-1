public class Counter {

    private int count = 0;
    
    public synchronized void add(int i) {
	count += i;
    }

    public int get() {
	return count;
    }
}
