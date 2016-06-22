package derpina.controllers;

import derpina.ImageFinder;
import derpina.Urls;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PostsController implements Initializable{

    private boolean isloading;
    private static final String IMG_BASE_URL = "http://img-9gag-fun.9cache.com/photo/";
    private static final String IMG_BIG_ID = "_700b";
    private static final String IMG_SMALL_ID = "_460s";
    private static final int TILE_WIDTH = 389;
    private static final int TILE_HEIGHT = 389;
    private static final int TILE_VSPACE = 100;
    private static final int TILE_PER_ROW = 3;


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
        Rectangle2D croppedPortion = new Rectangle2D(0, 0, TILE_WIDTH, TILE_HEIGHT);
        HBox[] hboxes = new HBox[3];
        for(int i = 0; i < hboxes.length ; i++) {
            hboxes[i] = new HBox();
            res.add(hboxes[i]);
        }
        for(int i = 0 ; i < ids.size() ; i++){

            hboxes[i%3].setPadding(new Insets(0,50,50,50));
            hboxes[i%3].setPrefWidth(250);
            hboxes[i%3].setPrefHeight(150);

            System.out.println(ids.get(i));
            ImageView img = new ImageView(IMG_BASE_URL + ids.get(i) +  IMG_SMALL_ID + ".jpg");
            img.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {

                    //returns the correct index for ids.get(trcuc), but I don't remember how to create the scene dynamically.
                    int truc = (int) (mouseEvent.getSceneX()/TILE_WIDTH) + (int) (mouseEvent.getSceneY()/(TILE_HEIGHT+TILE_VSPACE)) * TILE_PER_ROW;
                    System.out.print(truc + " hello bitch " );
                    Stage dialog = new Stage();
                    dialog.initStyle(StageStyle.UTILITY);
                    Scene scene = new Scene(new Group(new Text(("coucou"))));

                    dialog.setScene(scene);
                    dialog.show();
                }
            });
            img.setFitWidth(TILE_WIDTH);
            img.setFitHeight(TILE_HEIGHT);

            //I forgot whether this is still useful or not
            if(img.getImage().getHeight()> 500) {
                img.setViewport(croppedPortion);
            }

            hboxes[i%3].setMargin(img, new Insets(0,50,0,0));
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
