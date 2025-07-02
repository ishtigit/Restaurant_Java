import java.util.*;

class MenuItem {
    int id;
    String name;
    double price;

    public MenuItem(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String toString() {
        return id + ". " + name + " - Rs." + price;
    }
}

class OrderItem {
    MenuItem item;
    int quantity;

    public OrderItem(MenuItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public double getTotal() {
        return item.price * quantity;
    }

    public String toString() {
        return item.name + " x " + quantity + " = Rs." + getTotal();
    }
}

class Order {
    List<OrderItem> items = new ArrayList<>();

    public void addItem(MenuItem item, int quantity) {
        items.add(new OrderItem(item, quantity));
    }

    public double getTotalAmount() {
        double total = 0;
        for (OrderItem item : items) {
            total += item.getTotal();
        }
        return total;
    }

    public void printBill() {
        System.out.println("\n----- BILL -----");
        for (OrderItem item : items) {
            System.out.println(item);
        }
        System.out.println("Total: Rs." + getTotalAmount());
        System.out.println("----------------");
    }
}

public class RestaurantManagementSystem {
    static Scanner sc = new Scanner(System.in);
    static List<MenuItem> menu = new ArrayList<>();

    public static void main(String[] args) {
        initializeMenu();

        Order order = new Order();

        System.out.println("Welcome to the Restaurant Management System");

        boolean running = true;
        while (running) {
            displayMenu();
            System.out.print("Enter item ID to order (0 to finish): ");
            int id = sc.nextInt();

            if (id == 0) {
                running = false;
            } else {
                MenuItem item = getItemById(id);
                if (item != null) {
                    System.out.print("Enter quantity: ");
                    int qty = sc.nextInt();
                    order.addItem(item, qty);
                } else {
                    System.out.println("Invalid Item ID.");
                }
            }
        }

        order.printBill();
        System.out.println("Thank you for visiting!");
    }

    public static void initializeMenu() {
        menu.add(new MenuItem(1, "Masala Dosa", 60));
        menu.add(new MenuItem(2, "Paneer Butter Masala", 120));
        menu.add(new MenuItem(3, "Veg Biryani", 100));
        menu.add(new MenuItem(4, "Gulab Jamun", 40));
        menu.add(new MenuItem(5, "Cold Drink", 30));
    }

    public static void displayMenu() {
        System.out.println("\n------ MENU ------");
        for (MenuItem item : menu) {
            System.out.println(item);
        }
    }

    public static MenuItem getItemById(int id) {
        for (MenuItem item : menu) {
            if (item.id == id) {
                return item;
            }
        }
        return null;
    }
}

