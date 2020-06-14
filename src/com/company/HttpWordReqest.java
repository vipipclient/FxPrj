package com.company;
import com.sun.net.httpserver.HttpServer;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;
public class HttpWordReqest {
    public static void main(String args[]) throws UnknownHostException,MalformedURLException{
        System.out.println(args[0]);
        InetAddress Address = InetAddress.getLocalHost();
        System.out.println(Address);
 //       String NameOfServer = "triggur.org";
        String NameOfServer = "mail.ru";
        Address = InetAddress.getByName(NameOfServer);
        System.out.println(Address);
        int ContentLength = 0;
        int cnt = 10;
        ArrayList<Byte> gif = new ArrayList();
        StringBuffer bufa = new StringBuffer();

       try{
           String makeReqest = "https://sozdik.kz/translate/kk/ru/" + "ақ";
           URI dsfs = new URI(makeReqest);
           URL hp = new URL(dsfs.toASCIIString());

           System.out.println("%D0%B8%D0%B3%D1%80%D0%B0 " + hp.toURI().toASCIIString());
           // HttpURLConnection hpCon = (HttpURLConnection) hp.openConnection();
           HttpsURLConnection hpCon = (HttpsURLConnection) hp.openConnection();
            System.out.println("hpCon.getDate()" + hpCon.getContentLength());

           ContentLength = hpCon.getContentLength();
            Map<String, List<String>> gf = hpCon.getHeaderFields();
            gf.forEach((a,b) -> {
                Iterator<String> sdf = b.listIterator();
                while (sdf.hasNext())
                    System.out.println(String.format("%15s\t%s",a,sdf.next()));

            });
            InputStream asd = hpCon.getInputStream();
            byte[] buf = asd.readAllBytes();
            String Sozdik = new String(buf, StandardCharsets.UTF_8);
            System.out.println(Sozdik);
            for (int i = 0; i < buf.length; i++) {
  //              System.out.print((char) buf[i]);

                bufa.append((char) buf[i]);
                gif.add(buf[i]);
            }


        }catch (IOException | URISyntaxException e){System.out.println("EROOR");}

        try (FileWriter fout = new FileWriter("page.html")){

            fout.write(bufa.toString());
        }catch (IOException e){System.out.println("Ошыбка ,братан: ");}
        FileOutputStream f0 = null;
        try {
            f0 = new FileOutputStream("sdf.html");

            byte[] gifbuff = new byte[gif.size()];
            int i = 0;
            Iterator GifIt =  gif.iterator();
            while (GifIt.hasNext())
                {gifbuff[i] = (byte) GifIt.next();i++;}
            f0.write(gifbuff);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

/*
        try(Socket s = new Socket(Address,80);){
            System.out.print("Soket opened");
            InputStream in = s.getInputStream();
            OutputStream out = s.getOutputStream();


            String str = "GET / HTTP/1.1\r\n" + "Host: " + NameOfServer +"\r\n\r\n"  ;
            out.write(str.getBytes());
            byte Input[] = in.readAllBytes();
            System.out.print("recived " + Input.length + "bytes");
            if (Input.length>0)
                for (int i = 0;i<Input.length;i++)
                    System.out.print((char) Input[i]);

            System.out.print("Serever closed");
        }catch (IOException e){ System.out.println("EROOR");}
        finally {
            ;
        }
*/

    }
}
