import java.util.Stack;

public class Function {
    /**
     * ����׺���ʽת��Ϊ��׺���ʽ�����������ս����
     *
     * ����һ����ջ��һ�����ַ������ڴ洢��׺���ʽ��
     * �����ұ�����׺���ʽ��ÿ��Ԫ�ء�
     * ������������������֣���ֱ�ӽ�����ӵ���׺���ʽ�ַ����С�
     * �������������"("������ѹ��ջ�С�
     * �������������")"����ջ�е�Ԫ�����ε�������ӵ���׺���ʽ�ַ����У�ֱ������������Ϊֹ��ע�⣬�����Ų�����ӵ���׺���ʽ�У����Ǳ�������
     * ��������������"+", "-", "*", "/"�ȣ�����������²�����
     * ���ջΪ�գ���ջ��Ԫ��Ϊ������"("���򽫵�ǰ�����ѹ��ջ�С�
     * ���򣬱Ƚϵ�ǰ�������ջ������������ȼ���
     * �����ǰ��������ȼ�����ջ�������������ǰ�����ѹ��ջ�С�
     * ���򣬽�ջ���������������ӵ���׺���ʽ�ַ����У�Ȼ���ٴν��в���6�ıȽϲ�����
     * ��������׺���ʽ�󣬽�ջ��ʣ�����������ε�������ӵ���׺���ʽ�ַ����С�
     * ��׺���ʽ�ַ�����Ϊת����ĺ�׺���ʽ��
     *
     * @param exp ��׺���ʽ��
     * @return ��������ս����
     */
    public double compute(String exp) {
        char[] ch = exp.toCharArray(); // ����׺���ʽת��Ϊ�ַ�����
        Stack<Character> operatorStack = new Stack<>(); // �����ջ
        StringBuilder postfixExp = new StringBuilder(); // ��׺���ʽ�ַ���

        for (int i = 0; i < ch.length; i++) {
            char c = ch[i]; // ��ǰ�ַ�
            if (isOperator(c)) { // ����������
                while (!operatorStack.isEmpty() && hasHigherPrecedence(operatorStack.peek(), c)) {
                    postfixExp.append(operatorStack.pop()).append(" "); // �����и������ȼ����������ջ�е����������׺���ʽ�ַ���
                }
                operatorStack.push(c); // ��ǰ�������ջ
            } else if (isOperand(c)) { // ����ǲ�����
                // �����λ��
                StringBuilder operand = new StringBuilder();
                while (i < ch.length && isOperand(ch[i])) {
                    operand.append(ch[i++]); // ����λ����ÿһλ�ַ���ӵ��������ַ�����
                }
                postfixExp.append(operand.toString()).append(" "); // ����������ӵ���׺���ʽ�ַ���
                i--; // ��Ϊ��forѭ���л�������������Ҫ��1
            } else if (c == '(') { // �����������
                operatorStack.push(c); // ��������ջ
            } else if (c == ')') { // �����������
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    postfixExp.append(operatorStack.pop()).append(" "); // ��ջ�е�����������������׺���ʽ�ַ�����ֱ������������
                }
                if (!operatorStack.isEmpty() && operatorStack.peek() == '(') {
                    operatorStack.pop(); // ����������
                }
            }
        }

        while (!operatorStack.isEmpty()) {
            postfixExp.append(operatorStack.pop()).append(" "); // ��ʣ�������������������׺���ʽ�ַ���
        }

        System.out.println(postfixExp); // ��ӡ��׺���ʽ
        return evaluatePostfixExpression(postfixExp.toString()); // �����׺���ʽ�Ľ��
    }

    /**
     * �����׺���ʽ�Ľ����
     *
     * @param postfixExp ��׺���ʽ���ַ�����
     * @return ��������ս����
     */
    public double evaluatePostfixExpression(String postfixExp) {
        String[] tokens = postfixExp.trim().split("\\s+"); // ����׺���ʽ�ַ������
        Stack<Double> operandStack = new Stack<>(); // ������ջ

        for (String token : tokens) {
            if (isOperand(token.charAt(0))) { // ����ǲ�����
                operandStack.push(Double.parseDouble(token)); // ��������ת��Ϊdouble����ջ
            } else if (isOperator(token.charAt(0))) { // ����������
                double num2 = operandStack.pop(); // ����ջ���ĵڶ���������
                double num1 = operandStack.pop(); // ����ջ���ĵ�һ��������
                double result = performOperation(token.charAt(0), num1, num2); // ִ�����������
                operandStack.push(result); // �������ջ
            }
        }

        return operandStack.pop(); // ����ջ�����һ��Ԫ�أ�����������ս��
    }

    /**
     * ִ�������������
     *
     * @param operator �������
     * @param num1     ��һ����������
     * @param num2     �ڶ�����������
     * @return ��������
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
     * ����Ƿ�Ϊ�������
     *
     * @param c ��ǰ�ַ���
     * @return ����ֵ�����
     */
    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/'; // �����+��-��*��/��������򷵻�true�����򷵻�false
    }

    /**
     * ����Ƿ�Ϊ��������
     *
     * @param c ��ǰ�ַ���
     * @return ����ֵ�����
     */
    private boolean isOperand(char c) {
        return Character.isDigit(c); // ����������ַ����򷵻�true�����򷵻�false
    }

    /**
     * �������������ȼ���
     *
     * @param operator1 �����1��
     * @param operator2 �����2��
     * @return �����1�Ƿ���и��ߵ����ȼ���
     */
    private boolean hasHigherPrecedence(char operator1, char operator2) {
        if ((operator1 == '*' || operator1 == '/') && (operator2 == '+' || operator2 == '-')) {
            return true; // ��������1��*��/�������2��+��-���������1���и��ߵ����ȼ�������true
        }
        return false; // ���򷵻�false
    }
}
