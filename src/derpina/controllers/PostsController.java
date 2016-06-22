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

import java.util.ArrayList;
import java.util.List;

public class PostsController{

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
        scrollPane.lookup(".scroll-bar").setVisible(false);
    }

    @FXML
    private void handleScrolling(ScrollEvent e){
        System.out.println(postsList.getHeight());
        System.out.println(e.getDeltaY());
    }

    private static List<ImageView> getNewPosts(){
        List<ImageView> res = new ArrayList<>();
        List<String> ids = ImageFinder.getNext();

        for(String id : ids){
            res.add(new ImageView(ImageFinder.getBaseUrl() + "/?id=" + id));
        }

        return res;
    }

    public void main(String[] args){
        ImageFinder.setBaseUrl(Urls.get("wtf"));
        List<ImageView> res = getNewPosts();
    }
}
