package chikeigen;

public class Utils {
    public static void main(String[] args) {
        outProp("count", "SimpleIntegerProperty", "int");
        outProp("title", "SimpleStringProperty", "String");
        outProp("fieldMapOpacity", "SimpleDoubleProperty", "double");
        outProp("fieldWidth", "SimpleIntegerProperty", "int");
        outProp("fieldHeight", "SimpleIntegerProperty", "int");
        outProp("baseRate", "SimpleDoubleProperty", "double");
        outProp("woodRate", "SimpleDoubleProperty", "double");
        outProp("chipSize", "SimpleIntegerProperty", "int");
    }

    private static void outProp(String prop, String propType, String jtype) {
        String lprop = prop.substring(0, 1).toUpperCase() + prop.substring(1);
        System.out.println();
        System.out.println("private " + propType + " " + prop+ " = new " + propType+ "();");
        System.out.println("public " + propType + " " + prop+ "Property() {"+
                " return " + prop + "; }");
        System.out.println("public "+jtype+" get" + lprop + "() {"+
                " return " + prop + ".getValue(); }");
        System.out.println("public void set" + lprop + "("+jtype+" "+prop+") {"+
                " this." + prop + ".setValue("+prop+"); }");
    }
}
