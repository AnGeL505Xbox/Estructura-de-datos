public class Product {
    private String name;
    private String code;
    private double totalP;
    private double price;

    public Product(String name, String code, double totalP) {
        this.name = name;
        this.code = code;
        this.totalP = totalP;
        switch (this.name) {
            case "Pan blanco":{ this.price = 35; }
            case "Pan integral":{ this.price = 34; }
            case "Pan tostado":{ this.price = 26; }
            case "Roles de canela":{ this.price = 30; }
        }
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public double getTotalP() { return totalP; }
    public void setTotalP(double totalP) { this.totalP = totalP; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public double totalPrice() { return totalP * price; }
    public String alert() { return "Nombre: "+getName()+" Codigo: "+getCode()+" Precio total: "+totalPrice(); }
}
