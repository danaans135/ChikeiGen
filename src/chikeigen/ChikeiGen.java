package chikeigen;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChikeiGen extends Application {
    public static void main(String[] args) throws IOException {
        launch(ChikeiGen.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ToolFrame.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle(ToolModel.getInstance().getTitle());
        stage.show();
    }
}
