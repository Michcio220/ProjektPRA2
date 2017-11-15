import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import hibernate.klasy.Band;
import hibernate.klasy.MyMObjectsCreator;
import org.apache.log4j.Logger;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class JacksonSerialization {

    final static Logger logger = Logger.getLogger(JacksonSerialization.class);

    public static void serialiazeDemo(ObjectMapper mapper, String fileSuffix) throws IOException{

        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.registerModule(new JodaModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);;

        MyMObjectsCreator objectsCreator = new MyMObjectsCreator();
        Band band = objectsCreator.getBand();

        List<Band> bands = objectsCreator.getBands();
        mapper.writeValue(new File("result." + fileSuffix), bands);
        String bandsListSerialized = mapper.writeValueAsString(bands);
        System.out.println(bandsListSerialized);

    }

    public static void deserializeDemo(ObjectMapper mapper, String fileSuffix) throws IOException {

        InputStream bandIs = JacksonSerialization.class.getClassLoader().
                getResourceAsStream("onemusician." + fileSuffix);
        Band deserializedBand = mapper.readValue(bandIs, Band.class);
        String modifiedSerialzied = mapper.writeValueAsString(deserializedBand);
        logger.info("Printing modified re-serialized band to" + fileSuffix);
        System.out.println(modifiedSerialzied);

        InputStream bandIs2 = JacksonSerialization.class.getClassLoader().
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
