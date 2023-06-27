import java.util.Stack;

public class Function {
    /**
     * 将中缀表达式转换为后缀表达式，并计算最终结果。
     *
     * 创建一个空栈和一个空字符串用于存储后缀表达式。
     * 从左到右遍历中缀表达式的每个元素。
     * 如果遇到操作数（数字），直接将其添加到后缀表达式字符串中。
     * 如果遇到左括号"("，将其压入栈中。
     * 如果遇到右括号")"，则将栈中的元素依次弹出并添加到后缀表达式字符串中，直到遇到左括号为止。注意，左括号不会添加到后缀表达式中，而是被丢弃。
     * 如果遇到运算符（"+", "-", "*", "/"等），则进行以下操作：
     * 如果栈为空，或栈顶元素为左括号"("，则将当前运算符压入栈中。
     * 否则，比较当前运算符与栈顶运算符的优先级：
     * 如果当前运算符优先级高于栈顶运算符，将当前运算符压入栈中。
     * 否则，将栈顶运算符弹出并添加到后缀表达式字符串中，然后再次进行步骤6的比较操作。
     * 遍历完中缀表达式后，将栈中剩余的运算符依次弹出并添加到后缀表达式字符串中。
     * 后缀表达式字符串即为转换后的后缀表达式。
     *
     * @param exp 中缀表达式。
     * @return 计算的最终结果。
     */
    public double compute(String exp) {
        char[] ch = exp.toCharArray(); // 将中缀表达式转换为字符数组
        Stack<Character> operatorStack = new Stack<>(); // 运算符栈
        StringBuilder postfixExp = new StringBuilder(); // 后缀表达式字符串

        for (int i = 0; i < ch.length; i++) {
            char c = ch[i]; // 当前字符
            if (isOperator(c)) { // 如果是运算符
                while (!operatorStack.isEmpty() && hasHigherPrecedence(operatorStack.peek(), c)) {
                    postfixExp.append(operatorStack.pop()).append(" "); // 将具有更高优先级的运算符从栈中弹出并加入后缀表达式字符串
                }
                operatorStack.push(c); // 当前运算符入栈
            } else if (isOperand(c)) { // 如果是操作数
                // 处理多位数
                StringBuilder operand = new StringBuilder();
                while (i < ch.length && isOperand(ch[i])) {
                    operand.append(ch[i++]); // 将多位数的每一位字符添加到操作数字符串中
                }
                postfixExp.append(operand.toString()).append(" "); // 将操作数添加到后缀表达式字符串
                i--; // 因为在for循环中会自增，所以需要减1
            } else if (c == '(') { // 如果是左括号
                operatorStack.push(c); // 左括号入栈
            } else if (c == ')') { // 如果是右括号
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    postfixExp.append(operatorStack.pop()).append(" "); // 将栈中的运算符弹出并加入后缀表达式字符串，直到遇到左括号
                }
                if (!operatorStack.isEmpty() && operatorStack.peek() == '(') {
                    operatorStack.pop(); // 弹出左括号
                }
            }
        }

        while (!operatorStack.isEmpty()) {
            postfixExp.append(operatorStack.pop()).append(" "); // 将剩余的运算符弹出并加入后缀表达式字符串
        }

        System.out.println(postfixExp); // 打印后缀表达式
        return evaluatePostfixExpression(postfixExp.toString()); // 计算后缀表达式的结果
    }

    /**
     * 计算后缀表达式的结果。
     *
     * @param postfixExp 后缀表达式的字符串。
     * @return 计算的最终结果。
     */
    public double evaluatePostfixExpression(String postfixExp) {
        String[] tokens = postfixExp.trim().split("\\s+"); // 将后缀表达式字符串拆分
        Stack<Double> operandStack = new Stack<>(); // 操作数栈

        for (String token : tokens) {
            if (isOperand(token.charAt(0))) { // 如果是操作数
                operandStack.push(Double.parseDouble(token)); // 将操作数转换为double并入栈
            } else if (isOperator(token.charAt(0))) { // 如果是运算符
                double num2 = operandStack.pop(); // 弹出栈顶的第二个操作数
                double num1 = operandStack.pop(); // 弹出栈顶的第一个操作数
                double result = performOperation(token.charAt(0), num1, num2); // 执行运算符操作
                operandStack.push(result); // 将结果入栈
            }
        }

        return operandStack.pop(); // 返回栈中最后一个元素，即计算的最终结果
    }

    /**
     * 执行运算符操作。
     *
     * @param operator 运算符。
     * @param num1     第一个操作数。
     * @param num2     第二个操作数。
     * @return 运算结果。
     */
    private double performOperation(char operator, double num1, double num2) {
        double result = 0;
        switch (operator) {
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num1 / num2;
                break;
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
        }
        return result;
    }

    /**
     * 检查是否为运算符。
     *
     * @param c 当前字符。
     * @return 布尔值结果。
     */
    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/'; // 如果是+、-、*、/运算符，则返回true，否则返回false
    }

    /**
     * 检查是否为操作数。
     *
     * @param c 当前字符。
     * @return 布尔值结果。
     */
    private boolean isOperand(char c) {
        return Character.isDigit(c); // 如果是数字字符，则返回true，否则返回false
    }

    /**
     * 检查运算符的优先级。
     *
     * @param operator1 运算符1。
     * @param operator2 运算符2。
     * @return 运算符1是否具有更高的优先级。
     */
    private boolean hasHigherPrecedence(char operator1, char operator2) {
        if ((operator1 == '*' || operator1 == '/') && (operator2 == '+' || operator2 == '-')) {
            return true; // 如果运算符1是*或/，运算符2是+或-，则运算符1具有更高的优先级，返回true
        }
        return false; // 否则返回false
    }
}
