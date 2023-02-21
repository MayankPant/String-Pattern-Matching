import java.util.Scanner;

public class RegularExpressionMatchingRecursive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        String p = sc.next();

        System.out.println(isMatch(s, p, s.length() - 1, p.length() - 1));
    }

    private static boolean isMatch(String s, String p, int i, int j) {
        if(i < 0 && j < 0)
                return true;
        if(j < 0 && i > 0)
                return false;

        if(i < 0 && p.charAt(j) == '*') // for cases like s = "" and p = a*, a*b*, or a*b*c*
                return isMatch(s, p, i, j - 2);

        if(i < 0 && p.charAt(j) != '*')
                return false;

        char ch = s.charAt(i);
        char transition = p.charAt(j);

        if(transition == '*'){
            char prev = p.charAt(j - 1);
            // two cases occur
            /*
            *  case 1: when the character preceding * occurs one or multiple times in which case you on move i
            *  case 2 : when the character preceding * occurs 0 times and the corresponding characters from
            *  i and j do not match. In this case we simple move j
            *
            * */
            if(ch == prev)
                return isMatch(s, p, i - 1, j) || isMatch(s, p, i, j - 2); // match a char or dont match it at all
            else
                return isMatch(s, p, i, j - 2); // zero occurrence of previous character

        }
        else if(transition == '.' || transition == ch)
                return isMatch(s, p, i - 1, j - 1);
        else
            return false;

    }

}
/*
 * Test  case: aaa
               ab*a*c*a
 *
 *
 */
