package conversiones;

public class Conversiones {
    public String binary(int num){
        int exp=0,digit;
        double bin=0;
        while(num!=0){
            digit = num%2;
            bin=bin+digit*Math.pow(10, exp);
            exp++;
            num=num/2;
        }
        return String.format("%.0f %n",bin);
    }
    public String oct(int num){
        return Integer.toOctalString(num);
    }
    public String hex(int num){
        return Integer.toHexString(num);
    }
    public String conv(int num, int base){
        String dig  = "ABCDEF";
        String res= "";
        while (num > 0){
            int digit = num % base;
            res = dig.charAt(digit) + res;
            num = num/base;
        }
        return res;
    }
}
