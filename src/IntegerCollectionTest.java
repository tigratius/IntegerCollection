import static org.junit.Assert.*;

public class IntegerCollectionTest {

    private IntegerCollection integerCollection;

    private void init()
    {
        integerCollection = new IntegerCollection();
        integerCollection.add(1);
        integerCollection.add(2);
        integerCollection.add(3);
    }

    @org.junit.Test
    public void add() {

        init();

        assertEquals(3, integerCollection.size());
    }

    @org.junit.Test
    public void remove() {

        init();

        integerCollection.remove(5);

        assertEquals(2, integerCollection.size());
    }

    @org.junit.Test
    public void findByValue() {

        init();
        assertEquals(0, integerCollection.findByValue(6));
    }

    @org.junit.Test
    public void findByIndex() {
        init();
        assertEquals(6, integerCollection.findByIndex(0));
    }

    @org.junit.Test
    public void findMax() {
        init();
        assertEquals(6, integerCollection.findMax());
    }

    @org.junit.Test
    public void findMin() {
        init();
        assertEquals(3, integerCollection.findMin());
    }

    @org.junit.Test
    public void findAverage() {
        init();
        assertEquals(4, integerCollection.findAverage());
    }

    @org.junit.Test
    public void growWhenAddItem() {
        init();
        assertEquals(6, integerCollection.findByIndex(0));
    }

    @org.junit.Test
    public void decreaseWhenRemoveItem() {
        init();

        integerCollection.remove(6);
        assertEquals(-1, integerCollection.findByIndex(0));
    }
}