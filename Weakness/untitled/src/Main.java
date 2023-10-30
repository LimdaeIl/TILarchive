// 외부 클래스 정의
public class Main {
    private int outerField = 10; // 외부 클래스의 멤버 변수

    // 멤버 내부 클래스 정의
    class InnerClass {
        void displayOuterField() {
            System.out.println("outerField: " + outerField); // 외부 클래스의 멤버 변수에 접근
        }
    }

    public static void main(String[] args) {
        // 외부 클래스 객체 생성
        Main outer = new Main();

        // 내부 클래스 객체 생성
        InnerClass inner = outer.new InnerClass(); // 외부 클래스 객체를 이용하여 내부 클래스 객체 생성

        // 내부 클래스 메서드 호출
        inner.displayOuterField(); // 내부 클래스의 메서드에서 외부 클래스의 멤버 변수에 접근
    }
}