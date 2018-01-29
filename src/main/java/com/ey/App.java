package com.ey;

import com.itextpdf.text.DocumentException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    private static File jsonFile=new  File("C:\\app\\DPA\\jsontopdf\\resources\\test1.json");
    private static JFrame jFrame;
    public static GuiWindow window = new GuiWindow();
    private static JsonReader jsonReader = new JsonReader();
    public PDFconvertor pdFconvertor = new PDFconvertor();
    public static void main( String[] args ) throws IOException {

        App app = new App() ;
        System.out.println( "Hello World!" );

        jFrame= window.createGUI();
        app.addButton(jFrame);

        if(jsonFile==null){
            System.out.println("not json file in main");
        }

  //      jsonReader.jsonSerilizer(null);

    }

    public void addButton(JFrame jFrame){
        JButton selectButton = new JButton("Choose JSON File");
        selectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jsonFile = jsonReader.getFile();
                if(jsonFile==null){
                    System.out.println("null file in button");
                }
            }
        });

        //todo : probably not required. if required will have to save the selected file on the jframe and pass it on the convert button and also the function..
        JButton convertButton = new JButton("Save as PDF");
        convertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    pdFconvertor.savePDF(jsonReader.jsonSerilizer(jsonFile),jsonFile);
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
