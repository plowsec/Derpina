package derpina.controllers;

import derpina.ImageFinder;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import java.util.ArrayList;
import java.util.List;
import static derpina.Config.*;

public class PostsController {

    private boolean isloading;

    @FXML
    private Text title;

    @FXML
    private VBox postsList;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private ImageView loading;

    @FXML
    private Rectangle veil;

    @FXML
    private void handleScrolling(ScrollEvent e) {

        if (scrollPane.getVvalue() == 1.0 && !isloading) {

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    veil.setVisible(false);
                }
            });

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

            isloading = true;

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    List<HBox> imgs = getNewPosts();
                    postsList.getChildren().addAll(imgs);
                    scrollPane.setVvalue(0.8);
                    isloading = false;
                    veil.setVisible(true);
                }
            });
        }
    }

    private List<HBox> getNewPosts() {
        List<HBox> res = new ArrayList<>();

        List<String> ids = ImageFinder.getNext();
        Rectangle2D croppedPortion = new Rectangle2D(0, 0, TILE_WIDTH, TILE_HEIGHT);
        HBox[] hboxes = new HBox[3];
        for (int i = 0; i < hboxes.length; i++) {
            hboxes[i] = new HBox();
            res.add(hboxes[i]);
        }
        for (int i = 0; i < ids.size(); i++) {

            hboxes[i % 3].setPadding(new Insets(0, 50, 50, 50));
            hboxes[i % 3].setPrefWidth(250);
            hboxes[i % 3].setPrefHeight(150);

            final String id = ids.get(i);

            System.out.println(ids.get(i));
            ImageView img = new ImageView(IMG_BASE_URL + ids.get(i) + IMG_SMALL_ID + ".jpg");
            img.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {

                new DetailController(id);
            });

            img.setFitWidth(TILE_WIDTH);
            img.setFitHeight(TILE_HEIGHT);

            //I forgot whether this is still useful or not
            /*if (img.getImage().getHeight() > 500) {
                img.setViewport(croppedPortion);
            }*/

            hboxes[i % 3].setMargin(img, new Insets(0, 50, 0, 0));
            hboxes[i % 3].getChildren().add(img);
        }
        //loading.setVisible(false);
        return res;
    }

    public void init(String url) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                ImageFinder.setBaseUrl(url);
                List<HBox> imgs = getNewPosts();
                postsList.getChildren().addAll(imgs);
           }
        });
    }
}
