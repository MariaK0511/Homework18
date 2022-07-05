package task1;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Main {
    private static final String TAG_AUTHOR = "author";
    private static final String TAG_TITLE = "title";
    private static final String TAG_LINES = "lines";


    private static final String TAG_LINE = "line";

    private static final String TAG_LAST_NAME = "lastName";
    private static final String TAG_FIRST_NAME = "firstName";
    private static final String TAG_NATIONALITY = "nationality";
    private static final String TAG_YEAR_OF_BIRTH = "1564";
    private static final String TAG_YEAR_OF_DEATH = "1616";


    public static void main(String[] args) {
        /*
        1. Написать программу для парсинга xml документа.+
Программа на вход получает строку к папке, где находится документ.+
Распарсить нужно только один документа - соответственно,
предусмотреть различные проверки, например на то, что в папке нет
файлов, или в папке несколько документов формата xml и другие возможные проверки.+
Необходимо распарсить xml документ и содержимое тегов line записать в другой документ.
Название файла для записи должно состоять из значений тегов и имеет
вид: <firstName>_<lastName>_<title>.txt
Задание со *
Дополнительно реализовать следующий функционал:
если с консоли введено значение 1 - распарсить документ с помощью SAX
если с консоли введено значение 2 - распарсить документ с помощью DOM.
         */
//        File directory = new File("Task1_docs");
//        if (!directory.exists()) {
//            directory.mkdir();
//        }
//
//
//        File[] listOfFiles = directory.listFiles();//выводит имена файлов
//        System.out.println("File in the directory: " + Arrays.stream(directory.listFiles()).count());
//        System.out.println("File in the directory with xml extension: " + Arrays.stream(directory.listFiles()).
//                filter(file -> file.getName().endsWith(".xml")).count());
//        System.out.println("File in the directory with xml extension: " + Arrays.stream(directory.listFiles()).
//                filter(file -> file.getName().endsWith(".txt")).count());
//        if (listOfFiles != null) {
//            int count = 0;
//            int countValidFiles = 0;
//            int countInvalidFiles = 0;
//
//            for (File file : listOfFiles) {
//                if (file.canRead()) {
//                    countValidFiles++;
//                }else {
//                    countInvalidFiles++;
//                }
//            }
//            System.out.println("valid files: " + countValidFiles);
//            System.out.println("invalid files: " + countInvalidFiles);
//        }

        Sonnet sonnet = new Sonnet();
        Document doc;
        try {
            doc = buildDocument();
        } catch (Exception e) {
            System.out.println("Open parsing error" + e.toString());
            return;
        }

        Node rootNode = doc.getFirstChild();
        NodeList rootChild = rootNode.getChildNodes();

        Node mainAuthor = null;
        String titleNode = "";
        Node mainLines = null;

        for (int i = 0; i < rootChild.getLength(); i++) {
            if (rootChild.item(i).getNodeType() != Node.ELEMENT_NODE) { //check type element
                continue;
            }
            switch (rootChild.item(i).getNodeName()) { //method item give one element to i
                case TAG_AUTHOR: {
                    mainAuthor = rootChild.item(i);
                    break;
                }
                case TAG_TITLE: {
                    titleNode = rootChild.item(i).getTextContent();
                    break;
                }
                case TAG_LINES: {
                    mainLines = rootChild.item(i);
                    break;
                }
            }
        }
        if (mainAuthor == null) {
            return;
        }
        if (mainLines == null) {
            return;
        }

        List<Author> authorList = parsAuthor(mainAuthor);
        List<Lines>linesList=parsLines(mainLines);
        sonnet.setTitle(titleNode);
        sonnet.setAuthor(authorList);
        sonnet.setLines(linesList);
        System.out.println("Sonnet: " + sonnet);
    }

    private static Document buildDocument() throws Exception {
        //this piece of code opens file for parsing
        File file = new File("C:\\Users\\ААА\\IdeaProjects\\Homework18\\Task1_docs\\title.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        return dbf.newDocumentBuilder().parse(file);
    }


    private static List<Author> parsAuthor(Node mainAuthor) { //&
        List<Author> authorList = new ArrayList<>();
        NodeList authorChild = mainAuthor.getChildNodes();

        for (int i = 0; i < authorChild.getLength(); i++) {
            if (authorChild.item(i).getNodeType() != Node.ELEMENT_NODE) { //check type i
                continue;
            }
            if (!authorChild.item(i).getNodeName().equals(TAG_LINE)) {
                continue;
            }
            Author author = parsAuthorElements(authorChild.item(i));
            authorList.add(author);//&
        }
        return authorList;
    }


    public static Author parsAuthorElements(Node authorElements) {
        String lastName = "";
        String firstName = "";
        String nationality = "";
        int yearOfBirth = 0;
        int yearOfDeath = 0;

        NodeList authorChild = authorElements.getChildNodes();
        for (int j = 0; j < authorChild.getLength(); j++) {
            if (authorChild.item(j).getNodeType() != Node.ELEMENT_NODE) { //check type element
                continue;
            }

            switch (authorChild.item(j).getNodeName()) {
                case TAG_LAST_NAME: {
                    lastName = authorChild.item(j).getTextContent();
                    break;
                }
                case TAG_FIRST_NAME: {
                    firstName = authorChild.item(j).getTextContent();
                    break;
                }
                case TAG_NATIONALITY: {
                    nationality = authorChild.item(j).getTextContent();
                    break;
                }


                    case TAG_YEAR_OF_BIRTH: {
                        yearOfBirth = Integer.parseInt(authorChild.item(j).getTextContent());
                        break;
                    }
                    case TAG_YEAR_OF_DEATH: {
                        yearOfDeath = Integer.parseInt(authorChild.item(j).getTextContent());
                        break;
                    }
            }
        }
        Author author = new Author(lastName, firstName, nationality, yearOfBirth, yearOfDeath);
        return author;
    }

    private static List<Lines> parsLines(Node lineNode) {
        List<Lines> linesList = new ArrayList<>();
        NodeList linesChild = lineNode.getChildNodes();

        for (int i = 0; i < linesChild.getLength(); i++) { //get all child
            if (linesChild.item(i).getNodeType() != Node.ELEMENT_NODE) { //check type element
                continue;
            }
            if (!linesChild.item(i).getNodeName().equals(TAG_LINE)) {
                continue;
            }
            Lines lines = parsLine(linesChild.item(i));
            linesList.add(lines);
        }
        return linesList;
    }

    private static Lines parsLine(Node lineNode) {
        String line = "";

        NodeList lineChild = lineNode.getChildNodes();
        for (int j = 0; j < lineChild.getLength(); j++) { //each inner element
            if (lineChild.item(j).getNodeType() != Node.ELEMENT_NODE) { //check type element
                continue;
            }
            switch (lineChild.item(j).getNodeName()) {
                case TAG_LINE: {
                    line = lineChild.item(j).getTextContent();
                    break;
                }
            }
        }
        Lines lines = new Lines(line);
        return lines;
    }


//        BufferedWriter bufferedWriter;
//        HashMap<String, String> people1 = new HashMap<>();
//        try {
//            bufferedWriter = new BufferedWriter(new FileWriter(firstName + "_" + lastName + "_" + "_" + title + ".txt"));
//
//            bufferedWriter.close();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        return titleNode;
    //  }






}



