package my.app.transformer;


import java.util.*;

public class RomanNumberTransformer {
    TreeMap<String, String> romanNumbers = new TreeMap<String, String>();
    TreeMap<String, String> romanNumberDivByFive = new TreeMap<String, String>();

    public RomanNumberTransformer() {
        this.romanNumbers.put("1", "I");
        this.romanNumbers.put("5", "V");
        this.romanNumbers.put("10", "X");
        this.romanNumbers.put("50", "L");
        this.romanNumbers.put("100", "C");
        this.romanNumbers.put("500", "D");
        this.romanNumbers.put("1000", "M");
        this.romanNumberDivByFive.put("5", "V");
        this.romanNumberDivByFive.put("50", "L");
        this.romanNumberDivByFive.put("500", "D");
        this.romanNumberDivByFive.put("5000", "M");
        this.romanNumberDivByFive.put("5000", "M");
        this.romanNumberDivByFive.put("5000", "M");
        this.romanNumberDivByFive.put("5000", "M");

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
            } else counter--;
        }
        return str;
    }

    public TreeMap<String, String> getRomanNumbers() {
        return romanNumbers;
    }


    public String parsingToRoman(String[] arabic) {
        StringBuilder sb = new StringBuilder();
        for (String anArabic : arabic) {
            if (anArabic != null) {
                if (romanNumbers.containsKey(anArabic)) {
                    sb.append(romanNumbers.get(anArabic));
                } else {
                    String tmpOne = "";
                    String tmpTwo = "";
                    Iterator<Map.Entry<String, String>> iterator = romanNumbers.entrySet().iterator();
                    Iterator<Map.Entry<String, String>> it = romanNumberDivByFive.entrySet().iterator();
                    Map.Entry<String, String> entry = iterator.next();
                    Map.Entry<String, String> tmpEntry = it.next();
                    while (iterator.hasNext()) {
                        StringBuilder tempSb = new StringBuilder();
                        if (Integer.parseInt(anArabic) > Integer.parseInt(entry.getKey())) {
                            tmpOne = entry.getValue();
                            entry = iterator.next();
                            if (Integer.parseInt(anArabic) > Integer.parseInt(tmpEntry.getKey())) {
                                tmpTwo = tmpEntry.getValue();
                                tmpEntry = it.next();
                            }


                        } else {
                            if (Integer.parseInt(String.valueOf(anArabic.charAt(0))) <= 3) {
                                for (int i = 0; i < Integer.parseInt(String.valueOf(anArabic.charAt(0))); i++) {
                                    tempSb.append(tmpOne);
                                }
                                sb.append(tempSb.toString());
                                break;
                            } else if (Integer.parseInt(String.valueOf(anArabic.charAt(0))) == 4 || Integer.parseInt(String.valueOf(anArabic.charAt(0))) == 9) {
                                if (Integer.parseInt(String.valueOf(anArabic.charAt(0))) == 4)
                                    sb.append(tmpOne).append(tmpEntry.getValue());
                                else {
                                    sb.append(tmpOne).append(entry.getValue());
                                }
                                break;
                            } else {
                                tempSb.append(tmpTwo);
                                for (int i = 0; i < Integer.parseInt(String.valueOf(anArabic.charAt(0))) - 5; i++) {
                                    tempSb.append(tmpOne);
                                }
                                sb.append(tempSb.toString());
                                break;
                            }

                        }

                    }
                }

            }
        }
        return sb.toString();
    }

    public String parseToRoman(Integer arabicNumber) {
        String[] tmp = parseArabicNumber(arabicNumber);
        tmp = parseToExtensiveForm(tmp);
        return parsingToRoman(tmp);
    }
}
