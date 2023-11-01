public class Main {
    public static void main(String[] args) {
        // 1. static double abs(double a)
        //      static float abs(float f)
        //      static int abs(int f)
        //      static long abs(long f)
        // 주어진 값의 절대값을 반환한다.
        int i1 = Math.abs(-10);
        double d1 = Math.abs(-10.0);
        System.out.println(" i1 = " + i1 + " d1 = " + d1); //  i1 = 10 d1 = 10.0

        // 2. static double ceil(double a)
        // 주어진 값을 올림하여 반환한다.
        double d2 = Math.ceil(10.1);
        double d3 = Math.ceil(-10.1);
        double d4 = Math.ceil(10.0000015);
        System.out.println("d2 = " + d2 + " d3 = " + d3 + " d4 = " + d4); // d2 = 11.0 d3 = -10.0 d4 = 11.0


        // 3. static double floor(double a)
        // 주어진 값을 버림하여 반환한다.
        double d5 = Math.floor(10.8);
        double d6 = Math.floor(-10.8);
        System.out.println("d5 = " + d5 + " d6 = " + d6); // d5 = 10.0 d6 = -11.0

        // 4. static double max(double a, double b)
        //      static float max(float a, float b)
        //      static int max(int a, int b)
        //      static long max(long a, long b)
        // 주어진 두 값을 비교하여 큰 쪽을 반환한다.
        double d7 = Math.max(9.5, 9.50001);
        int i2 = Math.max(0, -1);
        System.out.println("d7 = " + d7 + " i2 = " + i2); // d7 = 9.50001 i2 = 0


        // 5. static double min(double a, double b)
        //      static float min(float a, float b)
        //      static int min(int a, int b)
        //      static long min(long a, long b)
        double d8 = Math.min(9.5, 9.50001);
        int i3 = Math.min(0, -1);
        System.out.println("d8 = " + d8 + " i3 = " + i3); // d8 = 9.5 i3 = -1

        // 6. static double random()
        // 0.0 ~ 1.0범위의 임의의 double값을 반환한다.
        // (1.0은 범위에 포함되지 않는다.)
        double d9 = Math.random();
        int i4 = (int)(Math.random() * 10) + 1;
        System.out.println("d9 = " + d9 + " i4  = " + i4); // d9 = 0.48526749990551155 i4  = 5

        // 7. static double rint(double a)
        // 주어진 double값과 가장 가까운 정수값을 double형으로 반환한다.
        double d10 = Math.rint(5.5);
        double d11 = Math.rint(5.1);
        double d12 = Math.rint(-5.5);
        double d13 = Math.rint(-5.1);
        System.out.println("d10 = " + d10 + " d11 = " + d11 + " d12 = " + d12 + " d13 = " + d13);
        // d10 = 6.0 d11 = 5.0 d12 = -6.0 d13 = -5.0

        // 8. static long round(double a)
        //      static long round(float a)
        // 소수점 첫째자리에서 반올림한 정수값(long)을 반환한다.
        // 매개변수의 값이 음수인 경우, round()와 rint()의 결과가 다르다.
        long l1 = Math.round(5.5);
        long l2 = Math.round(5.11);
        long l3 = Math.round(-5.5);
        long l4 = Math.round(-5.1);
        System.out.println("l1 = " + l1 + " l2 = " + l2 + " l3 = " + l3 + " l4 = " + l4);
        // l1 = 6 l2 = 5 l3 = -5 l4 = -5
        double d14 = 90.7552;
        double d15 = Math.round(d14 * 100) / 100.0;
        System.out.println("d15 = " + d15); // d15 = 90.76

    }
}