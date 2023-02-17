import java.util.Scanner;

public class WildcardMatchingRecursive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        String p = sc.next();

        System.out.println(isMatch(s, p));
    }

    private static boolean isMatch(String s, String p) {
        /*
        * This is a recursive approach to the wildcard expression matching technique used in quite a lot of
        * applications like mysql.
        * Although recursive will result in a tle for larger inputs this file is rather used to understand how the
        * recurrence relation for dp is later built from it.
        * This problem is also from leetcode 44 where:
        *
        *
        *  * means we can match any no of character to this including an empty string
        *  ? means we can match any single character
        *
        * */
        if(s.length() == 0 && p.length() == 0)
            return true;

        return wildcardRecursive(s, p, 0, 0);

    }

    private static boolean wildcardRecursive(String s, String p, int i, int j) {
        if(i == s.length() && j == p.length())
            return true;

        /*
        * for cases where we have patterns including *, **, ***...
        *
        * */
        if(i == s.length()) {
            for (int k = j; j < p.length(); j++) {
                if (p.charAt(k) != '*')
                    return false;
            }
            return true;
        }

        if(j == p.length())
            return false;

        char ch = s.charAt(i);
        char transition = p.charAt(j);

        /*
         * We can either map any single character to it or we can map multiple characters including an empty string
         * to it. This can be understood by a creating a finite state machine where states transition can
         * occur only by using a specific character from the pattern string. Encountering a * means either we
         * can match a single character and move to the next state, or match multiple characters and staying
         * at the state.
         *
         * Encountering a ? means we can match the current character and move on in both pattern and string.
         * This is also the case when we encounter the same character as pattern i.e ch == transition
         * */

        if(transition == '*'){

            boolean singleMatch = wildcardRecursive(s, p, i + 1, j + 1);
            boolean multipleMatch = wildcardRecursive(s, p, i + 1, j);
            boolean emptyMatch = wildcardRecursive(s, p, i, j + 1); // for when we need to match empty strings

            return singleMatch || multipleMatch || emptyMatch;
        }
        else if(transition == '?' || transition == ch)
            return wildcardRecursive(s, p, i + 1, j + 1);
        else
            return false;
    }
}
/*
ex : returns true
abcabczzzde
*abc???de*
* */
