import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilesReadWrite {
    public static void main(String[] args) throws IOException {
        try (
                Scanner myReader = new Scanner(new File(args[0]));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(args[1]))
        ) {
            String data;
            String regexExpression = "^(\\d+)+\\s{1}(\\d+)+\\s{1}([\\/,\\+,\\*,\\-]{1})\\n?$";
            Pattern pattern = Pattern.compile(regexExpression);
            Matcher matcher;

            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                matcher = pattern.matcher(data);

                if (!matcher.find()) {
                    bufferedWriter.write("This line does not match the pattern <integer> <integer> <operation>");
                } else {
                    int firstInteger = Integer.parseInt(matcher.group(1));
                    int secondInteger = Integer.parseInt(matcher.group(2));
                    String operation = matcher.group(3);
                    for (ArithmeticOperation arithmeticOperation : ArithmeticOperation.values()
                    ) {
                        if (arithmeticOperation.getText().equals(operation)) {
                            bufferedWriter.write(String.format("%d %s %d = %.2f", firstInteger, operation, secondInteger, arithmeticOperation.apply(firstInteger, secondInteger)));
                        }
                    }
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
