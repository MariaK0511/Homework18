package task1;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class SaxParsering {
    public Sonnet parse() throws ParserConfigurationException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SaxParserHandler handler =new SaxParserHandler();
        SAXParser parser= null; //take def parser
        try {
            parser = factory.newSAXParser();
        } catch (SAXException e) {
            System.out.println("open SAX parser error" + e.toString());
   return null;
        }

        File file = new File("C:\\Users\\ААА\\IdeaProjects\\Homework18\\Task1_docs\\title.xml");
        try {
            parser.parse(file,handler);
        } catch (SAXException e) {
            System.out.println("SAX parsing error" + e.toString());
            return null;
        } catch (IOException e) {
            System.out.println("IO parsing error" + e.toString());
            return null;
        }


        return handler.getSonnet();
    }
}
