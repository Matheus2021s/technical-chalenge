package br.com.sicredi.techinicalchalenge.utils;

public class StringUtil {

    private static final String REGEX_REMOVE_SPECIAL_CHARACTERS="[^0-9a-zA-Z]+";

    public static  String removeSpecialCaracters(String value){
        if (! value.isEmpty()) {
         return value.replaceAll(REGEX_REMOVE_SPECIAL_CHARACTERS, "");
        }
        throw  new IllegalArgumentException("Empty value passed!");
    }


}
