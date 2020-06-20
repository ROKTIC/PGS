package io.pgs.cmn;

public class ResultMapper<T> {

    private String status;
    private String msg;

    private T result;

    public ResultMapper(T result) {
        this(result, ServiceStatus.Successful);
    }

    public ResultMapper(ServiceStatus status) {
        this.status = status.code;
        this.msg = status.msg;
    }

    public ResultMapper(T result, ServiceStatus status) {
        this.result = result;
        this.status = status.code;
        this.msg = status.msg;
    }

    public String getStatus() {
        return (this.status == null || this.status.isEmpty()) ? ServiceStatus.Successful.code : this.status;
    }

    public String getMsg() {
        return this.msg;
    }

    public T getResult() {
        return this.result;
    }
}