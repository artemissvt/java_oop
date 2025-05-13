package assignments.assignment2;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
public class RandomWord {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://random-word-api.herokuapp.com/word");
            InputStreamReader reader = new InputStreamReader(url.openStream());
            JSONParser parser = new JSONParser();
            JSONArray json = (JSONArray) parser.parse(reader);
            System.out.println(json.get(0));
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }
}
