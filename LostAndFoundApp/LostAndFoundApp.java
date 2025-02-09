import java.util.ArrayList;
import java.util.Scanner;

class LostItem{
    String name;
    String description;
    String location;

    public LostItem(String name, String description, String location) {
        this.name = name;
        this.description = description;
        this.location = location;
    }

    public String getInfo() {
        return "Item: " + name + "\nDescription: " + description + "\nLocation: " + location;
    }
}
class LostAndFoundApp{

    static ArrayList<LostItem> lostItems = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        System.out.println("Welcome to the Lost & Found App!");
        
        while(true){
            System.out.println("\nSelect an option:");
            System.out.println("1. Report a Lost Item");
            System.out.println("2. Search for Lost Items");
            System.out.println("3. View All Lost Items");
            System.out.println("4. Exit");
            choice = scanner.nextInt();
            scanner.nextLine();  

            switch(choice){
                case 1:
                    reportLostItem(scanner);
                    break;
                case 2:
                    searchLostItems(scanner);
                    break;
                case 3:
                    viewAllLostItems();
                    break;
                case 4:
                    System.out.println("Thank you for using the Lost & Found App. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void reportLostItem(Scanner scanner){
        System.out.println("\nEnter the name of the lost item:");
        String name = scanner.nextLine();
        
        System.out.println("Enter a description of the lost item:");
        String description = scanner.nextLine();
        
        System.out.println("Enter the location where the item was lost:");
        String location = scanner.nextLine();
        LostItem newItem = new LostItem(name, description, location);
        lostItems.add(newItem);
        
        System.out.println("Lost item reported successfully!");
    }

    public static void searchLostItems(Scanner scanner){
        System.out.println("\nEnter the name or description of the item you're searching for:");
        String searchQuery = scanner.nextLine().toLowerCase();
        
        boolean found = false;
        System.out.println("\nSearch Results:");
        
        for (LostItem item : lostItems) {
            if (item.name.toLowerCase().contains(searchQuery) || item.description.toLowerCase().contains(searchQuery)) {
                System.out.println(item.getInfo());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No items found matching your search.");
        }
    }
    public static void viewAllLostItems() {
        if (lostItems.isEmpty()) {
            System.out.println("\nNo items have been reported yet.");
        } else {
            System.out.println("\nAll Lost Items:");
            for (LostItem item : lostItems) {
                System.out.println(item.getInfo());
            }
        }
    }
}
