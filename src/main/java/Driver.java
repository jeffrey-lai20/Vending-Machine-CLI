import java.util.Scanner;

public class Driver {

    private static Inventory inventory;
    private static CustomerSystem customerSystem;
    private static CashSystem cashSystem;
    private static StaffSystem staffSystem;
    private static VendingMachine vendingMachine;

    public static void main(String[] args) {
        // System.out.println(System.getProperty("user.dir"));
        System.out.println("Welcome to Group 43's Vending Machine.");
        inventory = new Inventory();
        customerSystem = new CustomerSystem(inventory.getItems());
        cashSystem = new CashSystem();
        staffSystem = new StaffSystem();
        vendingMachine = new VendingMachine();

        String itemSelected = null;
        int quantitySelected = 0;

        firstPage();

    }

    //Selecting role page some changes for jenkins
    public static void firstPage(){
        System.out.println("Please select your role : \n1 : Customer\n2 : Staff");
        Scanner roleInput = new Scanner(System.in);
        if (roleInput.hasNextLine()) {
            String userInput = roleInput.nextLine();
            while (userInput.equals("2")) {
               staffPage();
            }
            if (userInput.equals("1")) {
               customerPage();
                System.out.println("Please select your role : \n1 : Customer\n2 : Staff");
                userInput = roleInput.nextLine();
            }
            if (userInput.equalsIgnoreCase("Cancel")) {
                System.out.println("Cancelling transaction.");
            } else {
                System.out.println("Invalid input please try again");
                firstPage();
            }
        }
    }

    //Staff interface
    private static void staffPage(){
        staffSystem.loginScreen();
        if (staffSystem.staffLoggedIn()) {
            staffSystem.showMenu(inventory);
        }
    }

    //Customer interface
    private static void customerPage() {
        customerSystem.buyingPage();
    }
}
