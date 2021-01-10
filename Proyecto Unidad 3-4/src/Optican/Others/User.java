package Optican.Others;

public class User {
    String name = "";
    String lName = "";
    int age, ss;
    double kg, cm, imc, ppm, mgDl, spo2;

    public User(String name, String lName, int age, int ss, double kg, double cm, double ppm, double spo2, double mgDl) {
        this.name = name;
        this.lName = lName;
        this.age = age;
        this.ss = ss;
        this.kg = kg;
        this.cm = cm;
        this.ppm = ppm;
        this.mgDl = mgDl;
        this.spo2 = spo2;
    }

    public String getName() {  return name;  }

    public void setName(String name) {
        this.name = name;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSs() {
        return ss;
    }

    public void setSs(int ss) {
        this.ss = ss;
    }

    public double getKg() {
        return kg;
    }

    public void setKg(double kg) {
        this.kg = kg;
    }

    public double getCm() {
        return cm;
    }

    public void setCm(double cm) {
        this.cm = cm;
    }

    public double getPpm() { return ppm;  }

    public void setPpm(double ppm) { this.ppm = ppm; }

    public double getMgDl() { return mgDl; }

    public void setMgDl(double mgDl) { this.mgDl = mgDl; }

    public double getSpo2() { return spo2; }

    public void setSpo2(double spo2) { this.spo2 = spo2; }

    public double getImc() {
        return imc;
    }

    public void setImc(double imc) {
        this.imc = imc;
    }
}
