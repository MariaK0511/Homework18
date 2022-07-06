package task1;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DOMparsing {
    private static final String TAG_AUTHOR = "author";
    private static final String TAG_TITLE = "title";
    private static final String TAG_LINES = "lines";
    private static final String TAG_LINE = "line";
    private static final String TAG_LAST_NAME = "lastName";
    private static final String TAG_FIRST_NAME = "firstName";
    private static final String TAG_NATIONALITY = "nationality";
    private static final String TAG_YEAR_OF_BIRTH = "1564";
    private static final String TAG_YEAR_OF_DEATH = "1616";

    public Sonnet parse() {
        Sonnet sonnet = new Sonnet();
        Document doc;
        try {
            doc = buildDocument();
        } catch (Exception e) {
            System.out.println("Open parsing error" + e.toString());
            return null;
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
            return null;
        }
        if (mainLines == null) {
            return null;
        }

        List<Author> authorList = parsAuthor(mainAuthor);
        List<Lines> linesList = parsLines(mainLines);
        sonnet.setTitle(titleNode);
        sonnet.setAuthor(authorList);
        sonnet.setLines(linesList);
        System.out.println("Sonnet: " + sonnet);

        return sonnet;
    }


    private Document buildDocument() throws Exception {
        //this piece of code opens file for parsing
        File file = new File("C:\\Users\\ААА\\IdeaProjects\\Homework18\\Task1_docs\\title.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        return dbf.newDocumentBuilder().parse(file);
    }


    private List<Author> parsAuthor(Node mainAuthor) { //&
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


    public Author parsAuthorElements(Node authorElements) {
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

    private List<Lines> parsLines(Node lineNode) {
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

    private Lines parsLine(Node lineNode) {
        String line = "";

        NodeList lineChild = lineNode.getChildNodes();
        for (int j = 0; j < lineChild.getLength(); j++) { //each inner element
            if (lineChild.item(j).getNodeType() != Node.ELEMENT_NODE) { //check type element
                continue;
            }
            if (TAG_LINE.equals(lineChild.item(j).getNodeName())) {
                line = lineChild.item(j).getTextContent();
            }
        }
        Lines lines = new Lines(line);
        return lines;
    }
}
