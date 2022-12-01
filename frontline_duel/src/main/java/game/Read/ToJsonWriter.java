package game.Read;


import game.ui.FrontlineDuel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import com.google.gson.Gson;
import game.ui.PlayerS;

public class ToJsonWriter {

    public static void Write(ArrayList<PlayerS> people) {
        Gson gson = new Gson();


        String json = gson.toJson(people);


        try {

            File file = new File(".\\frontline_duel\\src\\main\\resources\\game\\ui\\players.txt");
            FileOutputStream fos = new FileOutputStream(file);
            fos.write( json.getBytes(StandardCharsets.UTF_8) );
            fos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
