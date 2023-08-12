package dao;

public enum SQLiteErrorCodes {
    CONSTRAINT(19),
    CONSTRAINT_FOREIGNKEY(787);

    private final int code;

    SQLiteErrorCodes(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
