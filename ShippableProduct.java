public class ShippableProduct extends Product implements ShippableItem {
    // A product that requires shipping and has a weight
    private double weight;

    public ShippableProduct(String productName, int productQuantity, double productPrice, double weight) {
        super(productName, productPrice, productQuantity);
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    public String getName() {
        return getProductName();
    }

}
