package chikeigen;

import java.awt.image.BufferedImage;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class ToolModel {
    private static final String TITLE = "ChikeiGen";

    private static final int DEFAULT_FIELD_WIDTH = 256;

    private static final int DEFAULT_FIELD_HEIGHT = 256;

    private static final int DEFAULT_COUNT = 128;

    private static final double DEFAULT_BASE_RATE = 0.5;

    private static final double DEFAULT_WOOD_RATE = 0.5;

    private static final int DEFAULT_CHIP_SIZE = 1;

    private static final int DEFAULT_SHUFFLE_COUNT = 128;

    private static ToolModel sInatance = new ToolModel();
    private FieldMapBuilder mBuilder;

    public static ToolModel getInstance() {
        return sInatance;
    }

//    private SimpleIntegerProperty count = new SimpleIntegerProperty();
//    public SimpleIntegerProperty countProperty() { return count; }
//    public int getCount() { return count.getValue(); }
//    public void setCount(int count) { this.count.setValue(count); }

    private SimpleStringProperty title = new SimpleStringProperty();
    public SimpleStringProperty titleProperty() { return title; }
    public String getTitle() { return title.getValue(); }
    public void setTitle(String title) { this.title.setValue(title); }

    private SimpleDoubleProperty fieldMapOpacity = new SimpleDoubleProperty();
    public SimpleDoubleProperty fieldMapOpacityProperty() { return fieldMapOpacity; }
    public double getFieldMapOpacity() { return fieldMapOpacity.getValue(); }
    public void setFieldMapOpacity(double fieldMapOpacity) { this.fieldMapOpacity.setValue(fieldMapOpacity); }

    private SimpleIntegerProperty fieldWidth = new SimpleIntegerProperty();
    public SimpleIntegerProperty fieldWidthProperty() { return fieldWidth; }
    public int getFieldWidth() { return fieldWidth.getValue(); }
    public void setFieldWidth(int fieldWidth) { this.fieldWidth.setValue(fieldWidth); }

    private SimpleIntegerProperty fieldHeight = new SimpleIntegerProperty();
    public SimpleIntegerProperty fieldHeightProperty() { return fieldHeight; }
    public int getFieldHeight() { return fieldHeight.getValue(); }
    public void setFieldHeight(int fieldHeight) { this.fieldHeight.setValue(fieldHeight); }

    private SimpleDoubleProperty baseRate = new SimpleDoubleProperty();
    public SimpleDoubleProperty baseRateProperty() { return baseRate; }
    public double getBaseRate() { return baseRate.getValue(); }
    public void setBaseRate(double baseRate) { this.baseRate.setValue(baseRate); }

    private SimpleDoubleProperty woodRate = new SimpleDoubleProperty();
    public SimpleDoubleProperty woodRateProperty() { return woodRate; }
    public double getWoodRate() { return woodRate.getValue(); }
    public void setWoodRate(double woodRate) { this.woodRate.setValue(woodRate); }

    private SimpleIntegerProperty chipSize = new SimpleIntegerProperty();
    public SimpleIntegerProperty chipSizeProperty() { return chipSize; }
    public int getChipSize() { return chipSize.getValue(); }
    public void setChipSize(int chipSize) { this.chipSize.setValue(chipSize); }

    private SimpleIntegerProperty baseShuffleCount = new SimpleIntegerProperty();
    public SimpleIntegerProperty baseShuffleCountProperty() { return baseShuffleCount; }
    public int getBaseShuffleCount() { return baseShuffleCount.getValue(); }
    public void setBaseShuffleCount(int baseShuffleCount) { this.baseShuffleCount.setValue(baseShuffleCount); }

    private SimpleIntegerProperty woodShuffleCount = new SimpleIntegerProperty();
    public SimpleIntegerProperty woodShuffleCountProperty() { return woodShuffleCount; }
    public int getWoodShuffleCount() { return woodShuffleCount.getValue(); }
    public void setWoodShuffleCount(int woodShuffleCount) { this.woodShuffleCount.setValue(woodShuffleCount); }

    private SimpleIntegerProperty desertShuffleCount = new SimpleIntegerProperty();
    public SimpleIntegerProperty desertShuffleCountProperty() { return desertShuffleCount; }
    public int getDesertShuffleCount() { return desertShuffleCount.getValue(); }
    public void setDesertShuffleCount(int desertShuffleCount) { this.desertShuffleCount.setValue(desertShuffleCount); }

    private SimpleIntegerProperty mountShuffleCount = new SimpleIntegerProperty();
    public SimpleIntegerProperty mountShuffleCountProperty() { return mountShuffleCount; }
    public int getMountShuffleCount() { return mountShuffleCount.getValue(); }
    public void setMountShuffleCount(int mountShuffleCount) { this.mountShuffleCount.setValue(mountShuffleCount); }

    private SimpleLongProperty baseSeed = new SimpleLongProperty();
    public SimpleLongProperty baseSeedProperty() { return baseSeed; }
    public long getBaseSeed() { return baseSeed.getValue(); }
    public void setBaseSeed(long baseSeed) { this.baseSeed.setValue(baseSeed); }

    private ToolModel() {
        mBuilder = new FieldMapBuilder();
        setTitle(TITLE);
//        setCount(DEFAULT_COUNT);
        setFieldMapOpacity(0.4);
        setFieldWidth(DEFAULT_FIELD_WIDTH);
        setFieldHeight(DEFAULT_FIELD_HEIGHT);
        setBaseRate(DEFAULT_BASE_RATE);
        setWoodRate(DEFAULT_WOOD_RATE);
        setChipSize(DEFAULT_CHIP_SIZE);
        setBaseShuffleCount(DEFAULT_SHUFFLE_COUNT);
        setWoodShuffleCount(DEFAULT_SHUFFLE_COUNT);
        setDesertShuffleCount(DEFAULT_SHUFFLE_COUNT);
        setMountShuffleCount(DEFAULT_SHUFFLE_COUNT);
    }

    public void executeBase() {
        mBuilder.setFieldSize(getFieldWidth(), getFieldHeight());
        mBuilder.setBaseShuffleCount(getBaseShuffleCount());
        mBuilder.setBaseSeed(getBaseSeed());
        mBuilder.executeBase();
//        mBuilder.printFieldMap();
    }

    public void executeWood() {
        mBuilder.setWoodShuffleCount(getWoodShuffleCount());
        mBuilder.executeWood();
    }

    public void executeDesert() {
        mBuilder.setDesertShuffleCount(getDesertShuffleCount());
        mBuilder.executeDesert();
    }

    public void executeMount() {
        mBuilder.setMountShuffleCount(getMountShuffleCount());
        mBuilder.executeMount();
    }

    public void generateFieldMap() {
        mBuilder.setFieldSize(getFieldWidth(), getFieldHeight());
        mBuilder.setBaseShuffleCount(getBaseShuffleCount());
        mBuilder.setWoodShuffleCount(getWoodShuffleCount());
        mBuilder.setMountShuffleCount(getMountShuffleCount());
        mBuilder.execute();
//        mBuilder.printFieldMap();
    }

    public Image getFieldMapImage() {
        BufferedImage img = mBuilder.getFieldMapImage2(getChipSize());
//        BufferedImage img = ToolModel.getInstance().getFieldMapImage();
        WritableImage fxImage = SwingFXUtils.toFXImage(img, null);
        return fxImage;
    }
    public void genBaseSeedRandom() {
        setBaseSeed((int)(Math.random() * Integer.MAX_VALUE));
    }
}
