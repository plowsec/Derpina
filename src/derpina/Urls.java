package derpina;

import java.util.HashMap;

/**
 * A simple class made to store the urls that will be used
 */
public class Urls {
    private static final HashMap<String, String> urls = new HashMap<>();

    /**
     * Has to be run before any call to get(), initializes the urls
     */
    public static void load(){
        urls.put("hot", "http://9gag.com/hot");
        urls.put("trending", "http://9gag.com/trending");
        urls.put("fresh", "http://9gag.com/fresh");
        urls.put("gif", "http://9gag.com/gif");
        urls.put("cosplay", "http://9gag.com/cosplay");
        urls.put("nsfw", "http://9gag.com/nsfw");
        urls.put("video", "http://9gag.com/movie-tv");
        urls.put("comic", "http://9gag.com/comic");
        urls.put("geeky", "http://9gag.com/gaming");
        urls.put("girl", "http://9gag.com/girl");
        urls.put("wtf", "http://9gag.com/wtf");
        urls.put("anime", "http://9gag.com/anime-manga");
    }

    /**
     * Gives the corresponding url
     * @param name The name of the url
     * @return The url
     */
    public static String get(String name){
        return urls.get(name);
    }
}
