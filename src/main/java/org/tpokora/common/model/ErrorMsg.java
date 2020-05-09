package org.tpokora.common.model;

public class ErrorMsg {
    private String error;

    public ErrorMsg() {

    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "ErrorMsg{" +
                "error='" + error + '\'' +
                '}';
    }
}