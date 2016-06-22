package derpina;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A static class which allows to get a list containing 9gag post ids
 */
public class ImageFinder {

    private static String next;
    private static String baseUrl;

    /**
     * Set the base url to use
     * @param url The new url (ie. http://9gag.com/hot)
     */
    public static void setBaseUrl(String url){
        baseUrl = url;
        next = null;
    }

    public static String getBaseUrl(){
        return baseUrl;
    }

    /**
     * A custom getNext() method that allows to give the post which has to be the first
     * @param start The first post to fetch
     * @return The list containing post ids
     */
    public static List<String> getNext(String start){
        next = start;
        return getNext();
    }

    /**
     * Fetch the next post ids to display. If called several times, it will return the next unseen.
     * @return The list containing post ids
     */
    public static List<String> getNext(){
        try {
            String target = baseUrl;
            if(next != null){
                target += "/?id="+next;
            }
            URL url = new URL(target);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream())); // Connection made here
            String line;
            Pattern test = Pattern.compile("data-entry-id=\"(.*)\""); // The pattern to search on the page
            List<String> ids = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                Matcher matcher = test.matcher(line);
                if(!matcher.find()) continue;
                ids.add(matcher.group(1)); // The ids are stored here !
            }
            reader.close();
            next = ids.get(ids.size()-1);
            ids.remove(ids.size()-1);

            return ids;

        } catch (MalformedURLException e) {
            System.out.println(baseUrl);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
