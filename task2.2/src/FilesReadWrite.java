import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilesReadWrite {

    public static final String REGEX_EXPRESSION = "^(\\d+)+\\s{1}(\\d+)+\\s{1}([\\/,\\+,\\*,\\-]{1})\\n?$";

    public static void main(String[] args) throws IOException {
        try (
                Scanner myReader = new Scanner(new File(args[0]));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(args[1]))
        ) {
            String data;
            Pattern pattern = Pattern.compile(REGEX_EXPRESSION);
            Matcher matcher;

            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                matcher = pattern.matcher(data);

                if (!matcher.find()) {
                    bufferedWriter.write("This line does not match the pattern <integer> <integer> <operation>");
                } else {
                    int firstInteger = Integer.parseInt(matcher.group(1));
                    int secondInteger = Integer.parseInt(matcher.group(2));
                    ArithmeticOperation operation = ArithmeticOperation.valueOf(matcher.group(3));

                    Calculator calculator = Calculator.builder()
                                                      .first(firstInteger)
                                                      .second(secondInteger)
                                                      .operation(operation)
                                                      .build();
                    double result = calculator.calculate();

                    bufferedWriter.write(String.format("%d %s %d = %.2f", firstInteger, operation.name(), secondInteger, result));
                }
                bufferedWriter.newLine();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Input/output file was not found!");
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Input/output file was not entered!");
            e.printStackTrace();
        }
    }
}
