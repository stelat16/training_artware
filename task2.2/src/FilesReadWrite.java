import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilesReadWrite {
    public static void main(String[] args) throws IOException {
        try {
            if (args.length != 2) {
                throw new FileNotFoundException();
            }
            File inputFile = new File(args[0]);
            File outputFile = new File(args[1]);
            Scanner myReader = new Scanner(inputFile);
            FileWriter myWriter = new FileWriter(outputFile);
            BufferedWriter bufferedWriter = new BufferedWriter(myWriter);
            String data;
            String regexExpression = "^(\\d+\\s{1}\\d+\\s{1}[\\\\,\\+,\\*,\\-]{1})\\n?$";
            Pattern pattern = Pattern.compile(regexExpression);
            Matcher matcher;

            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                matcher = pattern.matcher(data);

                if (matcher.find()) {
                    int firstInteger = Integer.parseInt(data.substring(0, data.indexOf(" ")));
                    int secondInteger = Integer.parseInt(data.substring(data.indexOf(" ") + 1, data.indexOf(" ", data.indexOf(" ") + 1)));
                    String operation = data.substring(data.length() - 1);

                    switch (operation) {
                        case "*": {
                            bufferedWriter.write(firstInteger + " " + operation + " " + secondInteger + " = " + (firstInteger * secondInteger));
                            break;
                        }
                        case "/": {
                            bufferedWriter.write(firstInteger + " " + operation + " " + secondInteger + " = " + (firstInteger / secondInteger));
                            break;
                        }
                        case "+": {
                            bufferedWriter.write(firstInteger + " " + operation + " " + secondInteger + " = " + (firstInteger + secondInteger));
                            break;
                        }
                        case "-": {
                            bufferedWriter.write(firstInteger + " " + operation + " " + secondInteger + " = " + (firstInteger - secondInteger));
                            break;
                        }
                    }
                } else {
                    bufferedWriter.write("This line does not match the pattern <integer> <integer> <operation>");
                }
                bufferedWriter.newLine();
            }
            myReader.close();
            bufferedWriter.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
