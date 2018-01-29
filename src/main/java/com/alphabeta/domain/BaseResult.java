package com.alphabeta.domain;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 返回值模型基类
 *
 * @author shi.pengyan
 * @date 2016年11月8日 上午10:49:47
 */
public class BaseResult {
    private static final long serialVersionUID = 1L;

    @JSONField(ordinal = -1003)
    private Boolean success = true;

    @JSONField(ordinal = -1002)
    private String errorCode;

    @JSONField(ordinal = -1001)
    private String errorMessage;

    @JSONField(ordinal = -1000)
    private String errorType; // 错误类别

    @JSONField(ordinal = -999)
    private Object result; // 默认返回结果

    public BaseResult() {
        this(true, null);
    }

    public BaseResult(boolean success, String errorMsg) {
        this.success = success;
        this.errorMessage = errorMsg;
    }

    public BaseResult(Object result) {
        this.success = true;
        this.result = result;
    }

    /**
     * 直接返回true
     *
     * @return BaseResult
     */
    public static BaseResult success() {
        return new BaseResult(true, null);
    }

    /**
     * 返回带true和Result的BaseResult
     *
     * @param result
     * @return BaseResult
     */
    public static BaseResult success(Object result) {
        return new BaseResult(result);
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
