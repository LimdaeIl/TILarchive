public class Main {
    public static void main(String[] args) {

        // 1. String(String s)
        String s1 = new String("Hello");
        System.out.println("s1 = " + s1); // s1 = Hello

        // 2. String(char[] value)
        char[] c1 = {'H', 'e', 'l', 'l', 'o'};
        String s2 = new String(c1);
        System.out.println("s2 = " + s2); // s2 = Hello

        // 3. String(StringBuffer buf)
        StringBuffer sb1 = new StringBuffer("Hello");
        String s3 = new String(sb1);
        System.out.println("sb1 = " + sb1 + " s3 = " + s3); // sb1 = Hello s3 = Hello

        // 4. char charAt(int index)
        String s4 = "Hello";
        String n1 = "0123456";
        char c2 = s4.charAt(1);
        char c3 = n1.charAt(1);
        System.out.println("c2 = " + c2 + " c3 = " + c3); // c2 = e c3 = 1

        // 5. int compareTo(String str)
        int i1 = "aaa".compareTo("aaa");
        int i2 = "aaa".compareTo("bbb");
        int i3 = "bbb".compareTo("aaa");
        System.out.println("i1 = " + i1 + " i2 = " + i2 + " i3 = " + i3); // i = 0 i2 = -1 i3 = 1
        int i4 = "aaa".compareTo("ddd");
        int i5 = "eee".compareTo("aaa");
        System.out.println("i4 = " + i4 + " i5 = " + i5); // i4 = -3 i5 = 4

        // 6. String concat(String str)
        String s5 = "Hello";
        String s6 = s5.concat(" World");
        System.out.println("s6 = " + s6); // s6 = Hello World

        // 7. boolean contains(CharSequence s)
        String s7 = "abcdefg";
        boolean b1 = s7.contains("bc");
        System.out.println("b1 = " + b1); // b1 = true

        // 8. boolean endWith(String suffix)
        String file1 = "Hello.txt";
        boolean b2 = file1.endsWith("txt");
        System.out.println("b2 = " + b2); // b2 = true

        // 9. boolean equals(Object obj)
        String s8 = "Hello";
        boolean b3 = s8.equals("Hello");
        boolean b4 = s8.equals("hello");
        System.out.println("b3 = " + b3 + " b4 = " + b4); // b3 = true b4 = false

        // 10. boolean equalsIgnoreCase(String str)
        String s9 = "Hello";
        boolean b5 = s9.equalsIgnoreCase("HELLO");
        boolean b6 = s9.equalsIgnoreCase("hello");
        System.out.println("b5 = " + b5 + " b6 = " + b6); // b5 = true b6 = true

        // 11. int indexOf(int ch)
        String s10 = "Hello";
        int idx1 = s10.indexOf('o');
        int idx2 = s10.indexOf('k');
        System.out.println("idx1 = " + idx1 + " idx2 = " + idx2); // idx1 = 4 idx2 = -1

        // 12. int indexOf(int ch, int pos)
        String s11 = "Hello";
        int idx3 = s11.indexOf('e', 0);
        int idx4 = s11.indexOf('e', 2);
        System.out.println("idx3 = " + idx3 + " idx4 = " + idx4); // idx3 = 1 idx4 = -1

        // 13. int indexOf(String str)
        String s12 = "ABCDEFG";
        int idx5 = s12.indexOf("CD");
        System.out.println("idx = " + idx5); // idx = 2

        // 14. String intern()
        String s13 = new String("abc");
        String s14 = new String("abc");

        boolean b7 = (s13 == s14);
        boolean b8 = s13.equals(s14);
        boolean b9 = (s13.intern() == s14.intern());
        System.out.println("b7 = " + b7 + " b8 = " + b8 + " b9 = " + b9); // b7 = false b8 = true b9 = true

        // 15. int lastIndexOf(int ch)
        String s15 = "java.lang.Object";
        int idx6 = s15.lastIndexOf('.');
        int idx7 = s15.indexOf('.');
        System.out.println("idx6 = " + idx6 + " idx7 = " + idx7); // idx6 = 9 idx7 = 4

        // 16. int lastIndexOf(String str)
        String s16 = "java.lang.java";
        int idx8 = s16.lastIndexOf("java");
        int idx9 = s16.indexOf("java");
        System.out.println("idx8 = " + idx8 + " idx9 = " + idx9); // idx8 = 10 idx9 = 0

        // 17. int length()
        String s17 = "Hello";
        int length = s17.length();
        System.out.println("length = " + length); // length = 5

        // 18. String replace(char old, char nw)
        String s18 = "Hello";
        String s19 = s18.replace('H', 'C');
        System.out.println("s19 = " + s19); // s19 = Cello

        // 19. String replace(CharSequence old, CharSequence nw)
        String s20 = "Hellollo";
        String s21 = s20.replace("ll", "LL");
        System.out.println("s21 = " + s21); // s21 = HeLLoLLo


        // 20 .String replaceAll(String regex, String replacement)
        String ab1 = "AABBAABB";
        String r1 = ab1.replaceAll("BB", "bb");
        System.out.println("r1 = " + r1); // r1 = AAbbAAbb

        // 21. String replaceFirst(String regex, String replacement)
        String ab2 = "AABBAABB";
        String r2 = ab2.replaceFirst("BB", "bb");
        System.out.println("r2 = " + r2); // r2 = AAbbAABB

        // 22. String[] split(String regex)
        String animals1 = "dog,cat,bear";
        String[] arr1 = animals1.split(",");
        for (String s : arr1) {
            System.out.printf("%s ", s); // dog cat bear
        }

        System.out.println();

        // 23. String[] split(String regex, int limit)
        String animals2 = "dog,cat,bear";
        String[] arr2 = animals2.split(",", 2);
        for (String s : arr2) {
            System.out.printf("%s ", s); // dog cat,bear
        }

        // 24. boolean startsWith(String prefix)
        String s22 = "java.lang.Object";
        boolean b10 = s22.startsWith("java");
        boolean b11 = s22.startsWith("lang");
        System.out.println("b10 = " + b10 +  " b11 = " + b11); // dog cat,bear b10 = true b11 = false

        // 25.  String substring(int begin), String substring(int begin, int end)
        String s23 = "java.lang.Object";
        String c4 = s23.substring(10);
        String p1 = s23.substring(5, 9);
        System.out.println("c4 = " + c4 + " p1 = " + p1); // c4 = Object p1 = lang

        // 26. String toLowerCase()
        String s24 = "Hello";
        String s25 = s24.toLowerCase();
        System.out.println("s25 = " + s25); // s25 = hello

        // 27. String toString()
        String s26 = "Hello";
        String s27 = s26.toString();
        System.out.println("s27 = " + s27); // s27 = Hello

        // 28. String toUpperCase()
        String s28 = "Hello";
        String s29 = s28.toUpperCase();
        System.out.println("s29 = " + s29); // s29 = HELLO

        // 29. String trim()
        String s30 = "   Hello World   ";
        String s31 = s30.trim();
        System.out.println("s31 = " + s31); // s31 = Hello World

        // 30. static String valueOf(boolean b)
        //      static String valueOf(char c)
        //      static String valueOf(int i)
        //      static String valueOf(long l)
        //      static String valueOf(float f)
        //      static String valueOf(double d)
        //      static String valueOf(Object o)
        String b12 = String.valueOf(true);
        String c5 = String.valueOf('a');
        String i6 = String.valueOf(100);
        String l1 = String.valueOf(100L);
        String f1 = String.valueOf(10f);
        String d1 = String.valueOf(10.0);
        System.out.println("b12 = " + b12 + " c5 = " + c5 + " i6 = " + i6 + " l1 = " + l1 +  " f1 = " + f1 +  " d1 = " + d1);
        // b12 = true c5 = a i6 = 100 l1 = 100 f1 = 10.0 d1 = 10.0
        java.util.Date dd = new java.util.Date();
        String date = String.valueOf(dd);
        System.out.println("date = " + date); // date = Wed Nov 01 16:55:07 KST 2023
    }
}