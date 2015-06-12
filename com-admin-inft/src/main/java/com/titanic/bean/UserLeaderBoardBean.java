package com.titanic.bean;

/**
 * Created by titanic on 15-6-12.
 */
public class UserLeaderBoardBean
{
    private String id ;//INT NOT NULL AUTO_INCREMENT,
    private String userId;// INT NOT NULL COMMENT '用户ID',
    private String seqNum ;//INT NULL COMMENT '排序号',
    private String operateUserId;// INT NULL COMMENT '操作人',
    private String remark ;//VARCHAR(200) NULL,
    private String createDate;// DATETIME NULL COMMENT '创建时间',
    private String updateDate ;//TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    private String deleteDate ;//DATETIME NULL COMMENT '逻辑删除时间',
    private String STATUS ;//INT DEFAULT 0 COMMENT '状态标记 0:正常 1:删除',

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getSeqNum()
    {
        return seqNum;
    }

    public void setSeqNum(String seqNum)
    {
        this.seqNum = seqNum;
    }

    public String getOperateUserId()
    {
        return operateUserId;
    }

    public void setOperateUserId(String operateUserId)
    {
        this.operateUserId = operateUserId;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public String getCreateDate()
    {
        return createDate;
    }

    public void setCreateDate(String createDate)
    {
        this.createDate = createDate;
    }

    public String getUpdateDate()
    {
        return updateDate;
    }

    public void setUpdateDate(String updateDate)
    {
        this.updateDate = updateDate;
    }

    public String getDeleteDate()
    {
        return deleteDate;
    }

    public void setDeleteDate(String deleteDate)
    {
        this.deleteDate = deleteDate;
    }

    public String getSTATUS()
    {
        return STATUS;
    }

    public void setSTATUS(String STATUS)
    {
        this.STATUS = STATUS;
    }
}
