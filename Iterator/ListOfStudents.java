package Iterator;

public class ListOfStudents {

    private Student studentList[];
    private int listSize;

    public ListOfStudents(int listSize){
        studentList = new Student[listSize];
        this.listSize = listSize;
    }

    public void initList(){
        Student s1 = new Student(250891, "Pawel", "Grzyb");
        s1.setGrade(4.0);
        Student s2 = new Student(254393, "Joanna", "Barszcz");
        s2.setGrade(5.0);
        Student s3 = new Student(236506, "Alicja", "Zurek");
        s3.setGrade(3.5);

        studentList[0] = s1;
        studentList[1] = s2;
        studentList[2] = s3;
    }


    public void printList(){
        Iterator<Student> iterator = new ArrayIterator<>(studentList);
        while(iterator.hasNext())
            System.out.println(iterator.next());
    }

    public void setGradeByIndex(double grade, int index){
        Predicate<Student> predicate = new Predicate<Student>() {
            @Override
            public boolean test(Student student) {
                return student.getIndex() == index;
            }
        };
        Iterator<Student> iterator = new ArrayIterator<Student>(studentList);
        Iterator<Student> filter = new FIterator<Student>(iterator, predicate);

        while (filter.hasNext())
            filter.next().setGrade(grade);
    }
    
    public void showFailure(){ //method to show studetns with grade less than 3
        Predicate<Student> predicate = new Predicate<Student>() {
            @Override
            public boolean test(Student student) { return student.getGrade() < 3.0; }
        };

        Iterator<Student> iterator = new ArrayIterator<Student>(studentList);
        Iterator<Student> filter = new FIterator<Student>(iterator, predicate);

        while (filter.hasNext())
            System.out.println(filter.next());
    }


}
