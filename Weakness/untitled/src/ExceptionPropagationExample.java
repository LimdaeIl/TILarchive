public class ExceptionPropagationExample {
    public static void main(String[] args) {
        try {
            performOperation();
        } catch (CustomException e) {
            System.out.println("상위 메서드에서 예외 처리: " + e.getMessage());
        }
    }

    public static void performOperation() throws CustomException {
        try {
            // 어떤 조건에서 예외 발생시키기
            int age = -5;
            if (age < 0) {
                throw new CustomException("나이 음수");
            }
        } catch (Exception e) {
            // 예외를 다시 던짐
            throw e;
        }
    }
}