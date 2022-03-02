import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.Vector;

public class App  {
    String url = "jdbc:mysql://localhost:3306/musicbands";
    String name = "root";
    String password = "hippino25";
    final static public Object shared = new Object();
    public static Connection con = null;
    public App() throws InterruptedException {

//Настройка внешнего вида некоторых интерфейсов
        UIManager.put("OptionPane.cancelButtonText", "Отмена");
        UIManager.put("OptionPane.okButtonText", "ОК");
        UIManager.put("OptionPane.yesButtonText", "Да");
        UIManager.put("OptionPane.noButtonText", "Нет");
        UIManager.put("OptionPane.messageFont", new Font("Century Gothic", Font.BOLD, 15));
        UIManager.put("OptionPane.buttonFont", new Font("Century Gothic", Font.BOLD, 15));
        UIManager.put("Button.background", new Color(112, 144, 237));
        UIManager.put("Button.foreground", new Color(250, 250, 250).brighter());
        UIManager.put("ToolBar.background", new Color(255, 255, 255));
        UIManager.put("MenuBar.background", new Color(255, 255, 255));
        UIManager.put("MenuItem.background", new Color(255, 255, 255));
        UIManager.put("Frame.background", new Color(255, 255, 255));
// Создание рамки самого приложения

        JFrame frame = new JFrame("MUSAPP");

        frame.setSize(900,700);
        frame.setLocation(300,100);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("./images/MENU.png");
        frame.setIconImage(icon.getImage());
        JPanel panel1= new JPanel();
        panel1.setBounds(0,60,900,900);
        JLabel imagelabel = new JLabel();
        imagelabel.setIcon(new ImageIcon("./images/MainBackGround2.jpg"));
        panel1.add(imagelabel);
        frame.add(panel1);

        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);

        JMenu menu = new JMenu("Сохранение отчетов");

        JMenu menu2 = new JMenu("Отчеты по концертам");
        menubar.add(menu);

        menubar.add(menu2);

        menu.setFont(new Font("Century Gothic", Font.BOLD, 15));

        menu2.setFont(new Font("Century Gothic", Font.BOLD, 15));

        JMenuItem addXML = new JMenuItem("Загрузка в формате XML");
        menu.add(addXML);
        JMenuItem saveXML = new JMenuItem("Сохранение в формате XML");
        menu.add(saveXML);
        JMenuItem savePDF = new JMenuItem("Сохранение в формате PDF");
        menu.add(savePDF);
        JMenuItem saveHTML = new JMenuItem("Сохранение в формате HTML");
        menu.add(saveHTML);

        JMenuItem Kiskis = new JMenuItem("Концерты группы Кис-кис");
        menu2.add(Kiskis);
        JMenuItem twentyonepilots = new JMenuItem("Концерты группы Twenty One Pilots");
        menu2.add(twentyonepilots);
        JMenuItem Maneskin = new JMenuItem("Концерты группы Maneskin");
        menu2.add(Maneskin);
        JMenuItem Bts = new JMenuItem("Концерты группы BTS");
        menu2.add(Bts);
        JMenuItem blackpink = new JMenuItem("Концерты группы BlackPink");
        menu2.add(blackpink );
        JMenuItem littlebig = new JMenuItem("Концерты группы Little Big");
        menu2.add(littlebig);
        JMenuItem frendzona = new JMenuItem("Концерты группы Френдзона");
        menu2.add(frendzona);
        JMenuItem poshlayamole = new JMenuItem("Концерты группы Пошлая Молли");
        menu2.add(poshlayamole);
        JMenuItem thehatters = new JMenuItem("Концерты группы The Hatters");
        menu2.add(thehatters);
        JMenuItem kreed = new JMenuItem("Концерты группы KReeD");
        menu2.add(kreed);

        Kiskis.setFont(new Font("Century Gothic", Font.BOLD, 12));
        twentyonepilots.setFont(new Font("Century Gothic", Font.BOLD,12));
        Maneskin.setFont(new Font("Century Gothic", Font.BOLD,12));
        Bts.setFont(new Font("Century Gothic", Font.BOLD,12));
        blackpink.setFont(new Font("Century Gothic", Font.BOLD, 12));
        littlebig.setFont(new Font("Century Gothic", Font.BOLD,12));
        frendzona.setFont(new Font("Century Gothic", Font.BOLD,12));
        poshlayamole.setFont(new Font("Century Gothic", Font.BOLD,12));
        thehatters.setFont(new Font("Century Gothic", Font.BOLD, 12));
        kreed.setFont(new Font("Century Gothic", Font.BOLD,12));





        addXML.setFont(new Font("Century Gothic", Font.BOLD, 12));
        saveXML.setFont(new Font("Century Gothic", Font.BOLD,12));
        savePDF.setFont(new Font("Century Gothic", Font.BOLD,12));
        saveHTML.setFont(new Font("Century Gothic", Font.BOLD,12));


//Создание панели инструментов

        JToolBar toolBar = new JToolBar("Панель инструментов");
        JButton save = new JButton(new ImageIcon("./image/save.png"));
        JButton database = new JButton(new ImageIcon("./image/download.png"));
        JButton add = new JButton(new ImageIcon("./image/add1.png"));
        JButton delete = new JButton(new ImageIcon("./image/delete.png"));
        JButton edit = new JButton(new ImageIcon("./image/edit.png"));
        JButton info = new JButton(new ImageIcon("./image/information.png"));
        JButton hitparade = new JButton(new ImageIcon("./image/hitparade.png"));


        toolBar.add(save);
        toolBar.add(database);
        toolBar.add(add);
        toolBar.add(delete);
        toolBar.add(edit);
        toolBar.add(hitparade);
        toolBar.add(info);

//Создание панели поиска
        JLabel	empty = new JLabel("       ");
        JLabel	searchByLabel = new JLabel("Введите слово:  ");
        searchByLabel .setFont(new Font("Century Gothic", Font.BOLD, 15));
        JTextField searchField = new JTextField(5);
        searchField.setFont(new Font("Century Gothic", Font.BOLD, 15));
        JLabel	empty1  = new JLabel("       ");
        JButton searchButton = new JButton("Поиск");
        searchButton .setFont(new Font("Century Gothic", Font.BOLD, 15));
        JLabel	empty2  = new JLabel("       ");

        toolBar.add(empty);
        toolBar.add(searchByLabel);
        toolBar.add(searchField);
        toolBar.add(empty1);
        toolBar.add(searchButton);
        toolBar.add(empty2);
        frame.setLayout(new BorderLayout());
        frame.add(toolBar,BorderLayout.NORTH);



//Создание таблицы
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
                resultSet = statement.executeQuery("SELECT * FROM musicbands.allbands2");
                while (resultSet.next()) {
                    Vector<String> row = new Vector<>();
                    row.add(String.valueOf(resultSet.getInt(1)));
                    row.add(resultSet.getNString(2));
                    row.add(resultSet.getNString(3));
                    row.add(resultSet.getNString(4));
                    row.add(String.valueOf(resultSet.getInt(5)));
                    row.add(resultSet.getNString(6));
                    row.add(resultSet.getNString(7));
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
        musicians.setPreferredScrollableViewportSize(new Dimension(750,350));
        musicians.setFillsViewportHeight(true);
        final TableRowSorter <TableModel> sorter = new TableRowSorter<>(model);
        musicians.setRowSorter(sorter);
        JScrollPane scroll = new JScrollPane(musicians);
        JPanel panel= new JPanel();
        frame.add(panel);
        panel.add(scroll);
        musicians.setEnabled(true);

//Обработка нажатия кнопок

        Actionlistener AL = new Actionlistener();

        database.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                AL.LoadFile(frame,data);
                model.setDataVector(data,columns);

                //new FileOperation().xmlOpen(model, musicians, frame);
            }
        });





        searchButton.addActionListener (new ActionListener()
        {
            public void actionPerformed (ActionEvent event)
            {

                String text = searchField.getText();
                if (text.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter(text));
                }
            }
        });



        info.addActionListener (new ActionListener()
        {
            public void actionPerformed (ActionEvent event)
            {

                JLabel label = new JLabel("<html>ИНФОРМАЦИЯ О ПРОГРАММЕ<br>Программа для менеджера музыкальных групп<br><br> Язык(Страна):Русский<br><br> Copyright © ИП HIPPINO 2021. Все права защищены.</html>");
                label.setFont(new Font("Century Gothic", Font.BOLD, 15));
                JOptionPane.showMessageDialog(null,label,"Информация о программе",JOptionPane.INFORMATION_MESSAGE);

            }
        });


        hitparade.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Top top = new Top();
            }
        });

        add.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                AL.Add(model,frame);
            }
        });

        edit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                AL.Changestring(model,frame);
            }
        });


        delete.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                AL.Delete(model,frame);
            }
        });


        save.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                AL.SaveFile(frame,model);
            }

        });





        Kiskis.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Concerts cs=new Concerts(1);
            }

        });
        twentyonepilots.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Concerts cs=new Concerts(2);
            }


        });
        Maneskin.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Concerts cs =new Concerts(3);
            }

        });
        Bts.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Concerts cs =new Concerts(4);
            }

        });
        blackpink.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Concerts cs =new Concerts(5);
            }

        });
        littlebig.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Concerts cs =new Concerts(6);
            }

        });
        frendzona.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Concerts cs =new Concerts(7);
            }

        });
        poshlayamole.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Concerts cs =new Concerts(8);
            }

        });
        thehatters.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Concerts cs =new Concerts(9);
            }
        });
        kreed.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Concerts cs =new Concerts(10);
            }

        });

































        addXML.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                new FileOperation().xmlOpen(model, musicians, frame);
            }
        });

        saveXML.addActionListener(new ActionListener() {


            public void actionPerformed(ActionEvent e) {

                try {
                    new FileOperation().xmlSave(model, musicians, frame);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        savePDF.addActionListener(new ActionListener() {


            public void actionPerformed(ActionEvent e) {

                HTMLPDF rp = new HTMLPDF();

                try {
                    rp.createPDF(model);
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        saveHTML.addActionListener(new ActionListener() {


            public void actionPerformed(ActionEvent e) {

                HTMLPDF rp = new HTMLPDF();
                rp.createHTML(model);
            }
        });

        //Показать окно
        frame.setVisible(true);
    }
}
