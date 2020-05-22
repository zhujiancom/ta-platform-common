package com.ta.platform.common.exception;

import com.ta.platform.common.api.ApiCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PlatformException extends RuntimeException {
    private Integer errorCode;

    private String message;

    public PlatformException() {
        super();
    }

    public PlatformException(String message) {
        super(message);
        this.message = message;
    }

    public PlatformException(Integer errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }

    public PlatformException(ApiCode apiCode) {
        super(apiCode.getMessage());
        this.errorCode = apiCode.getCode();
        this.message = apiCode.getMessage();
    }

    public PlatformException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlatformException(Throwable cause) {
        super(cause);
    }
}
