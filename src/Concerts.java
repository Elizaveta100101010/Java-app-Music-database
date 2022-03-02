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

public class Concerts extends JFrame {
    String url = "jdbc:mysql://localhost:3306/musicbands";
    String name = "root";
    String password = "hippino25";
    public  Concerts(int id) {
        JFrame frame = new JFrame("MUSAPP:CONCERTS");

        frame.setSize(900,400);
        frame.setLocation(300,200);
        frame.setResizable(false);

        //Установка иконки приложения
        ImageIcon icon = new ImageIcon("./images/concert.png");
        frame.setIconImage(icon.getImage());

        // Создание панели инструментов
        JToolBar toolBar = new JToolBar("Панель инструментов");
        JButton back = new JButton(new ImageIcon("./image/back.png"));
        JButton back1 = new JButton(new ImageIcon("./image/back4.png"));
        toolBar.add(back);
        toolBar.add(back1);

        frame.setLayout(new

        BorderLayout());
        frame.add(toolBar,BorderLayout.NORTH);

        String column[] = {"ID", "Город", "Страна","Дата"};
        Vector<String> columns = new Vector<>();

        for(
        int i = 0;
        i<column.length;i++)

        {
            columns.add(column[i]);
        }

        Vector<Vector<String>> data = new Vector<>();

        DefaultTableModel model = new DefaultTableModel(data, columns);
        JTable stock = new JTable(model);

        final TableRowSorter<TableModel> sorter = new TableRowSorter<>(model);
        stock.setRowSorter(sorter);
        JScrollPane scroll = new JScrollPane(stock);

        // Размещение таблицы в окне
        frame.add(scroll,BorderLayout.CENTER);
        stock.setEnabled(false);

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });
        ImageIcon icon1 = new ImageIcon("./image/databaseyes.png");
        JOptionPane.showMessageDialog(null,
                "Информация из базы данных успешно загружена !","Информация о работе базы данных",
                JOptionPane.INFORMATION_MESSAGE,
                icon1);
        if (id==1){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                try (Connection con = DriverManager.getConnection(url, name, password)) {

                    Statement statement = con.createStatement();
                    ResultSet resultSet;
                    resultSet = statement.executeQuery("SELECT * FROM musicbands.concerts2 WHERE idAllBands2=1");
                    while (resultSet.next()) {
                        Vector<String> row = new Vector<>();
                        row.add(String.valueOf(resultSet.getInt(1)));
                        row.add(resultSet.getNString(3));
                        row.add(resultSet.getNString(4));
                        row.add(String.valueOf(resultSet.getDate(5)));

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

        }
        if (id==2){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                try (Connection con = DriverManager.getConnection(url, name, password)) {

                    Statement statement = con.createStatement();
                    ResultSet resultSet;
                    resultSet = statement.executeQuery("SELECT * FROM musicbands.concerts2 WHERE idAllBands2=2");
                    while (resultSet.next()) {
                        Vector<String> row = new Vector<>();
                        row.add(String.valueOf(resultSet.getInt(1)));
                        row.add(resultSet.getNString(3));
                        row.add(resultSet.getNString(4));
                        row.add(String.valueOf(resultSet.getDate(5)));

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

        }
        if (id==3){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                try (Connection con = DriverManager.getConnection(url, name, password)) {

                    Statement statement = con.createStatement();
                    ResultSet resultSet;
                    resultSet = statement.executeQuery("SELECT * FROM musicbands.concerts2 WHERE idAllBands2=3");
                    while (resultSet.next()) {
                        Vector<String> row = new Vector<>();
                        row.add(String.valueOf(resultSet.getInt(1)));
                        row.add(resultSet.getNString(3));
                        row.add(resultSet.getNString(4));
                        row.add(String.valueOf(resultSet.getDate(5)));

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

        }
        if (id==4){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                try (Connection con = DriverManager.getConnection(url, name, password)) {

                    Statement statement = con.createStatement();
                    ResultSet resultSet;
                    resultSet = statement.executeQuery("SELECT * FROM musicbands.concerts2 WHERE idAllBands2=4");
                    while (resultSet.next()) {
                        Vector<String> row = new Vector<>();
                        row.add(String.valueOf(resultSet.getInt(1)));
                        row.add(resultSet.getNString(3));
                        row.add(resultSet.getNString(4));
                        row.add(String.valueOf(resultSet.getDate(5)));

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

        }
        if (id==5){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                try (Connection con = DriverManager.getConnection(url, name, password)) {

                    Statement statement = con.createStatement();
                    ResultSet resultSet;
                    resultSet = statement.executeQuery("SELECT * FROM musicbands.concerts2 WHERE idAllBands2=5");
                    while (resultSet.next()) {
                        Vector<String> row = new Vector<>();
                        row.add(String.valueOf(resultSet.getInt(1)));
                        row.add(resultSet.getNString(3));
                        row.add(resultSet.getNString(4));
                        row.add(String.valueOf(resultSet.getDate(5)));

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

        }
        if (id==6){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                try (Connection con = DriverManager.getConnection(url, name, password)) {

                    Statement statement = con.createStatement();
                    ResultSet resultSet;
                    resultSet = statement.executeQuery("SELECT * FROM musicbands.concerts2 WHERE idAllBands2=6");
                    while (resultSet.next()) {
                        Vector<String> row = new Vector<>();
                        row.add(String.valueOf(resultSet.getInt(1)));
                        row.add(resultSet.getNString(3));
                        row.add(resultSet.getNString(4));
                        row.add(String.valueOf(resultSet.getDate(5)));

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

        }
        if (id==7){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                try (Connection con = DriverManager.getConnection(url, name, password)) {

                    Statement statement = con.createStatement();
                    ResultSet resultSet;
                    resultSet = statement.executeQuery("SELECT * FROM musicbands.concerts2 WHERE idAllBands2=7");
                    while (resultSet.next()) {
                        Vector<String> row = new Vector<>();
                        row.add(String.valueOf(resultSet.getInt(1)));
                        row.add(resultSet.getNString(3));
                        row.add(resultSet.getNString(4));
                        row.add(String.valueOf(resultSet.getDate(5)));

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

        }
        if (id==8){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                try (Connection con = DriverManager.getConnection(url, name, password)) {

                    Statement statement = con.createStatement();
                    ResultSet resultSet;
                    resultSet = statement.executeQuery("SELECT * FROM musicbands.concerts2 WHERE idAllBands2=8");
                    while (resultSet.next()) {
                        Vector<String> row = new Vector<>();
                        row.add(String.valueOf(resultSet.getInt(1)));
                        row.add(resultSet.getNString(3));
                        row.add(resultSet.getNString(4));
                        row.add(String.valueOf(resultSet.getDate(5)));

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

        }
        if (id==9){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                try (Connection con = DriverManager.getConnection(url, name, password)) {

                    Statement statement = con.createStatement();
                    ResultSet resultSet;
                    resultSet = statement.executeQuery("SELECT * FROM musicbands.concerts2 WHERE idAllBands2=9");
                    while (resultSet.next()) {
                        Vector<String> row = new Vector<>();
                        row.add(String.valueOf(resultSet.getInt(1)));
                        row.add(resultSet.getNString(3));
                        row.add(resultSet.getNString(4));
                        row.add(String.valueOf(resultSet.getDate(5)));

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

        }
        if (id==10){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                try (Connection con = DriverManager.getConnection(url, name, password)) {

                    Statement statement = con.createStatement();
                    ResultSet resultSet;
                    resultSet = statement.executeQuery("SELECT * FROM musicbands.concerts2 WHERE idAllBands2=10");
                    while (resultSet.next()) {
                        Vector<String> row = new Vector<>();
                        row.add(String.valueOf(resultSet.getInt(1)));
                        row.add(resultSet.getNString(3));
                        row.add(resultSet.getNString(4));
                        row.add(String.valueOf(resultSet.getDate(5)));

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

        }

  frame.setVisible(true);

    }
}