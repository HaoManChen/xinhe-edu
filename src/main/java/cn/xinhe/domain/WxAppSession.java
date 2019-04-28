package cn.xinhe.domain;

import java.io.Serializable;

public class WxAppSession implements Serializable {
    private String wxAppSessionId;

    private String appid;

    private String openid;

    private String sessionKey;

    private static final long serialVersionUID = 1L;

    public String getWxAppSessionId() {
        return wxAppSessionId;
    }

    public void setWxAppSessionId(String wxAppSessionId) {
        this.wxAppSessionId = wxAppSessionId == null ? null : wxAppSessionId.trim();
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid == null ? null : appid.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey == null ? null : sessionKey.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", wxAppSessionId=").append(wxAppSessionId);
        sb.append(", appid=").append(appid);
        sb.append(", openid=").append(openid);
        sb.append(", sessionKey=").append(sessionKey);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}