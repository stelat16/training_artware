import java.io.IOException;
import java.util.Scanner;

public class math_operations {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("a = ");
        double a = scanner.nextDouble();
        System.out.println("b = ");
        double b = scanner.nextDouble();
        String operation;
        Boolean failed = false;
        do {
            System.out.println("operation = ");
            operation = scanner.next();
            failed = false;
            switch (operation) {
                case "*": {
                    System.out.println(a * b);
                    break;
                }
                case "/": {
                    System.out.println(a / b);
                    break;
                }
                case "+": {
                    System.out.println(a + b);
                    break;
                }
                case "-": {
                    System.out.println(a - b);
                    break;
                }
                default:
                    System.out.println("Please enter a valid operation (*, /, +, -)");
                    failed = true;
            }
            }
            while (failed);
        }
}
