package com.louay.projects.model.util.file;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.*;

@Configuration
@Component("fileOperation")
@Scope("prototype")
public class FileProcess {

    public byte[] readAPicture(String path) throws IOException {
        byte[] bytes;
        InputStream in;
        File file = new File(path);

        if (file.exists()) {
            if (file.canExecute()) {
                if (file.canRead()) {
                    bytes = new byte[(int) file.length()];
                    in = new BufferedInputStream(new FileInputStream(path));
                    int i = 0;
                    while (in.available() != 0) {
                        bytes[i] = (byte) in.read();
                        i++;
                    }
                    in.close();
                } else {
                    throw new IOException("file can not Read.");
                }
            } else {
                throw new IOException("file can not execute.");
            }
        } else {
            throw new FileNotFoundException("file not found.");
        }
        return bytes;
    }

    public void writePicture(String path, byte[] bytes) throws IOException {
        OutputStream out = new BufferedOutputStream(new FileOutputStream(path));
        out.write(bytes);
        out.flush();
        out.close();
    }
}