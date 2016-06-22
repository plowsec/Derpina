package derpina.controllers;

import derpina.ImageFinder;
import derpina.Urls;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PostsController implements Initializable{

    private boolean isloading;
    private static final String IMG_BASE_URL = "http://img-9gag-fun.9cache.com/photo/";
    private static final String IMG_BIG_ID = "_700b";

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

        if(scrollPane.getVvalue() == 1.0 && !isloading) {
            isloading = true;
            List<HBox> imgs = getNewPosts();
            postsList.getChildren().addAll(imgs);
            scrollPane.setVvalue(0.8);
            isloading = false;
        }
    }

    private List<HBox> getNewPosts(){
        List<HBox> res = new ArrayList<>();
        List<String> ids = ImageFinder.getNext();

        HBox[] hboxes = new HBox[3];
        for(int i = 0; i < hboxes.length ; i++) {
            hboxes[i] = new HBox();
            res.add(hboxes[i]);
        }
        for(int i = 0 ; i < ids.size() ; i++){

            hboxes[i%3].setPadding(new Insets(50,50,0,50));
            hboxes[i%3].setPrefWidth(250);
            hboxes[i%3].setPrefHeight(150);

            System.out.println(ids.get(i));
            ImageView img = new ImageView(IMG_BASE_URL + ids.get(i) +  IMG_BIG_ID + ".jpg");
            img.setFitWidth(389);
            img.setFitHeight(292);
            hboxes[i%3].setMargin(img, new Insets(0,50,0,0));
//            ImageView img = new ImageView(ImageFinder.getBaseUrl() + "/?id=" + id);
//            System.out.println(ImageFinder.getBaseUrl() + "/?id=" + id);
            //res.add(img);
            hboxes[i%3].getChildren().add(img);

        }

        return res;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ImageFinder.setBaseUrl(Urls.get("wtf"));
        List<HBox> imgs = getNewPosts();
        postsList.getChildren().addAll(imgs);
        System.out.println("ok");
    }
}
