package com.askjeffreyliu.mvvmsample.endpoint;

public final class ApiResponse<T> {
    private T response;
    private Throwable throwable;

    public ApiResponse(T response, Throwable throwable) {
        this.response = response;
        this.throwable = throwable;
    }

    public T getResponse() {
        return response;
    }

    public Throwable getThrowable() {
        return throwable;
    }
}
