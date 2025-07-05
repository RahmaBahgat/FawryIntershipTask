public class Product {
    // Base class for all products
    // Some may be expirable or shippable, which extend this class
    private String productName;
    private double productPrice;
    private int productQuantity;

    public boolean isExpired() {
        return false;
    }

    public Product(String productName, double productPrice, int productQuantity) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    // Reduce product stock after purchase
    public void reduceQuantity(int amount) {
        if (amount > productQuantity)
            throw new IllegalArgumentException("The quantity you need is more than the available product quantity.");
        else {
            productQuantity -= amount;
        }
    }
}

