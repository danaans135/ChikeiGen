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
    private TextField woodRateTextField;

    @FXML
    private TextField countTextField;

    @FXML
    private TextField fieldWidthTextField;

    @FXML
    private TextField baseRateTextField;

    @FXML
    private ImageView imageView;

    @FXML
    private TextField fieldHeightTextField;

    @FXML
    private TextField chipSizeTextField;

    @FXML
    private Slider alphaSlider;

    @FXML
    private TextField baseShuffleCountTextField;

    @FXML
    private TextField woodShuffleCountTextField;

    @FXML
    private TextField mountShuffleCountTextField;

    @FXML
    private Button executeBaseButton;

    @FXML
    private Button executeWoodButton;

    @FXML
    private TextField desertShuffleCountTextField;

    @FXML
    private Button executeDesertButton;

    @FXML
    private Button executeMountButton;

    @FXML
    private TextField baseSeedTextField;

    @FXML
    void initialize() {
        // イベント定義
        genButton.setOnAction(ev -> onActionGenButton(ev));
        executeBaseButton.setOnAction(ev -> onActionExecuteBaseButton(ev));
        chipSizeTextField.setOnAction(ev -> onActionChipSizeTextField(ev));
        executeWoodButton.setOnAction(ev -> onActionExecuteWoodButton(ev));
        executeDesertButton.setOnAction(ev -> onActionExecuteDesertButton(ev));
        executeMountButton.setOnAction(ev -> onActionExecuteMountButton(ev));

        // UIとモデルをバインド
        ToolModel toolModel = ToolModel.getInstance();
        Bindings.bindBidirectional(alphaSlider.valueProperty(), toolModel.fieldMapOpacityProperty());
        imageView.opacityProperty().bind(toolModel.fieldMapOpacityProperty());
//        Bindings.bindBidirectional(countTextField.textProperty(), toolModel.countProperty(),
//                new NumberStringConverter());
        Bindings.bindBidirectional(fieldWidthTextField.textProperty(), toolModel.fieldWidthProperty(),
                new NumberStringConverter());
        Bindings.bindBidirectional(fieldHeightTextField.textProperty(), toolModel.fieldHeightProperty(),
                new NumberStringConverter());
        Bindings.bindBidirectional(baseRateTextField.textProperty(), toolModel.baseRateProperty(),
                new NumberStringConverter());
        Bindings.bindBidirectional(woodRateTextField.textProperty(), toolModel.woodRateProperty(),
                new NumberStringConverter());
        Bindings.bindBidirectional(chipSizeTextField.textProperty(), toolModel.chipSizeProperty(),
                new NumberStringConverter());
        Bindings.bindBidirectional(baseShuffleCountTextField.textProperty(), toolModel.baseShuffleCountProperty(),
                new NumberStringConverter());
        Bindings.bindBidirectional(woodShuffleCountTextField.textProperty(), toolModel.woodShuffleCountProperty(),
                new NumberStringConverter());
        Bindings.bindBidirectional(desertShuffleCountTextField.textProperty(), toolModel.desertShuffleCountProperty(),
                new NumberStringConverter());
        Bindings.bindBidirectional(mountShuffleCountTextField.textProperty(), toolModel.mountShuffleCountProperty(),
                new NumberStringConverter());
        Bindings.bindBidirectional(baseSeedTextField.textProperty(), toolModel.baseSeedProperty(),
                new NumberStringConverter());

        // 地図生成
        genFieldMap();
    }

    private void onActionExecuteMountButton(ActionEvent ev) {
        ToolModel.getInstance().executeMount();
        refleshFieldMapImage();
    }

    private void onActionExecuteDesertButton(ActionEvent ev) {
        ToolModel.getInstance().executeDesert();
        refleshFieldMapImage();
    }

    private void onActionExecuteWoodButton(ActionEvent ev) {
        ToolModel.getInstance().executeWood();
        refleshFieldMapImage();
    }

    private void onActionExecuteBaseButton(ActionEvent ev) {
        ToolModel.getInstance().executeBase();
        refleshFieldMapImage();
    }

    private void onActionChipSizeTextField(ActionEvent ev) {
        refleshFieldMapImage();
    }

    private void onActionGenButton(ActionEvent ev) {
        genFieldMap();
    }

    private void genFieldMap() {
        ToolModel.getInstance().generateFieldMap();
        refleshFieldMapImage();
    }

    private void refleshFieldMapImage() {
        Image fxImage = ToolModel.getInstance().getFieldMapImage();
        imageView.setImage(fxImage);
        imageView.setFitWidth(fxImage.getWidth());
        imageView.setFitHeight(fxImage.getHeight());
    }

}
