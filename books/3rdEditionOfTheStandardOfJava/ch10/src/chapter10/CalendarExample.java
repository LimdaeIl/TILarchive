package chapter10;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarExample {
    public static void main(String[] args) {
        // 현재 날짜 및 시간 가져오기
        Calendar calendar = Calendar.getInstance();

        // 특정 날짜 및 시간으로 설정하기
        Calendar specificDate = new GregorianCalendar(2023, Calendar.JANUARY, 1);

        // 날짜 및 시간 필드 값 얻기
        int year = calendar.get(Calendar.YEAR); // 년
        int month = calendar.get(Calendar.MONTH); // 월은 0부터 시작
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH); // 일
        int hour = calendar.get(Calendar.HOUR_OF_DAY); // 시
        int minute = calendar.get(Calendar.MINUTE); // 분
        int second = calendar.get(Calendar.SECOND); // 초

        System.out.println("현재 날짜 및 시간: " + year + "-" + (month + 1) + "-" + dayOfMonth + " " + hour + ":" + minute + ":" + second);

        // 날짜 및 시간 필드 값 설정하기
        calendar.set(Calendar.YEAR, 2024); // 2024년으로 변경
        calendar.set(Calendar.MONTH, Calendar.JANUARY); // 1월로 변경
        calendar.set(Calendar.DAY_OF_MONTH, 11); // 11일로 변경

        System.out.println("설정된 날짜: " + calendar.getTime());

        // 날짜 및 시간 계산하기
        calendar.add(Calendar.DAY_OF_MONTH, 7); // 7일 추가
        System.out.println("일주일 후: " + calendar.getTime());
    }
}