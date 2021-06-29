import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.beans.EventHandler;
import java.io.*;

public class Main extends Application {
    Scene scene1, scene2, scene3;

    @Override
    public void start(Stage primaryStage) {
        // Scene1
        Text text = new Text("DINOQUEST");
        text.setFont(Font.font("Verdana", 45));
        text.setX(50);
        text.setY(150);
        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(4000)));
        timeline.play();
            Stop[] stops = new Stop[]{
                    new Stop(0, Color.TURQUOISE),
                    new Stop(1, Color.BLACK)
            };
            LinearGradient linearGradient =
                    new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
            text.setFill(linearGradient);
            Group root1 = new Group(text);
            scene1 = new Scene(root1, 400, 300);
           timeline.setOnFinished(e -> primaryStage.setScene(scene2));

        // Scene2
        BorderPane pane = new BorderPane();
        BorderPane panefortext = new BorderPane();
        Button newChar = new Button("New Characater");
        Button previous = new Button("Previous Character");
        panefortext.setPadding(new Insets(5, 5, 5, 5));
        panefortext.setStyle("-fx-border-color: black");
        pane.setLeft(newChar);
        pane.setTop(previous);
        TextField tf = new TextField();
        newChar.setOnAction(e -> {
                    panefortext.setLeft(new Label("Enter Character Name"));
                    panefortext.setRight(tf);
                    pane.setTop(panefortext);
                });
        ImageView spriteImage = new ImageView("Sprite Medium.png");
        spriteImage.relocate(-50,100);
        pane.getChildren().add(spriteImage);
        Bounds bounds = pane.getBoundsInLocal();
        Timeline spriteTimeline = new Timeline(new KeyFrame(Duration.seconds(5),
                new KeyValue(spriteImage.layoutXProperty(), bounds.getMaxX()-spriteImage.getFitWidth())));
        spriteTimeline.setCycleCount(Animation.INDEFINITE);
        spriteTimeline.play();
        Button Contin = new Button("Continue");
        Button confirm = new Button("Confirm");
        HBox buttonPane = new HBox();
        Button yes = new Button("Yes");
        Button no = new Button("no");
        buttonPane.getChildren().addAll(yes, no);
        pane.setRight(Contin);
        scene2 = new Scene(pane, 400,300);

        Contin.setOnAction(e -> {
                    panefortext.setLeft(new Label("Confirm Name: " + tf.getText()));
                    pane.getChildren().removeAll(newChar,previous);
                    pane.setRight(confirm);
                });
        File file = new File(tf.getText() + ".dat");
        FileChooser fileChooser = new FileChooser();
                          confirm.setOnAction(event -> {
                              pane.getChildren().removeAll(newChar,previous);
                                  try {
                                      if(!file.exists()) {
                                          try (DataOutputStream output = new DataOutputStream(new FileOutputStream(tf.getText() + ".dat"))) {
                                              output.writeUTF(tf.getText());
                                          }
                                      }
                                      else {
                                          try (DataInputStream input = new DataInputStream(new FileInputStream(tf.getText() + ".dat"))) {
                                              input.readUTF();
                                          }
                                      }
                                  } catch (IOException ioException) {
                                      ioException.printStackTrace();
                                  }
                              primaryStage.setScene(scene3);
                            });
                            Label label = new Label();
                            pane.setCenter(label);
                            previous.setOnAction(e -> {
                                File selectedFile = fileChooser.showOpenDialog(primaryStage);
                                label.setText("Is this correct? " + selectedFile);
                                pane.getChildren().remove(newChar);
                                pane.getChildren().remove(Contin);
                                pane.getChildren().remove(previous);
                                pane.getChildren().remove(spriteImage);
                                pane.setTop(buttonPane);
                            });
                            Button Next = new Button("Next");
                            Next.setOnAction(e -> {primaryStage.setScene(scene3);});
                            yes.setOnAction(e -> {primaryStage.setScene(scene3);});
                            no.setOnAction(e -> {
                                pane.getChildren().remove(label);
                                buttonPane.getChildren().removeAll(yes, no);
                                pane.getChildren().add(Next);
                                spriteImage.relocate(0,100);
                                pane.getChildren().add(spriteImage);
                                Bounds bound = pane.getBoundsInLocal();
                                Timeline spritetimeline = new Timeline(new KeyFrame(Duration.seconds(3),
                                        new KeyValue(spriteImage.layoutXProperty(), bound.getMaxX()-spriteImage.getFitWidth())));
                                spritetimeline.setCycleCount(2);
                                spritetimeline.play();
                            });

        // Scene3
        VBox b = new VBox(100);
        BorderPane imagePane = new BorderPane();
        imagePane.setPadding(new Insets(5,5,5,5));
        b.setPadding(new Insets(5, 5, 5, 5));
        imagePane.setLeft(b);
        Button next = new Button("Next");
        Glow glow = new Glow();
        glow.setLevel(0.9);
        RadioButton Sword = new RadioButton("Sword");
        ImageView SwordImage = new ImageView("Sword Art Medium.png");
        SwordImage.setEffect(glow);
        RadioButton Bow = new RadioButton("Bow");
        ImageView BowImage = new ImageView("Bow Art Medium.png");
        BowImage.setEffect(glow);
        RadioButton Staff = new RadioButton("Staff");
        ImageView StaffImage = new ImageView("Staff Art Medium.png");
        StaffImage.setEffect(glow);
        final ToggleGroup group = new ToggleGroup();
        Sword.setToggleGroup(group);
        Bow.setToggleGroup(group);
        Staff.setToggleGroup(group);
        b.getChildren().addAll(Sword, Bow, Staff);
        imagePane.setBottom(next);

        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if(group.getSelectedToggle() == Sword) {
                    imagePane.setCenter(SwordImage);
                } else if(group.getSelectedToggle() == Bow) {
                    imagePane.setCenter(BowImage);
                } else if(group.getSelectedToggle() == Staff) {
                    imagePane.setCenter(StaffImage);
                }
            }
        });
        next.setOnAction(e -> {
            b.getChildren().removeAll(Sword, Bow, Staff);
            Text txt = new Text("The Journey Continues Here....");
            txt.setFont(Font.font("Verdana", 25));
            txt.setStyle("-fx-border-color: Red");
            txt.setX(50);
            txt.setY(150);;
            b.getChildren().add(txt);
        });
        scene3 = new Scene(imagePane, 400,300);
        primaryStage.setTitle("DinoQuest");
        primaryStage.setScene(scene1);
        primaryStage.show();
    }
    
    public static void main(String[] args) { launch(args); }
}

