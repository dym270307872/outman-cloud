package cn.dyaoming.outman.cloud.gateway.common;

public class AppAccessException extends RuntimeException {

    private AuthFailLog authFailLog;

    public AppAccessException() {
        super();
    }

    public AppAccessException(String message) {
        super(message);
    }

    public AuthFailLog getAuthFailLog() {
        return authFailLog;
    }

    public void setAuthFailLog(AuthFailLog authFailLog) {
        this.authFailLog = authFailLog;
    }

    public static AppAccessException fail(AuthFailLog authFailLog, String message) {
        AppAccessException appAccessException = new AppAccessException(message);
        appAccessException.setAuthFailLog(authFailLog);
        return appAccessException;
    }

}
