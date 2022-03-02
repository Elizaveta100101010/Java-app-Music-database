import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;




//Создание фрейма с входом по логину и паролю
public class Enter1 extends LoginPasswordData implements ActionListener {

    JPanel panel = new JPanel();
    JFrame loginframe = new JFrame("MUSAPP: ВХОД");
    JLabel loginlabel = new JLabel("Логин");
    JTextField loginText = new JTextField(20);
    JLabel passwordlabel = new JLabel("Пароль");
    JLabel label = new JLabel("");
    JLabel label1 = new JLabel("");
    JPasswordField passwordText = new JPasswordField(20);
    JButton button = new JButton("Войти");
    JButton button1 = new JButton("Сброс");

    HashMap<String,String> logininfo = new HashMap<String,String>();

    Enter1(HashMap<String,String> loginInfoOriginal){

        logininfo = loginInfoOriginal;
        panel.setBackground(new Color(255, 255, 255));
        loginframe.setSize(300,250);
        loginframe.setLocation(600, 300);
        loginframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("./images/registration.png");
        loginframe.setIconImage(icon.getImage());
        loginframe.setResizable(false);
        loginframe.add(panel);

        panel.setLayout(null);

        label.setFont(new Font("Century Gothic", Font.BOLD, 14));
        label.setBounds(90,140,100,70);
        panel.add(label);

        loginlabel.setFont(new Font("Century Gothic", Font.BOLD, 15));
        loginlabel.setBounds(10,30,80,25);
        panel.add(loginlabel);


        loginText.setBounds(100,30,165,25);
        panel.add(loginText);
        loginText.setFont(new Font("Century Gothic", Font.BOLD, 15));

        passwordlabel.setFont(new Font("Century Gothic", Font.BOLD, 15));
        passwordlabel.setBounds(10,70,80,25);
        panel.add(passwordlabel);


        passwordText.setBounds(100,70,165,25);
        panel.add(passwordText);
        passwordText.setFont(new Font("Century Gothic", Font.BOLD, 15));
        button.setBounds(40,115,85,25);
        panel.add(button);
        button.setFont(new Font("Century Gothic", Font.BOLD, 15));
        button.setBackground(new Color(112, 144, 237));
        button.setForeground(new Color(250, 250, 250).brighter());
        button.setFocusable(false);
        button.addActionListener(this);

        button1.setBounds(160,115,85,25);
        panel.add(button1);
        button1.setFont(new Font("Century Gothic", Font.BOLD, 15));
        button1.setBackground(new Color(112, 144, 237));
        button1.setForeground(new Color(250, 250, 250).brighter());
        button1.setFocusable(false);
        button1.addActionListener(this);



        loginframe.setVisible(true);


    }
    //Обработка нажатия кнопки отмена
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button1) {
            loginText.setText("");
            passwordText.setText("");
        }
//Обработка нажатия кнопки вход
        if(e.getSource()==button) {
            String login = loginText.getText();
            String password = String.valueOf(passwordText.getPassword());



            if(logininfo.containsKey(login)) {
                if(logininfo.get(login).equals(password)) {
                    label.setForeground(Color.green);
                    label.setText("<html>Вход<br> выполнен !</html>");
                    try {
                        App app = new App();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    loginframe.dispose();
                }}

            else {
                label.setForeground(Color.red);
                label.setText("<html>Неправильный<br> пароль !</html>");
            }

        }





    }

}

