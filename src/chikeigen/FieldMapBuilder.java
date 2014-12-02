package chikeigen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class FieldMapBuilder {
    private static final int DEFAULT_WIDTH = 200;
    private static final int DEFAULT_HEIGHT = 200;

    private int[][] mFieldMapArrs;
    private int mWidth = DEFAULT_WIDTH;
    private int mHeight = DEFAULT_HEIGHT;
    private int baseShuffleCount = 100;
    private int woodShuffleCount = 100;
    private int desertShuffleCount = 100;
    private int mountShuffleCount = 100;
    private int[][] baseField;
    private int[][] woodField;
    private int[][] desertField;
    private int[][] mountField;
    private int[][] altitude;
    private long baseSeed = 0;

    private long woodSeed = 0;
    public long getWoodSeed() { return woodSeed; }
    public void setWoodSeed(long woodSeed) { this.woodSeed = woodSeed; }

    private long desertSeed;
    public long getDesertSeed() { return desertSeed; }
    public void setDesertSeed(long desertSeed) { this.desertSeed = desertSeed; }

    private long mountSeed;
    public long getMountSeed() { return mountSeed; }
    public void setMountSeed(long mountSeed) { this.mountSeed = mountSeed; }

    private Random rand = new Random(-1);
    private long randSeed;

    public void setFieldSize(int width, int height) {
        mWidth = width;
        mHeight = height;
    }

    public void executeBase() {
        mFieldMapArrs = initFieldMap2();
        baseField = initFieldMap2();
        woodField = initFieldMap2();
        desertField = initFieldMap2();
        mountField = initFieldMap2();

        // フィールド生成
        initRandom(baseSeed);
        ToolModel model = ToolModel.getInstance();
        genField(baseField, model.getBaseRate(), baseShuffleCount, true, null);
        calculateAltitude(baseField);

        composeFieldMap();
    }

    public void executeWood() {
        woodField = initFieldMap2();

        // フィールド生成
        initRandom(woodSeed);
        ToolModel model = ToolModel.getInstance();
        genField(woodField, model.getWoodRate(), woodShuffleCount, false, null);

        composeFieldMap();
    }

    public void executeDesert() {
        desertField = initFieldMap2();
        // フィールド生成
        ToolModel model = ToolModel.getInstance();
        genField(desertField, model.getWoodRate(), desertShuffleCount, false, baseField);

        composeFieldMap();

    }

    public void executeMount() {
        mountField = initFieldMap2();
        // フィールド生成
        ToolModel model = ToolModel.getInstance();
        genField(mountField, model.getWoodRate(), mountShuffleCount, false, baseField);

        composeFieldMap();

    }

    private void composeFieldMap() {
        //フィールド
        for (int i = 1; i < mHeight-1; i++) {
            for (int j = 1; j < mWidth-1; j++) {
                if (baseField[j][i] == 1) {
                    if (altitude[j][i] > 4) {
                        mFieldMapArrs[j][i] = 5;
                    } else {
                        mFieldMapArrs[j][i] = 1;
                    }
                }
            }
        }

        //フィールド合成　平地、森
        for (int i = 1; i < mHeight-1; i++) {
            for (int j = 1; j < mWidth-1; j++) {
                if (baseField[j][i] == 1 && woodField[j][i] == 1) {
                    mFieldMapArrs[j][i] = 2;
                }
            }
        }

        //フィールド合成　平地、砂地
        for (int i = 1; i < mHeight-1; i++) {
            for (int j = 1; j < mWidth-1; j++) {
                if (baseField[j][i] == 1 && desertField[j][i] == 1) {
                    mFieldMapArrs[j][i] = 4;
                }
            }
        }

        //フィールド合成　平地、山地
        for (int i = 1; i < mHeight-1; i++) {
            for (int j = 1; j < mWidth-1; j++) {
                if (baseField[j][i] == 1 && mountField[j][i] == 1) {
                    mFieldMapArrs[j][i] = 3;
                }
            }
        }
    }

    public void execute() {
        // 初期フィールド
        mFieldMapArrs = initFieldMap2();
        baseField = initFieldMap2();
        woodField = initFieldMap2();
        desertField = initFieldMap2();
        mountField = initFieldMap2();

        // フィールド生成
        initRandom(-1);
        ToolModel model = ToolModel.getInstance();
        genField(baseField, model.getBaseRate(), baseShuffleCount);
        calculateAltitude(baseField);
        genField(woodField, model.getWoodRate(), woodShuffleCount, false);
        genField(mountField, model.getWoodRate(), mountShuffleCount, false, baseField); //***
        genField(desertField, model.getWoodRate(), mountShuffleCount, false, baseField); //***

        composeFieldMap();
    }

    private void calculateAltitude(int[][] origField) {
        altitude = initFieldMap2();
        int[][] buf = initFieldMap2();
        for (int i = 1; i < mHeight-1; i++) {
            for (int j = 1; j < mWidth-1; j++) {
                if (origField[j][i] == 1) {
                    buf[j][i] = 1;
                }
            }
        }
        for (int i = 1; i < mHeight-1; i++) {
            for (int j = 1; j < mWidth-1; j++) {
                if (origField[j][i] == 1) {
                    altitude[j][i] = 1;
                }
            }
        }
        for (int h = 1; h < mWidth; h++) {
            for (int i = 1; i < mHeight-1; i++) {
                for (int j = 1; j < mWidth-1; j++) {
                    boolean b = buf[j][i] == h &&
                        (buf[j][i] == buf[j-1][i-1]) &&
                        (buf[j][i] == buf[j][i-1]) &&
                        (buf[j][i] == buf[j+1][i-1]) &&
                        (buf[j][i] == buf[j-1][i]) &&
                        (buf[j][i] == buf[j][i]) &&
                        (buf[j][i] == buf[j+1][i]) &&
                        (buf[j][i] == buf[j-1][i+1]) &&
                        (buf[j][i] == buf[j][i+1]) &&
                        (buf[j][i] == buf[j+1][i+1]);
                    if (b) {
                        altitude[j][i] = altitude[j][i]+1;
                    }
                }
            }
            for (int i = 1; i < mHeight-1; i++) {
                for (int j = 1; j < mWidth-1; j++) {
                    buf[j][i] = altitude[j][i];
                }
            }
        }

    }

    private void genField(int[][] field, double land, int count) {
        genField(field, land, count, true);
    }
    private void genField(int[][] field, double land, int count, boolean arrange) {
        genField(field, land, count, arrange, null);
    }
//    private void genField(int[][] field, double land, int count, boolean arrange, int[][] mask) {
//        genField(field, land, count, -1, arrange, null);
//    }
    private void genField(int[][] field, double land, int count, boolean arrange, int[][] mask) {
        // ランダム
//        double land = 0.5;
//        initRandom(seed);
//        rand.nextDouble();
        for (int i = 1; i < mHeight-1; i++) {
            for (int j = 1; j < mWidth-1; j++) {
                if (mask != null && mask[j][i] == 0) continue;
                field[j][i] = (random() < land) ? 1 : 0;
            }
        }

        // 走査
        for (int i = 0; i < count; i++) {
            procFieldMap2(field);
        }

        if (arrange) {
            arrangeFieldMap(field);
        } else {
            arrangeFieldMap2(field);
        }

    }

    private double random() {
        if (randSeed == -1) return Math.random();
        return rand.nextDouble();
    }

    private void initRandom(long seed) {
        randSeed = seed;
        rand = new Random(randSeed);
    }

    private void arrangeFieldMap2(int[][] field) {
        // 整理
        for (int i = 1; i < mHeight-1; i++) {
            for (int j = 1; j < mWidth-1; j++) {
                // 周辺4マスが不在の場合、除去
                int round = 0;
                round += field[j+0][i-1];
                round += field[j-1][i+0];
                round += field[j+1][i+0];
                round += field[j+0][i+1];
                if (round == 0) {
                    field[j][i] = 0;
                } else if (round == 4) {
                    field[j][i] = 1;
                }
            }
        }
    }

    private void arrangeFieldMap(int[][] field) {
        // 整理
        for (int i = 2; i < mHeight-2; i++) {
            for (int j = 2; j < mWidth-2; j++) {
                // 周辺25マス
                int round = 0;
                for (int k = -2; k <= 2; k++) {
                    for (int l = -2; l <= 2; l++) {
                        round += field[j+l][i+k];
                    }
                }
                if (round >= 14) {
                    field[j][i] = 1;
                } else if (round <= 11) {
                    field[j][i] = 0;
                }
            }
        }
    }

    private void procFieldMap2(int[][] field) {
        for (int i = 1; i < mHeight-1; i++) {
            for (int j = 1; j < mWidth-1; j++) {
                int count = 0;
                count += field[j-1][i-1];
                count += field[j+0][i-1];
                count += field[j+1][i-1];
                count += field[j-1][i+0];
                count += field[j+1][i+0];
                count += field[j-1][i+1];
                count += field[j+0][i+1];
                count += field[j+1][i+1];

                double[] rates = { 0, 0.1, 0.2, 0.2, 0.5, 0.8, 0.8, 0.9, 1 };
                double r = random();
                field[j][i] = (r < rates[count]) ? 1 : 0;
            }
        }
    }

    private int[][] initFieldMap2() {
        int[][] ret = new int[mWidth][mHeight];
        for (int i = 0; i < mHeight; i++) {
            for (int j = 0; j < mWidth; j++) {
                ret[j][i] = 0;
            }
        }
        return ret;
    }

    public void printFieldMap() {
        for (int i = 0; i < mHeight; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < mWidth; j++) {
                if (0 < sb.length()) sb.append(",");
                sb.append(mFieldMapArrs[j][i]);
            }
            System.out.println(sb.toString());
        }
    }

    public BufferedImage getFieldMapImage2(int chipSize) {
        BufferedImage img = new BufferedImage(mWidth * chipSize, mHeight * chipSize, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = img.createGraphics();

        for (int i = 0; i < mHeight; i++) {
            for (int j = 0; j < mWidth; j++) {
                Color c = Color.black;
                switch (mFieldMapArrs[j][i]) {
                case 0: c = Color.decode("#9090a0"); break;
                case 1: c = Color.decode("#c0ffc0"); break;
                case 2: c = Color.decode("#90f090"); break;
                case 3: c = Color.decode("#f0d090"); break;
                case 4: c = Color.decode("#f0f090"); break;
                case 5: c = Color.decode("#d8ffd8"); break;
                default:
                    break;
                }
                g2.setPaint(c);
                g2.fillRect(j * chipSize, i * chipSize, chipSize, chipSize);
            }
        }

        return img;
    }

    public int getBaseShuffleCount() {
        return baseShuffleCount;
    }

    public void setBaseShuffleCount(int baseShuffleCount) {
        this.baseShuffleCount = baseShuffleCount;
    }

    public int getWoodShuffleCount() {
        return woodShuffleCount;
    }

    public void setWoodShuffleCount(int woodShuffleCount) {
        this.woodShuffleCount = woodShuffleCount;
    }

    public int getMountShuffleCount() {
        return mountShuffleCount;
    }

    public void setMountShuffleCount(int mountShuffleCount) {
        this.mountShuffleCount = mountShuffleCount;
    }

    public int getDesertShuffleCount() {
        return desertShuffleCount;
    }

    public void setDesertShuffleCount(int desertShuffleCount) {
        this.desertShuffleCount = desertShuffleCount;
    }

    public long getBaseSeed() {
        return baseSeed;
    }

    public void setBaseSeed(long baseSeed) {
        this.baseSeed = baseSeed;
    }

}
