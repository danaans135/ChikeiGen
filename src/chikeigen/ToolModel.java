package chikeigen;

import java.awt.image.BufferedImage;

public class ToolModel {
    private static ToolModel sInatance = new ToolModel();
    private FieldMapBuilder mBuilder;

    public static ToolModel getInstance() {
        return sInatance;
    }

    private ToolModel() {
        mBuilder = new FieldMapBuilder();
    }

    public void generateFieldMap() {
        mBuilder.setFieldSize(128, 128);
        mBuilder.execute();
        mBuilder.printFieldMap();
    }

    public BufferedImage getFieldMapImage() {
        BufferedImage img = mBuilder.getFieldMapImage();
        return img;
    }
}
