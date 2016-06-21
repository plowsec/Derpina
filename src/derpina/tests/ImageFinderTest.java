package derpina.tests;

import derpina.ImageFinder;
import derpina.Urls;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class ImageFinderTest {

    private static Random random = new Random();

    public static void main(String[] args){
        Urls.load();
        ImageFinder.setBaseUrl(Urls.get("wtf"));
        List<String> urls;
        HashSet<String> set = new HashSet<>();

        int iter = random.nextInt(10);

        for(int i = 0; i < iter; i++){
            urls = ImageFinder.getNext();
            for(String elem : urls){
                set.add(elem);
            }
        }

        if(set.size() != iter*(10-1)){
            throw new RuntimeException("Urls not correctly found");
        }

        System.out.println("ImageFinder test successfully passed !");


    }
}
