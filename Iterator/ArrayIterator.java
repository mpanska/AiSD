package Iterator;

public class ArrayIterator<T> implements Iterator<T>{

    private T array[];
    private int position;

    public ArrayIterator(T[] array) {
        this.array = array;
        position = 0;
    }

    @Override
    public boolean hasNext() {
        if(position < this.array.length)
            return true;
        else return false;
    }

    @Override
    public T next() {
        // returns the current element and the cursor advances to next element;Before advancing the pointer,
        // we check whether next element exists
        //cursor = CustomDataStructure.this.element
        if(hasNext())
            return array[position++];
        else return null;
    }

}
