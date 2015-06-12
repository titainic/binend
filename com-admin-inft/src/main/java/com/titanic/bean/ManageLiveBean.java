package com.titanic.bean;

import java.sql.Timestamp;

/**
 * 后台在线视屏管理
 */
public class ManageLiveBean
{
    /**
     * id
     */
    private String liveId;

    /**
     * 直播发起用户id
     */
    private String liveUserId;

    /**
     * 直播所属用户呢称
     */
    private String liveNickName;

    /**
     * 直播发起用户id
     */
    private String liveUserPhoto;

    /**
     * 直播资源下载地址
     */
    private String liveDownloadUrl;

    /**
     * 直播位置，非人工干预为0
     */
    private String liveOrder;

    /**
     * 直播流id
     */
    private String liveStreamId;

    /**
     * RTMP直播推流地址
     */
    private String liveRtmpPublishUrl;

    /**
     * HLS直播推流地址
     */
    private String liveHlsPublishUrl;

    /**
     * RTMP 直播播放地址
     */
    private String liveRtmpPlayUrl;

    /**
     * HLS直播播放地址
     */
    private String liveHlsPlayUrl;

    /**
     * 直播分享URL
     */
    private String liveShareUrl;

    /**
     * 直播间即时通信groupid
     */
    private String liveGroupId;

    /**
     * 直播订阅人数
     */
    private String liveSubscibes;

    /**
     * 直播分享次数
     */
    private String liveShares;

    /**
     * 点赞数
     */
    private String liveLikes;

    /**
     * 直播观看人数'
     */
    private String liveAudiences;

    /**
     * 直播标题
     */
    private String liveTitle;

    /**
     * 直播描述
     */
    private String liveDescribe;

    /**
     * 直播缩略图片
     */
    private String liveImg;

    /**
     * 直播标签json数组
     */
    private String liveTags;

    /**
     * 直播隐私范围[0 .完全公开]
     */
    private String liveScope;

    /**
     * 直播状态[0:预约中，未开始 | 1：直播进行中 | 2:直播已正常结束 | 3: 直播预约超时被关闭  | 4: 直播违禁被强关  ]
     */
    private String liveState;

    /**
     * 直播预约开始时间
     */
    private String liveReserved;

    /**
     * 直播开始时间
     */
    private String liveBegin;

    /**
     * 直播停止时间
     */
    private String liveEnd;

    /**
     * 创建时间
     */
    private String liveCreated;

    /**
     * 更新时间'
     */
    private String liveUpdated;

    /**
     * 删除时间
     */
    private String liveDeleted;

    /**
     * 状态标记 0:正常 1:删除
     */
    private String liveStatus ;


    public String getLiveId()
    {
        return liveId;
    }

    public void setLiveId(String liveId)
    {
        this.liveId = liveId;
    }

    public String getLiveUserId()
    {
        return liveUserId;
    }

    public void setLiveUserId(String liveUserId)
    {
        this.liveUserId = liveUserId;
    }

    public String getLiveNickName()
    {
        return liveNickName;
    }

    public void setLiveNickName(String liveNickName)
    {
        this.liveNickName = liveNickName;
    }

    public String getLiveUserPhoto()
    {
        return liveUserPhoto;
    }

    public void setLiveUserPhoto(String liveUserPhoto)
    {
        this.liveUserPhoto = liveUserPhoto;
    }

    public String getLiveDownloadUrl()
    {
        return liveDownloadUrl;
    }

    public void setLiveDownloadUrl(String liveDownloadUrl)
    {
        this.liveDownloadUrl = liveDownloadUrl;
    }

    public String getLiveOrder()
    {
        return liveOrder;
    }

    public void setLiveOrder(String liveOrder)
    {
        this.liveOrder = liveOrder;
    }

    public String getLiveStreamId()
    {
        return liveStreamId;
    }

    public void setLiveStreamId(String liveStreamId)
    {
        this.liveStreamId = liveStreamId;
    }

    public String getLiveRtmpPublishUrl()
    {
        return liveRtmpPublishUrl;
    }

    public void setLiveRtmpPublishUrl(String liveRtmpPublishUrl)
    {
        this.liveRtmpPublishUrl = liveRtmpPublishUrl;
    }

    public String getLiveHlsPublishUrl()
    {
        return liveHlsPublishUrl;
    }

    public void setLiveHlsPublishUrl(String liveHlsPublishUrl)
    {
        this.liveHlsPublishUrl = liveHlsPublishUrl;
    }

    public String getLiveRtmpPlayUrl()
    {
        return liveRtmpPlayUrl;
    }

    public void setLiveRtmpPlayUrl(String liveRtmpPlayUrl)
    {
        this.liveRtmpPlayUrl = liveRtmpPlayUrl;
    }

    public String getLiveHlsPlayUrl()
    {
        return liveHlsPlayUrl;
    }

    public void setLiveHlsPlayUrl(String liveHlsPlayUrl)
    {
        this.liveHlsPlayUrl = liveHlsPlayUrl;
    }

    public String getLiveShareUrl()
    {
        return liveShareUrl;
    }

    public void setLiveShareUrl(String liveShareUrl)
    {
        this.liveShareUrl = liveShareUrl;
    }

    public String getLiveGroupId()
    {
        return liveGroupId;
    }

    public void setLiveGroupId(String liveGroupId)
    {
        this.liveGroupId = liveGroupId;
    }

    public String getLiveSubscibes()
    {
        return liveSubscibes;
    }

    public void setLiveSubscibes(String liveSubscibes)
    {
        this.liveSubscibes = liveSubscibes;
    }

    public String getLiveShares()
    {
        return liveShares;
    }

    public void setLiveShares(String liveShares)
    {
        this.liveShares = liveShares;
    }

    public String getLiveLikes()
    {
        return liveLikes;
    }

    public void setLiveLikes(String liveLikes)
    {
        this.liveLikes = liveLikes;
    }

    public String getLiveAudiences()
    {
        return liveAudiences;
    }

    public void setLiveAudiences(String liveAudiences)
    {
        this.liveAudiences = liveAudiences;
    }

    public String getLiveTitle()
    {
        return liveTitle;
    }

    public void setLiveTitle(String liveTitle)
    {
        this.liveTitle = liveTitle;
    }

    public String getLiveDescribe()
    {
        return liveDescribe;
    }

    public void setLiveDescribe(String liveDescribe)
    {
        this.liveDescribe = liveDescribe;
    }

    public String getLiveImg()
    {
        return liveImg;
    }

    public void setLiveImg(String liveImg)
    {
        this.liveImg = liveImg;
    }

    public String getLiveTags()
    {
        return liveTags;
    }

    public void setLiveTags(String liveTags)
    {
        this.liveTags = liveTags;
    }

    public String getLiveScope()
    {
        return liveScope;
    }

    public void setLiveScope(String liveScope)
    {
        this.liveScope = liveScope;
    }

    public String getLiveState()
    {
        return liveState;
    }

    public void setLiveState(String liveState)
    {
        this.liveState = liveState;
    }

    public String getLiveStatus()
    {
        return liveStatus;
    }

    public void setLiveStatus(String liveStatus)
    {
        this.liveStatus = liveStatus;
    }

    public String getLiveReserved()
    {
        return liveReserved;
    }

    public void setLiveReserved(String liveReserved)
    {
        this.liveReserved = liveReserved;
    }

    public String getLiveBegin()
    {
        return liveBegin;
    }

    public void setLiveBegin(String liveBegin)
    {
        this.liveBegin = liveBegin;
    }

    public String getLiveEnd()
    {
        return liveEnd;
    }

    public void setLiveEnd(String liveEnd)
    {
        this.liveEnd = liveEnd;
    }

    public String getLiveCreated()
    {
        return liveCreated;
    }

    public void setLiveCreated(String liveCreated)
    {
        this.liveCreated = liveCreated;
    }

    public String getLiveUpdated()
    {
        return liveUpdated;
    }

    public void setLiveUpdated(String liveUpdated)
    {
        this.liveUpdated = liveUpdated;
    }

    public String getLiveDeleted()
    {
        return liveDeleted;
    }

    public void setLiveDeleted(String liveDeleted)
    {
        this.liveDeleted = liveDeleted;
    }
}
