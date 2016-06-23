package derpina.controllers;


import derpina.Display;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static derpina.Config.*;
import static java.lang.System.exit;

public class DetailController {

    public DetailController(String id){
        List<String> comments = fetchComments(id);
        double totalTextLength = 0;

        for(int i = 0; i < comments.size() ; i++)
            System.out.println(comments.get(i));

        Pane root = new Pane();
        ScrollPane left = new ScrollPane();
        ScrollPane right = new ScrollPane();

        ImageView post = new ImageView(IMG_BASE_URL + id+  IMG_BIG_ID + ".jpg");
        double globalHeight = post.getImage().getHeight() > 742 ? 742 : post.getImage().getHeight();
        globalHeight += 100;
        double globalWidth = 1100 - post.getImage().getWidth();
        //post.setFitWidth(600);
        root.setPrefHeight(globalHeight);
        HBox main = new HBox();
        //main.setPrefHeight(742);
        main.setPrefHeight(globalHeight);

        VBox rightBox = new VBox();
        rightBox.setPrefWidth(globalWidth);
        Text title = new Text("Top 10 comments");
        title.setFont(Font.font("system", FontWeight.BOLD, 30));
        rightBox.setMargin(title, new Insets(20,0,0,30));
        rightBox.getChildren().add(title);

        for(int i = 0; i < comments.size() ; i++)   {
            VBox commentsBox = new VBox();
            HBox author = new HBox();
            HBox comment = new HBox();

            commentsBox.setPadding(new Insets(20, 0, 0, 30));
            author.setSpacing(20);

            Text at = new Text(comments.get(i).split("\\$SEPARATOR\\$")[0]);
            at.setFont(Font.font("system", FontWeight.BOLD, 13));

            String cmt = comments.get(i).split("\\$SEPARATOR\\$")[1];
            totalTextLength += cmt.length();

            Text cm = new Text(cmt);
            cm.setText(cm.getText().replaceAll("\\&#039;", "'"));
            cm.setText(cm.getText().replaceAll("\\&quot;", "\""));
            cm.setText(cm.getText().replaceAll("\n", " "));
            cm.setWrappingWidth(globalWidth - 100);
            cm.setTextAlignment(TextAlignment.JUSTIFY);

            author.getChildren().addAll(at);
            comment.getChildren().add(cm);

            commentsBox.getChildren().add(author);
            commentsBox.getChildren().add(comment);
            rightBox.getChildren().add(commentsBox);
        }


        rightBox.setMinHeight(totalTextLength*1.5);
        left.setContent(post);
        right.setContent(rightBox);

        main.getChildren().addAll(left, right);
        root.getChildren().add(main);

        Stage dialog = new Stage();
        dialog.setResizable(false);
        //dialog.setMinWidth(1100);
        dialog.setMinHeight(globalHeight);
        dialog.initStyle(StageStyle.UTILITY);
        Scene scene = new Scene(root);
        dialog.setWidth(1100);
        //dialog.setHeight(742);
        dialog.setHeight(globalHeight);
        dialog.setScene(scene);
        dialog.show();
    }
    private List<String> fetchComments(String id)   {

        try {
            URL url = new URL("http://infinigag.k3min.eu/comments/" + id + "?order=score&level=0");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            Pattern test = Pattern.compile("\"text\":\"(.*?)\",\"user.*?displayName\":\"(.*?)\",\"link");
            List<String> comments = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                System.out.println(line + "\n\n\n");
                Matcher matcher = test.matcher(line);
                if (!matcher.find()) continue;
                while(matcher.find())
                    comments.add(matcher.group(2) + "$SEPARATOR$" + matcher.group(1));
            }
            reader.close();
            return comments;
        } catch (MalformedURLException e) {
            Display.error("L'URL utilisée n'est pas valide, l'application va quitter");
            exit(10);
        } catch (IOException e) {
            Display.error("Un problème lié à la connexion est survenu, l'application va quitter");
            exit(11);
        }
        return null;
    }
}
