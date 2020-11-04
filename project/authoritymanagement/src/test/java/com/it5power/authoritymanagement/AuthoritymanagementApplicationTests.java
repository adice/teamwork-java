package com.it5power.authoritymanagement;

import com.it5power.authoritymanagement.authority.dao.AuthorityDao;
import com.it5power.authoritymanagement.department.dao.DepartmentDao;
import com.it5power.authoritymanagement.dictionary.dao.DictionaryDao;
import com.it5power.authoritymanagement.entity.aut.Authority;
import com.it5power.authoritymanagement.entity.aut.Role;
import com.it5power.authoritymanagement.entity.dep.Department;
import com.it5power.authoritymanagement.entity.sta.Staff;
import com.it5power.authoritymanagement.entity.sta.StaffDetail;
import com.it5power.authoritymanagement.entity.sta.StatusDictionary;
import com.it5power.authoritymanagement.role.dao.RoleDao;
import com.it5power.authoritymanagement.staff.dao.StaffDao;
import com.it5power.authoritymanagement.staffdetail.dao.StaffDetailDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.Set;

@SpringBootTest
class AuthoritymanagementApplicationTests {

    @Autowired
    private AuthorityDao authorityDao;
    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private StaffDao staffDao;
    @Autowired
    private StaffDetailDao staffDetailDao;
    @Autowired
    private DictionaryDao dictionaryDao;


    @Test
    void contextLoads() {}

    //添加权限
    @Test
    public void saveOneAuthority(){
        Authority authority = new Authority();
        authority.setAuthorityName("招聘");
        authority.setAuthorityInfo("可以为其下部门招聘");
        authority.setAuthorityStatus(1);
        //查询创建人
        Optional<Staff> byId = staffDao.findById("01000000000000");
        //传入构建人
        authority.setAuthorityCreator(byId.get());

        authorityDao.save(authority);
    }

    //根据id查询权限
    @Test
    public void findOneAuthority(){
        Optional<Authority> authority = authorityDao.findById(1);
        System.out.printf(authority.toString());
    }


    //根据id查询staff
    @Test
    public void findOneStaff(){
        Optional<Staff> staff = staffDao.findById("01000000000000");
        System.out.printf(staff.toString());
    }

    //根据角色查找部门
    @Test
    public void findRoleByDepartment(){
        Optional<Role> byId = roleDao.findById(1);
        Department department = byId.get().getDepartment();

        System.out.printf(department.toString());
    }

    //根据员工查询员工详情
    @Test
    public void findStaffDetailByStaff(){
        Optional<Staff> byId = staffDao.findById("01000000000000");
        System.out.printf(byId.get().getStatus().toString());
        StaffDetail staffDetail = byId.get().getStaffDetail();
        System.out.printf(staffDetail.toString());
    }

    //根据部门查询人员
    @Test
    public void findStaffByDepartment(){
        Optional<Department> department = departmentDao.findById(1);
        System.out.printf(department.toString());
        Set<Staff> staffs = department.get().getStaffs();
        System.out.printf(staffs.toString());

    }

    //插入一个新用户
    @Test
    public void saveOneStaff(){
        //添加介绍人
        Optional<Staff> byId = staffDao.findById("01000000000000");
        System.out.printf(byId.toString());
        //创建人
        Staff staff = new Staff();
        staff.setId("0101010000000000");
        staff.setName("one");
        staff.setPassword("123456");
        staff.setStaffCreator(byId.get());
        //查询状态
        Optional<StatusDictionary> byId1 = dictionaryDao.findById(1);
        //设置状态
        staff.setStatus(byId1.get());
        //创建一个详情
        StaffDetail staffDetail = new StaffDetail();
        staffDetail.setStaffId("0101010000000000");
        staffDetail.setStaffName("one");
//        staffDetail.setStaffSex("man");
//        staffDetail.setStaffAddress("河北");
        staffDetail.setStaff(staff);
        staffDetail.setStaffCreator(byId.get());
        //设置详情
        staff.setStaffDetail(staffDetail);
        //查询角色
        Optional<Role> byId2 = roleDao.findById(1);
        //设置角色
        staff.getRoles().add(byId2.get());
        //查询部门
        Optional<Department> byId3 = departmentDao.findById(1);
        //设置部门
        staff.getDepartments().add(byId3.get());

        //进行插入
        staffDao.save(staff);

    }

}
