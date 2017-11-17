import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hibernate.klasy.Band;
import hibernate.klasy.Utwory;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MyGsonSerlalization {

    public static void printJson() {

        Band band = new Band("Pink Floyd", 1998, 4, "Rock");
        band.addUtwor(new Utwory("Another Brick In The Wall",1982));

        Gson gson = new Gson();
        String serializedBand = gson.toJson(band);
        System.out.println(serializedBand);

        Gson gsonPretty = new GsonBuilder().setPrettyPrinting().create();
        String prettySerializeBand = gsonPretty.toJson(band);
        System.out.println(prettySerializeBand);

        Band firstBand = gson.fromJson(serializedBand, Band.class);
        System.out.println(firstBand.getNazwa() + " " + firstBand.getTyp());

        LocalDateTime now = LocalDateTime.now();
        System.out.println(now.toString());
        String serializedTime = gsonPretty.toJson(now);
        System.out.println(serializedTime);
        LocalDateTime now2 = gson.fromJson(serializedTime, LocalDateTime.class);
        System.out.println(now2.toString());

    }

    public static void main(String [ ] args) {
        printJson();
    }

}
