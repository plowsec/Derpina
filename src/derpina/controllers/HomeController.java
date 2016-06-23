package derpina.controllers;


import derpina.Display;
import derpina.Urls;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.System.exit;

public class HomeController implements Initializable{

    private Stage stage;

    @FXML
    private ImageView hotMenu;
    @FXML
    private Text hotLabel;

    @FXML
    private ImageView trendingMenu;
    @FXML
    private Text trendingLabel;

    @FXML
    private ImageView freshMenu;
    @FXML
    private Text freshLabel;

    @FXML
    private ImageView gifMenu;
    @FXML
    private Text gifLabel;

    @FXML
    private ImageView cosplayMenu;
    @FXML
    private Text cosplayLabel;

    @FXML
    private ImageView nsfwMenu;
    @FXML
    private Text nsfwLabel;

    @FXML
    private ImageView videoMenu;
    @FXML
    private Text videoLabel;

    @FXML
    private ImageView comicMenu;
    @FXML
    private Text comicLabel;

    @FXML
    private ImageView geekyMenu;
    @FXML
    private Text geekyLabel;

    @FXML
    private ImageView girlMenu;
    @FXML
    private Text girlLabel;

    @FXML
    private ImageView wtfMenu;
    @FXML
    private Text wtfLabel;

    @FXML
    private ImageView animeMenu;
    @FXML
    private Text animeLabel;

    @FXML
    public void handleSectionSelection(MouseEvent e) {
        String url = null;
        Object source = e.getSource();
        if(source.equals(hotLabel) || source.equals(hotMenu)) url = Urls.get("hot");
        else if(source.equals(trendingLabel) || source.equals(trendingMenu)) url = Urls.get("trending");
        else if(source.equals(freshLabel) || source.equals(freshMenu)) url = Urls.get("fresh");
        else if(source.equals(gifLabel) || source.equals(gifMenu)) url = Urls.get("gif");
        else if(source.equals(cosplayLabel) || source.equals(cosplayMenu)) url = Urls.get("cosplay");
        else if(source.equals(nsfwLabel) || source.equals(nsfwMenu)) url = Urls.get("nsfw");
        else if(source.equals(videoLabel) || source.equals(videoMenu)) url = Urls.get("video");
        else if(source.equals(comicLabel) || source.equals(comicMenu)) url = Urls.get("comic");
        else if(source.equals(geekyLabel) || source.equals(geekyMenu)) url = Urls.get("geeky");
        else if(source.equals(girlLabel) || source.equals(girlMenu)) url = Urls.get("girl");
        else if(source.equals(wtfMenu) || source.equals(wtfLabel)) url = Urls.get("wtf");
        else if(source.equals(animeLabel) || source.equals(animeMenu)) url = Urls.get("anime");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/derpina/PostScreen.fxml"));
            BorderPane postsView = loader.load();
            PostsController controller = loader.getController();
            controller.init(url);
            stage.setScene(new Scene(postsView));
        } catch (IOException e1) {
            Display.error("Une erreur s'est produite, l'application va quitter...");
            exit(10);
            e1.printStackTrace();
        }
    }

    public void setStage(Stage newStage){
        stage = newStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
