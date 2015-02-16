import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import tax.Register;

public class Driver {
    public static void main(String[] args) {
        try {
            final Scanner scanner = new Scanner(new File(args[0]));

            final Register register = new Register();
            while (scanner.hasNextLine()) {
                System.out.println(register.addItem(scanner.nextLine()));
            }
            System.out.println("Sales Taxes: " + register.calculateTax());
            System.out.println("Total: " + register.calculateTotal());
        } catch (FileNotFoundException e) {
            System.out.println("Please pass input file" + e);
        }
    }
}
