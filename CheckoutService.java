import java.util.*;

public class CheckoutService {

    public static void checkout(Customer customer, ShoppingCart cart) {
        if (cart.isEmpty()) {
            throw new IllegalStateException("Cart is empty!");
        }

        List<CartItem> items = cart.getItems();
        double subtotal = 0;
        double shippingFee = 0;
        boolean hasShipping = false;

        for (CartItem item : items) {
            Product product = item.getProduct();
            int quantity = item.getQuantity();


            if (product.isExpired()) {
                throw new IllegalStateException(product.getProductName() + " is expired.");
            }

            if (quantity > product.getProductQuantity()) {
                throw new IllegalStateException("Not enough stock for " + product.getProductName());
            }


            subtotal += product.getProductPrice() * quantity;


            if (product instanceof ShippableItem) {
                hasShipping = true;
            }
        }

        // Assuming fixed shipping fee of $30 for any order with shippable items
        if (hasShipping) {
            shippingFee = 30.0;
        }

        double totalAmount = subtotal + shippingFee;


        if (customer.getBalance() < totalAmount) {
            throw new IllegalStateException("You don't have enough balance for this order. Required: " + totalAmount);
        }


        for (CartItem item : items) {
            item.getProduct().reduceQuantity(item.getQuantity());
        }

        customer.makePayment(totalAmount);

        if (hasShipping) {
            ShippingService.shipItems(items);
        }

        // Print final receipt in expected format
        System.out.println("\n** Checkout receipt **");
        for (CartItem item : items) {
            Product product = item.getProduct();
            int quantity = item.getQuantity();
            double price = product.getProductPrice() * quantity;
            System.out.printf("%dx %-15s %.0f%n", quantity, product.getProductName(), price);
        }

        System.out.println("----------------------------");
        System.out.printf("Subtotal           %.0f%n", subtotal);
        System.out.printf("Shipping           %.0f%n", shippingFee);
        System.out.printf("Amount         %.0f%n", totalAmount);
        System.out.printf("Customer Balance   %.0f%n", customer.getBalance());


        cart.clear();
    }
}
