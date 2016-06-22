package derpina.controllers;

import derpina.ImageFinder;
import derpina.Urls;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PostsController implements Initializable{

    private boolean isloading;

    @FXML
    private Text title;

    @FXML
    private VBox postsList;

    @FXML
    private ScrollPane scrollPane;

    public PostsController(){
//        ImageFinder.setBaseUrl(Urls.get(category));
        /*scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollBarPolicy.NEVER);*/
//        scrollPane.lookup(".scroll-bar").setVisible(false);
//        ImageFinder.setBaseUrl(Urls.get("wtf"));
    }

    @FXML
    private void handleScrolling(ScrollEvent e){
//        System.out.println(scrollPane.getVvalue());
        if(scrollPane.getVvalue() == 1.0 && !isloading) {
            isloading = true;
            List<ImageView> imgs = getNewPosts();
            postsList.getChildren().addAll(imgs);
            scrollPane.setVvalue(0.8);
            isloading = false;
        }
    }

    private static List<ImageView> getNewPosts(){
        List<ImageView> res = new ArrayList<>();
        List<String> ids = ImageFinder.getNext();

        for(String id : ids){
            System.out.println(id);
            ImageView img = new ImageView("http://img-9gag-fun.9cache.com/photo/" + id + "_700b.jpg");
//            ImageView img = new ImageView(ImageFinder.getBaseUrl() + "/?id=" + id);
//            System.out.println(ImageFinder.getBaseUrl() + "/?id=" + id);
            res.add(img);
        }

        return res;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ImageFinder.setBaseUrl(Urls.get("wtf"));
        List<ImageView> imgs = getNewPosts();
        postsList.getChildren().addAll(imgs);
        System.out.println("ok");
    }
}
