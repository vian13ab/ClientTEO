package config;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

// Importing json packaes
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Configurations {
  

    // FFkey is used in Encryption.java
    private String ffcryptkey;

   

    public String getFfcryptkey() {
        return ffcryptkey;
    }

    public void setFfcryptkey(String ffcryptkey) {
        this.ffcryptkey = ffcryptkey;
    }




    // Method to read files from jSON file

    public void readFile() {
        JSONParser jsonParser = new JSONParser();

        try {
            FileReader json = new FileReader("src/config.json");

            Object obj = jsonParser.parse(json);
            JSONObject jsonObject = (JSONObject) obj;


            // Getting json values for KEY variables
            setFfcryptkey((String) jsonObject.get("ffcryptkey"));


        } catch (ParseException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
