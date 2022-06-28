package externalLibrary;

import java.util.ArrayList;
import java.util.Arrays;

public class MyFunctions {

    public static ArrayList<String> stringToArrayList(String text) {
        return new ArrayList<String>(Arrays.asList(text.split(", ")));
    }
    
    public static String arrayListToString(ArrayList<String> array) {
        return String.join(", ", array);
    }
}
