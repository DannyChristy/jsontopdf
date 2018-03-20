package com.ey;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FilenameUtils;

import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonReader {
    public List<File> getFile(JFrame jFrame) {
        int fileCount=0;
        List<File> selectedFileList = new ArrayList<>();
        final JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        jFileChooser.setMultiSelectionEnabled(true);
        jFileChooser.showOpenDialog(jFrame);
        //jFileChooser.showSaveDialog(null);
        //  jFileChooser.addChoosableFileFilte
        // r(new FileNameExtensionFilter("*.txt","txt"));

        //jFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("json files only","json"));
     //   jFileChooser.setFileFilter(new FileNameExtensionFilter("json files only","json"));


        for (File selectedFile:jFileChooser.getSelectedFiles()
             ) {
            if(selectedFile.isDirectory()){
                for (File fileInDirectory:selectedFile.listFiles()
                     ) {
                    if(isJsonFile(fileInDirectory)){
                        selectedFileList.add(fileInDirectory);
                        fileCount++;
                    }
                }
            }

            else if(isJsonFile(selectedFile)){
                selectedFileList.add(selectedFile);
                fileCount++;
            }
        }
        if(fileCount!=0){
            JOptionPane.showMessageDialog(jFileChooser,"You have selected "+fileCount+" JSON file(s). This would be converted to PDF");
        }
        else{
            JOptionPane.showMessageDialog(jFileChooser,"There were no JSON files in the selection");
        }
        return selectedFileList;

    }

    private boolean isJsonFile(File file) {
        if (FilenameUtils.getExtension(file.getName()).equalsIgnoreCase("json")) {
            return true;
        } else {
            return false;
        }

    }
    public Map<String,Object> jsonSerilizer(File jsonFile) throws IOException {
        jsonFile = new File("C:\\app\\DPA\\jsontopdf\\resources\\test1.json");
        ObjectMapper objectMapper = new ObjectMapper();
        Reader reader = new FileReader(jsonFile);
       // String json =
        Map<String, Object> map = objectMapper.readValue(reader, new TypeReference<Map<String,Object>>(){});
        return map;
    }
}
