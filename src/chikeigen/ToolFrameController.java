package chikeigen;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.converter.NumberStringConverter;

public class ToolFrameController {

    @FXML
    private Button genButton;

    @FXML
    private ImageView imageView;

    @FXML
    private Slider alphaSlider;

    @FXML
    private TextField countTextField;

    @FXML
    void initialize() {
        genButton.setOnAction(ev -> onActionGenButton(ev));

        ToolModel toolModel = ToolModel.getInstance();
        Bindings.bindBidirectional(alphaSlider.valueProperty(), toolModel.fieldMapOpacityProperty());
        imageView.opacityProperty().bind(toolModel.fieldMapOpacityProperty());
        Bindings.bindBidirectional(countTextField.textProperty(), toolModel.countProperty(),
                new NumberStringConverter());
    }

    private void onActionGenButton(ActionEvent ev) {
        ToolModel.getInstance().generateFieldMap();
        Image fxImage = ToolModel.getInstance().getFieldMapImage();
        imageView.setImage(fxImage);
        imageView.setFitWidth(fxImage.getWidth());
        imageView.setFitHeight(fxImage.getHeight());
    }

}
