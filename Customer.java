public class Customer {
    private String customerName;
    private double balance;
    public Customer(String CustomerName, double balance) {
        this.customerName = CustomerName;
        this.balance = balance;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public void makePayment(double amount) {
        if (amount > balance) {
            throw new IllegalArgumentException("You can't pay $" + amount + ". Available balance is $" + balance);
        }
        else{
        balance -= amount;
    }
    }
}
