package com.it5power.authoritymanagement.entity.sta;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * @author ssk
 * @createTime 2020-10-30 9:16
 * @projectName authoritymanagement
 * @className StaffDetail
 * @description
 */

@Entity
@Table(name = "sta_staff_detail")
public class StaffDetail {
    private String staffId;//员工编号
    private String staffName;//员工姓名
    private String staffSex;//员工性别
    private String staffAddress;//员工住址
    private Date createTime;//创建时间
    private Staff staffCreator;//创建人

    private Staff staff;//员工

    @Id
    @GenericGenerator(name = "staffIdGeneration",strategy = "assigned")
    @GeneratedValue(generator = "staffIdGeneration")
    @Column(name = "staff_id",length = 30)
    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    @Column(name = "staff_name",length = 10)
    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    @Column(name = "staff_sex",length = 5)
    public String getStaffSex() {
        return staffSex;
    }

    public void setStaffSex(String staffSex) {
        this.staffSex = staffSex;
    }

    @Column(name = "staff_address",length = 255)
    public String getStaffAddress() {
        return staffAddress;
    }

    public void setStaffAddress(String staffAddress) {
        this.staffAddress = staffAddress;
    }

    @OneToOne(mappedBy = "staffDetail",fetch = FetchType.EAGER)
    @org.hibernate.annotations.ForeignKey(name = "none")
    @JsonIgnore
    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @ManyToOne
    @JoinColumn(name = "staff_creator",foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    public Staff getStaffCreator() {
        return staffCreator;
    }

    public void setStaffCreator(Staff staffCreator) {
        this.staffCreator = staffCreator;
    }

    @Override
    public String toString() {
        return "StaffDetail{" +
                "staffId='" + staffId + '\'' +
                ", staffName='" + staffName + '\'' +
                ", staffSex='" + staffSex + '\'' +
                ", staffAddress='" + staffAddress + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
