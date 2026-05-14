/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.factor.util;

import java.lang.constant.Constable;
import java.util.LinkedList;
import java.util.Stack;
import moiland.baseland.factor.face.FactorStdVoFace;
import moiland.baseland.factor.util.EnumFactorComparison;
import moiland.baseland.factor.util.EnumFactorLogic;

public class ReversePolishNotationTool {
    public static LinkedList<Object> getNotation(FactorStdVoFace factorStdVoFace, double d) {
        LinkedList<Object> linkedList = new LinkedList<Object>();
        Stack<EnumFactorLogic> stack = new Stack<EnumFactorLogic>();
        ReversePolishNotationTool.addFormula(null, d, EnumFactorComparison.findSelfByString(factorStdVoFace.getA_symbol()), factorStdVoFace.getA_digital(), linkedList, stack);
        EnumFactorLogic enumFactorLogic = EnumFactorLogic.findSelfByString(factorStdVoFace.getAb_logic());
        EnumFactorLogic enumFactorLogic2 = EnumFactorLogic.findSelfByString(factorStdVoFace.getBc_logic());
        EnumFactorLogic enumFactorLogic3 = EnumFactorLogic.findSelfByString(factorStdVoFace.getCd_logic());
        if (enumFactorLogic != null) {
            ReversePolishNotationTool.addFormula(enumFactorLogic, d, EnumFactorComparison.findSelfByString(factorStdVoFace.getB_symbol()), factorStdVoFace.getB_digital(), linkedList, stack);
        }
        if (enumFactorLogic2 != null) {
            ReversePolishNotationTool.addFormula(enumFactorLogic2, d, EnumFactorComparison.findSelfByString(factorStdVoFace.getC_symbol()), factorStdVoFace.getC_digital(), linkedList, stack);
        }
        if (enumFactorLogic3 != null) {
            ReversePolishNotationTool.addFormula(enumFactorLogic3, d, EnumFactorComparison.findSelfByString(factorStdVoFace.getD_symbol()), factorStdVoFace.getD_digital(), linkedList, stack);
        }
        while (!stack.empty()) {
            linkedList.add((Object)stack.pop());
        }
        return linkedList;
    }

    private static void addFormula(EnumFactorLogic enumFactorLogic, double d, EnumFactorComparison enumFactorComparison, double d2, LinkedList<Object> linkedList, Stack<EnumFactorLogic> stack) {
        if (enumFactorLogic != null) {
            if (stack.empty()) {
                stack.push(enumFactorLogic);
            } else if (stack.peek().compareTo(enumFactorLogic) < 0) {
                stack.push(enumFactorLogic);
            } else {
                linkedList.add((Object)stack.pop());
                stack.push(enumFactorLogic);
            }
        }
        linkedList.add(d);
        linkedList.add(d2);
        linkedList.add((Object)enumFactorComparison);
    }

    public static boolean calculateNotation(LinkedList<Object> linkedList) throws Exception {
        Stack<Object> stack = new Stack<Object>();
        for (Object e : linkedList) {
            Constable constable;
            Constable constable2;
            Enum enum_;
            if (e instanceof EnumFactorComparison) {
                enum_ = (EnumFactorComparison)((Object)e);
                constable2 = (Double)stack.pop();
                constable = (Double)stack.pop();
                stack.push(ReversePolishNotationTool.rangeCompare(constable, (EnumFactorComparison)enum_, (Double)constable2));
                continue;
            }
            if (e instanceof EnumFactorLogic) {
                enum_ = (EnumFactorLogic)((Object)e);
                constable2 = (Boolean)stack.pop();
                constable = (Boolean)stack.pop();
                stack.push(ReversePolishNotationTool.groupCompare((Boolean)constable, (EnumFactorLogic)enum_, (Boolean)constable2));
                continue;
            }
            stack.push(e);
        }
        if (stack.size() != 1) {
            throw new Exception("\u7121\u6cd5\u8a08\u7b97....." + stack);
        }
        return (Boolean)stack.pop();
    }

    private static Object rangeCompare(double d, EnumFactorComparison enumFactorComparison, double d2) {
        switch (enumFactorComparison) {
            case EQUAL: {
                return d == d2;
            }
            case GREATER_THAN: {
                return d > d2;
            }
            case GREATER_THAN_OR_EQUAL: {
                return d >= d2;
            }
            case LESS_THAN: {
                return d < d2;
            }
            case LESS_THAN_OR_EQUAL: {
                return d <= d2;
            }
        }
        return false;
    }

    private static Object groupCompare(Boolean bl, EnumFactorLogic enumFactorLogic, Boolean bl2) {
        switch (enumFactorLogic) {
            case AND: {
                return bl != false && bl2 != false;
            }
            case OR: {
                return bl != false || bl2 != false;
            }
        }
        return false;
    }
}

