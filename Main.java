import Weapons.weaponSelect;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import Character.character;
import java.io.*;

public class Main extends Application  {
    public weaponSelect weaponchoice = new weaponSelect();
    character ch = new character();
    
    @Override
    public void start(Stage primaryStage) {
        File file = new File("Character.txt");
        if(file.exists()) {
            try (

                    ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));

            ) {
                ch = (character) input.readObject();
                System.out.println("test");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        System.out.println(ch.getCharacter());
        BorderPane pane = new BorderPane();

        HBox stats = new HBox();
        pane.setCenter(stats);
        stats.setAlignment(Pos.CENTER);;
        stats.setStyle("-fx-border-color: black");

        VBox paneforbuttons = new VBox(60);
        Label wp = new Label("Choose your Weapon");
        paneforbuttons.getChildren().add(wp);
        paneforbuttons.setPadding(new Insets(5, 5, 5, 5));
        paneforbuttons.setStyle("-fx-border-color: black");
        paneforbuttons.setStyle("-fx-border-width: 2px; -fx-border-color: black");

        ToggleButton sword = new ToggleButton("Sword");
        ToggleButton bow = new ToggleButton("Bow");
        ToggleButton dagger = new ToggleButton("Dagger");
        ToggleButton axe = new ToggleButton("Axe");
        Button save = new Button("Save");
        paneforbuttons.getChildren().addAll(sword, bow, dagger, axe);
        pane.setLeft(paneforbuttons);

        ToggleGroup group = new ToggleGroup();
        sword.setToggleGroup(group);
        sword.setSelected(true);
        bow.setToggleGroup(group);
        dagger.setToggleGroup(group);
        axe.setToggleGroup(group);

        Label label = new Label("Enter Character Name");
        TextField tf = new TextField();
        tf.setText(ch.getCharacter());
        HBox hbox = new HBox();
        pane.setTop(hbox);
        hbox.setAlignment(Pos.TOP_CENTER);
        hbox.setSpacing(10);
        hbox.setLayoutY(200);
        hbox.setStyle("-fx-border-color: black");
        tf.setLayoutX(300);
        tf.setLayoutY(300);
        hbox.getChildren().addAll(label, tf);

        HBox savebox = new HBox();
        pane.setBottom(savebox);
        savebox.setAlignment(Pos.BOTTOM_CENTER);
        savebox.getChildren().add(save);

        sword.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (sword.isSelected()) {
                    ch.setWp(weaponchoice.sword);
                    sword.requestFocus();
                }
            }
        });

        bow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (bow.isSelected()) {
                    ch.setWp(weaponchoice.bow);
                    bow.requestFocus();
                }
            }
        });

        dagger.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (dagger.isSelected()) {
                    ch.setWp(weaponchoice.dagger);
                    dagger.requestFocus();
                }
            }
        });

        axe.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (axe.isSelected()) {
                    ch.setWp(weaponchoice.axe);
                    axe.requestFocus();
                }
            }
        });

        save.setOnMouseClicked(event ->  {
            ch.setCharacter(tf.getText());
                try (
                        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("Character.txt", true));
                ) {
                    output.writeObject(ch);
                    pane.setCenter(label);
                    label.setText("Character Info Saved");
                } catch (FileNotFoundException e) {
                    System.out.println("File Cannot Be Saved");
                } catch (IOException e) {
                    e.printStackTrace();
                }

               /*try(
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("Character.txt"));
                    ) {
                    ch = (character) input.readObject();
                    weaponchoice = (weaponSelect) input.readObject();
                    if (ch.getWp() == weaponchoice.sword) {
                        sword.setSelected(true);
                    } else if(ch.getWp() == weaponchoice.bow) {
                        bow.setSelected(true);
                    } else if(ch.getWp() == weaponchoice.dagger) {
                        dagger.setSelected(true);
                    }else if(ch.getWp() == weaponchoice.axe) {
                        axe.setSelected(true);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }*/
        });
                
        Scene scene = new Scene(pane, 600, 600);
        primaryStage.setTitle("DinoQuest 0.1");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) { launch(args); }
}
