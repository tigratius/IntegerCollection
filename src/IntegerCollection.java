import java.util.Iterator;
import java.util.NoSuchElementException;

public class IntegerCollection implements Iterable<Integer> {

    private int[] q;
    private int n;

    public IntegerCollection() {
        q = new int[2];
        n = 0;
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    //добавление
    public void add(int item) {

        for (int i = 0; i < n; i++) {
            q[i] += item;
        }

        if (n == q.length)
            resize(2 * q.length);

        q[n++] = item;
    }

    //удаление
    public boolean remove(int item) {

        if (isEmpty())
            return false;

        int index = findByValue(item);

        if (index == -1)
            return false;

        for (int i = 0; i < n - 1; i++) {
            if (i >= index) {
                q[i] = q[i + 1] - item;
            } else {
                q[i] -= item;
            }
        }
        n--;

        if (n > 0 && n == q.length / 4) resize(q.length / 2);

        return true;
    }

    //поиск элемента по значению
    public int findByValue(int item) {

        for (int i = 0; i < n; i++) {
            if (item == q[i]) {
                return i;
            }
        }

        return -1;
    }

    //поиска элемента по индексу
    public int findByIndex(int ind) {
        if (isEmpty())
            throw new NoSuchElementException("Коллекция пустая");

        if (ind < 0 || ind >= n)
            throw new IndexOutOfBoundsException("Индекс за пределами диапозона");

        return q[ind];
    }

    public int findMax() {

        if (isEmpty())
            throw new NoSuchElementException("Коллекция пустая");

        int max = q[0];
        for (int i = 1; i < n; i++) {
            if (q[i] > max) {
                max = q[i];
            }
        }

        return max;
    }

    public int findMin() {

        if (isEmpty())
            throw new NoSuchElementException("Коллекция пустая");

        int min = q[0];
        for (int i = 1; i < n; i++) {
            if (q[i] < min) {
                min = q[i];
            }
        }

        return min;
    }

    //поиска среднего арифметического всех элементов
    public int findAverage() {
        if (isEmpty())
            throw new NoSuchElementException("Коллекция пустая");

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += q[i];
        }

        return sum / n;
    }

    private void resize(int capacity) {
        int[] temp = new int[capacity];

        for (int i = 0; i < n; i++) {
            temp[i] = q[i];
        }

        q = temp;
    }

    public Iterator<Integer> iterator() {
        return new IntegerIterator();
    }

    private class IntegerIterator implements Iterator<Integer> {
        private int i = 0;

        public boolean hasNext() {
            return i < n;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Integer next() {
            if (!hasNext()) throw new NoSuchElementException();

            int item = q[i];
            i++;

            return item;
        }
    }
}
