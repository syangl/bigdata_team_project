package util;

import java.text.DecimalFormat;

public class NumberUtil {
    public static String format(int num,int length){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0;i<length;i++){
            stringBuilder.append("0");//追加

        }
        DecimalFormat df = new DecimalFormat(stringBuilder.toString());
        return df.format(num);
    }

    public static void main(String[] args) {
        String format = format(788, 4);
        System.out.println(format);

    }
}
