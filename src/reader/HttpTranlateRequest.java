package reader;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HttpTranlateRequest {
    String lenguage = "kk-ru";
    public String getLenguage() {
        return lenguage;
    }

    public String getXmlString(){
        try {
            // Создается построитель документа
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Создается дерево DOM документа из файла
            Document document = documentBuilder.parse("conf.xml");

            // Получаем корневой элемент
            Node root = document.getDocumentElement();

            System.out.println("List of books:");
            System.out.println();
            // Просматриваем все подэлементы корневого - т.е. книги
            NodeList books = root.getChildNodes();
            for (int i = 0; i < books.getLength(); i++) {
                Node book = books.item(i);
                // Если нода не текст, то это книга - заходим внутрь
                if (book.getNodeType() != Node.TEXT_NODE) {
                    NodeList bookProps = book.getChildNodes();
                    for(int j = 0; j < bookProps.getLength(); j++) {
                        Node bookProp = bookProps.item(j);
                        // Если нода не текст, то это один из параметров книги - печатаем
                        if (bookProp.getNodeType() != Node.TEXT_NODE) {
                            System.out.println(bookProp.getNodeName() + ":" + bookProp.getChildNodes().item(0).getTextContent());
                        }
                    }
                    System.out.println("===========>>>>");
                }
            }


        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (SAXException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        return "Done";
    }

    public void setLenguage(String lenguage) {
        this.lenguage = lenguage;
    }


    public HttpTranlateRequest(){

    }
    public  String makeTranlation(String wordToTranslate)throws Throwable{
        if ( wordToTranslate.isEmpty())
            return "";


        System.out.println("Продолжаем дальше пирп");
        try{
            String makeYaPostKz = "https://translate.yandex.net/api/v1/tr.json/translate?id=8c92ac22.5ee53713.7c1ef051-3-0&srv=tr-text&lang=kk-ru&reason=auto&format=text";
            String makeYaPostEng ="https://translate.yandex.net/api/v1/tr.json/translate?id=6b618e39.5ee883f6.2e3fd56f-1-0&srv=tr-text&lang=en-ru&reason=auto&format=text";
            String makeYaPost = "https://translate.yandex.net/api/v1/tr.json/translate?id=8b344928.5ef77b27.59e0a7f8-3-0&srv=tr-text&lang="+
                    lenguage+
                    "&reason=auto&format=text";

            URI dsfs = new URI(makeYaPost);
            URL hp = new URL(dsfs.toASCIIString());
            HttpsURLConnection hpCon = (HttpsURLConnection) hp.openConnection();
            hpCon.setRequestMethod("POST");
            hpCon.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            hpCon.setDoOutput(true);
            DataOutputStream out = new DataOutputStream(hpCon.getOutputStream());
            String postData = "text="+URLEncoder.encode(wordToTranslate,StandardCharsets.UTF_8).replace("+","%20")
                    +"&options=4";

//            System.out.println(postData);
             out.writeBytes(postData);



            Map<String, List<String>> gf = hpCon.getHeaderFields();
            gf.forEach((a,b) -> {
                Iterator<String> sdf = b.listIterator();
                while (sdf.hasNext())
                    System.out.println(String.format("%15s\t%s",a,sdf.next()));

            });
            System.out.println(hpCon.getContent().toString()); ;


            InputStream asd = hpCon.getInputStream();
            byte[] buf = asd.readAllBytes();
            String Sozdik = new String(buf, StandardCharsets.UTF_8);

            JsonNode dsa = new ObjectMapper().readTree(Sozdik);
            JsonNode dsaPath = dsa.findPath("text");

            return dsaPath.get(0).getTextValue();
        }catch (IOException | URISyntaxException e){System.out.println("EROOR");}
        return "";
    }
    public static void main(String args[]) throws Throwable {
//        ArrayList<String> arrayList = new ArrayList<>();
//        for (int i = 0; i <100 ; i++) {
//            arrayList.add(makeTranlation("өте жабықталған " + i));
//        }
//        makeTranlation("өте жабықталған " );


  //      System.exit(1);
  //      com.company.webservertest.main(new String[]{"sdfdsf", "fdsf"});
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
           String makeGoogle = "https://translate.google.ru/translate_a/single?client=webapp&sl=ru&tl=en&hl=ru&dt=at&dt=bd&dt=ex&dt=ld&dt=md&dt=qca&dt=rw&dt=rm&dt=sos&dt=ss&dt=t&clearbtn=1&otf=1&pc=1&ssel=5&tsel=5&xid=45662847&kc=2&tk=788593.701725&q="
                   +"прихоть";
           String makeYandex = "https://dictionary.yandex.net/dicservice.json/queryCorpus?ui=ru&srv=tr-text&sid=8c261421.5ece5778.33ee61f7&src=" +
                   "good" +
                   "&dst=&lang=en-ru&flags=39&chunks=1&maxlen=200";
           String localHost  = "http://localhost";
           String makeYaPost = "https://translate.yandex.net/api/v1/tr.json/translate?id=b5e22b11.5ed13d92.5bba1006-1-0&srv=tr-text&lang=en-ru&reason=paste&format=text";
           String makeYaPostKz = "https://translate.yandex.net/api/v1/tr.json/translate?id=b5e22b11.5ed13d92.5bba1006-13-0&srv=tr-text&lang=kk-ru&reason=paste&format=text";
           String troubleYa =    "https://translate.yandex.net/api/v1/tr.json/translate?id=dada2289.5eda9435.02c008e6-6-0&srv=tr-text&lang=kk-ru&reason=paste&format=text";
           URI dsfs = new URI(troubleYa);
           URL hp = new URL(dsfs.toASCIIString());


           System.out.println(dsfs.toASCIIString());

///           System.out.println("%D0%B8%D0%B3%D1%80%D0%B0 " + hp.toURI().toASCIIString());
           // HttpURLConnection hpCon = (HttpURLConnection) hp.openConnection();

           HttpsURLConnection hpCon = (HttpsURLConnection) hp.openConnection();
           hpCon.setRequestMethod("POST");
           hpCon.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
           hpCon.setDoOutput(true);
           DataOutputStream out = new DataOutputStream(hpCon.getOutputStream());
           String toTranslatetxt = "шындықпен";
           String postData = "text="+URLEncoder.encode(toTranslatetxt,StandardCharsets.UTF_8).replace("+","%20")
                   +"&options=4";

           System.out.println(postData);
 //          out.writeBytes("text=have%20a%20good%20day&options=4");
       //    out.writeBytes("text=%D1%88%D1%8B%D0%BD%D0%B4%D1%8B%D2%9B%20%D0%B0%D0%BB%D1%83&options=4");
           out.writeBytes(postData);

           System.out.println("HttpURLConnection hpCon = (HttpURLConnection) hp.openConnection();");
///

           ContentLength = hpCon.getContentLength();
            Map<String, List<String>> gf = hpCon.getHeaderFields();
            gf.forEach((a,b) -> {
                Iterator<String> sdf = b.listIterator();
                while (sdf.hasNext())
                    System.out.println(String.format("%15s\t%s",a,sdf.next()));

           });
           System.out.println(hpCon.getContent().toString()); ;


           InputStream asd = hpCon.getInputStream();
           byte[] buf = asd.readAllBytes();
           String Sozdik = new String(buf, StandardCharsets.UTF_8);

           System.out.println(Sozdik);

           JsonNode dsa = new ObjectMapper().readTree(Sozdik);
           JsonNode dsaPath = dsa.findPath("text");
           dsaPath.forEach(element->{
               System.out.println(element.getTextValue());
               ;
           });
           System.out.println();

           System.exit(0);


           JsonNode translation = dsa.findPath("translation");
           JsonNode examples = dsa.findPath("examples");
           System.out.println(translation.findValue("text").getTextValue()) ;
           examples.forEach(exmpl->{
               System.out.println("*********************");
               System.out.println(exmpl.findValue("src").getTextValue());
               System.out.println(exmpl.findValue("dst").getTextValue());
           });





           System.exit(1);


//           for (int i = 0; i < buf.length; i++) {
//               bufa.append((char) buf[i]);
//               gif.add(buf[i]);
//           }


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


    }
}
