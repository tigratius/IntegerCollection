import java.util.Iterator;
import java.util.NoSuchElementException;

public class IntegerCollection implements Iterable<Integer> {

    private int[] values;
    private int length;

    public IntegerCollection() {
        values = new int[2];
        length = 0;
    }

    public int size() {
        return length;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    //добавление
    public void add(int item) {

        for (int i = 0; i < length; i++) {
            values[i] += item;
        }

        if (length == values.length)
            resize(2 * values.length);

        values[length++] = item;
    }

    //удаление
    public boolean remove(int item) {

        if (isEmpty())
            return false;

        int index = findByValue(item);

        if (index == -1)
            return false;

        for (int i = 0; i < length - 1; i++) {
            if (i >= index) {
                values[i] = values[i + 1] - item;
            } else {
                values[i] -= item;
            }
        }
        length--;

        if (length > 0 && length == values.length / 4) resize(values.length / 2);

        return true;
    }

    //поиск элемента по значению
    public int findByValue(int item) {

        for (int i = 0; i < length; i++) {
            if (item == values[i]) {
                return i;
            }
        }

        return -1;
    }

    //поиска элемента по индексу
    public int findByIndex(int ind) {
        if (isEmpty())
            throw new NoSuchElementException("Коллекция пустая");

        if (ind < 0 || ind >= length)
            throw new IndexOutOfBoundsException("Индекс за пределами диапозона");

        return values[ind];
    }

    public int findMax() {

        if (isEmpty())
            throw new NoSuchElementException("Коллекция пустая");

        int max = values[0];
        for (int i = 1; i < length; i++) {
            if (values[i] > max) {
                max = values[i];
            }
        }

        return max;
    }

    public int findMin() {

        if (isEmpty())
            throw new NoSuchElementException("Коллекция пустая");

        int min = values[0];
        for (int i = 1; i < length; i++) {
            if (values[i] < min) {
                min = values[i];
            }
        }

        return min;
    }

    //поиска среднего арифметического всех элементов
    public int findAverage() {
        if (isEmpty())
            throw new NoSuchElementException("Коллекция пустая");

        int sum = 0;
        for (int i = 0; i < length; i++) {
            sum += values[i];
        }

        return sum / length;
    }

    private void resize(int capacity) {
        int[] temp = new int[capacity];

        for (int i = 0; i < length; i++) {
            temp[i] = values[i];
        }

        values = temp;
    }

    public Iterator<Integer> iterator() {
        return new IntegerIterator();
    }

    private class IntegerIterator implements Iterator<Integer> {
        private int i = 0;

        public boolean hasNext() {
            return i < length;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Integer next() {
            if (!hasNext()) throw new NoSuchElementException();

            int item = values[i];
            i++;

            return item;
        }
    }
}
