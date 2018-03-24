package com.ey;

import com.ey.document.JSONDocument;
import com.ey.document.SessionInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.DocumentException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 * additional comment for commit
 */
public class App 
{
    private static int filesConverted=0;
    private static File jsonFile=new  File("C:\\app\\DPA\\jsontopdf\\resources\\test1.json");
    private static List<File> jsonFileList = new ArrayList<>();
    private static JFrame jFrame;
    public static GuiWindow window = new GuiWindow();
    private static JsonReader jsonReader = new JsonReader();
    private static PDFconvertor pdfConvertor = new PDFconvertor();
    public static void main( String[] args ) throws IOException, DocumentException {

        App app = new App() ;
        ObjectMapper objectMapper= new ObjectMapper();
        //jFrame=
        // window.createGUI();
        //app.addButton(jFrame);



        Reader jsonreader =  new FileReader("C:\\Users\\dedvall1\\IdeaProjects\\jsontopdf\\resources\\CFO Space Open Day_v03.json");
        JSONDocument jsonDocument= objectMapper.readValue(jsonreader,JSONDocument.class);
        SessionInfo sessionInfo=jsonDocument.getSessionInfo();
        System.out.println(sessionInfo.getClientName()+"\n"+sessionInfo.getClientLogoPath());
        System.out.println(jsonDocument.getSessionInfo());

        pdfConvertor.createFromJSONDocument(jsonDocument);



  //      jsonReader.jsonSerilizer(null);

    }

    public void addButton(JFrame jFrame){
        JButton selectButton = new JButton("Choose Folder");
        selectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jsonFileList = jsonReader.getFile(jFrame);
                if(jsonFileList==null){
                    JOptionPane.showMessageDialog(jFrame,"Something has gone wrong in the processing of the JSON files. Try again later or contact Administrator if problem remains");
                }
                else{
                    for (File jsonFile:jsonFileList
                            ) {
                        try {
                            if(pdfConvertor.pdfWriterInitializer(jsonFile,jFrame)){
                                filesConverted++;
                            }
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        } catch (DocumentException e1) {
                            e1.printStackTrace();
                        }
                    }
                    JOptionPane.showMessageDialog(jFrame,filesConverted+" file(s) has  been successfully converted");
                }

            }
        });

        //todo : probably not required. if required will have to save the selected file on the jframe and pass it on the convert button and also the function..
        JButton convertButton = new JButton("Save as PDF");
        convertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    pdfConvertor.savePDF(jsonReader.jsonSerilizer(jsonFile),jsonFile);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (DocumentException e1) {
                    e1.printStackTrace();
                }
            }
        });
        jFrame.getContentPane().add(selectButton);
        jFrame.getContentPane().add(convertButton);
    }
}
