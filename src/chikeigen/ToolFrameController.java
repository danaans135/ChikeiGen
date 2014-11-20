package chikeigen;

import java.awt.image.BufferedImage;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;

public class ToolFrameController {

    @FXML
    private Button genButton;

    @FXML
    private ImageView imageView;

    @FXML
    private Slider alphaSlider;

    @FXML
    void initialize() {
        genButton.setOnAction(ev -> onActionGenButton(ev));
//        alphaSlider.valueProperty().bind(imageView.opacityProperty());
        imageView.opacityProperty().bind(alphaSlider.valueProperty());
    }

    private void onActionGenButton(ActionEvent ev) {
        System.out.println("hi");
        ToolModel.getInstance().generateFieldMap();
        BufferedImage img = ToolModel.getInstance().getFieldMapImage();
        WritableImage fxImage = SwingFXUtils.toFXImage(img, null);
        imageView.setImage(fxImage);
        imageView.setFitWidth(fxImage.getWidth());
        imageView.setFitHeight(fxImage.getHeight());
    }

}
