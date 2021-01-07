package Optican.Others;

public class Patient extends User{
    int id;

    public Patient(String name, String lName, int age, int ss, double kg, double cm, int id) {
        super(name, lName, age, ss, kg, cm);
        this.id = id;
    }

    public int getId() {  return id;  }

    public void setId(int id) {  this.id = id;  }
}
