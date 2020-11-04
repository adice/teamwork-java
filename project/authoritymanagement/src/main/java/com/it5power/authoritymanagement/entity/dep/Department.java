package com.it5power.authoritymanagement.entity.dep;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.it5power.authoritymanagement.entity.sta.Staff;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ssk
 * @createTime 2020-10-30 9:16
 * @projectName authoritymanagement
 * @className Department
 * @description
 */

@Entity
@Table(name = "dep_department")
public class Department {
    private Integer departmentId;//部门id
    private String departmentName;//部门名称
    private Staff departmentOriginator;//部门创始人
    private Date createTime;//部门创建时间
    private String departmentInfo;//部门简介
    private Staff departmentPrincipal;//部门负责人
    private Department superiorDepartment;//父部门
    private Integer departmentStatus;//部门状态(1代表工作，0代表注销)

    private Set<Staff> staffs = new HashSet<Staff>();//部门员工

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    @Column(name = "department_name",length = 30)
    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @ManyToOne
    @JoinColumn(name = "department_originator",foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    public Staff getDepartmentOriginator() {
        return departmentOriginator;
    }

    public void setDepartmentOriginator(Staff departmentOriginator) {
        this.departmentOriginator = departmentOriginator;
    }

    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "department_info",length = 255)
    public String getDepartmentInfo() {
        return departmentInfo;
    }

    public void setDepartmentInfo(String departmentInfo) {
        this.departmentInfo = departmentInfo;
    }

    @ManyToOne
    @JoinColumn(name = "department_principal",foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    public Staff getDepartmentPrincipal() {
        return departmentPrincipal;
    }

    public void setDepartmentPrincipal(Staff departmentPrincipal) {
        this.departmentPrincipal = departmentPrincipal;
    }

    @ManyToOne
    @JoinColumn(name = "superior_department_id",foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    public Department getSuperiorDepartment() {
        return superiorDepartment;
    }

    public void setSuperiorDepartment(Department superiorDepartment) {
        this.superiorDepartment = superiorDepartment;
    }

    @Column(name = "department_status")
    public Integer getDepartmentStatus() {
        return departmentStatus;
    }

    public void setDepartmentStatus(Integer departmentStatus) {
        this.departmentStatus = departmentStatus;
    }

    @ManyToMany(mappedBy = "departments",fetch = FetchType.EAGER)
    @org.hibernate.annotations.ForeignKey(name = "none")
    @JsonIgnore
    public Set<Staff> getStaffs() {
        return staffs;
    }

    public void setStaffs(Set<Staff> staffs) {
        this.staffs = staffs;
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                ", departmentOriginator=" + departmentOriginator +
                ", createTime=" + createTime +
                ", departmentInfo='" + departmentInfo + '\'' +
                ", departmentStatus=" + departmentStatus +
                '}';
    }
}
