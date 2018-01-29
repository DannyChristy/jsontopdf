package com.ey;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.util.Map;

public class PDFconvertor {
    public File createPDF(String fileName) throws IOException {
        File file = new File("resources\\"+fileName+".pdf");
        if(file.exists()){
            System.out.println("Existing file deleted");
            file.delete();
        }
        boolean fileCreated = file.createNewFile();
        if(fileCreated){
            System.out.println("created file" + file.getName());
            return file;
        }
        else{
            System.out.println("File not created");
            return null;
        }

    }
    public void savePDF (Map<String,Object> stringObjectMap, File jsonFile) throws IOException, DocumentException {
        File pdfFile = createPDF(getBaseFileName(jsonFile));
      //  File pdfFile = createPdf(jsonFile.getName());
        if(pdfFile!=null){
            FileOutputStream fileOutputStream = new FileOutputStream(pdfFile);
            Document document = new Document();
            PdfWriter.getInstance(document, fileOutputStream);
            document.open();

            /*
             * adding the logo on the pdf
             * */
            Image image= Image.getInstance("resources/download.png");
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

            for (Map.Entry<String, Object> jsonEntity:stringObjectMap.entrySet()
            ){
                Object jsonEntityValue =  jsonEntity.getValue();
                if(jsonEntityValue.getClass()==String.class||jsonEntityValue.getClass()==Integer.class){
                    Paragraph entity =  new Paragraph();
                    entity.add(jsonEntity.getKey());
                    entity.add(" : ");
                    entity.add(jsonEntityValue.toString());
                    document.add(entity);
                    System.out.println("Key:"+jsonEntity.getKey()+" ;value : "+jsonEntity.getValue());
                }
               // System.out.println(jsonEntity.toString());
            }

            //Chunk mapChunk = new Chunk(stringObjectMap,font);

            document.close();
            System.out.println("pdf written");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(jsonFile));
        }

        else{
            System.out.println("Writing  not possible");
        }

    }

    private Paragraph serializeObject(Map<Object,Object> objectMap){
        return null;
    }
    private String getBaseFileName(File file){
        return FilenameUtils.getBaseName(file.getName());
    }
}
