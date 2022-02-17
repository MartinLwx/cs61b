package deque;

/** Circular implementation */
public class ArrayDeque<T> {
    private T[] items;
    private int size;

    // the next position of nextFront && nextLast
    private int nextFirst;
    private int nextLast;

    public static final int INIT_SIZE = 8;
    public static final double USAGE_RATIO = 0.25;
    public static final int RESIZE_FACTOR = 2;

    /** Constructor */
    public ArrayDeque() {
        items = (T[]) new Object[INIT_SIZE];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    /** Resize */
    public void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        int startPos = addOne(nextFirst);
        for (int i = 0; i < size; i++) {
            newItems[i] = items[startPos];
            startPos = addOne(startPos);
        }
        items = newItems;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    /** Add one element circularly */
    public int addOne(int index) {
        return (index + 1) % items.length;
    }

    /** Minus one element circularly */
    public int minusOne(int index) {
        return (index + items.length - 1) % items.length;
    }

    public void addFirst(T item) {
        if (isFull()) {
            resize(items.length * 2);
        }
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size += 1;
    }

    public void addLast(T item) {
        if (isFull()) {
            resize(items.length * 2);
        }
        items[nextLast] = item;
        nextLast = addOne(nextLast);
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == items.length;
    }

    /** isSparse = true if the usage ratior < 0.25 */
    public boolean isSparse() {
        return items.length >= 16 && size < (items.length / 4);
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (!isEmpty()) {
            System.out.print(items[addOne(nextFirst)]);
            for (int i = addOne(addOne(nextFirst)); i != nextLast; i = addOne(i)) {
                System.out.print(" " + items[i]);
            }
        }
        System.out.println();
    }

    public T removeFirst() {
        if (isSparse()) {
            resize(items.length / 2);
        }
        nextFirst = addOne(nextFirst);
        T tmp = items[nextFirst];
        items[nextFirst] = null;
        if (!isEmpty()) {
            size -= 1;
        }
        return tmp;
    }

    public T removeLast() {
        if (isSparse()) {
            resize(items.length / 2);
        }
        nextLast = minusOne(nextLast);
        T tmp = items[nextLast];
        items[nextLast] = null;
        if (!isEmpty()) {
            size -= 1;
        }
        return tmp;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        int startPos = addOne(nextFirst);
        return items[(startPos + index) % items.length];
    }
}
