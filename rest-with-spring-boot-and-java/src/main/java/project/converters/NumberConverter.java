package project.converters;

public class NumberConverter {

    public static Double convertToDouble(String number) {
        if (number == null || number.isEmpty()) return 0.0;
        String convertNumber = number.replaceAll(",", ".");
        if(isNumeric(convertNumber)) return Double.parseDouble(convertNumber);
        return 0D;
    }

    public static Boolean isNumeric(String number){
        if (number == null || number.isEmpty()) return false;
        String convertNumber = number.replaceAll(",", ".");
        return convertNumber.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}
