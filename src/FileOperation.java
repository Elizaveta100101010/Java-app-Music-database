
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class FileOperation {

    private static String fileNameOpen;
    private static String fileNameSave;
    private static String tags[] = {"id", "group", "members", "genre", "year", "hits", "concerts"};


    private static Document getDocumentOpen() throws Exception {
        try {
            DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = f.newDocumentBuilder();
            return builder.parse(new File(fileNameOpen));
        } catch (Exception exception) {
            throw new Exception("XML parsing error!");
        }

    }


    private static Document getDocumentSave() throws Exception {
        try {
            DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = f.newDocumentBuilder();
            return builder.newDocument();

        } catch (Exception exception) {
            throw new Exception(" XML");
        }
    }

    //Открытие XML файла
    public void xmlOpen(DefaultTableModel model, JTable table,JFrame frame) {

        FileDialog openXml = new FileDialog(frame," XML ",FileDialog.LOAD);
        openXml.setFile(".xml");
        openXml.setVisible(true);
        fileNameOpen = openXml.getDirectory() + openXml.getFile();
        if(fileNameOpen == null) return;

        model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
//Чтение файла
        try {
            Document doc = getDocumentOpen();
            doc.getDocumentElement().normalize();
            NodeList list = doc.getElementsByTagName("music");
            String s[] = new String[tags.length];
            for(int i = 0;i < list.getLength();i++) {
                Node elem = list.item(i);
                NamedNodeMap atr = elem.getAttributes();
                for(int j = 0;j < tags.length;j++) {
                    s[j] = atr.getNamedItem(tags[j]).getNodeValue();
                }
                model.addRow(s);
            }
//Ошибка при чтении XML файла
        }catch(SAXException e){
            e.printStackTrace();

        }catch(Exception e){
            e.printStackTrace();
        }

    }
    //Сохранение XML файла
    public void xmlSave(DefaultTableModel model, JTable table,JFrame frame) throws Exception {
        FileDialog saveXml = new FileDialog(frame," XML",FileDialog.SAVE);
        saveXml.setFile("*.xml");
        saveXml.setVisible(true);
        fileNameSave = saveXml.getDirectory() + saveXml.getFile();
        if(fileNameSave == null) return;
//Запись элементов в файл
        Document doc = getDocumentSave();
        Node musiclist = doc.createElement("musicList");
        doc.appendChild(musiclist);
        for(int i = 0; i < model.getRowCount(); i++){
            Element music = doc.createElement("music");
            musiclist.appendChild(music);

            for(int j = 0;j < tags.length;j++) {
                music.setAttribute(tags[j],(String) model.getValueAt(i,j));
            }

        }
//Создание преобразования документа
        try{
            Transformer trans= TransformerFactory.newInstance().newTransformer();
            trans.setOutputProperty(OutputKeys.METHOD, "xml");
            trans.setOutputProperty(OutputKeys.INDENT, "yes");
            trans.transform(new DOMSource(doc), new StreamResult(new FileOutputStream(fileNameSave)));
        }catch(TransformerConfigurationException e){
            e.printStackTrace();
        }catch(TransformerException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}


