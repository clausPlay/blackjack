package com.example.mariu.blackjack;
import java.util.Arrays;
import java.lang.String;
/**
 * Created by Marius on 2/1/2018.
 */

public class encryptPassword {
    //simplest algorithm for encrypt
    public static String normalizeText(String text){
        // normalizedText;
        text = text.replace(" ","");
        text = text.replaceAll("[/|!?.,()\"'=;:_^~`´]", "");
        text= text.toUpperCase();
        return text;
    }

    public static String chiper(String msg){
        String s = "";
        int len = msg.length();
        for (int x=0; x < len; x++){
            char c = (char)(msg.charAt(x) + 1);
            if (c > 'z')
                s += (char)(msg.charAt(x)-(26-1));
            else
                s += (char)(msg.charAt(x)+1);
        }
        return s;
    }

}
