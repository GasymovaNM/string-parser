import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final char OPEN_BRACKET = '[';
    private static final char CLOSE_BRACKET = ']';

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type expression:");
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            try {
                validate(line);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }
            Expression root = new Expression();
            handleExpression(line, root);
            root.print();
        }
    }

    private static void handleExpression(String line, Expression root) {
        for (int i = 0; i < line.length(); i++) {
            Expression expression = new Expression();
            root.append(expression);
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
                expression.setMultiplier(Integer.parseInt(number.toString()));

                int openBracket = line.indexOf(OPEN_BRACKET, i);
                int closeBracket = line.indexOf(CLOSE_BRACKET, i);
                String strInBrackets = line.substring(openBracket + 1, closeBracket);
                long openCount = countOpenBrackets(strInBrackets);
                long closeCount = countCloseBrackets(strInBrackets);

                while (openCount > closeCount) {
                    closeBracket = line.indexOf(CLOSE_BRACKET, closeBracket + 1);
                    strInBrackets = line.substring(openBracket + 1, closeBracket);
                    openCount = countOpenBrackets(strInBrackets);
                    closeCount = countCloseBrackets(strInBrackets);
                }
                handleExpression(strInBrackets, expression);
                i = closeBracket;
            } else {
                expression.append(c);
            }
        }
    }

    private static long countOpenBrackets(String str) {
        return str.chars().filter(ch -> ch == OPEN_BRACKET).count();
    }

    private static long countCloseBrackets(String str) {
        return str.chars().filter(ch -> ch == CLOSE_BRACKET).count();
    }

    private static void validate(String line) {
        Pattern validLettersPattern = Pattern.compile("[\\d\\[\\]a-zA-Z]+");
        Matcher matcher = validLettersPattern.matcher(line);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Incorrect input, line should contain only latin letters, digits and '[',']'");
        }

        Pattern sequencePattern = Pattern.compile("([a-zA-Z]*\\d+\\[.+\\][a-zA-Z]*)|([a-zA-Z]+)");
        matcher = sequencePattern.matcher(line);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Incorrect expression, valid examples:\n3[xyz]4[xy]z\n2[3[x]y]");
        }
    }
}
