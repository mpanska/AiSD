public class HashTable {

    private int size = 10;
    private Element[] table;

    public HashTable(){
        table = new Element[size];
    }


    public boolean put(String key, String value) {
        for(Element e : table){
            if(e == null)
                continue;
            if(e.key.equals(key)) {
                System.out.println("An element with key " + key + " already exists");
                return false;
            }
        }
        int index = getHashCode(key);
        Element elem = new Element(key, value);
        if(table[index] == null)
            table[index] = elem;
        else {
            Element temp = table[index];
            while(temp.next != null) {
                temp = temp.next;
            }
            temp.next = elem;
        }
        return true;
    }


    private int getHashCode(String key) { return key.hashCode() % size; }


    public Element findElement(String key) {
        int index = getHashCode(key);
        if(table[index] != null) {
            Element temp = table[index];
            while(!temp.key.equals(key) && temp.next != null ){
                temp = temp.next;
            }
            System.out.println(temp.toString());
            return temp;
        }
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
        String key;
        String value;
        Element next;

        public Element(String key, String value) {
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

        HashTable hashTable = new HashTable();

        hashTable.put("a", "aaa");
        hashTable.put("b", "bbb");
        hashTable.put("c", "ccc");
        hashTable.put("d", "ddd");
        hashTable.put("e", "eee");
        hashTable.put("f", "fff");

        hashTable.printTable();

        hashTable.put("f", "f8ff");

        hashTable.findElement("t");
        hashTable.findElement("b");
    }

}
