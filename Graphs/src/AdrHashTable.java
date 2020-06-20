public class AdrHashTable {

    private int size = 10;
    private Element[] table;

    public AdrHashTable(){
        table = new Element[size];
    }


    public boolean put(int key, String value) {
        for(Element e : table){
            if(e == null)
                continue;
            if(e.key == key) {
                System.out.println("An element with given key already exists");
                return false;
            }
            if(key < 0){
                System.out.println("Key can't be negative");
                return false;
            }
        }
        Element elem = new Element(key, value);
        if(elem.key > table.length){
            System.out.print("Index is out of bounds");
            return false;
        }
        int index = elem.key;
        table[index] = elem;
        return true;
    }


    public Element findElement(int key) {
        if(key > table.length){
            System.out.print("Index is out of bounds");
            return null;
        }
        if(table[key] != null)
            return table[key];

        System.out.println("There's no such element with key " + key);
        return null;
    }

    public int findIndex(Element elem) {
        int index = 0;
        while(table[index]!=elem)
            index++;
        return index;
    }


    private class Element {
        int key;
        String value;
        Element next;

        public Element(int key, String value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        public String toString() { return "[" + key + ", " + value + "]"; }
    }


    public void printTable() {
        for (Element elem : table) {
            if(elem == null) {
                continue;
            }
            int index = findIndex(elem);
            System.out.print("\n index: " + index + "; element: " + elem.toString());
        }
        System.out.println("\n");
    }


    public static void main(String[] args) {

        AdrHashTable hashTable = new AdrHashTable();

        hashTable.put(8, "aaa");
        hashTable.put(2, "bbb");
        hashTable.put(3, "ccc");
        hashTable.put(4, "ddd");
        hashTable.put(5, "eee");
        hashTable.put(6, "fff");

        hashTable.printTable();

        hashTable.put(2,"5676");

        hashTable.findElement(9);
        hashTable.findElement(5);
    }

}
