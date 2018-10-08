import java.util.LinkedList;

/**
 * Created by yuxinfeng on 17/4/18.
 */
public class SolutionFivfth {

    /**
     * 1. 遇到左括号压栈
     * 2. 遇到又括号把左括号出站,更新括号数
     */
    public static int solution(String s) {
        if (s.length() < 2) {
            return 0;
        }

        LinkedList<Integer> stack = new LinkedList<>();
        int result = 0;
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ')' && stack.size() > 1 && s.charAt(stack.peek()) == '(') {
                stack.pop();
                result = Math.max(result, i - stack.peek());
            } else {
                stack.push(i);
            }
        }
        return result;

    }

    public static int solution(String s, int type) {
        if (s.length() < 2) {
            return 0;
        }
        LinkedList<Integer> stack = new LinkedList<Integer>();
        int max = 0;
        int left = -1;
        for (int j = 0; j < s.length(); j++) {
            if (s.charAt(j) == '(') {
                stack.push(j);
            } else {
                if (stack.isEmpty()) {
                    left = j;
                } else {
                    stack.pop();
                    if (stack.isEmpty()) {
                        max = Math.max(max, j - left);
                    } else {
                        max = Math.max(max, j - stack.peek());
                    }
                }
            }
        }
        return max;

    }

    public static void main(String[] args) {
        System.out.println(solution("(()"));
    }
}
