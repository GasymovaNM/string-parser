import java.util.ArrayList;
import java.util.List;

public class Expression {

    private StringBuilder result = new StringBuilder();

    private int multiplier = 1;

    private List<Expression> children = new ArrayList<>();

    public void append(Expression child) {
        children.add(child);
    }

    public void append(char ch) {
        result.append(ch);
    }

    public void append(String str) {
        result.append(str);
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    public void print() {
        StringBuilder parsedExpressions = new StringBuilder();
        printResult(parsedExpressions);
        System.out.println(parsedExpressions);

    }

    private void printResult(StringBuilder parsedExpressions) {
        String resultStr = result.toString();
        if (!resultStr.isEmpty()) {
            for (int i = 0; i < multiplier; i++) {
                parsedExpressions.append(resultStr);
            }
        }
        for (Expression expr : children) {
            expr.printResult(parsedExpressions);
        }
    }
}
