import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadFileInput {
    public static void main(String[] args) throws IOException {
        try {
            File myObj = new File("input.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String regexExpression = "^(\\d+\\s{1}\\d+\\s{1}[\\\\,\\+,\\*,\\-]{1})\\n?$";
                Pattern pattern = Pattern.compile(regexExpression);
                Matcher matcher = pattern.matcher(data);
                boolean matchFound = matcher.find();

                if (matchFound) {
                    int firstInteger = Integer.parseInt(data.substring(0, data.indexOf(" ")));
                    int secondInteger = Integer.parseInt(data.substring(data.indexOf(" ") + 1, data.indexOf(" ", data.indexOf(" ") + 1)));
                    String operation = data.substring(data.length() - 1);
                    for (ArithmeticOperation arithmeticOperation : ArithmeticOperation.values()
                    ) {
                        if (arithmeticOperation.getText().equals(operation)) {
                            System.out.println(firstInteger + " " + operation + " " + secondInteger + " = " + arithmeticOperation.apply(firstInteger, secondInteger));
                        }
                    }
                } else {
                    System.out.println("This line does not match the pattern <integer> <integer> <operation>");
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
