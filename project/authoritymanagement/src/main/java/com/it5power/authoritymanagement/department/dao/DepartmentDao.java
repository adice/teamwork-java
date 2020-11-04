package com.it5power.authoritymanagement.department.dao;

import com.it5power.authoritymanagement.entity.dep.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author ssk
 * @createTime 2020-10-30 9:16
 * @projectName authoritymanagement
 * @className DepartmentDao
 * @description
 */
public interface DepartmentDao extends JpaRepository<Department,Integer>, JpaSpecificationExecutor<Department> {}
