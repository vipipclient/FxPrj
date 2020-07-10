package sample;

import org.codehaus.jackson.map.ext.DOMSerializer;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.util.*;

public  class AppSettings {
    private HashMap fHashMap;
    private static AppSettings SINGLETON;
    private AppSettings(){
        fHashMap = new HashMap();
    }
    static {
        SINGLETON = new AppSettings();
    }
    //get collection
    public static HashMap getSettingsHashMap(){
            return SINGLETON.fHashMap;
    }
    // Извлечение объекта из коллекции
    public static Object get(String key) {
        return SINGLETON.fHashMap.get(key);
    }

    // Извлечение объекта из коллекции
// при отсутствии данных возвращается значение по умолчанию
    public static Object get(String key, Object deflt) {
        Object obj = SINGLETON.fHashMap.get(key);
        if (obj == null) {
            return deflt;
        } else {
            return obj;
        }
    }

    // Для упрощения извлечения данных типа int
    public static int getInt(String key, int deflt) {
        Object obj = SINGLETON.fHashMap.get(key);
        if (obj == null) {
            return deflt;
        } else {
            return Integer.valueOf((String) obj);
        }
    }

    // Добавление объекта в коллекцию
    public static void put(String key, Object data) {
        // prevent null values. Hasmap allow them
        if (data == null) {
            throw new IllegalArgumentException();
        } else {
            SINGLETON.fHashMap.put(key, data);
        }
    }

    public static boolean save(File file) throws Exception {
        // Создаем новое DOM дерево
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        // Создается дерево DOM документа из файла
        Document document = documentBuilder.parse("conf.xml");


        Element root = document.getDocumentElement();
//        Element propertiesElement = document.createElement("properties");
//        root.appendChild(propertiesElement);

        NodeList propertiesNL = document.getDocumentElement().getChildNodes();
        if (propertiesNL != null) {
            for (int i = 0; (i < propertiesNL.getLength()); i++) {
                if (propertiesNL.item(i).getNodeName().equals("properties")) {
                    NodeList propertyList = propertiesNL.item(i).getChildNodes();
                    for (int j = 0; j < propertyList.getLength(); j++) {
                        NamedNodeMap attributes = propertyList.item(j).getAttributes();
                        if (attributes != null) {
                            Node n = attributes.getNamedItem("key");
                            NodeList childs = propertyList.item(j).getChildNodes();
                            if (childs != null) {
                                for (int k = 0; k < childs.getLength(); k++) {
                                    if (childs.item(k).getNodeType() == Node.TEXT_NODE) {

                                        childs.item(k).setNodeValue(AppSettings.get(n.getNodeValue().toString()).toString());
//                                        put(n.getNodeValue(), childs.item(k).getNodeValue());
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }
        Set set = SINGLETON.fHashMap.keySet();


        if (set != null) {
            for (Iterator iterator = set.iterator(); iterator.hasNext(); ) {
                String key = iterator.next().toString();
                // get setting elements

//                Element propertyElement = document.createElement("property");
//                propertyElement.setAttribute("key", key);
//                Text nameText = document.createTextNode(get(key).toString());
//                propertyElement.appendChild((Node) nameText);
//                propertiesElement.appendChild(propertyElement);
            }
        }
//        // Сериализируем DOM дерево в файл
//        DOMSerializer serializer = new DOMSerializer();
        writeDocument(document);
        return true;
    }
    public static boolean load(File file) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(file);
        if (doc == null) {
            throw new NullPointerException();
        }
        NodeList propertiesNL = doc.getDocumentElement().getChildNodes();
        if (propertiesNL != null) {
            for (int i = 0; (i < propertiesNL.getLength()); i++) {
                if (propertiesNL.item(i).getNodeName().equals("properties")) {
                    NodeList propertyList = propertiesNL.item(i).getChildNodes();
                    for (int j = 0; j < propertyList.getLength(); j++) {
                        NamedNodeMap attributes = propertyList.item(j).getAttributes();
                        if (attributes != null) {
                            Node n = attributes.getNamedItem("key");
                            NodeList childs = propertyList.item(j).getChildNodes();
                            if (childs != null) {
                                for (int k = 0; k < childs.getLength(); k++) {
                                    if (childs.item(k).getNodeType() == Node.TEXT_NODE) {
                                        put(n.getNodeValue(), childs.item(k).getNodeValue());
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }
    ArrayList<String> settings;
    Map<String,String> settingsMap;
    public static String xmlLode() throws FileNotFoundException {
        InputStream xml = new FileInputStream("conf.xml");
        // Сщоздать источник для транформации из потоков
        StreamSource xmlSource = new StreamSource(xml);
        try {
            // Создается построитель документа
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Создается дерево DOM документа из файла
            Document document = documentBuilder.parse("conf.xml");

            // Получаем корневой элемент
            Node root = document.getDocumentElement();

            System.out.println("List of settings:");
            System.out.println();
            // Просматриваем все подэлементы корневого - т.е. книги
            NodeList books = root.getChildNodes();
            for (int i = 0; i < books.getLength(); i++) {
                Node book = books.item(i);

                // Если нода не текст, то это книга - заходим внутрь
                if (book.getNodeType() != Node.TEXT_NODE) {
                    System.out.println("Main");
                    System.out.println("book.getNodeName() : "+book.getNodeName());

                    NodeList bookProps = book.getChildNodes();
                    for(int j = 0; j < bookProps.getLength(); j++) {
                        Node bookProp = bookProps.item(j);

                        // Если нода не текст, то это один из параметров книги - печатаем
                        if (bookProp.getNodeType() != Node.TEXT_NODE) {
                            System.out.println("Children");
                            System.out.println("book.getNodeName() : "+book.getNodeName());

                            NamedNodeMap attrib;
                            attrib =  bookProp.getAttributes();
                            for(int k = 0;k < attrib.getLength();k++)
                                if (attrib.item(k).getNodeName() == "webLink") {
                                    attrib.item(k).setTextContent("XAXAXAXAXXAXA");
                                    // Сама книга <Book>


                                    // Записываем XML в файл
                                    save(new File("df"));
                                    //writeDocument(document);
                                    return attrib.item(k).getTextContent();
                                }

                        }
                    }

                }
            }

        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (SAXException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private static void writeDocument(Document document) throws TransformerFactoryConfigurationError {
        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            FileOutputStream fos = new FileOutputStream("conf.xml");
            StreamResult result = new StreamResult(fos);
            tr.transform(source, result);
        } catch (TransformerException | IOException e) {
            e.printStackTrace(System.out);
        }
    }

}
