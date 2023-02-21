import java.util.Scanner;

public class RegularExpressionsMatching {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        String p = sc.next();

        System.out.println(isMatch(s, p));
    }

    private static boolean isMatch(String s, String p) {

        int n = s.length() + 1;
        int m = p.length() + 1;

        boolean[][] dp = new boolean[n][m];

        dp[0][0] = true; // empty pattern and string


        /*
            first row is empty string with non empty pattern
        * Here you need to be careful because pattern of the form a*b*, a*b*c* or a* can still match an empty
        * string
        * */
        for (int i = 1; i < m; i++) {
            if(p.charAt(i - 1) == '*')
                dp[0][i] = dp[0][i - 2];
            else
                dp[0][i] = false;
        }

        /*
        * For an empty pattern and non empty string we will always get a false
        * this loop is optional and is only written to make the reader understand what the author is doing.
        * this is because java already initialises boolean array with false every
        * */

        for (int i = 1; i < n; i++) {
            dp[i][0] = false;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                char ch = s.charAt(i - 1);
                char transition = p.charAt(j - 1);

                if(transition == '*'){
                    char prev = p.charAt(j - 2);
                    if(ch == prev)
                        dp[i][j] = dp[i - 1][j] ||  dp[i][j - 2];
                    else
                        dp[i][j] = dp[i][j - 2];
                }
                else if(transition == '.' || transition == ch)
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = false;
            }
        }


        return dp[n - 1][m - 1];
    }
}
