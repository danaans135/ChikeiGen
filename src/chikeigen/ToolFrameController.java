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
    private TextField woodSeedTextField;

    @FXML
    private Button executeWoodButton;

    @FXML
    private TextField baseSeedTextField;

    @FXML
    private TextField baseRateTextField;

    @FXML
    private ImageView imageView;

    @FXML
    private TextField baseShuffleCountTextField;

    @FXML
    private Button executeBaseButton;

    @FXML
    private Slider alphaSlider;

    @FXML
    private TextField desertShuffleCountTextField;

    @FXML
    private TextField desertSeedTextField;

    @FXML
    private Button executeMountButton;

    @FXML
    private TextField chipSizeTextField;

    @FXML
    private Slider alpha2Slider;

    @FXML
    private Button desertSeedRandomButton;

    @FXML
    private TextField woodShuffleCountTextField;

    @FXML
    private TextField mountSeedTextField;

    @FXML
    private TextField fieldWidthTextField;

    @FXML
    private TextField fieldHeightTextField;

    @FXML
    private ImageView imageView2;

    @FXML
    private Button genButton;

    @FXML
    private TextField woodRateTextField;

    @FXML
    private Button baseSeedRandomButton;

    @FXML
    private Button executeDesertButton;

    @FXML
    private TextField alphaTextField;

    @FXML
    private Button woodSeedRandomButton;

    @FXML
    private Button mountSeedRandomButton;

    @FXML
    private TextField mountShuffleCountTextField;

    @FXML
    private TextField alpha2TextField;

    @FXML
    void initialize() {
        // イベント定義
        genButton.setOnAction(ev -> onActionGenButton(ev));
        executeBaseButton.setOnAction(ev -> onActionExecuteBaseButton(ev));
        chipSizeTextField.setOnAction(ev -> onActionChipSizeTextField(ev));
        executeWoodButton.setOnAction(ev -> onActionExecuteWoodButton(ev));
        executeDesertButton.setOnAction(ev -> onActionExecuteDesertButton(ev));
        executeMountButton.setOnAction(ev -> onActionExecuteMountButton(ev));
        baseSeedRandomButton.setOnAction(ev -> onActionBaseSeedRandomButton(ev));
        woodSeedRandomButton.setOnAction(ev -> onActionWoodSeedRandomButton(ev));
        desertSeedRandomButton.setOnAction(ev -> onActionDesertSeedRandomButton(ev));
        mountSeedRandomButton.setOnAction(ev -> onActionMountSeedRandomButton(ev));

        // UIとモデルをバインド
        ToolModel toolModel = ToolModel.getInstance();
        Bindings.bindBidirectional(alphaSlider.valueProperty(), toolModel.fieldMapOpacityProperty());
        Bindings.bindBidirectional(alphaTextField.textProperty(), toolModel.fieldMapOpacityProperty(), new NumberStringConverter());
        imageView.opacityProperty().bind(toolModel.fieldMapOpacityProperty());
        Bindings.bindBidirectional(alpha2Slider.valueProperty(), toolModel.regionMapOpacityProperty());
        Bindings.bindBidirectional(alpha2TextField.textProperty(), toolModel.regionMapOpacityProperty(), new NumberStringConverter());
        imageView2.opacityProperty().bind(toolModel.regionMapOpacityProperty());
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
        Bindings.bindBidirectional(woodSeedTextField.textProperty(), toolModel.woodSeedProperty(),
                new NumberStringConverter());
        Bindings.bindBidirectional(desertSeedTextField.textProperty(), toolModel.desertSeedProperty(),
                new NumberStringConverter());
        Bindings.bindBidirectional(mountSeedTextField.textProperty(), toolModel.mountSeedProperty(),
                new NumberStringConverter());

        // 地図生成
        genFieldMap();
    }

    private void onActionBaseSeedRandomButton(ActionEvent ev) {
        ToolModel.getInstance().genBaseSeedRandom();
    }

    private void onActionWoodSeedRandomButton(ActionEvent ev) {
        ToolModel.getInstance().genWoodSeedRandom();
    }

    private void onActionDesertSeedRandomButton(ActionEvent ev) {
        ToolModel.getInstance().genDesertSeedRandom();
    }

    private void onActionMountSeedRandomButton(ActionEvent ev) {
        ToolModel.getInstance().genMountSeedRandom();
    }

    private void onActionExecuteMountButton(ActionEvent ev) {
        ToolModel.getInstance().genMountSeedRandom();
        ToolModel.getInstance().executeMount();
        refleshFieldMapImage();
    }

    private void onActionExecuteDesertButton(ActionEvent ev) {
        ToolModel.getInstance().genDesertSeedRandom();
        ToolModel.getInstance().executeDesert();
        refleshFieldMapImage();
    }

    private void onActionExecuteWoodButton(ActionEvent ev) {
        ToolModel.getInstance().genWoodSeedRandom();
       ToolModel.getInstance().executeWood();
        refleshFieldMapImage();
    }

    private void onActionExecuteBaseButton(ActionEvent ev) {
        ToolModel.getInstance().genBaseSeedRandom();
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

        Image fxImage2 = ToolModel.getInstance().getRegionMapImage();
        imageView2.setImage(fxImage2);
        imageView2.setFitWidth(fxImage2.getWidth());
        imageView2.setFitHeight(fxImage2.getHeight());
    }

}
