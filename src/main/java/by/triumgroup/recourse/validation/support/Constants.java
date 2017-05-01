package by.triumgroup.recourse.validation.support;

public class Constants {

    private static final String ATOM = "[a-z0-9!#$%&'*+/=?^_`{|}~-]";
    private static final String DOMAIN = "(" + ATOM + "+(\\." + ATOM + "+)+)";
    private static final String IP_DOMAIN = "\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\]";
    public static final String PATTERN = "^" + ATOM + "+(\\." + ATOM + "+)*@(" + DOMAIN + "|" + IP_DOMAIN + ")$";

}
