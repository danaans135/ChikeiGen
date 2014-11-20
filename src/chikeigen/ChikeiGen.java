package chikeigen;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.imageio.ImageIO;

public class ChikeiGen extends Application {
    public static void main(String[] args) throws IOException {
        launch(ChikeiGen.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FieldMapBuilder builder = new FieldMapBuilder();
        builder.setFieldSize(64, 64);
        builder.execute();
        builder.printFieldMap();
        BufferedImage img = builder.getFieldMapImage();

        ImageIO.write(img, "png", new File("aa.png"));
        Parent root = FXMLLoader.load(getClass().getResource("ToolFrame.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }
}
