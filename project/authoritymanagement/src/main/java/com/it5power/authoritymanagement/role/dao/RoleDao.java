package com.it5power.authoritymanagement.role.dao;

import com.it5power.authoritymanagement.entity.aut.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author ssk
 * @createTime 2020-10-30 9:16
 * @projectName authoritymanagement
 * @className RoleDao
 * @description
 */
public interface RoleDao extends JpaRepository<Role,Integer>, JpaSpecificationExecutor<Role> {}
