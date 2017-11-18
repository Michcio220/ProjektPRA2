package hibernate;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import hibernate.klasy.Band;
import org.apache.log4j.Logger;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Jserialization {

    final static Logger logger = Logger.getLogger(Jserialization.class);

    public static void serialiazeDemo(ObjectMapper mapper, String fileSuffix) throws IOException{

        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.registerModule(new JodaModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        MyMObjectsCreator objectsCreator = new MyMObjectsCreator();

        List<Band> bands = objectsCreator.getBands();
        String bandsList = mapper.writeValueAsString(bands);
        mapper.writeValue(new File("result." +fileSuffix),bands);
        System.out.println(bandsList);


    }

    public static void deserializeDemo(ObjectMapper mapper, String fileSuffix) throws IOException {

        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.registerModule(new JodaModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        InputStream bandIs2 = Jserialization.class.getClassLoader().
                getResourceAsStream("musician." + fileSuffix);
        List<Band> bands = mapper.readValue(bandIs2,new TypeReference<List<Band>>(){});
        String json = mapper.writeValueAsString(bands);
        System.out.println(json);

    }

    public static void main(String [] args) throws IOException{


        ObjectMapper jsonMapper = new ObjectMapper();
        jsonMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
        //serialiazeDemo(jsonMapper, "json");
        deserializeDemo(jsonMapper, "json");



    }
}
