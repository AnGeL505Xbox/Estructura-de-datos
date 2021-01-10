package Optican.Others;

public class Patient extends User{
    int id;

    public Patient(String name, String lName, int age, int ss, double kg, double cm, double ppm, double spo2, double mgDl) {
        super(name, lName, age, ss, kg, cm, ppm, spo2, mgDl);
        this.id = id;
    }

    public int getId() {  return id;  }

    public void setId(int id) {  this.id = id;  }
}