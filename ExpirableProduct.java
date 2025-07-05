import java.time.LocalDate;

public class ExpirableProduct extends Product {
    // A product that has an expiry date (e.g. cheese, biscuits)
    private LocalDate expiryDate;
    public ExpirableProduct(LocalDate expiryDate, String productName, int productQuantity, double productPrice) {
        super(productName, productPrice, productQuantity);
        this.expiryDate = expiryDate;
    }
    public LocalDate getExpiryDate() {
        return expiryDate;
    }
    public boolean isExpired() {
        // Check if the product is expired based on today's date
        return LocalDate.now().isAfter(expiryDate);
    }
}
