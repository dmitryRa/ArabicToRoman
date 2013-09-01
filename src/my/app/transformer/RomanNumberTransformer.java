package my.app.transformer;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RomanNumberTransformer {
    HashMap<String,String> romanNumbers = new HashMap<String, String>();

    public RomanNumberTransformer() {
        this.romanNumbers.put("1","I");
        this.romanNumbers.put("5","V");
        this.romanNumbers.put("10","X");
        this.romanNumbers.put("50","L");
        this.romanNumbers.put("100","C");
        this.romanNumbers.put("500","D");
        this.romanNumbers.put("1000","M");

    }

    public String[] parseArabicNumber(Integer number) {
        StringBuilder sb = new StringBuilder(number.toString());
        String[] str = new String[sb.length()];
        for (int i = 0; i < sb.length(); i++) {
            str[i] = sb.substring(i, i + 1);
        }
        return str;
    }

    public String[] parseToExtensiveForm(String[] input) {
        int counter = input.length;
        StringBuilder sb = new StringBuilder();
        String[] out = new String[input.length];
        for (int i = 0; i < input.length; i++) {
            counter--;
            if (counter > 0) {
                sb.append(input[i]);
                for (int j = 0; j < counter; j++) {
                    sb.append("0");
                }
                out[i] = sb.toString();
                sb.delete(0, sb.length());
            } else {
                out[i] = input[i];
            }
        }
        return deleteStringsWithZeroAtStart(out);

    }

    public String[] deleteStringsWithZeroAtStart(String[] inputArr) {
        String[] str = new String[inputArr.length];
        for (int i = 0, counter = 0; i < inputArr.length; i++, counter++) {
            if (!inputArr[i].startsWith("0")) {
                str[counter] = inputArr[i];
            }
            else counter--;
        }
        return str;
    }
    public HashMap getRomanNumbers(){
        return romanNumbers;
    }


    public String parsingToRoman(String[] arabic){
        StringBuilder sb = new StringBuilder();
        for (String anArabic : arabic) {
            if (romanNumbers.containsKey(anArabic)) {
                sb.append(romanNumbers.get(anArabic));
            }
        }
        return sb.toString();
    }
}
