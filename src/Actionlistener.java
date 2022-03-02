
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.*;
import java.util.Vector;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class Actionlistener {
    String url = "jdbc:mysql://localhost:3306/musicbands";
    String name = "root";
    String password = "hippino25";

    //Кнопка загрузки данных из файла
    public void LoadFile(JFrame frame,Vector <Vector<String>> data) {

        String fileNameOpen = null;

        try
        {
            FileDialog open = new FileDialog(frame,"Выбор файла для загрузки",FileDialog.LOAD);
            open.setFile("*.csv");
            open.setVisible(true);
            fileNameOpen = open.getDirectory() + open.getFile();
            if(fileNameOpen==null||fileNameOpen.equals("nullnull"))
                return;
            BufferedReader reader = new BufferedReader(new FileReader(fileNameOpen));


            data.clear();
            String line;
            String s[];

            int count = 0;
            while ((line = reader.readLine()) != null) {
                s = line.split(";");
                Vector <String> row = new Vector<>();
                for(int i = 0;i < s.length;i++) {
                    row.add(s[i]);
                }
                row.add(0,String.valueOf(count+1));
                data.add(row);
                count++;
            }

        } catch (IOException ev) {
            JOptionPane.showMessageDialog(frame,"ФАЙЛ НЕ НАЙДЕН! ПОВТОРИТЕ ВВОД!","ОШИБКА", JOptionPane.ERROR_MESSAGE);
        }

    }


//Кнопка сохранения файла

    public void SaveFile(JFrame frame,DefaultTableModel model) {



        FileDialog fdialog = new FileDialog(frame,"Сохранение файла",FileDialog.SAVE);
        fdialog.setFile("*.csv");
        fdialog.setVisible(true);
        String fileFullName = fdialog.getDirectory() + fdialog.getFile();
        if(fileFullName != null)
        {

            try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileFullName))) {

                for( int i = 0;i < model.getRowCount();i++) {
                    for(int  j = 1;j < model.getColumnCount();j++) {
                        if(j != model.getColumnCount()-1) {
                            writer.write(model.getValueAt(i,j) + ";");
                        }
                        else {
                            writer.write((String)model.getValueAt(i,j));
                        }
                    }
                    writer.write("\n");
                }
                writer.close();

            }

            catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

//Кнопка удаления строки

//Кнопка добавления строки

    public void Add(DefaultTableModel model,JFrame frame) {
        JDialog window = new JDialog(frame,"Добавление группы");
        window.setModal(true);
        window.setSize(390,370);
        window.setLocation(550,300);
        window.setResizable(false);
        window.setContentPane(new JLabel(new ImageIcon("./images/AddBack2.jpg")));

        Container contentPane = window.getContentPane();

        SpringLayout layout = new SpringLayout();
        contentPane.setLayout(layout);

        JLabel Groupname = new JLabel("Название группы:");
        Groupname.setFont(new Font("Century Gothic", Font.BOLD, 17));
        layout.putConstraint(SpringLayout.WEST , Groupname, 10,
                SpringLayout.WEST , contentPane);
        contentPane.add(Groupname);
        layout.putConstraint(SpringLayout.NORTH, Groupname, 15,
                SpringLayout.NORTH, contentPane);

        JLabel Members = new JLabel("Члены группы:");
        Members.setFont(new Font("Century Gothic", Font.BOLD, 17));
        layout.putConstraint(SpringLayout.WEST , Members, 10,
                SpringLayout.WEST , contentPane);
        contentPane.add(Members);
        layout.putConstraint(SpringLayout.NORTH, Members, 61,
                SpringLayout.NORTH, contentPane);

        JLabel Genre = new JLabel("Жанр:");
        Genre.setFont(new Font("Century Gothic", Font.BOLD, 17));
        layout.putConstraint(SpringLayout.WEST , Genre, 10,
                SpringLayout.WEST , contentPane);
        contentPane.add(Genre);
        layout.putConstraint(SpringLayout.NORTH, Genre, 107,
                SpringLayout.NORTH, contentPane);


        JLabel Startyear = new JLabel("Год основания:");
        Startyear.setFont(new Font("Century Gothic", Font.BOLD, 17));
        layout.putConstraint(SpringLayout.WEST , Startyear, 10,
                SpringLayout.WEST , contentPane);
        contentPane.add(Startyear);
        layout.putConstraint(SpringLayout.NORTH, Startyear, 153,
                SpringLayout.NORTH, contentPane);


        JLabel Hitparade = new JLabel("Хит парад:");
        Hitparade.setFont(new Font("Century Gothic", Font.BOLD, 17));
        layout.putConstraint(SpringLayout.WEST , Hitparade, 10,
                SpringLayout.WEST , contentPane);
        contentPane.add(Hitparade);
        layout.putConstraint(SpringLayout.NORTH, Hitparade, 199,
                SpringLayout.NORTH, contentPane);


        JLabel Concerts = new JLabel("Концерты:");
        Concerts.setFont(new Font("Century Gothic", Font.BOLD, 17));
        layout.putConstraint(SpringLayout.WEST , Concerts, 10,
                SpringLayout.WEST , contentPane);
        contentPane.add(Concerts);
        layout.putConstraint(SpringLayout.NORTH,Concerts, 245,
                SpringLayout.NORTH, contentPane);

        JTextField textGroupName = new JTextField(12);
        textGroupName.setFont(new Font("Century Gothic", Font.BOLD, 17));
        layout.putConstraint(SpringLayout.WEST , textGroupName, 170,
                SpringLayout.WEST , contentPane);
        contentPane.add(textGroupName);
        layout.putConstraint(SpringLayout.NORTH,textGroupName, 15,
                SpringLayout.NORTH, contentPane);

        JTextField textMembers = new JTextField(12);
        textMembers.setFont(new Font("Century Gothic", Font.BOLD, 17));
        layout.putConstraint(SpringLayout.WEST ,textMembers, 170,
                SpringLayout.WEST , contentPane);
        contentPane.add(textMembers);
        layout.putConstraint(SpringLayout.NORTH,textMembers, 61,
                SpringLayout.NORTH, contentPane);

        String genre[] = {"KPOP","Rap-Rock","Glam-Rock","Pop-music","Hip-Hop","Alternative Hip-Hop","Abstract Hip-Hop","Pop-Rock","Synth-Punk","Folk-Punk","Experimental music","Indie-Rock","Rave","Disco","Сhanson","Pop-Punk"};
        JComboBox combogenre = new JComboBox<>(genre);
        combogenre.setBackground(new Color (255,255,255));
        layout.putConstraint(SpringLayout.WEST , combogenre,170,
                SpringLayout.WEST , contentPane);
        contentPane.add(combogenre);
        combogenre.setFont(new Font("Century Gothic", Font.BOLD, 17));
        layout.putConstraint(SpringLayout.NORTH ,combogenre, 107,
                SpringLayout.NORTH, contentPane);

        String year[] = {"2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020","2021"};
        JComboBox comboyear = new JComboBox<>(year);
        comboyear.setBackground(new Color (255,255,255));
        layout.putConstraint(SpringLayout.WEST , comboyear,170,
                SpringLayout.WEST , contentPane);
        contentPane.add(comboyear);
        comboyear.setFont(new Font("Century Gothic", Font.BOLD, 17));
        layout.putConstraint(SpringLayout.NORTH ,comboyear, 153,
                SpringLayout.NORTH, contentPane);


        JTextField textHitparade = new JTextField(12);
        textHitparade.setFont(new Font("Century Gothic", Font.BOLD, 17));
        layout.putConstraint(SpringLayout.WEST , textHitparade, 170,
                SpringLayout.WEST , contentPane);
        contentPane.add(textHitparade);
        layout.putConstraint(SpringLayout.NORTH,textHitparade, 199,
                SpringLayout.NORTH, contentPane);

        JTextField textConcerts = new JTextField(12);
        textConcerts.setFont(new Font("Century Gothic", Font.BOLD, 17));
        layout.putConstraint(SpringLayout.WEST , textConcerts, 170,
                SpringLayout.WEST , contentPane);
        contentPane.add(textConcerts);
        layout.putConstraint(SpringLayout.NORTH,textConcerts, 245,
                SpringLayout.NORTH, contentPane);

        JButton ok = new JButton("ОК");
        JButton cancel = new JButton("ОТМЕНА");

        layout.putConstraint(SpringLayout.WEST , ok, 70,
                SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, ok, 285,
                SpringLayout.NORTH, contentPane);

        layout.putConstraint(SpringLayout.NORTH, cancel, 285,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST , cancel, 85,
                SpringLayout.EAST , ok      );
        ok.setFont(new Font("Century Gothic", Font.BOLD, 15));
        cancel.setFont(new Font("Century Gothic", Font.BOLD, 15));
        contentPane.add(ok);
        contentPane.add(cancel);




        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector <String> s = new Vector<>();
                s.add(textGroupName.getText());
                s.add(textMembers.getText());
                s.add(genre[combogenre.getSelectedIndex()]);
                s.add(year[comboyear.getSelectedIndex()]);
                s.add(textHitparade.getText());
                s.add(textConcerts.getText());

                int count = 0;
                for(String s1:s) {
                    if((s1.length() > 0) && (!s1.equals(" "))) {
                        count++;
                    }
                }

                if(count == 6) {
                    try{
                        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

                        try (Connection con = DriverManager.getConnection(url, name, password)){
                            try {
                                String sql="INSERT INTO allbands2 (GroupName, GroupMembers, Genre, Startyear, HitParade, Concerts) VALUES (?,?,?,?,?,?)";
                                PreparedStatement ps = con.prepareStatement(sql);
                                ps.setString(1,s.get(0));
                                ps.setString(2, s.get(1));
                                ps.setString(3, s.get(2));
                                ps.setInt(4, Integer.parseInt(s.get(3)));
                                ps.setString(5, s.get(4));
                                ps.setString(6, s.get(5));
                                ps.executeUpdate();
                                ImageIcon icon1 = new ImageIcon("./image/databaseyes.png");
                                JOptionPane.showMessageDialog(null,
                                        "Информация успешно добавлена в базу данных !","Информация о работе базы данных",
                                        JOptionPane.INFORMATION_MESSAGE,
                                        icon1);
                            } catch (SQLException exception) {
                                exception.printStackTrace();
                            }
                            System.out.println("Подключение успешно!");
                        }
                    }
                    catch(Exception ex){
                        System.out.println("Подключение не удалось...");
                        System.out.println(ex);
                    }
                    s.add(0,String.valueOf(model.getRowCount()+1));
                    model.addRow(s);
                    window.dispose();
                }
                else {
                    JOptionPane.showMessageDialog (frame,"Заполнены не все поля!");
                }
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n = JOptionPane.showConfirmDialog(window,"<html>Вы выходите из режима редактирования карты.<br>Данные не сохранятся.</html>","Message",JOptionPane.YES_NO_OPTION);
                if(n == JOptionPane.YES_OPTION) {
                    window.dispose();
                }
            }
        });

        window.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        window.setVisible(true);
    }


    private int IDRow(DefaultTableModel model,String number) {
        int num = -1;
        for(int i = 0;i< model.getRowCount();i++) {
            if(model.getValueAt(i,0).equals(number)) {
                num = i;
                break;
            }
        }
        return num;
    }


    public void Delete(DefaultTableModel model, JFrame frame ) {

        String s = "allbands2";
        JDialog window1 = new JDialog(frame,"Удаление записи");
        window1.setModal(true);
        window1.setSize(400,170);
        window1.setLocation(550,300);
        window1.setResizable(false);
        JPanel panel1= new JPanel();
        panel1.setBounds(0,0,40,50);
        JLabel imagelabel1 = new JLabel();
        imagelabel1.setIcon(new ImageIcon("./images/delete5.png"));
        panel1.add(imagelabel1);
       window1.add(panel1);
        Container contentPane = window1.getContentPane();

        SpringLayout layout = new SpringLayout();
        contentPane.setLayout(layout);

        JLabel delete = new JLabel("Введите номер строки для удаления:");
        delete.setFont(new Font("Century Gothic", Font.BOLD, 15));
        layout.putConstraint(SpringLayout.WEST , delete, 90,
                SpringLayout.WEST , contentPane);
        contentPane.add(delete);
        layout.putConstraint(SpringLayout.NORTH, delete, 23,
                SpringLayout.NORTH, contentPane);
        JTextField textdelete = new JTextField(18);
        textdelete.setFont(new Font("Century Gothic", Font.BOLD, 17));
        layout.putConstraint(SpringLayout.WEST , textdelete, 90,
                SpringLayout.WEST , contentPane);
        contentPane.add(textdelete);
        layout.putConstraint(SpringLayout.NORTH,textdelete, 50,
                SpringLayout.NORTH, contentPane);

        JButton ok = new JButton("ОК");
        JButton cancel = new JButton("ОТМЕНА");

        layout.putConstraint(SpringLayout.WEST , ok, 130,
                SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, ok, 85,
                SpringLayout.NORTH, contentPane);

        layout.putConstraint(SpringLayout.NORTH, cancel, 85,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST , cancel, 235,
                SpringLayout.WEST ,contentPane);
        ok.setFont(new Font("Century Gothic", Font.BOLD, 15));
        cancel.setFont(new Font("Century Gothic", Font.BOLD, 15));
        contentPane.add(ok);
        contentPane.add(cancel);


        ok.addActionListener(new ActionListener() {

                                 public void actionPerformed(ActionEvent e) {
                                     String number = textdelete.getText();
                                     int num = IDRow(model, number);

                                     if (num > 0) {
                                         try {
                                             try {
                                                 Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

                                                 try (Connection conn = DriverManager.getConnection(url, name, password)) {
                                                     ImageIcon icon1 = new ImageIcon("./image/databaseyes.png");
                                                     JOptionPane.showMessageDialog(null,
                                                             "Информация в базе данных успешно удалена !","Информация о работе базы данных",
                                                             JOptionPane.INFORMATION_MESSAGE,
                                                             icon1);
                                                     Statement statement = conn.createStatement();
                                                     String sqlCommand = "DELETE FROM " + s + " WHERE idAllBands2 = " + number;
                                                     int rows = statement.executeUpdate(sqlCommand);
                                                     model.removeRow(num);
                                                 }
                                             } catch (Exception ex) {
                                                 System.out.println("Подключение не удалось...");
                                                System.out.println(ex);
                                             }
                                         } catch (IndexOutOfBoundsException ev) {
                                             JOptionPane.showMessageDialog(frame, "НЕТ ИНФОРМАЦИИ ДЛЯ УДАЛЕНИЯ!", "ОШИБКА", JOptionPane.WARNING_MESSAGE);
                                         }
                                     }
                                     else{
                                             JOptionPane.showMessageDialog(frame, "Ошибка! Несуществующий ID!", "Error", JOptionPane.ERROR_MESSAGE);
                                     }
                                     window1.dispose();
                                 }
                             });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    window1.dispose();
            }
        });

window1.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
window1.setVisible(true);

    }

    public void Changestring (DefaultTableModel model, JFrame frame) {

        JDialog window1 = new JDialog(frame,"Изменение записи");
        window1.setModal(true);
        window1.setSize(400,170);
        window1.setLocation(550,300);
        window1.setResizable(false);
        JPanel panel1= new JPanel();
        panel1.setBounds(0,0,40,50);
        JLabel imagelabel1 = new JLabel();
        imagelabel1.setIcon(new ImageIcon("./images/change1.png"));
        panel1.add(imagelabel1);
        window1.add(panel1);
        Container contentPane = window1.getContentPane();

        SpringLayout layout = new SpringLayout();
        contentPane.setLayout(layout);

        JLabel delete = new JLabel("Введите номер строки для изменения:");
        delete.setFont(new Font("Century Gothic", Font.BOLD, 15));
        layout.putConstraint(SpringLayout.WEST , delete, 90,
                SpringLayout.WEST , contentPane);
        contentPane.add(delete);
        layout.putConstraint(SpringLayout.NORTH, delete, 23,
                SpringLayout.NORTH, contentPane);
        JTextField textdelete = new JTextField(18);
        textdelete.setFont(new Font("Century Gothic", Font.BOLD, 17));
        layout.putConstraint(SpringLayout.WEST , textdelete, 90,
                SpringLayout.WEST , contentPane);
        contentPane.add(textdelete);
        layout.putConstraint(SpringLayout.NORTH,textdelete, 50,
                SpringLayout.NORTH, contentPane);

        JButton ok = new JButton("ОК");
        JButton cancel = new JButton("ОТМЕНА");

        layout.putConstraint(SpringLayout.WEST , ok, 130,
                SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, ok, 85,
                SpringLayout.NORTH, contentPane);

        layout.putConstraint(SpringLayout.NORTH, cancel, 85,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST , cancel, 235,
                SpringLayout.WEST ,contentPane);
        ok.setFont(new Font("Century Gothic", Font.BOLD, 15));
        cancel.setFont(new Font("Century Gothic", Font.BOLD, 15));
        contentPane.add(ok);
        contentPane.add(cancel);


        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                window1.dispose();

                String number = textdelete.getText();
                int num = IDRow(model, number);

                if (num >= 0) {
                    JDialog window2 = new JDialog(frame, "Изменение строки");
                    window2.setModal(true);
                    window2.setSize(390, 370);
                    window2.setLocation(550, 300);
                    window2.setResizable(false);
                    window2.setContentPane(new JLabel(new ImageIcon("./images/AddBack3.png")));

                    Container contentPane = window2.getContentPane();

                    SpringLayout layout = new SpringLayout();
                    contentPane.setLayout(layout);

                    JLabel Groupname = new JLabel("Название группы:");
                    Groupname.setFont(new Font("Century Gothic", Font.BOLD, 17));
                    layout.putConstraint(SpringLayout.WEST, Groupname, 10,
                            SpringLayout.WEST, contentPane);
                    contentPane.add(Groupname);
                    layout.putConstraint(SpringLayout.NORTH, Groupname, 15,
                            SpringLayout.NORTH, contentPane);

                    JLabel Members = new JLabel("Члены группы:");
                    Members.setFont(new Font("Century Gothic", Font.BOLD, 17));
                    layout.putConstraint(SpringLayout.WEST, Members, 10,
                            SpringLayout.WEST, contentPane);
                    contentPane.add(Members);
                    layout.putConstraint(SpringLayout.NORTH, Members, 61,
                            SpringLayout.NORTH, contentPane);

                    JLabel Genre = new JLabel("Жанр:");
                    Genre.setFont(new Font("Century Gothic", Font.BOLD, 17));
                    layout.putConstraint(SpringLayout.WEST, Genre, 10,
                            SpringLayout.WEST, contentPane);
                    contentPane.add(Genre);
                    layout.putConstraint(SpringLayout.NORTH, Genre, 107,
                            SpringLayout.NORTH, contentPane);


                    JLabel Startyear = new JLabel("Год основания:");
                    Startyear.setFont(new Font("Century Gothic", Font.BOLD, 17));
                    layout.putConstraint(SpringLayout.WEST, Startyear, 10,
                            SpringLayout.WEST, contentPane);
                    contentPane.add(Startyear);
                    layout.putConstraint(SpringLayout.NORTH, Startyear, 153,
                            SpringLayout.NORTH, contentPane);


                    JLabel Hitparade = new JLabel("Хит парад:");
                    Hitparade.setFont(new Font("Century Gothic", Font.BOLD, 17));
                    layout.putConstraint(SpringLayout.WEST, Hitparade, 10,
                            SpringLayout.WEST, contentPane);
                    contentPane.add(Hitparade);
                    layout.putConstraint(SpringLayout.NORTH, Hitparade, 199,
                            SpringLayout.NORTH, contentPane);


                    JLabel Concerts = new JLabel("Концерты:");
                    Concerts.setFont(new Font("Century Gothic", Font.BOLD, 17));
                    layout.putConstraint(SpringLayout.WEST, Concerts, 10,
                            SpringLayout.WEST, contentPane);
                    contentPane.add(Concerts);
                    layout.putConstraint(SpringLayout.NORTH, Concerts, 245,
                            SpringLayout.NORTH, contentPane);

                    JTextField textGroupName = new JTextField(12);
                    textGroupName.setFont(new Font("Century Gothic", Font.BOLD, 17));
                    layout.putConstraint(SpringLayout.WEST, textGroupName, 170,
                            SpringLayout.WEST, contentPane);
                    contentPane.add(textGroupName);
                    layout.putConstraint(SpringLayout.NORTH, textGroupName, 15,
                            SpringLayout.NORTH, contentPane);

                    JTextField textMembers = new JTextField(12);
                    textMembers.setFont(new Font("Century Gothic", Font.BOLD, 17));
                    layout.putConstraint(SpringLayout.WEST, textMembers, 170,
                            SpringLayout.WEST, contentPane);
                    contentPane.add(textMembers);
                    layout.putConstraint(SpringLayout.NORTH, textMembers, 61,
                            SpringLayout.NORTH, contentPane);

                    String genre[] = {"KPOP", "Rap-Rock", "Glam-Rock", "Pop-music", "Hip-Hop", "Alternative Hip-Hop", "Abstract Hip-Hop", "Pop-Rock", "Synth-Punk", "Folk-Punk", "Experimental music", "Indie-Rock", "Rave", "Disco", "Сhanson", "Pop-Punk"};
                    JComboBox combogenre = new JComboBox<>(genre);
                    combogenre.setBackground(new Color(255, 255, 255));
                    layout.putConstraint(SpringLayout.WEST, combogenre, 170,
                            SpringLayout.WEST, contentPane);
                    contentPane.add(combogenre);
                    combogenre.setFont(new Font("Century Gothic", Font.BOLD, 17));
                    layout.putConstraint(SpringLayout.NORTH, combogenre, 107,
                            SpringLayout.NORTH, contentPane);

                    String year[] = {"2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021"};
                    JComboBox comboyear = new JComboBox<>(year);
                    comboyear.setBackground(new Color(255, 255, 255));
                    layout.putConstraint(SpringLayout.WEST, comboyear, 170,
                            SpringLayout.WEST, contentPane);
                    contentPane.add(comboyear);
                    comboyear.setFont(new Font("Century Gothic", Font.BOLD, 17));
                    layout.putConstraint(SpringLayout.NORTH, comboyear, 153,
                            SpringLayout.NORTH, contentPane);


                    JTextField textHitparade = new JTextField(12);
                    textHitparade.setFont(new Font("Century Gothic", Font.BOLD, 17));
                    layout.putConstraint(SpringLayout.WEST, textHitparade, 170,
                            SpringLayout.WEST, contentPane);
                    contentPane.add(textHitparade);
                    layout.putConstraint(SpringLayout.NORTH, textHitparade, 199,
                            SpringLayout.NORTH, contentPane);

                    JTextField textConcerts = new JTextField(12);
                    textConcerts.setFont(new Font("Century Gothic", Font.BOLD, 17));
                    layout.putConstraint(SpringLayout.WEST, textConcerts, 170,
                            SpringLayout.WEST, contentPane);
                    contentPane.add(textConcerts);
                    layout.putConstraint(SpringLayout.NORTH, textConcerts, 245,
                            SpringLayout.NORTH, contentPane);

                    JButton ok = new JButton("ОК");
                    JButton cancel = new JButton("ОТМЕНА");

                    layout.putConstraint(SpringLayout.WEST, ok, 70,
                            SpringLayout.WEST, contentPane);
                    layout.putConstraint(SpringLayout.NORTH, ok, 285,
                            SpringLayout.NORTH, contentPane);

                    layout.putConstraint(SpringLayout.NORTH, cancel, 285,
                            SpringLayout.NORTH, contentPane);
                    layout.putConstraint(SpringLayout.WEST, cancel, 85,
                            SpringLayout.EAST, ok);
                    ok.setFont(new Font("Century Gothic", Font.BOLD, 15));
                    cancel.setFont(new Font("Century Gothic", Font.BOLD, 15));
                    contentPane.add(ok);
                    contentPane.add(cancel);


                    ok.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            Vector<String> s = new Vector<>();
                            s.add(textGroupName.getText());
                            s.add(textMembers.getText());
                            s.add(genre[combogenre.getSelectedIndex()]);
                            s.add(year[comboyear.getSelectedIndex()]);
                            s.add(textHitparade.getText());
                            s.add(textConcerts.getText());

                            int count = 0;
                            for (String s1 : s) {
                                if ((s1.length() > 0) && (!s1.equals(" "))) {
                                    count++;
                                }
                            }

                            if (count == 6) {
                                s.add(0, number);

                                try {
                                    Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

                                    try (Connection conn = DriverManager.getConnection(url, name, password)) {
                                        Statement statement = conn.createStatement();

                                        statement.executeUpdate("UPDATE allbands2 SET GroupName = '" + s.get(1) + "' WHERE idAllBands2 = " + number);
                                        statement.executeUpdate("UPDATE allbands2 SET GroupMembers = '" + s.get(2) + "' WHERE idAllBands2 = " + number);
                                        statement.executeUpdate("UPDATE allbands2 SET Genre = '" + s.get(3) + "' WHERE idAllBands2 = " + number);
                                        statement.executeUpdate("UPDATE allbands2 SET Startyear = '" + s.get(4) + "' WHERE idAllBands2 = " + number);
                                        statement.executeUpdate("UPDATE allbands2 SET Hitparade = '" + s.get(5) + "' WHERE idAllBands2 = " + number);
                                        statement.executeUpdate("UPDATE allbands2 SET Concerts = '" + s.get(6) + "' WHERE idAllBands2 = " + number);
                                        ImageIcon icon1 = new ImageIcon("./image/databaseyes.png");
                                        JOptionPane.showMessageDialog(null,
                                                "Информация в базе данных успешно изменена !","Информация о работе базы данных",
                                                JOptionPane.INFORMATION_MESSAGE,
                                                icon1);
                                    }
                                } catch (Exception ex) {
                                    System.out.println("Подключение не удалось...");
                                    System.out.println(ex);
                                }

                                for (int i = 1; i < s.size(); i++) {
                                    model.setValueAt(s.get(i), num, i);
                                }

                                window2.dispose();
                            } else {
                                JOptionPane.showMessageDialog(frame, "Заполнены не все поля!");
                            }
                        }
                    });

                    cancel.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent e) {
                            int n = JOptionPane.showConfirmDialog(window2,"<html>Вы выходите из режима редактирования карты.<br>Данные не сохранятся.</html>","Message",JOptionPane.YES_NO_OPTION);
                            if(n == JOptionPane.YES_OPTION) {
                                window2.dispose();
                            }
                        }
                    });

                    window2.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    window2.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(frame, "Ошибка! Несуществующий ID!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

            });
                cancel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    window1.dispose();
                }
            });
        window1.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        window1.setVisible(true);
    }
    public void Changestring1 (DefaultTableModel model, JFrame frame) {

        JDialog window1 = new JDialog(frame,"Изменение записи");
        window1.setModal(true);
        window1.setSize(400,170);
        window1.setLocation(550,300);
        window1.setResizable(false);
        JPanel panel1= new JPanel();
        panel1.setBounds(0,0,40,50);
        JLabel imagelabel1 = new JLabel();
        imagelabel1.setIcon(new ImageIcon("./images/change1t.png"));
        panel1.add(imagelabel1);
        window1.add(panel1);
        Container contentPane = window1.getContentPane();

        SpringLayout layout = new SpringLayout();
        contentPane.setLayout(layout);

        JLabel delete = new JLabel("Введите номер строки для изменения:");
        delete.setFont(new Font("Century Gothic", Font.BOLD, 15));
        layout.putConstraint(SpringLayout.WEST , delete, 90,
                SpringLayout.WEST , contentPane);
        contentPane.add(delete);
        layout.putConstraint(SpringLayout.NORTH, delete, 23,
                SpringLayout.NORTH, contentPane);
        JTextField textdelete = new JTextField(18);
        textdelete.setFont(new Font("Century Gothic", Font.BOLD, 17));
        layout.putConstraint(SpringLayout.WEST , textdelete, 90,
                SpringLayout.WEST , contentPane);
        contentPane.add(textdelete);
        layout.putConstraint(SpringLayout.NORTH,textdelete, 50,
                SpringLayout.NORTH, contentPane);

        JButton ok = new JButton("ОК");
        JButton cancel = new JButton("ОТМЕНА");

        layout.putConstraint(SpringLayout.WEST , ok, 130,
                SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, ok, 85,
                SpringLayout.NORTH, contentPane);

        layout.putConstraint(SpringLayout.NORTH, cancel, 85,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST , cancel, 235,
                SpringLayout.WEST ,contentPane);
        ok.setFont(new Font("Century Gothic", Font.BOLD, 15));
        cancel.setFont(new Font("Century Gothic", Font.BOLD, 15));
        contentPane.add(ok);
        contentPane.add(cancel);


        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                window1.dispose();

                String number = textdelete.getText();
                int num = IDRow(model, number);

                if (num >= 0) {
                    JDialog window2 = new JDialog(frame, "Изменение строки");
                    window2.setModal(true);
                    window2.setSize(390, 370);
                    window2.setLocation(550, 300);
                    window2.setResizable(false);
                    window2.setContentPane(new JLabel(new ImageIcon("./images/AddBack3.png")));

                    Container contentPane = window2.getContentPane();

                    SpringLayout layout = new SpringLayout();
                    contentPane.setLayout(layout);

                    JLabel Groupname = new JLabel("Название группы:");
                    Groupname.setFont(new Font("Century Gothic", Font.BOLD, 17));
                    layout.putConstraint(SpringLayout.WEST, Groupname, 10,
                            SpringLayout.WEST, contentPane);
                    contentPane.add(Groupname);
                    layout.putConstraint(SpringLayout.NORTH, Groupname, 15,
                            SpringLayout.NORTH, contentPane);

                    JLabel Members = new JLabel("Члены группы:");
                    Members.setFont(new Font("Century Gothic", Font.BOLD, 17));
                    layout.putConstraint(SpringLayout.WEST, Members, 10,
                            SpringLayout.WEST, contentPane);
                    contentPane.add(Members);
                    layout.putConstraint(SpringLayout.NORTH, Members, 61,
                            SpringLayout.NORTH, contentPane);

                    JLabel Genre = new JLabel("Жанр:");
                    Genre.setFont(new Font("Century Gothic", Font.BOLD, 17));
                    layout.putConstraint(SpringLayout.WEST, Genre, 10,
                            SpringLayout.WEST, contentPane);
                    contentPane.add(Genre);
                    layout.putConstraint(SpringLayout.NORTH, Genre, 107,
                            SpringLayout.NORTH, contentPane);


                    JLabel Startyear = new JLabel("Год основания:");
                    Startyear.setFont(new Font("Century Gothic", Font.BOLD, 17));
                    layout.putConstraint(SpringLayout.WEST, Startyear, 10,
                            SpringLayout.WEST, contentPane);
                    contentPane.add(Startyear);
                    layout.putConstraint(SpringLayout.NORTH, Startyear, 153,
                            SpringLayout.NORTH, contentPane);


                    JLabel Hitparade = new JLabel("Хит парад:");
                    Hitparade.setFont(new Font("Century Gothic", Font.BOLD, 17));
                    layout.putConstraint(SpringLayout.WEST, Hitparade, 10,
                            SpringLayout.WEST, contentPane);
                    contentPane.add(Hitparade);
                    layout.putConstraint(SpringLayout.NORTH, Hitparade, 199,
                            SpringLayout.NORTH, contentPane);


                    JLabel Concerts = new JLabel("Концерты:");
                    Concerts.setFont(new Font("Century Gothic", Font.BOLD, 17));
                    layout.putConstraint(SpringLayout.WEST, Concerts, 10,
                            SpringLayout.WEST, contentPane);
                    contentPane.add(Concerts);
                    layout.putConstraint(SpringLayout.NORTH, Concerts, 245,
                            SpringLayout.NORTH, contentPane);

                    JTextField textGroupName = new JTextField(12);
                    textGroupName.setFont(new Font("Century Gothic", Font.BOLD, 17));
                    layout.putConstraint(SpringLayout.WEST, textGroupName, 170,
                            SpringLayout.WEST, contentPane);
                    contentPane.add(textGroupName);
                    layout.putConstraint(SpringLayout.NORTH, textGroupName, 15,
                            SpringLayout.NORTH, contentPane);

                    JTextField textMembers = new JTextField(12);
                    textMembers.setFont(new Font("Century Gothic", Font.BOLD, 17));
                    layout.putConstraint(SpringLayout.WEST, textMembers, 170,
                            SpringLayout.WEST, contentPane);
                    contentPane.add(textMembers);
                    layout.putConstraint(SpringLayout.NORTH, textMembers, 61,
                            SpringLayout.NORTH, contentPane);

                    String genre[] = {"KPOP", "Rap-Rock", "Glam-Rock", "Pop-music", "Hip-Hop", "Alternative Hip-Hop", "Abstract Hip-Hop", "Pop-Rock", "Synth-Punk", "Folk-Punk", "Experimental music", "Indie-Rock", "Rave", "Disco", "Сhanson", "Pop-Punk"};
                    JComboBox combogenre = new JComboBox<>(genre);
                    combogenre.setBackground(new Color(255, 255, 255));
                    layout.putConstraint(SpringLayout.WEST, combogenre, 170,
                            SpringLayout.WEST, contentPane);
                    contentPane.add(combogenre);
                    combogenre.setFont(new Font("Century Gothic", Font.BOLD, 17));
                    layout.putConstraint(SpringLayout.NORTH, combogenre, 107,
                            SpringLayout.NORTH, contentPane);

                    String year[] = {"2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021"};
                    JComboBox comboyear = new JComboBox<>(year);
                    comboyear.setBackground(new Color(255, 255, 255));
                    layout.putConstraint(SpringLayout.WEST, comboyear, 170,
                            SpringLayout.WEST, contentPane);
                    contentPane.add(comboyear);
                    comboyear.setFont(new Font("Century Gothic", Font.BOLD, 17));
                    layout.putConstraint(SpringLayout.NORTH, comboyear, 153,
                            SpringLayout.NORTH, contentPane);


                    JTextField textHitparade = new JTextField(12);
                    textHitparade.setFont(new Font("Century Gothic", Font.BOLD, 17));
                    layout.putConstraint(SpringLayout.WEST, textHitparade, 170,
                            SpringLayout.WEST, contentPane);
                    contentPane.add(textHitparade);
                    layout.putConstraint(SpringLayout.NORTH, textHitparade, 199,
                            SpringLayout.NORTH, contentPane);

                    JTextField textConcerts = new JTextField(12);
                    textConcerts.setFont(new Font("Century Gothic", Font.BOLD, 17));
                    layout.putConstraint(SpringLayout.WEST, textConcerts, 170,
                            SpringLayout.WEST, contentPane);
                    contentPane.add(textConcerts);
                    layout.putConstraint(SpringLayout.NORTH, textConcerts, 245,
                            SpringLayout.NORTH, contentPane);

                    JButton ok = new JButton("ОК");
                    JButton cancel = new JButton("ОТМЕНА");

                    layout.putConstraint(SpringLayout.WEST, ok, 70,
                            SpringLayout.WEST, contentPane);
                    layout.putConstraint(SpringLayout.NORTH, ok, 285,
                            SpringLayout.NORTH, contentPane);

                    layout.putConstraint(SpringLayout.NORTH, cancel, 285,
                            SpringLayout.NORTH, contentPane);
                    layout.putConstraint(SpringLayout.WEST, cancel, 85,
                            SpringLayout.EAST, ok);
                    ok.setFont(new Font("Century Gothic", Font.BOLD, 15));
                    cancel.setFont(new Font("Century Gothic", Font.BOLD, 15));
                    contentPane.add(ok);
                    contentPane.add(cancel);


                    ok.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            Vector<String> s = new Vector<>();
                            s.add(textGroupName.getText());
                            s.add(textMembers.getText());
                            s.add(genre[combogenre.getSelectedIndex()]);
                            s.add(year[comboyear.getSelectedIndex()]);
                            s.add(textHitparade.getText());
                            s.add(textConcerts.getText());

                            int count = 0;
                            for (String s1 : s) {
                                if ((s1.length() > 0) && (!s1.equals(" "))) {
                                    count++;
                                }
                            }

                            if (count == 6) {
                                s.add(0, number);

                                try {
                                    Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

                                    try (Connection conn = DriverManager.getConnection(url, name, password)) {
                                        Statement statement = conn.createStatement();

                                        statement.executeUpdate("UPDATE topchart2 SET GroupNameT = '" + s.get(1) + "' WHERE idTopChart2 = " + number);
                                        statement.executeUpdate("UPDATE topchart2 SET GroupMembersT = '" + s.get(2) + "' WHERE idTopChart2 = " + number);
                                        statement.executeUpdate("UPDATE topchart2 SET Genre = '" + s.get(3) + "' WHERE idTopChart2 = " + number);
                                        statement.executeUpdate("UPDATE topchart2 SET StartyearT = '" + s.get(4) + "' WHERE idTopChart2 = " + number);
                                        statement.executeUpdate("UPDATE topchart2 SET HitparadeT = '" + s.get(5) + "' WHERE idTopChart2 = " + number);
                                        statement.executeUpdate("UPDATE topchart2 SET Concerts = '" + s.get(6) + "' WHERE idTopChart2 = " + number);
                                        ImageIcon icon1 = new ImageIcon("./image/databaseyes.png");
                                        JOptionPane.showMessageDialog(null,
                                                "Информация в базе данных успешно изменена !","Информация о работе базы данных",
                                                JOptionPane.INFORMATION_MESSAGE,
                                                icon1);
                                    }
                                } catch (Exception ex) {
                                    System.out.println("Подключение не удалось...");
                                    System.out.println(ex);
                                }

                                for (int i = 1; i < s.size(); i++) {
                                    model.setValueAt(s.get(i), num, i);
                                }

                                window2.dispose();
                            } else {
                                JOptionPane.showMessageDialog(frame, "Заполнены не все поля!");
                            }
                        }
                    });

                    cancel.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent e) {
                            int n = JOptionPane.showConfirmDialog(window2,"<html>Вы выходите из режима редактирования карты.<br>Данные не сохранятся.</html>","Message",JOptionPane.YES_NO_OPTION);
                            if(n == JOptionPane.YES_OPTION) {
                                window2.dispose();
                            }
                        }
                    });

                    window2.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    window2.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(frame, "ДАННЫЙ ID ОТСУТСТВУЕТ!", "ОШИБКА", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window1.dispose();
            }
        });
        window1.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        window1.setVisible(true);
    }


}
