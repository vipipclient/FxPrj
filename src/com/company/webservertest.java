package com.company;
import com.sun.net.httpserver.HttpServer;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class webservertest {

    public static void main(String[] args) throws Throwable {
   //     ImageFilterDemo.main(new String[]{"Winter", "Spring", "Summer", "Autumn"});
        System.err.println("ImageFilterDemo sdfds = new ImageFilterDemo();");
        ServerSocket ss = new ServerSocket(80);
        while (true) {
            Socket s = ss.accept();
            System.err.println("Client accepted");
            new Thread(new SocketProcessor(s)).start();
        }

    }

    private static class SocketProcessor implements Runnable {

        private Socket s;
        private InputStream is;
        private OutputStream os;

        private SocketProcessor(Socket s) throws Throwable {
            this.s = s;
            this.is = s.getInputStream();
            this.os = s.getOutputStream();
        }

        public void run() {
            try {
                readInputHeaders();
                String in = "";
                try (SeekableByteChannel fChan = Files.newByteChannel(Paths.get("test.html")))
                {
                    System.out.println("fChan.size()" + fChan.size());
                    in =  new String(Files.readAllBytes(Paths.get("test.html")));
                }catch (IOException e2) {System.out.println("Ошыбка ,братан: ");}
                writeResponse(in);
            } catch (Throwable t) {
                /*do nothing*/
            } finally {
                try {
                    s.close();
                } catch (Throwable t) {
                    /*do nothing*/
                }
            }
            System.err.println("Client processing finished");
        }

        private void writeResponse(String s) throws Throwable {
            String response = "HTTP/1.1 200 OK\r\n" +
                    "Server: YarServer/2009-09-09\r\n" +
                    "Content-Type: text/html\r\n" +
                    "Content-Length: " + s.length() + "\r\n" +
                    "Connection: close\r\n\r\n";
            String result = response + s;
            os.write(result.getBytes());
            os.flush();
        }

        private void readInputHeaders() throws Throwable {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while(true) {
                String s = br.readLine();
                if (s.contains("GET")){
                }
                System.out.println(s);
                if(s == null || s.trim().length() == 0) {
                    break;
                }
            }
        }
    }
}