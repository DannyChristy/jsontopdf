package com.ey;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class PDFGenerator {

  public static void process(Document document, JSONObject json) throws JSONException, DocumentException {
    for (String k : json.keySet()) {
      Object object = json.get(k);
      if (object instanceof JSONArray) {
        JSONArray list = json.getJSONArray(k);
        process(document, list);
      } else if (object instanceof JSONObject) {
        process(document, json.getJSONObject(k));
      } else {
        document.add(new Paragraph(k + " " + json.get(k)));
      }

    }
  }

  public static void process(Document document, JSONArray json) throws JSONException, DocumentException {
    for (int x = 0; x < json.length(); x++) {
      Object object = json.get(x);
      if (object instanceof JSONArray) {
        JSONArray list = json.getJSONArray(x);
        process(document, list);
      } else if (object instanceof JSONObject) {
        process(document, json.getJSONObject(x));
      } else {
        document.add(new Paragraph(json.get(x).toString()));
      }

    }
  }

  public static File jsonTopdf(JSONObject json) throws IOException, DocumentException {

    Document document = new Document(PageSize.A4, 70, 55, 100, 55);
    File file = new File("resources\\"+"somefile"+".pdf");
    if(file.exists()){
      file.delete();
    }
    boolean fileCreated = file.createNewFile();
    FileOutputStream output = new FileOutputStream(file);

    PdfWriter writer = PdfWriter.getInstance(document, output);
/*    writer.setEncryption("a".getBytes(), "b".getBytes(), PdfWriter.ALLOW_PRINTING, PdfWriter.STANDARD_ENCRYPTION_128);
    writer.createXmpMetadata();
    writer.setBoxSize("art", new Rectangle(36, 54, 559, 788));*/


    document.open();
    document.addCreationDate();
    document.addTitle("Ernst&Young");
    document.newPage();

    process(document, json);

    document.close();

    return file;
  }

  public static void main(String args[]) throws IOException, ParseException, DocumentException {
    PDFGenerator pdfGenerator = new PDFGenerator();

    File file = new File("C:\\app\\DPA\\jsontopdf\\resources\\test1.json");
    String content = FileUtils.readFileToString(file,"utf-8");

    JSONParser jsonParser= new JSONParser();
    Object parse = jsonParser.parse(new FileReader("C:\\app\\DPA\\jsontopdf\\resources\\test1.json"));
    JSONObject jsonObject = new JSONObject(content);
    File pdfFile = jsonTopdf(jsonObject);
  }

}