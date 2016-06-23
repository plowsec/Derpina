package derpina.controllers;


import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static derpina.Config.*;
public class DetailController {

    public DetailController(String id){
        Pane root = new Pane();
        ImageView post = new ImageView(IMG_BASE_URL + id+  IMG_SMALL_ID + ".jpg");
        post.setFitWidth(600);
        VBox rightBox = new VBox();
        VBox commentsBox = new VBox();
        rightBox.setPrefWidth(500);
        Text title = new Text("Top commments");
        title.setFont(Font.font("system", FontWeight.BOLD, 30));
        rightBox.setMargin(title, new Insets(20,0,0,30));

        commentsBox.setPadding(new Insets(20, 0, 0, 30));

        HBox author = new HBox();
        author.setSpacing(20);
        HBox comment = new HBox();

        Text at = new Text("bedondy");
        at.setFont(Font.font("system", FontWeight.BOLD, 13));

        Text pt = new Text("1337 pts");
        Text cm = new Text("Heard it all in Steve Irwin's voice.");

        author.getChildren().addAll(at, pt);
        comment.getChildren().add(cm);

        commentsBox.getChildren().add(author);
        commentsBox.getChildren().add(comment);

        rightBox.getChildren().add(commentsBox);

        root.getChildren().addAll(post, rightBox);

        Stage dialog = new Stage();
        dialog.setMinWidth(1100);
        dialog.setMinHeight(742);
        dialog.initStyle(StageStyle.UTILITY);
        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.show();
    }
}
