public class Main {
    // 계절을 나타내는 열거형 정의
    enum Season {
        SPRING, SUMMER, FALL, WINTER
    }

    public static void main(String[] args) {
        // 열거형 상수 사용
        Season currentSeason = Season.SUMMER;
        System.out.println("Current season is: " + currentSeason);

        // 열거형 상수 비교
        if (currentSeason == Season.SUMMER) {
            System.out.println("It's a great time for outdoor activities!");
        }

        // switch 문으로 열거형 사용
        switch (currentSeason) {
            case SPRING:
                System.out.println("Spring is the season of renewal.");
                break;
            case SUMMER:
                System.out.println("Summer is all about fun in the sun.");
                break;
            case FALL:
                System.out.println("Fall brings colorful leaves and cooler weather.");
                break;
            case WINTER:
                System.out.println("Winter is the time for snow and holiday festivities.");
                break;
        }

        // 열거형의 모든 상수 순회
        System.out.println("All seasons:");
        for (Season season : Season.values()) {
            System.out.println(season);
        }
    }
}
