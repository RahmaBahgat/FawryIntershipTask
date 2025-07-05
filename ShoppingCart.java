import java.util.*;
public class ShoppingCart {
    private List<CartItem> items;

    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    public void add(Product product, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive.");
        }

        if (quantity > product.getProductQuantity()) {
            throw new IllegalArgumentException("Cannot add more than available stock.");
        }

        // Check if item already exists in cart
        for (CartItem item : items) {
            if (item.getProduct().equals(product)) {
                if (item.getQuantity() + quantity > product.getProductQuantity()) {
                    int maxAvailable = product.getProductQuantity() - item.getQuantity();
                    throw new IllegalArgumentException("You can add at most %d more %s to your cart."
                            .formatted(maxAvailable, product.getProductName()));
                }
                item.increaseQuantity(quantity);
                return;
            }
        }

        // Not in cart already
        items.add(new CartItem(product, quantity));
    }

    public List<CartItem> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void clear() {
        items.clear();
    }
}