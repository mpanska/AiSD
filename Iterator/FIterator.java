package Iterator;

public class FIterator<T> implements Iterator<T>{

    private Iterator<T> iterator;
    private Predicate<T> predicate;
    private T nextElement = null;
    private boolean ifHasNext = true;

    public FIterator(Iterator<T> iterator, Predicate<T> predicate) {
        this.iterator = iterator;
        this.predicate = predicate;
        findNextValid();
    }

    public void findNextValid(){
        while(iterator.hasNext()){ //while there are elements in our collection
            nextElement = iterator.next();
            if (predicate.test(nextElement)) return;
        }
        ifHasNext = false;
        nextElement = null;
    }

    @Override
    public boolean hasNext() { return ifHasNext; }

    @Override
    public T next() {
        T nextValue = nextElement;
        findNextValid();
        return nextValue;
    }
}
