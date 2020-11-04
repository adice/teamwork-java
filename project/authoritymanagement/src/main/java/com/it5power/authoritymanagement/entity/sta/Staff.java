package com.it5power.authoritymanagement.entity.sta;

import com.it5power.authoritymanagement.entity.aut.Role;
import com.it5power.authoritymanagement.entity.dep.Department;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ssk
 * @createTime 2020-10-30 9:16
 * @projectName authoritymanagement
 * @className Staff
 * @description
 */

@Entity
@Table(name = "sta_staff")
public class Staff {
    private String id;//员工id
    private String name;//员工姓名
    private String password;//员工密码
    private StatusDictionary status;//员工状态
    private Date createTime;//创建时间
    private Staff staffCreator;//创建人

    private StaffDetail staffDetail;//员工详情
    private Set<Role> roles = new HashSet<Role>();//员工角色
    private Set<Department> departments = new HashSet<Department>();//员工部门


    @Id
    @GenericGenerator(name = "idGeneration",strategy = "assigned")
    @GeneratedValue(generator = "idGeneration")
    @Column(name = "id",length = 30)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "name",length = 10)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "password",length = 100)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ManyToOne
    @JoinColumn(name = "status",foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    public StatusDictionary getStatus() {
        return status;
    }

    public void setStatus(StatusDictionary status) {
        this.status = status;
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "staff_detail",foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    public StaffDetail getStaffDetail() {
        return staffDetail;
    }

    public void setStaffDetail(StaffDetail staffDetail) {
        this.staffDetail = staffDetail;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "rel_staff_role",
            joinColumns = {@JoinColumn(name = "staff_id",foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))},
            inverseJoinColumns = {@JoinColumn(name = "role_id",foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))})
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "rel_staff_department",
            joinColumns = {@JoinColumn(name = "staff_id",foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))},
            inverseJoinColumns = {@JoinColumn(name = "department_id",foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))})
    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                '}';
    }
}
