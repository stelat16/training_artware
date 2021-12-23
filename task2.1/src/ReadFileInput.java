import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadFileInput {
    public static void main(String[] args) throws IOException {
        try (
                Scanner myReader = new Scanner(new File("input.txt"));) {
            String data;
            String regexExpression = "^(\\d+)+\\s{1}(\\d+)+\\s{1}([\\/,\\+,\\*,\\-]{1})\\n?$";
            Pattern pattern = Pattern.compile(regexExpression);
            Matcher matcher;

            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                matcher = pattern.matcher(data);

                if (!matcher.find()) {
                    System.out.println("This line does not match the pattern <integer> <integer> <operation>");
                } else {
                    int firstInteger = Integer.parseInt(matcher.group(1));
                    int secondInteger = Integer.parseInt(matcher.group(2));
                    String operation = matcher.group(3);
                    for (ArithmeticOperation arithmeticOperation : ArithmeticOperation.values()) {
                        if (arithmeticOperation.getText().equals(operation)) {
                            System.out.println(String.format("%d %s %d = %.2f", firstInteger, operation, secondInteger,
                                    arithmeticOperation.apply(firstInteger, secondInteger)));
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
