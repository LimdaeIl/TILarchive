package ch11;

interface A {
    static void abc() {
        System.out.println("A 인터페이스의 abc()");
    }
}

public class finalEx {
    public static void main(String[] args) {
        A.abc();
    }
}
