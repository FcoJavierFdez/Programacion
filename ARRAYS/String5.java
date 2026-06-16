public class String5 {
    public static void main(String[] args) {
        String str = "abracadabra";
        char charToCount = 'a';
        int count = 0;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == charToCount) {
                count++;
            }
        }

        System.out.println("El carácter '" + charToCount + "' aparece " + count + " veces en la cadena \"" + str + "\".");
    }
}
