package Iterator;

public class Main {

    public static void main(String[] args){

        ListOfStudents list = new ListOfStudents(4);
        list.initList();

        list.printList();
        System.out.println();

        list.setGradeByIndex(5.25,250891);
        list.setGradeByIndex(0,236506);
        list.printList();
        System.out.println();

        list.showFailure();
    }
}
