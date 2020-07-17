package reader;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class HttpIdReqest {
    public static String getId(String ... v) throws UnknownHostException,MalformedURLException
    {
        int ContentLength = 0;
        int cnt = 10;
        ArrayList<Character> gif = new ArrayList();
        StringBuffer bufa = new StringBuffer();

       try{
           String makeReqest = "https://translate.yandex.ru/?clid=2233626";
           URI dsfs = new URI(makeReqest);
           URL hp = new URL(dsfs.toASCIIString());

           HttpsURLConnection hpCon = (HttpsURLConnection) hp.openConnection();

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
//            System.out.println(Sozdik);
            for (int i = 0; i < buf.length; i++) {
                bufa.append((char) buf[i]);
                gif.add((char) buf[i]);
            }
           String htmlString = Sozdik;

           System.out.println(Sozdik);
           System.out.println(htmlString.substring(htmlString.indexOf("SID: '")+"SID: '".length(),htmlString.indexOf("',",htmlString.indexOf("SID: '"))));
           String token = htmlString.substring(htmlString.indexOf("SID: '") + "SID: '".length(), htmlString.indexOf("',", htmlString.indexOf("SID: '")));
           StringTokenizer st = new StringTokenizer(token," \r/,\\«.'~@#$%^_+{}[]><=&-*?!—:;`»()\n");
           StringBuffer stringBuffer = new StringBuffer();
           while (st.hasMoreTokens())
               stringBuffer.append(new StringBuffer(st.nextToken()).reverse() + ".");
           stringBuffer.deleteCharAt(stringBuffer.length()-1);
//               System.out.println(new StringBuffer(st.nextToken()).reverse());
//           System.out.println(gif.toString().substring(gif.toString().indexOf("SID: '")));;
           System.out.println(stringBuffer.toString());
           return stringBuffer.toString();

        }catch (IOException | URISyntaxException e){System.out.println("EROOR");}finally {
           System.out.println("end of Id reqest");
       }

        return "";

    }
}
