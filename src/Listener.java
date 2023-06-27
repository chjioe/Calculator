import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;

public class Listener implements ActionListener {
    private Calculator cl;
    private ArrayList<String> list = new ArrayList<String>();
    private ArrayList<String> his = new ArrayList<String>();  // 此列表用于添加最终结果。
    private ArrayList<String> arr = new ArrayList<String>();  // 将列表中的整个字符串拆分为单个字符，然后连接起来。
    private String[] arrayStr = new String[] {};  // 存储单个历史记录。
    private String out = "";// 当前的输出结果
    private String output = "";// 当前的表达式

    public Listener(Calculator cl) {
        this.cl = cl;
    }

    public void actionPerformed(ActionEvent event) {
        JButton button = (JButton) event.getSource();

        // 点击"="按钮时计算最终结果。如果是错误的表达式，则在文本框中显示"Input Error!"
        if (button.getText().equals("=")) {
            try {
                Function f = new Function();// 创建 Function 对象
                double result = f.compute(out);// 计算表达式的结果
                cl.textField.setText(Double.toString(result));// 将结果转换为字符串并设置到 textField 中显示
            } catch (Exception e) {
                cl.textField.setText("Input Error!"); // 如果计算出现异常，则在 textField 中显示 "Input Error!"
            }
        }
        // 点击"×"按钮时将其转换为"*"
        else if (button.getText().equals("×")) {
            if (list.isEmpty()) {
                arr.add("*"); // 将"*"添加到arr列表中
                output += "*"; // 在output字符串中追加"*"
                out = output; // 更新out变量为当前的output值
                cl.textField.setText(output); // 在文本框中显示output的值
            } else {
                list.add("*"); // 将"*"添加到list列表中
                output += "*"; // 在output字符串中追加"*"
                out = output; // 更新out变量为当前的output值
                cl.textField.setText(output); // 在文本框中显示output的值
            }
        }
        // 点击"÷"按钮时将其转换为"/"
        else if (button.getText().equals("÷")) {
            if (list.isEmpty()) {
                arr.add("/"); // 将"/"添加到arr列表中
                output += "/"; // 在output字符串中追加"/"
                out = output; // 更新out变量为当前的output值
                cl.textField.setText(output); // 在文本框中显示output的值
            } else {
                list.add("/"); // 将"/"添加到list列表中
                output += "/"; // 在output字符串中追加"/"
                out = output; // 更新out变量为当前的output值
                cl.textField.setText(output); // 在文本框中显示output的值
            }
        }
        // 点击"DEL"按钮时删除表达式中的最后一个字符
        else if (button.getText().equals("DEL")) {
            if (list.isEmpty()) {
                arr.remove(arr.size() - 1); // 从arr列表中删除最后一个元素
                output = ""; // 将output字符串重置为空
                for (int i = 0; i < arr.size(); i++)
                    output += arr.get(i); // 重新构建output字符串
                out = output; // 更新out变量为当前的output值
                cl.textField.setText(output); // 在文本框中显示output的值
            } else {
                list.remove(list.size() - 1); // 从list列表中删除最后一个元素
                String output = ""; // 创建一个新的output字符串
                for (int i = 0; i < list.size(); i++)
                    output += list.get(i); // 重新构建output字符串
                out = output; // 更新out变量为当前的output值
                cl.textField.setText(output); // 在文本框中显示output的值
            }
        }
        // 点击"AC"按钮时删除list，并将表达式保存到list中
        else if (button.getText().equals("AC")) {
            his.add(out); // 将out变量的值添加到his列表中
            list.clear(); // 清空list列表
            output = ""; // 将output字符串重置为空
            cl.textField.setText(output); // 在文本框中显示output的值
        }
        // 点击"Replay"按钮时，文本框中显示上一个表达式
        else if (button.getText().equals("Replay")) {
            output = his.get(his.size() - 1); // 将his列表中最后一个表达式赋值给output
            cl.textField.setText(output); // 在文本框中显示output的值
            arr.clear(); // 清空arr列表
            char[] a = output.toCharArray(); // 将output转换为字符数组
            for (int i = 0; i < a.length; i++) {
                arr.add(String.valueOf(a[i])); // 将字符数组中的每个字符转换为字符串并添加到arr列表中
            }
            his.remove(his.size() - 1); // 从his列表中删除最后一个表达式
        }

        // 其他按钮直接添加到表达式中
        else {
            if (list.isEmpty()) {
                arr.add(button.getText()); // 将按钮文本添加到arr列表中
                output += button.getText(); // 在output字符串中追加按钮文本
                out = output; // 更新out变量为当前的output值
                cl.textField.setText(output); // 在文本框中显示output的值
            } else {
                list.add(button.getText()); // 将按钮文本添加到list列表中
                output += button.getText(); // 在output字符串中追加按钮文本
                out = output; // 更新out变量为当前的output值
                cl.textField.setText(output); // 在文本框中显示output的值
            }
        }
    }
}
