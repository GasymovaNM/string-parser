import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();

            //todo добавить проверку что скобки идут только после цифр
            Pattern pattern = Pattern.compile("[\\d\\[\\]a-zA-Z]+");
            Matcher matcher = pattern.matcher(line);
            if (!matcher.matches()) {
                System.out.println("Incorrect input, line should contain only latin letters, digits and '[',']'");
                break;
            }
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);

                if (Character.isDigit(c)) {
                    StringBuilder number = new StringBuilder();
                    int digitCount = i;
                    char digitChar = c;
                    while (Character.isDigit(digitChar)) {
                        number.append(digitChar);
                        i = digitCount;
                        if (++digitCount < line.length()) {
                            digitChar = line.charAt(digitCount);
                        } else break;
                    }

                    String numStr = number.toString();
                    //todo добавить обработку вложенности
                    if (!numStr.isEmpty()) {
                        int openBracket = line.indexOf('[', i);
                        int closeBracket = line.indexOf(']', i);
                        String substring = line.substring(openBracket + 1, closeBracket);
                        for (int j = 0; j < Integer.parseInt(numStr); j++) {
                            result.append(substring);
                        }
                        i = closeBracket;
                    }
                } else {
                    result.append(c);
                }
            }
            System.out.println(result);
        }
    }
}
