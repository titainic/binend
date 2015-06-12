package com.titanic.bean;

/**
 * Created by titanic on 15-6-12.
 */
public class UserInfoBean
{
   private String userId;// INT(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
    private String userAccount ;//VARCHAR(64) NOT NULL COMMENT '用户账号',
    private String userAccountPassword;// VARCHAR(256) NOT NULL COMMENT '用户密码',
    private String userAccountType ;//INT(2) NOT NULL  COMMENT '[ 3.phone || 2.wechat || 1. sinaweibo ]',
    private String userType;// INT(1) DEFAULT 0 COMMENT '[ 0：普通用户 || 1: 认证加V用户 ||  2：超级管理员  || 3：普通管理员]',
    private String userNickname;// VARCHAR(32) DEFAULT '' COMMENT '用户呢称',
    private String userSex;// INT(1) NOT NULL DEFAULT 1 COMMENT '[1. 女 || 2. 男 || 3. 未知 ]',
    private String userSign;// VARCHAR(256) DEFAULT '' COMMENT '用户签名',
    private String userPhone ;//VARCHAR(32) DEFAULT '' COMMENT '用户手机',
    private String userWechatNickName ;//VARCHAR(32) DEFAULT '' COMMENT '微信昵称',
    private String userSinaNickName;// VARCHAR(32) DEFAULT '' COMMENT '新浪微博昵称',
    private String userPhoto;// VARCHAR(128) DEFAULT '' COMMENT '用户头像url',
    private String userFocusNum ;//INT(11) DEFAULT 0 COMMENT '关注数量',
    private String userFansNum ;//INT(11) DEFAULT 0 COMMENT '粉丝数量',
    private String userClienType;// INT(11) DEFAULT 3 COMMENT '[1：浏览器设备； || 2：PC设备； || 3：Andriod设备；|| 4：iOS设备 || 5：Windows Phone设备]',
    private String userGTClientId;// VARCHAR(32) DEFAULT '' COMMENT '用户个推ClientId',
    private String userLng;// DOUBLE(14,11)  DEFAULT 0 COMMENT '用户经度',
    private String userLat ;//DOUBLE(14,11)  DEFAULT 0 COMMENT '用户纬度',
    private String userAddr;// VARCHAR(256) DEFAULT '' COMMENT '用户最新地址',
    private String userIsForbid;// BOOL DEFAULT FALSE COMMENT '用户是否被禁止登陆',
    private String userCreated ;//DATETIME NOT NULL COMMENT '创建时间',
    private String userUpdated;// TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP  COMMENT '更新时间',
    private String userDeleted;// DATETIME DEFAULT NULL COMMENT '删除时间',
    private String userStatus ;//INT(1)  DEFAULT 0 COMMENT '状态标记 0:正常 1:删除',


    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getUserAccount()
    {
        return userAccount;
    }

    public void setUserAccount(String userAccount)
    {
        this.userAccount = userAccount;
    }

    public String getUserAccountPassword()
    {
        return userAccountPassword;
    }

    public void setUserAccountPassword(String userAccountPassword)
    {
        this.userAccountPassword = userAccountPassword;
    }

    public String getUserAccountType()
    {
        return userAccountType;
    }

    public void setUserAccountType(String userAccountType)
    {
        this.userAccountType = userAccountType;
    }

    public String getUserType()
    {
        return userType;
    }

    public void setUserType(String userType)
    {
        this.userType = userType;
    }

    public String getUserNickname()
    {
        return userNickname;
    }

    public void setUserNickname(String userNickname)
    {
        this.userNickname = userNickname;
    }

    public String getUserSex()
    {
        return userSex;
    }

    public void setUserSex(String userSex)
    {
        this.userSex = userSex;
    }

    public String getUserSign()
    {
        return userSign;
    }

    public void setUserSign(String userSign)
    {
        this.userSign = userSign;
    }

    public String getUserPhone()
    {
        return userPhone;
    }

    public void setUserPhone(String userPhone)
    {
        this.userPhone = userPhone;
    }

    public String getUserWechatNickName()
    {
        return userWechatNickName;
    }

    public void setUserWechatNickName(String userWechatNickName)
    {
        this.userWechatNickName = userWechatNickName;
    }

    public String getUserSinaNickName()
    {
        return userSinaNickName;
    }

    public void setUserSinaNickName(String userSinaNickName)
    {
        this.userSinaNickName = userSinaNickName;
    }

    public String getUserPhoto()
    {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto)
    {
        this.userPhoto = userPhoto;
    }

    public String getUserFocusNum()
    {
        return userFocusNum;
    }

    public void setUserFocusNum(String userFocusNum)
    {
        this.userFocusNum = userFocusNum;
    }

    public String getUserFansNum()
    {
        return userFansNum;
    }

    public void setUserFansNum(String userFansNum)
    {
        this.userFansNum = userFansNum;
    }

    public String getUserClienType()
    {
        return userClienType;
    }

    public void setUserClienType(String userClienType)
    {
        this.userClienType = userClienType;
    }

    public String getUserGTClientId()
    {
        return userGTClientId;
    }

    public void setUserGTClientId(String userGTClientId)
    {
        this.userGTClientId = userGTClientId;
    }

    public String getUserLng()
    {
        return userLng;
    }

    public void setUserLng(String userLng)
    {
        this.userLng = userLng;
    }

    public String getUserLat()
    {
        return userLat;
    }

    public void setUserLat(String userLat)
    {
        this.userLat = userLat;
    }

    public String getUserAddr()
    {
        return userAddr;
    }

    public void setUserAddr(String userAddr)
    {
        this.userAddr = userAddr;
    }

    public String getUserIsForbid()
    {
        return userIsForbid;
    }

    public void setUserIsForbid(String userIsForbid)
    {
        this.userIsForbid = userIsForbid;
    }

    public String getUserCreated()
    {
        return userCreated;
    }

    public void setUserCreated(String userCreated)
    {
        this.userCreated = userCreated;
    }

    public String getUserUpdated()
    {
        return userUpdated;
    }

    public void setUserUpdated(String userUpdated)
    {
        this.userUpdated = userUpdated;
    }

    public String getUserDeleted()
    {
        return userDeleted;
    }

    public void setUserDeleted(String userDeleted)
    {
        this.userDeleted = userDeleted;
    }

    public String getUserStatus()
    {
        return userStatus;
    }

    public void setUserStatus(String userStatus)
    {
        this.userStatus = userStatus;
    }
}
