package com.cd.dota.common;

import java.io.Serializable;
/**
 * 
 * @author suny
 *
 */
public class BaseResultDTO implements Serializable {
    
    private static final long serialVersionUID = 5473134981280912898L;
    
    protected boolean success = true;
    
    protected String resultCode;
    protected String errorDetail;
    
    public String getErrorDetail() {
        return errorDetail;
    }
    
    public void setErrorDetail(String errorDetail) {
        this.errorDetail = errorDetail;
    }
    
    public boolean isSuccess() {
        return success;
    }
    
    public void setSuccess(boolean success) {
        this.success = success;
    }
    
    public boolean isFailed() {
        return !success;
    }
    
    public String getResultCode() {
        return resultCode;
    }
    
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }
    
}
