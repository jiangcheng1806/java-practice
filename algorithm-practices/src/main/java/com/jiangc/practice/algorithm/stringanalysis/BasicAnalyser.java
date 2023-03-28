package com.jiangc.practice.algorithm.stringanalysis;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class BasicAnalyser {

	private List<String> predicationList = new ArrayList<String>();

    public boolean isSentence(String exp) {
        exp = exp.trim();

        if (StringUtils.isEmpty(exp)) {
            return false;
        }
        exp = StringUtils2.trimBracket(exp);
        if (isAtomicSentence(exp)) {
            return true;
        }

        if (exp.charAt(0) == '!') {
            return isSentence(exp.substring(1));
        } else if (exp.charAt(0) == '~' || exp.charAt(0) == '@') {
            // means any or exist, must followed by VAR
            String tmp = "";
            int i = 0;
            for (i = 1; i < exp.length(); i++) {
                if ((exp.charAt(i) >= 'A' && exp.charAt(i) <= 'Z') || exp.charAt(i) == '_') {
                    tmp = tmp + exp.charAt(i);
                } else {
                    break;
                }
            }
            if (i >= exp.length()) {
                // means ~X
                return false;
            } else {
                return isSentence(exp.substring(i));
            }
        } else {
            // means S1 op S2
            StringBuffer lastSentence = new StringBuffer();
            int leftBracketNum = 0;
            int rightBracketNum = 0;
            for (int i = 0; i < exp.length(); i++) {
                if (isOperator(exp.charAt(i))) {
                    if (leftBracketNum == rightBracketNum) {
                        if (i >= exp.length()) {
                            return false;
                        } else {
                            if (isSentence(lastSentence.toString()) && isSentence(exp.substring(i + 1))) {
                                return true;
                            } else {
                                return false;
                            }
                        }
                    }

                } else {
                    if (exp.charAt(i) == '(') {
                        leftBracketNum++;
                    } else if (exp.charAt(i) == ')') {
                        rightBracketNum++;
                    }
                    lastSentence = lastSentence.append(exp.charAt(i));
                }
            }

        }
        return false;

    }

    private boolean isOperator(char x) {
        return x == '|' || x == '&' || x == '>' || x == '=';
    }

    public boolean isVar(String expression) {

        expression.trim();
        expression = StringUtils2.trimBracket(expression);
        if (StringUtils.isEmpty(expression)) {
            return false;
        }
        if (expression.charAt(0) >= 'A' && expression.charAt(0) <= 'Z') {
            for (int i = 1; i < expression.length(); i++) {
                if ((expression.charAt(i) >= 'a' && expression.charAt(0) <= 'z') || (expression.charAt(i) >= 'A' && expression.charAt(i) <= 'Z')
                        || expression.charAt(i) == '_') {
                    // do nothing
                } else {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean isConstant(String expression) {
        expression.trim();
        expression = StringUtils2.trimBracket(expression);
        if (StringUtils.isEmpty(expression)) {
            return false;
        }
        if (expression.charAt(0) >= 'a' && expression.charAt(0) <= 'z') {
            for (int i = 1; i < expression.length(); i++) {
                if ((expression.charAt(i) >= 'a' && expression.charAt(0) <= 'z') || (expression.charAt(i) >= 'A' && expression.charAt(i) <= 'Z')
                        || expression.charAt(i) == '_') {
                    // do nothing
                } else {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean isLikeFormFunc(String expression, boolean upperCase) {
        if (StringUtils.isEmpty(expression)) {
            return false;
        }
        char startLetterFrom = upperCase ? 'A' : 'a';
        char startLetterTo = upperCase ? 'Z' : 'z';
        expression.trim();
        expression = StringUtils2.trimBracket(expression);
        expression = expression.replaceAll(" ", "");
        if (expression.charAt(0) >= startLetterFrom && expression.charAt(0) <= startLetterTo) {
            int leftBracket = 0;
            int rightBracket = 0;
            StringBuffer lastString = new StringBuffer();
            for (int i = 0; i < expression.length(); i++) {
                if (leftBracket == 0) {
                    if (!((expression.charAt(i) >= 'A' && expression.charAt(i) <= 'Z')
                            || (expression.charAt(i) >= 'a' && expression.charAt(i) <= 'z') || expression.charAt(i) == '_' || expression.charAt(i) == '(')) {
                        return false;// illegal
                    }
                }
                if (expression.charAt(i) == '(') {
                    leftBracket++;
                    if (leftBracket > 1) {
                        lastString.append(expression.charAt(i));
                    }
                } else if (expression.charAt(i) == ')') {
                    if (i == expression.length() - 1) {
                        // last one
                        if (!isVar(lastString.toString()) && !isConstant(lastString.toString()) && !isFunction(lastString.toString())) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                    rightBracket++;
                    lastString.append(expression.charAt(i));
                } else if (expression.charAt(i) == ',') {
                    if (leftBracket - rightBracket == 1) {
                        if (!isVar(lastString.toString()) && !isConstant(lastString.toString()) && !isFunction(lastString.toString())) {
                            return false;
                        }
                        lastString = new StringBuffer();
                    } else {
                        lastString.append(expression.charAt(i));
                    }
                } else {
                    if (leftBracket != 0) {
                        lastString.append(expression.charAt(i));
                    }
                }
            }
        }
        return false;
    }

    public boolean isAtomicSentence(String expression) {
        // Friends(father_of(david),mother_of(peter))
        boolean isJustification = isLikeFormFunc(expression, true);
        if (isJustification) {
            predicationList.add(expression);
        }
        return isJustification;
    }

    public boolean isFunction(String expression) {
        return isLikeFormFunc(expression, false);
    }

    public static void main(String[] args) {
        BasicAnalyser x = new BasicAnalyser();
        String m = "Test(test(t),a,b)&Fuck(st,m)>Monday(today)";
        System.out.println(x.isSentence(m));
        for (int i = 0; i < x.getPredicationList().size(); i++) {
            System.out.println(x.getPredicationList().get(i));
            
        }
    }

    public List<String> getPredicationList() {
        return predicationList;
    }

    public void setPredicationList(List<String> predicationList) {
        this.predicationList = predicationList;
    }
}
