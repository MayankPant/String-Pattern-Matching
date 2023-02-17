import java.util.Scanner;

public class WildcardMatching {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        String p = sc.next();

        System.out.println(isMatch(s, p));
    }

    private static boolean isMatch(String s, String p) {
        /*
        * This is a dp optimisation to the recursive approach. Here we use an (n + 1 * m + 1) boolean matrix where
        * dp[i][j] = whether the substring from 0 to i - 1 in s can be matched with the pattern from 0 to m - 1 in p
        * here the matrix is of the size (n + 1 * m + 1) because both the p and s can be empty
        * */
        int n = s.length();
        int m = p.length();
        boolean[][] dp = new boolean[n + 1][m + 1]; // considering empty strings

        // base conditions
        dp[0][0] = true; // when both s and p are empty

        /*
        * when s is empty no matter the pattern can only be of the form *, **, ***, ****... and so on and nothing else
        * so we fill the very first row as follows
        * */
        for (int i = 1; i < m + 1; i++) {
            if(p.charAt(i - 1) == '*')
                dp[0][i] = true;
            else
                break;
        }

        /*
        * For the first column when we have empty pattern no matter what the string is will never match
        * */
        for (int i = 1; i < n + 1; i++) {
            dp[i][0] = false;
        }

        // filling the table

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                char ch = s.charAt(i - 1);
                char transition = p.charAt(j - 1);

                if(transition == '*'){
                    // matching a single or multiple or no characters
                    dp[i][j] = dp[i - 1][j - 1] || dp[i - 1][j] || dp[i][j - 1];
                }
                else if(transition == '?' || transition == ch){
                    dp[i][j] = dp[i - 1][j - 1];  // whether you previous is matched or not
                }
                else
                    dp[i][j] = false;
            }

        }

        return dp[n][m];

    }
}
