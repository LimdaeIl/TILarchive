package chapter10;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateFormatExample {
    public static void main(String[] args) {
        // 현재 날짜를 가져옴
        Date currentDate = new Date();

        // format() SimpleDateFormat을 사용하여 날짜를 문자열로 형식화
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = sdf.format(currentDate);
        System.out.println("형식화된 날짜: " + formattedDate);

        // parse() 문자열을 날짜로 파싱
        String dateString = "2023-01-01 12:30:45";
        try {
            Date parsedDate = sdf.parse(dateString);
            System.out.println("파싱된 날짜: " + parsedDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}