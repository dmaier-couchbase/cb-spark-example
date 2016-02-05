package com.couchbase.demo.spark.helper;

import com.google.common.io.Files;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

/**
 *
 * @author David Maier <david.maier at couchbase.com>
 */
public class Helper {
   
    /**
     * Get content from the web
     * 
     * @param url
     * @return
     * @throws MalformedURLException
     * @throws IOException 
     */
    public static StringBuffer wget(String url) throws MalformedURLException, IOException {
       
       StringBuffer result = new StringBuffer();
        
       URL u = new URL(url);
       
       InputStream is = u.openStream();
       
       DataInputStream dis = new DataInputStream(is);
       
       
       String line;
       
       while ( (line = dis.readLine()) != null){
           
           result.append(line).append("\n");
       }
       
       return result;
    }
    
    /**
     * Write a string buffer to a file
     * 
     * @param content
     * @param file
     * @throws IOException 
     */
    public static void writeToFile(StringBuffer content, File file) throws IOException {
        
        Files.write(content.toString(), file, Charset.defaultCharset());
 
    }
}
