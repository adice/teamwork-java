package com.it5power.authoritymanagement.entity.aut;

import com.it5power.authoritymanagement.entity.dep.Department;
import com.it5power.authoritymanagement.entity.sta.Staff;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ssk
 * @createTime 2020-10-30 9:16
 * @projectName authoritymanagement
 * @className Role
 * @description
 */

@Entity
@Table(name = "aut_role")
public class Role {
    private Integer roleId;//角色id
    private String roleName;//角色名称
    private Integer roleStatus;//角色状态
    private Staff roleCreator;//创建人
    private Date createTime;//创建时间

    private Set<Authority> authorities = new HashSet<Authority>();//拥有权限
    private Department department;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Column(name = "role_name",length = 30)
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


    @Column(name = "role_status")
    public Integer getRoleStatus() {
        return roleStatus;
    }

    public void setRoleStatus(Integer roleStatus) {
        this.roleStatus = roleStatus;
    }

    @ManyToOne
    @JoinColumn(name = "role_creator",foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    public Staff getRoleCreator() {
        return roleCreator;
    }

    public void setRoleCreator(Staff roleCreator) {
        this.roleCreator = roleCreator;
    }

    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "rel_role_authority",
            joinColumns = {@JoinColumn(name = "role_id",foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))},
            inverseJoinColumns = {@JoinColumn(name = "authority_id",foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))})
    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    @ManyToOne
    @JoinColumn(name = "department_id",foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", roleStatus=" + roleStatus +
                ", createTime=" + createTime +
                '}';
    }
}
