import java.util.ArrayList;
import java.util.List;

public class Expression {

    private Character letter;
    private int multiplier = 1;
    private List<Expression> children = new ArrayList<>();

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    public void append(Expression child) {
        children.add(child);
    }

    public void append(char ch) {
        letter = ch;
    }

    public void print() {
        StringBuilder parsedExpressions = new StringBuilder();
        printResult(parsedExpressions);
        System.out.println(parsedExpressions);

    }

    private void printResult(StringBuilder parsedExpressions) {
        if (letter != null) {
            for (int i = 0; i < multiplier; i++) {
                parsedExpressions.append(letter);
            }
        }

        for (int i = 0; i < multiplier; i++) {
            for (Expression expr : children) {
                expr.printResult(parsedExpressions);
            }
        }
    }
}
