package com.ey;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FilenameUtils;

import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Map;

public class JsonReader {
    public File getFile() {
        final JFileChooser jFileChooser = new JFileChooser();
        //jFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        jFileChooser.showSaveDialog(null);
        //  jFileChooser.addChoosableFileFilte
        // r(new FileNameExtensionFilter("*.txt","txt"));


        if(isJsonFile(jFileChooser.getSelectedFile())){
            return jFileChooser.getSelectedFile();
        }
        else
        {
            return null ;
        }

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
