package com.jpcchaves.blogapp.payload;

import java.util.Map;

public class ArgumentsNotValidError {
    private Map<String, String> errors;
    private String currentError;
    private Integer errorCount;

    public ArgumentsNotValidError() {
    }

    public ArgumentsNotValidError(Map<String, String> errors, String currentError, Integer errorCount) {
        this.errors = errors;
        this.currentError = currentError;
        this.errorCount = errorCount;
    }
    
    public Map<String, String> getErrors() {
        return errors;
    }

    public String getCurrentError() {
        return currentError;
    }

    public Integer getErrorCount() {
        return errorCount;
    }
}
