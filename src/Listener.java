import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;

public class Listener implements ActionListener {
    private Calculator cl;
    private ArrayList<String> list = new ArrayList<String>();
    private ArrayList<String> his = new ArrayList<String>();  // ���б�����������ս����
    private ArrayList<String> arr = new ArrayList<String>();  // ���б��е������ַ������Ϊ�����ַ���Ȼ������������
    private String[] arrayStr = new String[] {};  // �洢������ʷ��¼��
    private String out = "";// ��ǰ��������
    private String output = "";// ��ǰ�ı��ʽ

    public Listener(Calculator cl) {
        this.cl = cl;
    }

    public void actionPerformed(ActionEvent event) {
        JButton button = (JButton) event.getSource();

        // ���"="��ťʱ�������ս��������Ǵ���ı��ʽ�������ı�������ʾ"Input Error!"
        if (button.getText().equals("=")) {
            try {
                Function f = new Function();// ���� Function ����
                double result = f.compute(out);// ������ʽ�Ľ��
                cl.textField.setText(Double.toString(result));// �����ת��Ϊ�ַ��������õ� textField ����ʾ
            } catch (Exception e) {
                cl.textField.setText("Input Error!"); // �����������쳣������ textField ����ʾ "Input Error!"
            }
        }
        // ���"��"��ťʱ����ת��Ϊ"*"
        else if (button.getText().equals("��")) {
            if (list.isEmpty()) {
                arr.add("*"); // ��"*"��ӵ�arr�б���
                output += "*"; // ��output�ַ�����׷��"*"
                out = output; // ����out����Ϊ��ǰ��outputֵ
                cl.textField.setText(output); // ���ı�������ʾoutput��ֵ
            } else {
                list.add("*"); // ��"*"��ӵ�list�б���
                output += "*"; // ��output�ַ�����׷��"*"
                out = output; // ����out����Ϊ��ǰ��outputֵ
                cl.textField.setText(output); // ���ı�������ʾoutput��ֵ
            }
        }
        // ���"��"��ťʱ����ת��Ϊ"/"
        else if (button.getText().equals("��")) {
            if (list.isEmpty()) {
                arr.add("/"); // ��"/"��ӵ�arr�б���
                output += "/"; // ��output�ַ�����׷��"/"
                out = output; // ����out����Ϊ��ǰ��outputֵ
                cl.textField.setText(output); // ���ı�������ʾoutput��ֵ
            } else {
                list.add("/"); // ��"/"��ӵ�list�б���
                output += "/"; // ��output�ַ�����׷��"/"
                out = output; // ����out����Ϊ��ǰ��outputֵ
                cl.textField.setText(output); // ���ı�������ʾoutput��ֵ
            }
        }
        // ���"DEL"��ťʱɾ�����ʽ�е����һ���ַ�
        else if (button.getText().equals("DEL")) {
            if (list.isEmpty()) {
                arr.remove(arr.size() - 1); // ��arr�б���ɾ�����һ��Ԫ��
                output = ""; // ��output�ַ�������Ϊ��
                for (int i = 0; i < arr.size(); i++)
                    output += arr.get(i); // ���¹���output�ַ���
                out = output; // ����out����Ϊ��ǰ��outputֵ
                cl.textField.setText(output); // ���ı�������ʾoutput��ֵ
            } else {
                list.remove(list.size() - 1); // ��list�б���ɾ�����һ��Ԫ��
                String output = ""; // ����һ���µ�output�ַ���
                for (int i = 0; i < list.size(); i++)
                    output += list.get(i); // ���¹���output�ַ���
                out = output; // ����out����Ϊ��ǰ��outputֵ
                cl.textField.setText(output); // ���ı�������ʾoutput��ֵ
            }
        }
        // ���"AC"��ťʱɾ��list���������ʽ���浽list��
        else if (button.getText().equals("AC")) {
            his.add(out); // ��out������ֵ��ӵ�his�б���
            list.clear(); // ���list�б�
            output = ""; // ��output�ַ�������Ϊ��
            cl.textField.setText(output); // ���ı�������ʾoutput��ֵ
        }
        // ���"Replay"��ťʱ���ı�������ʾ��һ�����ʽ
        else if (button.getText().equals("Replay")) {
            output = his.get(his.size() - 1); // ��his�б������һ�����ʽ��ֵ��output
            cl.textField.setText(output); // ���ı�������ʾoutput��ֵ
            arr.clear(); // ���arr�б�
            char[] a = output.toCharArray(); // ��outputת��Ϊ�ַ�����
            for (int i = 0; i < a.length; i++) {
                arr.add(String.valueOf(a[i])); // ���ַ������е�ÿ���ַ�ת��Ϊ�ַ�������ӵ�arr�б���
            }
            his.remove(his.size() - 1); // ��his�б���ɾ�����һ�����ʽ
        }

        // ������ťֱ����ӵ����ʽ��
        else {
            if (list.isEmpty()) {
                arr.add(button.getText()); // ����ť�ı���ӵ�arr�б���
                output += button.getText(); // ��output�ַ�����׷�Ӱ�ť�ı�
                out = output; // ����out����Ϊ��ǰ��outputֵ
                cl.textField.setText(output); // ���ı�������ʾoutput��ֵ
            } else {
                list.add(button.getText()); // ����ť�ı���ӵ�list�б���
                output += button.getText(); // ��output�ַ�����׷�Ӱ�ť�ı�
                out = output; // ����out����Ϊ��ǰ��outputֵ
                cl.textField.setText(output); // ���ı�������ʾoutput��ֵ
            }
        }
    }
}
