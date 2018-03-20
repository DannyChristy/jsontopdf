package com.ey;

import com.github.wnameless.json.flattener.JsonFlattener;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.commons.io.FilenameUtils;

import javax.swing.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PDFconvertor {
  private static final Map<String,String> PDF_MAP= new HashMap<String, String>();


  public File createPDF(String fileName) throws IOException {
    File file = new File("//home//vini//coremedia//jsontopdfDan//resources//" + "test_json_CFO" + ".pdf");
    if (file.exists()) {
      System.out.println("Existing file deleted");
      file.delete();
    }
    boolean fileCreated = file.createNewFile();
    if (fileCreated) {
      System.out.println("created file" + file.getName());
      return file;
    }
    else {
      System.out.println("File not created");
      return null;
    }

  }

  public Map<String,Object> jsonSerializer(File jsonFile){
      try{
          Scanner scanner = new Scanner(jsonFile);
          String json = scanner.useDelimiter("\\A").next();
          scanner.close();

          Map<String,Object> flattenedJson = JsonFlattener.flattenAsMap(json);
          return flattenedJson;
      }
      catch (Exception e){
          return null;
      }


  }

  public Boolean pdfWriterInitializer(File jsonFile, JFrame jFrame) throws IOException, DocumentException {
      Map<String, Object> serializedJSONMap = jsonSerializer(jsonFile);
      File pdfFile = createPDF(jsonFile.getName());
      pdfFile= intializePDF(pdfFile);
      pdfFile = writeJSON(pdfFile,serializedJSONMap);
      return true;
  }

    private File writeJSON(File pdfFile, Map<String, Object> serializedJSONMap) throws FileNotFoundException, DocumentException {
        FileOutputStream fileOutputStream = new FileOutputStream(pdfFile);
        Document document = new Document();
        PdfWriter.getInstance(document, fileOutputStream);
        document.open();
        Paragraph elements = new Paragraph();
        elements.add("someline");
        elements.add("someother line");
        document.add(elements);
        document.close();
      return pdfFile;
    }

    public File intializePDF(File pdfFile) throws IOException, DocumentException {

      FileOutputStream fileOutputStream = new FileOutputStream(pdfFile);
      Document document = new Document();
      PdfWriter.getInstance(document, fileOutputStream);
      document.open();

      /*
       * adding the logo on the pdf
       * */
      Image image = Image.getInstance("resources/download.png");
      image.setAlignment(Image.RIGHT);
      document.add(image);
      document.close();
      return pdfFile;
  }

  public void savePDF(Map<String, Object> stringObjectMap, File jsonFile) throws IOException, DocumentException {
    File pdfFile = createPDF(getBaseFileName(jsonFile));
    //  File pdfFile = createPdf(jsonFile.getName());
    if (pdfFile != null) {
      FileOutputStream fileOutputStream = new FileOutputStream(pdfFile);
      Document document = new Document();
      PdfWriter.getInstance(document, fileOutputStream);
      document.open();

            /*
             * adding the logo on the pdf
             * */
      Image image = Image.getInstance("resources/download.png");
      image.setAlignment(Image.RIGHT);
      document.add(image);

/*      document.addAuthor("JsonToPdf_program");
      document.addTitle("Doesn't matter");*/



/*      Font font = new Font(Font.COURIER);
      font.setStyle(Font.UNDERLINE);
      font.setStyle(Font.ITALIC);
      chunk.setFont(font);
      chunk.setBackground(Color.CYAN);*/

            /*
             * adding the title of the pdf file
             *
             * */
      Paragraph paragraph = new Paragraph();
      paragraph.add("Main Content");
      paragraph.setAlignment(Element.ALIGN_CENTER);
      document.add(paragraph);

            /*
             * Adding the remaining part of the pdf from JSON
             * */
            /*Font font = FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK);
            Chunk chunk = new Chunk("test",font);
            document.add(chunk);*/

      for (Map.Entry<String, Object> jsonEntity : stringObjectMap.entrySet()
              ) {
        if(jsonEntity.getValue().getClass()==java.util.LinkedHashMap.class){
          //addValuetoPdfMap();
        }
        Object jsonEntityValue = jsonEntity.getValue();
        if (jsonEntityValue.getClass() == String.class || jsonEntityValue.getClass() == Integer.class) {
          Paragraph entity = new Paragraph();
          entity.add(jsonEntity.getKey());
          entity.add(" : ");
          entity.add(jsonEntityValue.toString());
          document.add(entity);
          System.out.println("Key:" + jsonEntity.getKey() + " ;value : " + jsonEntity.getValue());
        }
        // System.out.println(jsonEntity.toString());
      }

      //Chunk mapChunk = new Chunk(stringObjectMap,font);

      document.close();
      System.out.println("pdf written");
      BufferedReader bufferedReader = new BufferedReader(new FileReader(jsonFile));
    }

    else {
      System.out.println("Writing  not possible");
    }

  }



 /* public static Stream<Object> flatten(Object o) {
    if (o instanceof Map<?, ?>) {
      return ((Map<?, ?>) o).values().stream().flatMap(FlatMap::flatten);
    }
    return Stream.of(o);
  }*/
  private void addValuetoPdfMap(Map<String,Object> stringObjectMap){

  }



  private Paragraph serializeObject(Map<Object, Object> objectMap) {
    return null;
  }

  private String getBaseFileName(File file) {
    return FilenameUtils.getBaseName(file.getName());
  }
}
