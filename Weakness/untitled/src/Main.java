public class Main {
    public static void main(String[] args) {
        String sentence = "This is a sample sentence.";
        String[] words = sentence.split(" "); // 공백을 기준으로 문자열 분할
        String subString = sentence.substring(5, 10); // 5번째 문자부터 10번째 문자까지 추출

        for (String word : words) {
            System.out.println("word = " + word);
        }

        System.out.println("subString = " + subString);
    }
}