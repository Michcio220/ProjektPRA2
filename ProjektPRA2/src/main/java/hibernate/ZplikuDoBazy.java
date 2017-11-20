package hibernate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.IOException;


public class ZplikuDoBazy{

    public static void main(String [] args) throws IOException {

        ObjectMapper xmlmapper = new XmlMapper();
        MyMObjectsCreator myMObjectsCreator = new MyMObjectsCreator();
        myMObjectsCreator.zapiszDoPliku(xmlmapper,"xml");
        ObjectMapper jsonMapper = new ObjectMapper();
        MyMObjectsCreator myMObjectsCreator1 = new MyMObjectsCreator();
        myMObjectsCreator1.zapiszDoPliku(jsonMapper,"json");
    }
}