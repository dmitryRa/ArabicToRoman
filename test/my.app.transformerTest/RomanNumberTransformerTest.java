package mmy.app.transformerTest;


import my.app.transformer.RomanNumberTransformer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RomanNumberTransformerTest {
    RomanNumberTransformer transformer = new RomanNumberTransformer();

    @Test
    public void shouldReturnOneByOneDigitFromInteger() {

        Integer i = 1203;
        String[] actual = transformer.parseArabicNumber(i);
        assertEquals("1", actual[0]);
        assertEquals("2", actual[1]);
        assertEquals("0", actual[2]);
        assertEquals("3", actual[3]);

    }

    @Test
    public void shouldReturnDigitsWithZerosAdded() {

        Integer i = 1034;
        String[] str = transformer.parseArabicNumber(i);
        str = transformer.parseToExtensiveForm(str);
        assertEquals("1000", str[0]);
        assertEquals("30", str[1]);
        assertEquals("4", str[2]);
    }

    @Test
    public void shouldReturnStringArrayWithOutZeros() {

        Integer i = 1034;
        String[] actual = transformer.parseArabicNumber(i);
        actual = transformer.deleteStringsWithZeroAtStart(actual);
        assertEquals("1", actual[0]);
        assertEquals("3", actual[1]);
        assertEquals("4", actual[2]);
    }

    @Test
    public void shouldReturnRomanNumbers() {

        Integer i = 1515;
        String[] expected = transformer.parseArabicNumber(i);
        expected=transformer.parseToExtensiveForm(expected);
        String actual=transformer.parsingToRoman(expected);
        assertEquals("MDXV",actual);
    }
}
