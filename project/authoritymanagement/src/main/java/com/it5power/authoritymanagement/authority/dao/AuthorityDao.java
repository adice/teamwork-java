package com.it5power.authoritymanagement.authority.dao;

import com.it5power.authoritymanagement.entity.aut.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author ssk
 * @createTime 2020-10-30 9:16
 * @projectName authoritymanagement
 * @className AuthorityDao
 * @description
 */
public interface AuthorityDao extends JpaRepository<Authority,Integer>, JpaSpecificationExecutor<Authority> {}
