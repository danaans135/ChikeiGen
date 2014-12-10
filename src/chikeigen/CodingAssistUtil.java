package chikeigen;

import java.util.HashMap;
import java.util.Map;

public class CodingAssistUtil {
    private static final String DEFAULT_PROP_TYPE = "SimpleObjectProperty";
    private static final Map<String, String> propTypeMap = new HashMap<>();
    static {
        propTypeMap.put("int", "SimpleIntegerProperty");
        propTypeMap.put("String", "SimpleStringProperty");
        propTypeMap.put("double", "SimpleDoubleProperty");
        propTypeMap.put("long", "SimpleLongProperty");
    }

    public static void main(String[] args) {
        printToolModelProperties();
    }

    private static void printToolModelProperties() {
        outProp("count", "int");
        outProp("title", "String");
        outProp("fieldMapOpacity", "double");
        outProp("fieldWidth", "int");
        outProp("fieldHeight", "int");
        outProp("baseRate", "double");
        outProp("woodRate", "double");
        outProp("chipSize", "int");
        outProp("baseShuffleCount", "int");
        outProp("woodShuffleCount", "int");
        outProp("desertShuffleCount", "int");
        outProp("mountShuffleCount", "int");
        outProp("baseSeed", "long");
        outProp("woodSeed", "long");
        outProp("desertSeed", "long");
        outProp("mountSeed", "long");
        outProp("regionMapOpacity", "double");

//        System.out.println("----------------------");
//        System.out.println("public class Builder {");
//        System.out.println("private FieldMapBuilder instance = new FieldMapBuilder();");
//        System.out.println("public FieldMapBuilder build() { return instance; }");
//        outBuilder("count", "int");
//        outBuilder("title", "String");
//        outBuilder("fieldMapOpacity", "double");
//        outBuilder("fieldWidth", "int");
//        outBuilder("fieldHeight", "int");
//        outBuilder("baseRate", "double");
//        outBuilder("woodRate", "double");
//        outBuilder("chipSize", "int");
//        System.out.println("}");
    }

//    public class Builder {
//        private FieldMapBuilder instance = new FieldMapBuilder();
//        public FieldMapBuilder build() { return instance; }
//        public Builder count(int count) { instance.count = count; return this; }
//    }
    /**
     * <pre>
     *    public class Builder {
     *        private FieldMapBuilder instance = new FieldMapBuilder();
     *        public FieldMapBuilder build() { return instance; }
     *        [...]
     *    }
     * </pre>
     * @param prop
     * @param jtype
     */
    private static void outBuilder(String prop, String jtype) {

//        System.out.printf("%n"
//                + "private %1$s %2$s = new %1$s();%n"
//                + "public %1$s %2$sProperty() { return %2$s; }%n"
//                + "public %3$s get%4$s() { return %2$s.getValue(); }%n"
//                + "public void set%4$s(%3$s %2$s) { this.%2$s.setValue(%2$s); }%n"
//                , propType, prop, jtype, lprop);
        System.out.printf(
                "public Builder %1$s(%2$s %1$s) { instance.%1$s = %1$s; return this; }%n",
                prop, jtype);

    }

    private static void outProp(String prop, String jtype) {
        outProp(prop, getPropType(jtype), jtype);
//        outNormalProp(prop, jtype);
    }

    private static String getPropType(String jtype) {
        String propType = propTypeMap.get(jtype);
        return (propType != null) ? propType : DEFAULT_PROP_TYPE;
    }

    private static void outProp(String prop, String propType, String jtype) {
        String lprop = prop.substring(0, 1).toUpperCase() + prop.substring(1);

        System.out.printf("%n"
                + "private %1$s %2$s = new %1$s();%n"
                + "public %1$s %2$sProperty() { return %2$s; }%n"
                + "public %3$s get%4$s() { return %2$s.getValue(); }%n"
                + "public void set%4$s(%3$s %2$s) { this.%2$s.setValue(%2$s); }%n"
                , propType, prop, jtype, lprop);
    }

    private static void outNormalProp(String prop, String javaType) {
        String lprop = prop.substring(0, 1).toUpperCase() + prop.substring(1);

        System.out.printf("%n"
                + "private %1$s %2$s;%n"
                + "public %1$s get%3$s() { return %2$s; }%n"
                + "public void set%3$s(%1$s %2$s) { this.%2$s = %2$s; }%n"
                , javaType, prop, lprop);
    }
}
