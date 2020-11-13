public class CustomProduct extends Product{
    private double priceCustom;

    public CustomProduct(String name, String code, double totalP, double priceCustom) {
        super(name, code, totalP);
        this.priceCustom = priceCustom;
    }

    public double getPriceCustom() { return priceCustom; }
    public void setPriceCustom(double priceCustom) { this.priceCustom = priceCustom; }

    public String ticketAlert() { return "Nombre: "+getName()+" Codigo: "+getCode()+" Precio: "+getPriceCustom()+" Precio total: "+ this.totalPrice(); }

    public double totalPrice() { return super.getTotalP() * this.priceCustom; }
}
