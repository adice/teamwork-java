package com.it5power.authoritymanagement.entity.sta;

import javax.persistence.*;

/**
 * @author ssk
 * @createTime 2020-10-30 9:16
 * @projectName authoritymanagement
 * @className StatusDictionary
 * @description
 */

@Entity
@Table(name = "sta_status_dictionary")
public class StatusDictionary {
    private Integer statusId;
    private String statusName;
    private String statusInfo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id")
    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    @Column(name = "status_name",length = 10)
    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @Column(name = "status_info",length = 255)
    public String getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(String statusInfo) {
        this.statusInfo = statusInfo;
    }

    @Override
    public String toString() {
        return "StatusDictionary{" +
                "statusId=" + statusId +
                ", statusName='" + statusName + '\'' +
                ", statusInfo='" + statusInfo + '\'' +
                '}';
    }
}
