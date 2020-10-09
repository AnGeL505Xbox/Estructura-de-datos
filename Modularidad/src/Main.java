import conversiones.Conversiones;

public class Main {
    public static void main(String[] args) {
        Conversiones conversiones= new Conversiones();
        String res = conversiones.binary(2);
        System.out.println(res);
        res = conversiones.hex(16);
        System.out.println(res);
    }
}
