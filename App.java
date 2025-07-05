

import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        //TEST CASE 1 (Normal Scenario)
        testSuccessfulCheckout();

        // Uncomment each test below to run other testcases

//        System.out.println("\nTEST CASE 2: Expired Product in Cart\n");
//        testExpiredProduct();
//
//        System.out.println("\nTEST CASE 3: Out of Stock Product\n");
//        testOutOfStock();
//
//        System.out.println("\nTEST CASE 4: Customer doesn't have enough balance\n");
//        testNotEnoughBalance();
//
//        System.out.println("\nTEST CASE 5: Empty Cart Checkout\n");
//        testEmptyCart();
    }

    public static void testSuccessfulCheckout() {
        Product cheese = new ShippableProduct("Cheese", 10, 100, 0.2);       // 200g each
        Product biscuits = new ShippableProduct("Biscuits", 5, 150, 0.7);    // 700g each
        Product scratchCard = new Product("ScratchCard", 50, 20);            // Not shippable

        Customer customer = new Customer("Rahma", 500);

        ShoppingCart cart = new ShoppingCart();
        cart.add(cheese, 2);
        cart.add(biscuits, 1);
        cart.add(scratchCard, 1);

        try {
            CheckoutService.checkout(customer, cart);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public static void testExpiredProduct() {
        Product expiredCheese = new ExpirableProduct(LocalDate.now().minusDays(1), "Old Cheese", 5, 100);
        Customer customer = new Customer("Rahma", 200);
        ShoppingCart cart = new ShoppingCart();
        cart.add(expiredCheese, 1);

        try {
            CheckoutService.checkout(customer, cart);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public static void testOutOfStock() {
        Product cheese = new ShippableProduct("Cheese", 2, 100, 0.2);
        Customer customer = new Customer("Rahma", 300);
        ShoppingCart cart = new ShoppingCart();

        try {
            cart.add(cheese, 3);  // Exceeds available quantity
            CheckoutService.checkout(customer, cart);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }


    public static void testNotEnoughBalance() {
        Product cheese = new ShippableProduct("Cheese", 10, 100, 0.2);
        Customer customer = new Customer("Rahma", 50);

        ShoppingCart cart = new ShoppingCart();
        cart.add(cheese, 2);  // Total = 200

        try {
            CheckoutService.checkout(customer, cart);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public static void testEmptyCart() {
        Customer customer = new Customer("Rahma", 200);
        ShoppingCart cart = new ShoppingCart();

        try {
            CheckoutService.checkout(customer, cart);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
