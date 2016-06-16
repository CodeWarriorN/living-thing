package jayank.livingthing;

import java.util.Random;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
 
public class World extends Application {

    public static ExecutorService th = Executors.newFixedThreadPool(9000); // stores all the Organism threads
    public static int count=1;// No. of living organisms
    
 
    public static void main(String[] args) {
        launch(args);
    }
 
    @Override
    public void start(Stage world) { // initiates the World graph.
        world.setTitle("Organism Test World");
        Group root = new Group();
        root.getChildren().add(Organism.livingSpace);
        world.setScene(new Scene(root));
        world.show();
        Random ran = new Random();
        Organism org = new Organism((short)ran.nextInt(32767));
        th.execute(org);
    }

    
}
