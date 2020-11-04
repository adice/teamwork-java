package com.it5power.authoritymanagement.entity.aut;

import com.it5power.authoritymanagement.entity.sta.Staff;

import javax.persistence.*;
import java.util.Date;

/**
 * @author ssk
 * @createTime 2020-10-30 9:16
 * @projectName authoritymanagement
 * @className Authority
 * @description
 */

@Entity
@Table(name = "aut_authority")
public class Authority {
    private Integer authorityId;//权限id
    private String authorityName;//权限名称
    private String authorityInfo;//权限简介
    private Integer authorityStatus;//权限状态（1代表可用，0代表不可用）
    private Date createTime;//创建时间
    private Staff authorityCreator;//创建人


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authority_id")
    public Integer getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Integer authorityId) {
        this.authorityId = authorityId;
    }

    @Column(name = "authority_name",length = 30)
    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    @Column(name = "authority_info",length = 255)
    public String getAuthorityInfo() {
        return authorityInfo;
    }

    public void setAuthorityInfo(String authorityInfo) {
        this.authorityInfo = authorityInfo;
    }

    @Column(name = "authority_status")
    public Integer getAuthorityStatus() {
        return authorityStatus;
    }

    public void setAuthorityStatus(Integer authorityStatus) {
        this.authorityStatus = authorityStatus;
    }

    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @ManyToOne
    @JoinColumn(name = "authority_creator",foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    public Staff getAuthorityCreator() {
        return authorityCreator;
    }

    public void setAuthorityCreator(Staff authorityCreator) {
        this.authorityCreator = authorityCreator;
    }

    @Override
    public String toString() {
        return "Authority{" +
                "authorityId=" + authorityId +
                ", authorityName='" + authorityName + '\'' +
                ", authorityInfo='" + authorityInfo + '\'' +
                ", authorityStatus=" + authorityStatus +
                ", createTime=" + createTime +
                '}';
    }
}
