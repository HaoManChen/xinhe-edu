package cn.xinhe.domain;

import java.io.Serializable;
import java.util.Date;

public class WxServiceUser implements Serializable {
    private String id;

    private String wxUserId;

    private String appid;

    private String openid;

    private Byte subscribed;

    private Date subscribeTime;

    private Date unsubscribeTime;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getWxUserId() {
        return wxUserId;
    }

    public void setWxUserId(String wxUserId) {
        this.wxUserId = wxUserId == null ? null : wxUserId.trim();
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

    public Byte getSubscribed() {
        return subscribed;
    }

    public void setSubscribed(Byte subscribed) {
        this.subscribed = subscribed;
    }

    public Date getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(Date subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    public Date getUnsubscribeTime() {
        return unsubscribeTime;
    }

    public void setUnsubscribeTime(Date unsubscribeTime) {
        this.unsubscribeTime = unsubscribeTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", wxUserId=").append(wxUserId);
        sb.append(", appid=").append(appid);
        sb.append(", openid=").append(openid);
        sb.append(", subscribed=").append(subscribed);
        sb.append(", subscribeTime=").append(subscribeTime);
        sb.append(", unsubscribeTime=").append(unsubscribeTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}