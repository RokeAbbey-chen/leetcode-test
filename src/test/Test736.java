package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

public class Test736 {

    private static final Pattern NUM_PATTERN = Pattern.compile("^-?\\d+$");
    public int evaluate(String expression) {
        ArrayList<HashMap<String, Integer>> variables = new ArrayList<>();

        String[] symbols = expression.split(" +|(?<=\\()(?!= )|(?<! )(?=\\))");
        int len = symbols.length;

        for (int i = 0; i < len; i ++) {
            String s = symbols[i];
            if ("(".equals(symbols[i])) continue;
            if ("let".equals(s)) {
                return evalLet(symbols, i, len - 1, variables)[0];
            } else if ("add".equals(symbols[i]) || "mult".equals(symbols[i])) {
                return evalAM("add".equals(symbols[i]), symbols, i, len - 1, variables)[0];
            } else {
                return Integer.parseInt(symbols[i]);
            }
        }

        return 0;
    }


    private int[] evalLet(String[] symbols, int start, int end, ArrayList<HashMap<String, Integer>> variables) {
        assert "let".equals(symbols[start]);
        boolean bSetVariable = false;
        String curV = null;
        HashMap<String, Integer> curVariableMap = new HashMap<>();
        variables.add(curVariableMap);
        int size = variables.size();
        for (int i = start + 1; i <= end;) {
            if ("(".equals(symbols[i])) {
                i ++;
                continue;
            }
            if ("add".equals(symbols[i]) || "mult".equals(symbols[i])) {
                int[] subResult = evalAM("add".equals(symbols[i]), symbols, i, end, variables);
                i = subResult[1] + 1;
                if (!bSetVariable) {
                    assert ")".equals(symbols[i]);
                    variables.remove(size - 1);
                    return new int[]{subResult[0], i};
                }
                else {
                    curVariableMap.put(curV, subResult[0]);
                    bSetVariable = false;
                }

            } else if ("let".equals(symbols[i])) {
                int[] subResult = evalLet(symbols, i, end, variables);
                i = subResult[1] + 1;
                if (!bSetVariable) {
                    assert ")".equals(symbols[i]);
                    variables.remove(size - 1);
                    return new int[]{subResult[0], i};
                }
                else {
                    curVariableMap.put(curV, subResult[0]);
                    bSetVariable = false;
                }
            } else if (bSetVariable) {
                int v;
                if (!NUM_PATTERN.matcher(symbols[i]).find())
                    v = getVariable(symbols[i], variables);
                else
                    v = Integer.parseInt(symbols[i]);
                curVariableMap.put(curV, v);
                bSetVariable = false;
                i ++;
            } else {
                if (i + 1 > end || ")".equals(symbols[i + 1]) ) {
                    System.out.println("i = " + i + ", symbols[i] = " + symbols[i]);
                    int v = NUM_PATTERN.matcher(symbols[i]).find() ? Integer.parseInt(symbols[i]) : getVariable(symbols[i], variables);
                    variables.remove(size - 1);
                    return new int[]{v, i + 1};
                }
                else {
                    curV = symbols[i];
                    bSetVariable = true;
                    i++;
                }
            }
        }
        return null;
    }

    private Integer getVariable(String name, ArrayList<HashMap<String, Integer>> vars) {
        int size = vars.size();
        for (int i = size - 1; i >= 0; i --) {
            HashMap<String, Integer> map = vars.get(i);
            Integer v = map.get(name);
            if (null != v)
                return v;
        }
        return null;
    }
    private int[] evalAM(boolean addOrMult, String[] symbols, int start, int end, ArrayList<HashMap<String, Integer>> variables) {
        assert addOrMult && "add".equals(symbols[start])
                || !addOrMult && "mult".equals(symbols[start]);

        int step = 0;
        int firstValue = 0;
        for (int i = start + 1; i <= end;) {
            if ("(".equals(symbols[i])) {
                i ++;
                continue;
            }
            int[] subResult;
            if ("add".equals(symbols[i]) || "mult".equals(symbols[i])) {
                subResult = evalAM("add".equals(symbols[i]), symbols, i, end, variables);
                i = subResult[1] + 1;
            } else if ("let".equals(symbols[i])) {
                subResult = evalLet(symbols, i, end, variables);
                i = subResult[1] + 1;
            } else {
                int v = !NUM_PATTERN.matcher(symbols[i]).find() ? getVariable(symbols[i], variables) : Integer.parseInt(symbols[i]);
                subResult = new int[]{v, i + 1};
                i ++;
            }
            if (1 == step) {
                int secondValue = subResult[0];
                assert ")".equals(symbols[i]);
                if (addOrMult) {
                    return new int[]{firstValue + secondValue, i};
                }
                return new int[]{firstValue * secondValue, i};

            } else {
                firstValue = subResult[0];
                step = 1;
            }

        }
        return null;
    }

    public static void main(String[] args) {
        String str = "(let a 1 b 2 e (add 3 4) e)";
        str = "(let x 2 (mult x (let x 3 y 4 (add x y))))";
        str = "(let x 2 (let x 3 7))";
        str = "( let x (add 3 4) d x (add d 3))";
        str = "(let x 7 -12)";
        str = "(let y 2 x (let q 2 z y 2) x)";


        Test736 test = new Test736();
        int result = test.evaluate(str);
        System.out.println(result);
    }

}
