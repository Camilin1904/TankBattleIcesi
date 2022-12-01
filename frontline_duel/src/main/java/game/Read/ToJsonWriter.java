package game.Read;


import game.model.PlayerS;
import game.ui.FrontlineDuel;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ToJsonWriter {

    /*public static void Write() {
        Gson gson = new Gson();

        ArrayList<PlayerS> people = new ArrayList<>();

        String json = gson.toJson(people);


        try {

            String uri = "file:"+ FrontlineDuel.class.getResource("players.txt").getPath();
            FileOutputStream fos = new FileOutputStream(uri);
            fos.write( json.getBytes(StandardCharsets.UTF_8) );
            fos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/
}
