import javax.swing.*;
import java.awt.*;

public class Calculator extends JFrame {

    //�ı���
    JTextField textField = new JTextField();
    JPanel row = new JPanel();

    //��ť
    String[][] buttons = {{"7","8","9","DEL","AC"},{"4","5","6","��","��"},{"1","2","3","+","-"},{"0","(",")","Replay","="}};
    JButton[][]button = new JButton[4][5];

    public Calculator()
    {
        super("Calculator");// ���ø���Ĺ��캯�����������"Calculator"
        setSize(450,400);// ���ô��ڵĴ�СΪ450*400����
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ���ùرմ���ʱ�Ĳ���Ϊ��ֹ����
        setLayout(new BorderLayout()); // ���ò��ֹ�����ΪBorderLayout


        /**
         * �����ı��򲢽�������
         **/
        textField.setPreferredSize(new Dimension(30, 40));
        // �����ı������ѡ��СΪ30x40����
        textField.setHorizontalAlignment(SwingConstants.TRAILING);
        // �����ı������ı���ˮƽ���뷽ʽΪ�Ҷ���
        textField.setEditable(false);
        // �����ı��򲻿ɱ༭
        getContentPane().add(textField, BorderLayout.NORTH);
        // ���ı�����ӵ����ڵı�����������λ��

        add(row, BorderLayout.CENTER);// ����һ����ť������

        GridLayout layout = new GridLayout(4,5,5,5);// ����һ��4��5�е����񲼾֣��м����м��Ϊ5����
        row.setLayout(layout);


        // ѭ��������ά����buttons�е�ÿ��Ԫ�أ�������ť����ӵ���ť��������
        for(int i = 0; i < buttons.length; i++)
        {
            for(int j = 0; j < buttons[0].length; j++)
            {
                button[i][j] = new JButton(buttons[i][j]);
                row.add(button[i][j]);
            }
        }


        // ����ť��������ӵ����ڵ�����λ��
        add(row);
        setResizable(false);// ���ô��ڴ�С���ɵ���
        setVisible(true);// ���ô��ڿɼ�
    }

    private static void setLookAndFeel()
    {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName()// ����ϵͳui
            );
        } catch (Exception e) {
            // �����쳣
        }
    }

    /**
     * ������
     */
    public static void main(String[] args)
    {
        Calculator.setLookAndFeel();// ���ý������
        Calculator cl = new Calculator();
        cl.listener();// ��Ӱ�ť���¼�������
    }

    /**
     * Ϊ��ť����¼�������
     */
    public void listener()
    {
        Listener l = new Listener(this);// ����Listener���󣬲����뵱ǰ���������
        for(int i = 0; i < buttons.length; i++)
        {
            for(int j = 0; j < buttons[0].length; j++)
            {
                button[i][j].addActionListener(l);// Ϊÿ����ť���Listener������Ϊ�¼�������
            }
        }
    }

}
