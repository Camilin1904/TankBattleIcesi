package game.Read;
import game.ui.FrontlineDuel;

import java.io.*;

import java.util.ArrayList;

import com.google.gson.Gson;
import game.ui.PlayerS;

public class ToJsonReader {


    public static ArrayList<PlayerS> readP() {
        ArrayList<PlayerS> people = new ArrayList<>();
        try {
            String path = FrontlineDuel.class.getResource("players.txt").getPath();
            File file = new File(path);
            //File file = new File(uri);
            FileInputStream fis = new FileInputStream(file);

            BufferedReader reader = new  BufferedReader(new InputStreamReader(fis));

            String json = "";
            String line;
            if((line=reader.readLine())!=null){
                json= line;
            }
            fis.close();


            Gson gson = new Gson();
            PlayerS[] peopleFromJson = gson.fromJson(json, PlayerS[].class);

        if(peopleFromJson != null) {
            for (PlayerS p : peopleFromJson) {
                people.add(p);
            }
            return people;
        }else {
            return null;
        }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return people;

	}
    


}