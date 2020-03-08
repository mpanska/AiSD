package Iterator;

public class Student{

    private int index;
    private double grade;
    private String name;
    private String surname;

    public Student(int index, String name, String surname) {
        this.index = index;
        this.name = name;
        this.surname = surname;
        this.grade = 0;
    }

    public int getIndex() { return index; }
    public double getGrade() { return grade;}

    public void setGrade(double grade) {
        if(grade < 2.0)
            this.grade = 2.0;
        else if (grade > 5.5)
            this.grade = 5.5;
        else this.grade = Math.floor(grade);
    }


    public String toString(){
        return "index: " + index + "; name: " + name + "; surname: " + surname + "; grade: " + grade;
    }


}
