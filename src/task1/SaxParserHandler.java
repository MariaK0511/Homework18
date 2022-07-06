package task1;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class SaxParserHandler extends DefaultHandler {
    private static final String TAG_AUTHOR = "author";
    private static final String TAG_TITLE = "title";
    private static final String TAG_LINES = "lines";
    private static final String TAG_LINE = "line";
    private static final String TAG_LAST_NAME = "lastName";
    private static final String TAG_FIRST_NAME = "firstName";
    private static final String TAG_NATIONALITY = "nationality";
    private static final String TAG_YEAR_OF_BIRTH = "1564";
    private static final String TAG_YEAR_OF_DEATH = "1616";


    // there is def logic which parsing doc
    Sonnet sonnet = new Sonnet();
    List<Lines>linesList = new ArrayList<>();
    List<Author>authorList = new ArrayList<>();

    private String currentTagName;
    private boolean isLines = false;
    private boolean isLine = false;
    private boolean isAuthor=false;


    private boolean isLastName = false;
    private boolean isFirstName= false;
    private boolean isNationality = false;
    private boolean isYearOfBirth = false;
    private boolean isYearOfDeath = false;

    private String lastName;
    private String firstName;
    private String nationality;
    private int yearOfBirth;
    private int yearOfDeath;
private String line;
    public Sonnet getSonnet() {
        return sonnet;
    }

    @Override
    public void startDocument() throws SAXException {
    }

    @Override
    public void endDocument() throws SAXException {
        sonnet.setLines(linesList);
        sonnet.setAuthor(authorList);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //  System.out.println("Start element " + qName);//qName - name of tag
        currentTagName = qName;
        if (currentTagName.equals(TAG_AUTHOR)) {
            isAuthor = true;
        }
        if (currentTagName.equals(TAG_LINES)) {
            isLines = true;
        }else if (currentTagName.equals(TAG_LINE)) {
            isLine = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        //  System.out.println("End element "+ qName);
        if (currentTagName!=null){
            if ( currentTagName.equals(TAG_AUTHOR)) {
                isAuthor = false;
                Author author=new Author(lastName,firstName,nationality,yearOfBirth,yearOfDeath);
            }
            if ( currentTagName.equals(TAG_LINES)) {
                isLines = false;
            } else if (currentTagName.equals(TAG_LINE)) {
                isLine=false;
                Lines lines1=new Lines(line);
                linesList.add(lines1);
            }
        }

        currentTagName = null;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException { //get content of tag
        //    System.out.println("Characters "+ new String(ch,start,length));
        if (currentTagName == null) {
            return;
        }
        if (isLastName && isFirstName && isNationality && isYearOfBirth && isYearOfDeath && isAuthor ){
            if (currentTagName.equals(TAG_AUTHOR)){
                lastName=new String(ch, start, length);
                firstName=new String(ch, start, length);
                nationality=new String(ch, start, length);
                Integer integer = Integer.valueOf(new String(ch, start, length));
                yearOfBirth= integer;
                yearOfDeath= integer;
            }
        }
        if (!isLines && currentTagName.equals(TAG_TITLE)) {
            sonnet.setTitle(new String(ch, start, length));
        }
        if (isLines&& isLine){
            if (currentTagName.equals(TAG_LINE)){
line=new String(ch, start, length);
            }
        }
    }
}
