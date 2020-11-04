package com.it5power.authoritymanagement.staffdetail.dao;

import com.it5power.authoritymanagement.entity.sta.StaffDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author ssk
 * @createTime 2020-10-30 9:16
 * @projectName authoritymanagement
 * @className StaffDetailDao
 * @description
 */
public interface StaffDetailDao extends JpaRepository<StaffDetail,String>, JpaSpecificationExecutor<StaffDetail> {}
