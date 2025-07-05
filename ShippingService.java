import java.util.*;

public class ShippingService {
    public static void shipItems(List<CartItem> cartItems) {
        System.out.println("** Shipment notice **");

        double totalWeight = 0;

        for (CartItem item : cartItems) {
            Product product = item.getProduct();

            if (product instanceof ShippableItem) {
                ShippableItem shippable = (ShippableItem) product;
                int quantity = item.getQuantity();
                double weight = shippable.getWeight() * quantity;
                totalWeight += weight;

                System.out.printf("%dx %-15s %.0fg%n", quantity, shippable.getName(), weight * 1000);
            }
        }

        System.out.printf("Total package weight %.1fkg%n", totalWeight);
    }
}
