package com.it5power.authoritymanagement.staff.dao;

import com.it5power.authoritymanagement.entity.sta.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author ssk
 * @createTime 2020-10-30 9:16
 * @projectName authoritymanagement
 * @className StaffDao
 * @description
 */
public interface StaffDao extends JpaRepository<Staff,String>, JpaSpecificationExecutor<Staff> {}
