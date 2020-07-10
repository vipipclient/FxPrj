package sample;

import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.util.Arrays;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
class someObj{
    int cnt = 0;
    someObj(){}
    public String toString() {
        return Integer.toString(++cnt);
    }
}
public class XslConverter
{
    public String xmlToString(String xmlFile, String xslFile) throws Exception {
        // Открыть файлы в виде потоков
        InputStream xml = new FileInputStream(xmlFile);
        InputStream xsl = new FileInputStream(xslFile);
        // Сщоздать источник для транформации из потоков
        StreamSource xmlSource = new StreamSource(xml);
        StreamSource stylesource = new StreamSource(xsl);

        // Создать байтовый поток для результата
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        // СОздать приемноик для результатат из байтового потока
        StreamResult xmlOutput = new StreamResult(bos);
        // Создать трансформатор и выполнить трансформацию
        Transformer transformer = TransformerFactory.newInstance().newTransformer(stylesource);
        transformer.transform(xmlSource, xmlOutput);

        // вернуть результат в виде строки
        return bos.toString();
    }

    public static void main(String[] arg) throws IOException {

        AppSettings.xmlLode();

        XslConverter c = new XslConverter();

        final String xml = "BookCatalog.xml";
        final String xsl = "BookCatalog.xsl";
        try {
            String result = c.xmlToString(xml, xsl);
            try(FileWriter out = new FileWriter("xls.html");){
                out.write(result);
            }catch (IOException e){e.printStackTrace();}
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
}