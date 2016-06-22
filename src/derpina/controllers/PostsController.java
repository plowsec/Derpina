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

    private double scrolled;

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

    }

    private static List<ImageView> getNewPosts(){
        List<ImageView> res = new ArrayList<>();
        List<String> ids = ImageFinder.getNext();

        for(String id : ids){
            res.add(new ImageView(ImageFinder.getBaseUrl() + "/?id=" + id));
        }

        return res;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ImageFinder.setBaseUrl(Urls.get("wtf"));
        scrolled = 0.0;
    }
}
