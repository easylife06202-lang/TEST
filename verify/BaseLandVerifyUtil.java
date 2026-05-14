/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.verify;

import moiland.baseland.verify.VerifyException;

public class BaseLandVerifyUtil {
    private static final int ZERO = 0;
    private static final String REGEX_CITY = "[A-Z]";
    private static final String REGEX_OFCE = "[A-Z][0A-Z]";
    private static final String REGEX_OFCE_OF_PART = "[A-Z]{1}[0A-Z]?";
    private static final String REGEX_DIST = "[0][1-9]|[1-3][0-9]";
    private static final String REGEX_AA48 = "[0-9]{4}";
    private static final String REGEX_YEAR = "[01][0-9]{2}";
    private static final String REGEX_MONTH = "^0[1-9]|1[012]$";
    private static final String REGEX_YEAR_MONTH = "^[01][0-9]{2}(0[1-9]|1[012])$";
    private static final String REGEX_DATE = "^[01][0-9]{2}(0[1-9]|1[012])(0[1-9]|[12][0-9]|[3][01])$";
    private static final String REGEX_FACTOR_VERSION = "A3B[ABDF]";
    private static final String REGEX_FACTOR_MAIN_CODE = "[A-Z]";
    private static final String REGEX_FACTOR_ITEM_CODE = "[A-Z][0A-Z]";
    private static final String REGEX_FACTOR_ITEM_CODE_OF_PART = "[A-Z]{1}[0A-Z]?";
    private static final int MAX_FACTOR_IMPACT = 999;
    private static final int MAX_FACTOR_DEGREE = 99;
    private static final String REGEX_FACTOR_ILLEGAL_DNAMES = "[`~!@#$%^&*\\(\\)_+-=|}{\\[\\]'\";:\\./\\?><\\s]";
    private static final String REGEX_BASELAND_NO = "^[A-Z]([0][1-9]|[1-3][0-9])(B[ABDFHPXZ]|A[DHJ]|E[ABCDEFGHJKLMNPQRST])[0-9]{4}$";
    private static final String REGEX_BASELAND_URBAN = "^(B[ABDFHPXZ]|A[DHJ]|E[ABCDEFGHJKLMNPQRST])(,(B[ABDFHPXZ]|A[DHJ]|E[ABCDEFGHJKLMNPQRST]))*$";
    private static final String REGEX_BASELAND_SEQ = "^[0-9]{4}$";
    private static final String REGEX_GLOSSARY_CODE_0 = "[A-Za-z]{1}[_A-Za-z0-9]{1,19}";
    private static final String REGEX_GLOSSARY_CODE_1 = "[A-Za-z]{1}[_A-Za-z0-9]{1,19}";
    private static final int MAX_GLOSSARY_SNO = 999999999;
    private static final String REGEX_GLOSSARY_ILLEGAL_LITERAL = "[^ \\r\\n\\S]|['\"\\\\]";
    private static final String REGEX_PRICERATE_TYPE = "[CPB]PI(_B[ABD])?";
    private static final String REGEX_PRICERATE_DIST = "[0-3][0-9]";
    private static final double MAX_PRICERATE_INDEX_RATE = 999.99;
    private static final String REGEX_INSTRU_CODE = "[0-9A-Z]{1,2}";
    private static final String REGEX_INSTRU_NAME = "[`~!@#$%^&*\\(\\)_+=-|\\}\\{\\[\\]\\\":;'\\?><\\/\\.,\\s]";
    private static final int MAX_INSTRU_YEAR_LIMITS = 999;
    private static final double MAX_INSTRU_RESIDUAL_RATE = 999.99;
    private static final String REGEX_INSTRU_REGD_CODES = "^[0-9A-Za-z]+(,[0-9A-Za-z]+)*$";
    private static final String REGEX_CASENO = "[1-3]{1}";
    private static final double MAX_REPORT_PARAM_RATE_AND_RATIO = 999.99;
    private static final double MAX_BUILDPRICE_ADJUST_RATIO = 999.99;
    private static final int MAX_BUILD_FLOOR = 999;
    private static final String REGEX_SELL_INSTANCE_DATE_TYPE = "(madate)|(tdate)";
    private static final String REGEX_SELL_INSTANCE_CASE_TYPE = "(build)|(land)";
    private static final String REGEX_SELL_INSTANCE_BUFFER_TYPE = "(sectmap)|(distance)";
    private static final int MAX_SELL_INSTANCE_DISTANCE = 99999;
    private static final int MAX_AHP_PARAM_RATIO = 99;
    private static final long MAX_PRICE = 999999999999999999L;
    private static final String REGEX_FLOOR_EFFECT_FLOOR = "^((B[1-8])|([1-9])|([1-9][0-9])|([1][0][0-7]))F$";
    private static final double MAX_FLOOR_EFFECT_RATIO = 999.99;
    private static final int MIN_FLOOR_EFFECT_FLOOR = -8;
    private static final int MAX_FLOOR_EFFECT_FLOOR = 107;
    private static final String REGEX_PHOTO_KIND = "^(SKT)|(BASE)|(SELL[123])|(RENT[123])$";
    private static final String REGEX_PHOTO_TYPE = "^(SKT)|(BASE_[AB])|(SELL[123]_[AB])|(RENT[123]_[AB])$";
    private static final String REGEX_PHOTO_TYPE_CODE = "^(SKT)|(BASE[12])|(SELL[123][12])|(RENT[123][12])$";

    public static boolean checkCity(String string, boolean bl) {
        return BaseLandVerifyUtil.isMatchText("[A-Z]", string, bl);
    }

    public static String checkCity(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkText("[A-Z]", string, false);
    }

    public static String checkCityAllowEmpty(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkText("[A-Z]", string, true);
    }

    public static boolean checkOffice(String string, boolean bl) {
        return BaseLandVerifyUtil.isMatchText("[A-Z][0A-Z]", string, bl);
    }

    public static String checkOffice(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkText("[A-Z][0A-Z]", string, false);
    }

    public static String checkOfficeAllowEmpty(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkText("[A-Z][0A-Z]", string, true);
    }

    public static String checkOfficeAllowPart(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkText("[A-Z]{1}[0A-Z]?", string, true);
    }

    public static boolean checkDist(String string, boolean bl) {
        return BaseLandVerifyUtil.isMatchText(REGEX_DIST, string, bl);
    }

    public static String checkDist(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkText(REGEX_DIST, string, false);
    }

    public static String checkDistAllowEmpty(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkText(REGEX_DIST, string, true);
    }

    public static boolean checkAa48(String string, boolean bl) {
        return BaseLandVerifyUtil.isMatchText(REGEX_AA48, string, bl);
    }

    public static String checkAa48(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkText(REGEX_AA48, string, false);
    }

    public static String checkAa48AllowEmpty(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkText(REGEX_AA48, string, true);
    }

    public static boolean checkYear(String string, boolean bl) {
        return BaseLandVerifyUtil.isMatchText(REGEX_YEAR, string, bl);
    }

    public static String checkYear(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkText(REGEX_YEAR, string, false);
    }

    public static String checkYearAllowEmpty(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkText(REGEX_YEAR, string, true);
    }

    public static boolean checkMonth(String string, boolean bl) {
        return BaseLandVerifyUtil.isMatchText(REGEX_MONTH, string, bl);
    }

    public static String checkMonth(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkText(REGEX_MONTH, string, false);
    }

    public static String checkMonthAllowEmpty(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkText(REGEX_MONTH, string, true);
    }

    public static boolean checkYearMonth(String string, boolean bl) {
        return BaseLandVerifyUtil.isMatchText(REGEX_YEAR_MONTH, string, bl);
    }

    public static String checkYearMonth(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkText(REGEX_YEAR_MONTH, string, false);
    }

    public static String checkYearMonthAllowEmpty(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkText(REGEX_YEAR_MONTH, string, true);
    }

    public static boolean checkDate(String string, boolean bl) {
        return BaseLandVerifyUtil.isMatchText(REGEX_DATE, string, bl);
    }

    public static String checkDate(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkText(REGEX_DATE, string, false);
    }

    public static String checkDateAllowEmpty(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkText(REGEX_DATE, string, true);
    }

    public static boolean checkPriceNumber(long l) {
        return BaseLandVerifyUtil.isNumberBetween(0L, 999999999999999999L, l);
    }

    public static long checkPrice(long l) throws VerifyException {
        return BaseLandVerifyUtil.checkNumberBetween(0L, 999999999999999999L, l);
    }

    public static boolean checkFactorVersion(String string, boolean bl) {
        return BaseLandVerifyUtil.isMatchText(REGEX_FACTOR_VERSION, string, bl);
    }

    public static String checkFactorVersion(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkText(REGEX_FACTOR_VERSION, string, false);
    }

    public static String checkFactorVersionAllowEmpty(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkText(REGEX_FACTOR_VERSION, string, true);
    }

    public static boolean checkFactorMainCode(String string, boolean bl) {
        return BaseLandVerifyUtil.isMatchText("[A-Z]", string, bl);
    }

    public static String checkFactorMainCode(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkText("[A-Z]", string, false);
    }

    public static String checkFactorMainCodeAllowEmpty(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkText("[A-Z]", string, true);
    }

    public static boolean checkFactorItemCode(String string, boolean bl) {
        return BaseLandVerifyUtil.isMatchText("[A-Z][0A-Z]", string, bl);
    }

    public static String checkFactorItemCode(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkText("[A-Z][0A-Z]", string, false);
    }

    public static String checkFactorItemCodeAllowEmpty(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkText("[A-Z][0A-Z]", string, true);
    }

    public static boolean checkFactorItemCodeAllowPart(String string, boolean bl) {
        return BaseLandVerifyUtil.isMatchText("[A-Z]{1}[0A-Z]?", string, true);
    }

    public static String checkFactorItemCodeAllowPart(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkText("[A-Z]{1}[0A-Z]?", string, true);
    }

    public static boolean checkFactorImpactNumber(int n) {
        return BaseLandVerifyUtil.isNumberBetween(0, 999, n);
    }

    public static int checkFactorImpact(int n) throws VerifyException {
        return BaseLandVerifyUtil.checkNumberBetween(0, 999, n);
    }

    public static boolean checkFactorDegreeNumber(int n) {
        return BaseLandVerifyUtil.isNumberBetween(0, 99, n);
    }

    public static int checkFactorDegree(int n) throws VerifyException {
        return BaseLandVerifyUtil.checkNumberBetween(0, 99, n);
    }

    public static boolean checkFactorDname(String string, boolean bl) {
        String[] stringArray;
        for (String string2 : stringArray = string.split(",")) {
            if (BaseLandVerifyUtil.isLegalText(REGEX_FACTOR_ILLEGAL_DNAMES, string2, bl)) continue;
            return false;
        }
        return true;
    }

    public static String checkFactorDname(String string) throws VerifyException {
        String[] stringArray;
        for (String string2 : stringArray = string.split(",")) {
            BaseLandVerifyUtil.checkIllegalText(REGEX_FACTOR_ILLEGAL_DNAMES, string2, false);
        }
        return string;
    }

    public static boolean checkBaselandNo(String string, boolean bl) {
        return BaseLandVerifyUtil.isMatchText(REGEX_BASELAND_NO, string, bl);
    }

    public static String checkBaselandNo(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkText(REGEX_BASELAND_NO, string, false);
    }

    public static String checkBaselandNoAllowEmpty(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkText(REGEX_BASELAND_NO, string, true);
    }

    public static boolean checkBaselandUrban(String string, boolean bl) {
        return BaseLandVerifyUtil.isMatchText(REGEX_BASELAND_URBAN, string, bl);
    }

    public static String checkBaselandUrban(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkText(REGEX_BASELAND_URBAN, string, false);
    }

    public static String checkBaselandUrbanAllowEmpty(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkText(REGEX_BASELAND_URBAN, string, true);
    }

    public static boolean checkBaselandSeq(String string, boolean bl) {
        return BaseLandVerifyUtil.isMatchText(REGEX_BASELAND_SEQ, string, bl);
    }

    public static String checkBaselandSeq(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkText(REGEX_BASELAND_SEQ, string, false);
    }

    public static String checkBaselandSeqAllowEmpty(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkText(REGEX_BASELAND_SEQ, string, true);
    }

    public static boolean checkGlossaryTbName(String string, boolean bl) {
        return BaseLandVerifyUtil.isMatchText("[A-Za-z]{1}[_A-Za-z0-9]{1,19}", string, bl);
    }

    public static String checkGlossaryTbName(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkText("[A-Za-z]{1}[_A-Za-z0-9]{1,19}", string, false);
    }

    public static String checkGlossaryTbNameAllowEmpty(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkText("[A-Za-z]{1}[_A-Za-z0-9]{1,19}", string, true);
    }

    public static boolean checkGlossaryTbField(String string, boolean bl) {
        return BaseLandVerifyUtil.isMatchText("[A-Za-z]{1}[_A-Za-z0-9]{1,19}", string, bl);
    }

    public static String checkGlossaryTbField(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkText("[A-Za-z]{1}[_A-Za-z0-9]{1,19}", string, false);
    }

    public static String checkGlossaryTbFieldAllowEmpty(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkText("[A-Za-z]{1}[_A-Za-z0-9]{1,19}", string, true);
    }

    public static boolean checkGlossarySnoNumber(int n) {
        return BaseLandVerifyUtil.isNumberBetween(0, 999999999, n);
    }

    public static int checkGlossarySno(int n) throws VerifyException {
        return BaseLandVerifyUtil.checkNumberBetween(0, 999999999, n);
    }

    public static boolean checkGlossaryLiteral(String string, boolean bl) {
        return BaseLandVerifyUtil.isLegalText(REGEX_GLOSSARY_ILLEGAL_LITERAL, string, bl);
    }

    public static String checkGlossaryLiteral(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkIllegalText(REGEX_GLOSSARY_ILLEGAL_LITERAL, string, false);
    }

    public static String checkGlossaryLiteralAllowEmpty(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkIllegalText(REGEX_GLOSSARY_ILLEGAL_LITERAL, string, true);
    }

    public static boolean checkPriceRateType(String string, boolean bl) {
        return BaseLandVerifyUtil.isMatchText(REGEX_PRICERATE_TYPE, string, bl);
    }

    public static String checkPriceRateType(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkText(REGEX_PRICERATE_TYPE, string, false);
    }

    public static String checkPriceRateTypeAllowEmpty(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkText(REGEX_PRICERATE_TYPE, string, true);
    }

    public static boolean checkPriceRateDist(String string, boolean bl) {
        return BaseLandVerifyUtil.isMatchText(REGEX_PRICERATE_DIST, string, bl);
    }

    public static String checkPriceRateDist(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkText(REGEX_PRICERATE_DIST, string, false);
    }

    public static String checkPriceRateDistEmpty(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkText(REGEX_PRICERATE_DIST, string, true);
    }

    public static boolean checkPriceRateIndexRateNumber(double d) {
        return BaseLandVerifyUtil.isNumberBetween(0.0, 999.99, d);
    }

    public static double checkPriceRateIndexRate(double d) throws VerifyException {
        return BaseLandVerifyUtil.checkNumberBetween(0.0, 999.99, d);
    }

    public static boolean checkInstruCode(String string, boolean bl) {
        return BaseLandVerifyUtil.isMatchText(REGEX_INSTRU_CODE, string, bl);
    }

    public static String checkInstruCode(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkText(REGEX_INSTRU_CODE, string, false);
    }

    public static String checkInstruCodeAllowEmpty(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkText(REGEX_INSTRU_CODE, string, true);
    }

    public static boolean checkInstruNameE(String string, boolean bl) {
        return BaseLandVerifyUtil.isLegalText(REGEX_INSTRU_NAME, string, bl);
    }

    public static String checkInstruNameE(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkIllegalText(REGEX_INSTRU_NAME, string, false);
    }

    public static String checkInstruNameEAllowEmpty(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkIllegalText(REGEX_INSTRU_NAME, string, true);
    }

    public static boolean checkInstruYearLimitsNumber(int n) {
        return BaseLandVerifyUtil.isNumberBetween(0, 999, n);
    }

    public static int checkInstruYearLimits(int n) throws VerifyException {
        return BaseLandVerifyUtil.checkNumberBetween(0, 999, n);
    }

    public static boolean checkInstruResidualRateNumber(double d) {
        return BaseLandVerifyUtil.isNumberBetween(0.0, 999.99, d);
    }

    public static double checkInstruResidualRate(double d) throws VerifyException {
        return BaseLandVerifyUtil.checkNumberBetween(0.0, 999.99, d);
    }

    public static boolean checkInstruRegdCode(String string, boolean bl) {
        return BaseLandVerifyUtil.isMatchText(REGEX_INSTRU_REGD_CODES, string, bl);
    }

    public static String checkInstruRegdCode(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkText(REGEX_INSTRU_REGD_CODES, string, false);
    }

    public static String checkInstruRegdCodeAllowEmpty(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkText(REGEX_INSTRU_REGD_CODES, string, true);
    }

    public static boolean checkBuildFloorNumber(int n) {
        return BaseLandVerifyUtil.isNumberBetween(0, 999, n);
    }

    public static int checkBuildFloor(int n) throws VerifyException {
        return BaseLandVerifyUtil.checkNumberBetween(0, 999, n);
    }

    public static boolean checkReportParamRateOrRatioNumber(double d) {
        return BaseLandVerifyUtil.isNumberBetween(0.0, 999.99, d);
    }

    public static double checkReportParamRateOrRatio(double d) throws VerifyException {
        return BaseLandVerifyUtil.checkNumberBetween(0.0, 999.99, d);
    }

    public static boolean checkBuildPriceAdjustRatioNumber(double d) {
        return BaseLandVerifyUtil.isNumberBetween(0.0, 999.99, d);
    }

    public static double checkBuildPriceAdjustRatio(double d) throws VerifyException {
        return BaseLandVerifyUtil.checkNumberBetween(0.0, 999.99, d);
    }

    public static boolean checkCaseno(String string, boolean bl) {
        return BaseLandVerifyUtil.isMatchText(REGEX_CASENO, string, bl);
    }

    public static String checkCaseno(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkText(REGEX_CASENO, string, false);
    }

    public static String checkCasenoAllowEmpty(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkText(REGEX_CASENO, string, true);
    }

    public static boolean checkSellInstanceDateType(String string, boolean bl) {
        return BaseLandVerifyUtil.isMatchText(REGEX_SELL_INSTANCE_DATE_TYPE, string, bl);
    }

    public static String checkSellInstanceDateType(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkText(REGEX_SELL_INSTANCE_DATE_TYPE, string, false);
    }

    public static String checkSellInstanceDateTypeAllowEmpty(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkText(REGEX_SELL_INSTANCE_DATE_TYPE, string, true);
    }

    public static boolean checkSellInstanceCaseType(String string, boolean bl) {
        return BaseLandVerifyUtil.isMatchText(REGEX_SELL_INSTANCE_CASE_TYPE, string, bl);
    }

    public static String checkSellInstanceCaseType(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkText(REGEX_SELL_INSTANCE_CASE_TYPE, string, false);
    }

    public static String checkSellInstanceCaseTypeAllowEmpty(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkText(REGEX_SELL_INSTANCE_CASE_TYPE, string, true);
    }

    public static boolean checkSellInstanceBufferType(String string, boolean bl) {
        return BaseLandVerifyUtil.isMatchText(REGEX_SELL_INSTANCE_BUFFER_TYPE, string, bl);
    }

    public static String checkSellInstanceBufferType(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkText(REGEX_SELL_INSTANCE_BUFFER_TYPE, string, false);
    }

    public static String checkSellInstanceBufferTypeAllowEmpty(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkText(REGEX_SELL_INSTANCE_BUFFER_TYPE, string, true);
    }

    public static boolean checkSellInstanceDistanceNumber(int n) {
        return BaseLandVerifyUtil.isNumberBetween(0, 99999, n);
    }

    public static int checkSellInstanceDistance(int n) throws VerifyException {
        return BaseLandVerifyUtil.checkNumberBetween(0, 99999, n);
    }

    public static boolean checkAhpParamRatioNumber(int n) {
        return BaseLandVerifyUtil.isNumberBetween(0, 99, n);
    }

    public static int checkAhpParamRatio(int n) throws VerifyException {
        return BaseLandVerifyUtil.checkNumberBetween(0, 99, n);
    }

    public static boolean checkFloorEffectFloor(String string, boolean bl) {
        return BaseLandVerifyUtil.isMatchText(REGEX_FLOOR_EFFECT_FLOOR, string, bl);
    }

    public static String checkFloorEffectFloor(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkText(REGEX_FLOOR_EFFECT_FLOOR, string, false);
    }

    public static boolean checkFloorEffectFloorNumber(int n) {
        return BaseLandVerifyUtil.isNumberBetween(-8, 107, n);
    }

    public static int checkFloorEffectFloor(int n) throws VerifyException {
        return BaseLandVerifyUtil.checkNumberBetween(-8, 107, n);
    }

    public static boolean checkFloorEffectRatioNumber(double d) {
        return BaseLandVerifyUtil.isNumberBetween(0.0, 999.99, d);
    }

    public static double checkFloorEffectRatio(double d) throws VerifyException {
        return BaseLandVerifyUtil.checkNumberBetween(0.0, 999.99, d);
    }

    public static boolean checkPhotoKind(String string, boolean bl) {
        return BaseLandVerifyUtil.isMatchText(REGEX_PHOTO_KIND, string, bl);
    }

    public static String checkPhotoKind(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkText(REGEX_PHOTO_KIND, string, false);
    }

    public static boolean checkPhotoType(String string, boolean bl) {
        return BaseLandVerifyUtil.isMatchText(REGEX_PHOTO_TYPE, string, bl);
    }

    public static String checkPhotoType(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkText(REGEX_PHOTO_TYPE, string, false);
    }

    public static String checkPhotoTypeAllowEmpty(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkText(REGEX_PHOTO_TYPE, string, true);
    }

    public static String checkPhotoTypeCode(String string) throws VerifyException {
        return BaseLandVerifyUtil.checkText(REGEX_PHOTO_TYPE_CODE, string, false);
    }

    public static String checkTextByCustom(String string, String string2) throws VerifyException {
        return BaseLandVerifyUtil.checkText(string, string2, false);
    }

    public static String checkTextByCustomAllowEmpty(String string, String string2) throws VerifyException {
        return BaseLandVerifyUtil.checkText(string, string2, true);
    }

    private static String checkText(String string, String string2, boolean bl) throws VerifyException {
        if (bl && string2.equals("") || string2.matches(string)) {
            return string2;
        }
        throw new VerifyException();
    }

    private static String checkIllegalText(String string, String string2, boolean bl) throws VerifyException {
        if (bl && string2.equals("")) {
            return string2;
        }
        String string3 = string2.replaceAll(string, "");
        if (string2.equals(string3)) {
            return string2;
        }
        throw new VerifyException();
    }

    private static int checkNumberBetween(int n, int n2, int n3) throws VerifyException {
        if (n3 >= n && n3 <= n2) {
            return n3;
        }
        throw new VerifyException();
    }

    private static long checkNumberBetween(long l, long l2, long l3) throws VerifyException {
        if (l3 >= l && l3 <= l2) {
            return l3;
        }
        throw new VerifyException();
    }

    private static double checkNumberBetween(double d, double d2, double d3) throws VerifyException {
        if (d3 >= d && d3 <= d2) {
            return d3;
        }
        throw new VerifyException();
    }

    private static boolean isMatchText(String string, String string2, boolean bl) {
        return bl && string2.equals("") || string2.matches(string);
    }

    private static boolean isLegalText(String string, String string2, boolean bl) {
        if (bl && string2.equals("")) {
            return true;
        }
        String string3 = string2.replaceAll(string, "");
        return string2.equals(string3);
    }

    private static boolean isNumberBetween(int n, int n2, int n3) {
        return n3 >= n && n3 <= n2;
    }

    private static boolean isNumberBetween(long l, long l2, long l3) {
        return l3 >= l && l3 <= l2;
    }

    private static boolean isNumberBetween(double d, double d2, double d3) {
        return d3 >= d && d3 <= d2;
    }
}

