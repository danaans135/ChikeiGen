package chikeigen;

public class Utils {
    public static void main(String[] args) {
        String prop = "count";
//        String propType = "IntegerProperty";
        String propType = "SimpleIntegerProperty";
        String jtype = "int";

        outProp(prop, propType, jtype);
        outProp("title", "SimpleStringProperty", "String");
        outProp("fieldMapOpacity", "SimpleDoubleProperty", "double");

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
