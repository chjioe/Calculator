import javax.swing.*;
import java.awt.*;

public class Calculator extends JFrame {

    //文本框
    JTextField textField = new JTextField();
    JPanel row = new JPanel();

    //按钮
    String[][] buttons = {{"7","8","9","DEL","AC"},{"4","5","6","×","÷"},{"1","2","3","+","-"},{"0","(",")","Replay","="}};
    JButton[][]button = new JButton[4][5];

    public Calculator()
    {
        super("Calculator");// 调用父类的构造函数，传入标题"Calculator"
        setSize(450,400);// 设置窗口的大小为450*400像素
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 设置关闭窗口时的操作为终止程序
        setLayout(new BorderLayout()); // 设置布局管理器为BorderLayout


        /**
         * 创建文本框并进行设置
         **/
        textField.setPreferredSize(new Dimension(30, 40));
        // 设置文本框的首选大小为30x40像素
        textField.setHorizontalAlignment(SwingConstants.TRAILING);
        // 设置文本框中文本的水平对齐方式为右对齐
        textField.setEditable(false);
        // 设置文本框不可编辑
        getContentPane().add(textField, BorderLayout.NORTH);
        // 将文本框添加到窗口的北部（顶部）位置

        add(row, BorderLayout.CENTER);// 声明一个按钮组容器

        GridLayout layout = new GridLayout(4,5,5,5);// 创建一个4行5列的网格布局，行间距和列间距为5像素
        row.setLayout(layout);


        // 循环遍历二维数组buttons中的每个元素，创建按钮并添加到按钮组容器中
        for(int i = 0; i < buttons.length; i++)
        {
            for(int j = 0; j < buttons[0].length; j++)
            {
                button[i][j] = new JButton(buttons[i][j]);
                row.add(button[i][j]);
            }
        }


        // 将按钮组容器添加到窗口的中央位置
        add(row);
        setResizable(false);// 设置窗口大小不可调整
        setVisible(true);// 设置窗口可见
    }

    private static void setLookAndFeel()
    {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName()// 跟随系统ui
            );
        } catch (Exception e) {
            // 忽略异常
        }
    }

    /**
     * 主函数
     */
    public static void main(String[] args)
    {
        Calculator.setLookAndFeel();// 设置界面外观
        Calculator cl = new Calculator();
        cl.listener();// 添加按钮的事件监听器
    }

    /**
     * 为按钮添加事件监听器
     */
    public void listener()
    {
        Listener l = new Listener(this);// 创建Listener对象，并传入当前对象的引用
        for(int i = 0; i < buttons.length; i++)
        {
            for(int j = 0; j < buttons[0].length; j++)
            {
                button[i][j].addActionListener(l);// 为每个按钮添加Listener对象作为事件监听器
            }
        }
    }

}
