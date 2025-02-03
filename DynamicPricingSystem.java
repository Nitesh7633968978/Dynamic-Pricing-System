import java.util.*;

class Product {
    private String name;
    private double basePrice;
    private double currentPrice;
    private int stock;
    private int demand;

    public Product(String name, double basePrice, int stock) {
        this.name = name;
        this.basePrice = basePrice;
        this.currentPrice = basePrice;
        this.stock = stock;
        this.demand = 0;
    }

    public String getName() { return name; }
    public double getCurrentPrice() { return currentPrice; }
    public int getStock() { return stock; }
    public void increaseDemand() { demand++; }
    public void decreaseStock() { if (stock > 0) stock--; }

    public void updatePrice() {
        if (demand > 5 && stock < 10) {
            currentPrice = basePrice * 1.2; // Increase price by 20%
        } else if (stock > 20) {
            currentPrice = basePrice * 0.9; // Decrease price by 10%
        } else {
            currentPrice = basePrice;
        }
        demand = 0; // Reset demand after adjustment
    }
}

class ECommercePlatform {
    private List<Product> products = new ArrayList<>();

    public void addProduct(String name, double basePrice, int stock) {
        products.add(new Product(name, basePrice, stock));
    }

    public void simulateUserInteraction(String productName) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(productName)) {
                product.increaseDemand();
                product.decreaseStock();
                System.out.println("User interacted with: " + productName);
                return;
            }
        }
        System.out.println("Product not found.");
    }

    public void updatePrices() {
        for (Product product : products) {
            product.updatePrice();
        }
    }

    public void displayProducts() {
        for (Product product : products) {
            System.out.println("Product: " + product.getName() + " | Price: " + product.getCurrentPrice() + " | Stock: " + product.getStock());
        }
    }
}

public class DynamicPricingSystem {
    public static void main(String[] args) {
        ECommercePlatform platform = new ECommercePlatform();

        platform.addProduct("Laptop", 1000, 15);
        platform.addProduct("Smartphone", 800, 30);

        platform.simulateUserInteraction("Laptop");
        platform.simulateUserInteraction("Laptop");

        platform.updatePrices();
        platform.displayProducts();
    }
}
