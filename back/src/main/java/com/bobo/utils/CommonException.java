package com.bobo.utils;

import com.bobo.entity.Result;
import lombok.Data;

@Data
public class CommonException extends Exception {
    private Result result;
    public CommonException(Result result) {
        this.result = result;
    }

    public CommonException(String s) {
        this.result = Result.fail(s);
    }

    public CommonException() {
        this.result = Result.fail();
    }
}