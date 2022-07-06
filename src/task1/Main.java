package task1;

import javax.xml.parsers.ParserConfigurationException;

public class Main {

    public static void main(String[] args) throws ParserConfigurationException {
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

        // DOMparsing doMparsing = new DOMparsing();
        //  Sonnet sonnet = doMparsing.parse();
       SaxParsering saxParsering = new SaxParsering();
       Sonnet sonnet = saxParsering.parse();
        System.out.println("sonnet: " + sonnet.toString());

    }
}



