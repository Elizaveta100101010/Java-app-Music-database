import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class HTMLPDF  {

    //Создание PDF отчета
    void createPDF(DefaultTableModel model) throws IOException {

        String FONT = "./arial.ttf";
        PdfFont font = PdfFontFactory.createFont(FONT, PdfEncodings.IDENTITY_H);
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter("./NewData/Отчет PDF по музыкальным группам.pdf"));
        pdfDoc.addNewPage();
        Document doc = new Document(pdfDoc);
        Paragraph paragraph = new Paragraph("REPORT");
        doc.add(paragraph);
        String column[] = {"ID", "Название группы", "Участники группы", "Жанр", "Год основания", "#Хит парад", "Концерты"};
        Table table = new Table(column.length);
        table.setFont(font);

        for(String s: column) {
            table.addCell(new Cell().add(s));
        }

        for(int i = 0;i < model.getRowCount();i++) {
            for(int j = 0;j < model.getColumnCount();j++) {
                table.addCell(new Cell().add(model.getValueAt(i,j).toString()));
            }
        }

        doc.add(table);
        doc.close();
        ImageIcon icon1 = new ImageIcon("./image/PDF.png");
        JOptionPane.showMessageDialog(null,
                "<html>Отчет в формате PDF сохранен !</html>","Сохранение PDF",
                JOptionPane.INFORMATION_MESSAGE,
                icon1);
    }

    //Создание HTML отчета
    void createHTML(DefaultTableModel model) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileWriter("./NewData/Отчет HTML по музыкальным группам.html"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        pw.println("<TABLE BORDER><TR><TH>ID<TH>Название группы<TH>Участники группы<TH>Жанр<TH>Год основания<TH>#Хит парад<TH>Концерты</TR>");
        for(int i = 0; i < model.getRowCount(); i++) {
            pw.println("<TR><TD>" + (String) model.getValueAt(i,0)
                    + "<TD>" + (String) model.getValueAt(i,1)
                    + "<TD>" + (String) model.getValueAt(i,2)
                    + "<TD>" + (String) model.getValueAt(i,3)
                    + "<TD>" + (String) model.getValueAt(i,4)
                    + "<TD>" + (String) model.getValueAt(i,5)
                    + "<TD>" + (String) model.getValueAt(i,6));
        }
        pw.println("</TABLE>");
        pw.close();
        ImageIcon icon2 = new ImageIcon("./image/HTML.png");
        JOptionPane.showMessageDialog(null,
                "<html>Отчет в формате HTML сохранен !</html>","Сохранение HTML",
                JOptionPane.INFORMATION_MESSAGE,
                icon2);
    }
}


