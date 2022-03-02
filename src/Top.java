import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.Vector;

public class Top {
    String url = "jdbc:mysql://localhost:3306/musicbands";
    String name = "root";
    String password = "hippino25";
    public Top()  {


        JFrame frame1 = new JFrame("MUSAPP:TOP");

        frame1.setSize(1000,300);
        frame1.setLocation(250,200);
        frame1.setResizable(false);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("./images/TOP.png");
        frame1.setIconImage(icon.getImage());
        JPanel panel2= new JPanel();
        panel2.setBounds(0,60,1000,1000);
        JLabel imagelabel = new JLabel();
        imagelabel.setIcon(new ImageIcon("./images/top.jpg"));
        panel2.add(imagelabel);
        frame1.add(panel2);

        JToolBar toolBar = new JToolBar("Панель инструментов");
        JButton save = new JButton(new ImageIcon("./image/save.png"));
        JButton database = new JButton(new ImageIcon("./image/download.png"));
        JButton edit = new JButton(new ImageIcon("./image/edit.png"));
        JButton back = new JButton(new ImageIcon("./image/back.png"));
        frame1.add(toolBar,BorderLayout.NORTH);
        toolBar.add(save);
        toolBar.add(database);
        toolBar.add(edit);
        toolBar.add(back);



        final String column[] = {"ID","Название группы", "Участники группы", "Жанр", "Год основания", "#Хит парад","Концерты"};
        Vector<String> columns = new Vector<>();

        for(int i = 0;i < column.length;i++) {
            columns.add(column[i]);
        }

        Vector <Vector<String>> data = new Vector<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection con = DriverManager.getConnection(url, name, password)) {

                Statement statement = con.createStatement();
                ResultSet resultSet;
                resultSet = statement.executeQuery("SELECT * FROM musicbands.topchart2");
                while (resultSet.next()) {
                    Vector<String> row = new Vector<>();
                    row.add(String.valueOf(resultSet.getInt(1)));
                    row.add(resultSet.getNString(3));
                    row.add(resultSet.getNString(4));
                    row.add(resultSet.getNString(5));
                    row.add(String.valueOf(resultSet.getInt(6)));
                    row.add(resultSet.getNString(7));
                    row.add(resultSet.getNString(8));
                    data.add(row);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        icon = new ImageIcon("./image/databaseyes.png");
        JOptionPane.showMessageDialog(null,
                "Информация из баз данных загружена в таблицу !","Информация о работе базы данных",
                JOptionPane.INFORMATION_MESSAGE,
                icon);
        DefaultTableModel model = new DefaultTableModel(data,columns);
        JTable musicians = new JTable(model);
        musicians.setFont(new Font("Century Gothic", 1, 15));
        musicians.setPreferredScrollableViewportSize(new Dimension(750,100));
        musicians.setFillsViewportHeight(true);
        final TableRowSorter<TableModel> sorter = new TableRowSorter<>(model);
        musicians.setRowSorter(sorter);
        JScrollPane scroll = new JScrollPane(musicians);
        JPanel panel= new JPanel();
        frame1.add(panel,BorderLayout.CENTER);
        panel.add(scroll);
        musicians.setEnabled(true);

        Actionlistener AL1 = new Actionlistener();

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame1.setVisible(false);
            }
        });

        edit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
               AL1.Changestring1(model,frame1);
            }
        });


        frame1.setVisible(true);

    }
}
