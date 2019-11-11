package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Test {

    public static void main(String[] args) {

        try {
            URL url = new URL("file:///Users/steve/Documents/javafx/search_engine_test/src/sample/Scooter-normals.obj");
            System.out.println("file "+url.getFile());
            System.out.println("proto "+url.getProtocol());
            System.out.println("port "+url.getPort());
            System.out.println("toString "+url.toString());
            System.out.println("path "+url.getPath());
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String inputLine;
            while((inputLine = in.readLine()) != null)
                System.out.println(inputLine);
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }//main
}//class
