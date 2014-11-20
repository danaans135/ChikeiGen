package chikeigen;

import java.awt.image.BufferedImage;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class ToolModel {
    private static final String TITLE = "ChikeiGen";

    private static ToolModel sInatance = new ToolModel();
    private FieldMapBuilder mBuilder;

    public static ToolModel getInstance() {
        return sInatance;
    }

    private SimpleIntegerProperty count = new SimpleIntegerProperty();
    public SimpleIntegerProperty countProperty() { return count; }
    public int getCount() { return count.getValue(); }
    public void setCount(int count) { this.count.setValue(count); }

    private SimpleStringProperty title = new SimpleStringProperty();
    public SimpleStringProperty titleProperty() { return title; }
    public String getTitle() { return title.getValue(); }
    public void setTitle(String title) { this.title.setValue(title); }

    private SimpleDoubleProperty fieldMapOpacity = new SimpleDoubleProperty();
    public SimpleDoubleProperty fieldMapOpacityProperty() { return fieldMapOpacity; }
    public double getFieldMapOpacity() { return fieldMapOpacity.getValue(); }
    public void setFieldMapOpacity(double fieldMapOpacity) { this.fieldMapOpacity.setValue(fieldMapOpacity); }

    private ToolModel() {
        mBuilder = new FieldMapBuilder();
        setTitle(TITLE);
        setFieldMapOpacity(1.0);
    }

    public void generateFieldMap() {
        mBuilder.setFieldSize(512, 512);
        mBuilder.execute();
//        mBuilder.printFieldMap();
    }

    public Image getFieldMapImage() {
        BufferedImage img = mBuilder.getFieldMapImage();
//        BufferedImage img = ToolModel.getInstance().getFieldMapImage();
        WritableImage fxImage = SwingFXUtils.toFXImage(img, null);
        return fxImage;
    }
}
