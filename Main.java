import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

class Item {
    private int quantity;
    private String name;
    private double price;
    private boolean isImported;
    private boolean isExempt;

    public Item(int quantity, String name, double price, boolean isImported, boolean isExempt) {
        this.quantity = quantity;
        this.name = name;
        this.price = price;
        this.isImported = isImported;
        this.isExempt = isExempt;
    }

    public double getTax() {
        double tax = 0.0;
        if (!isExempt) {
            tax += 0.1 * price;
        }
        if (isImported) {
            tax += 0.05 * price;
        }
        return tax;
    }

    public double getPriceWithTax() {
        return price + getTax();
    }

    public String toString() {
        DecimalFormat df = new DecimalFormat("#.##");
        String result = quantity + " " + name + ": " + df.format(getPriceWithTax());
        return result;
    }
}

public class Main {
    public static void main(String[] args) {
        List<Item> items1 = new ArrayList<Item>();
        items1.add(new Item(1, "book", 12.49, false, true));
        items1.add(new Item(1, "music CD", 14.99, false, false));
        items1.add(new Item(1, "chocolate bar", 0.85, false, true));
        printReceipt(items1);

        List<Item> items2 = new ArrayList<Item>();
        items2.add(new Item(1, "imported box of chocolates", 10.00, true, true));
        items2.add(new Item(1, "imported bottle of perfume", 47.50, true, false));
        printReceipt(items2);

        List<Item> items3 = new ArrayList<Item>();
        items3.add(new Item(1, "imported bottle of perfume", 27.99, true, false));
        items3.add(new Item(1, "bottle of perfume", 18.99, false, false));
        items3.add(new Item(1, "packet of headache pills", 9.75, false, true));
        items3.add(new Item(1, "box of imported chocolates", 11.25, true, true));
        printReceipt(items3);
    }

    public static void printReceipt(List<Item> items) {
        double totalTax = 0.0;
        double totalPrice = 0.0;
        for (Item item : items) {
            System.out.println(item.toString());
            totalTax += item.getTax();
            totalPrice += item.getPriceWithTax();
        }
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println("Sales Taxes: " + df.format(totalTax));
        System.out.println("Total: " + df.format(totalPrice));
        System.out.println();
    }
}
