package com.it5power.authoritymanagement.dictionary.dao;

import com.it5power.authoritymanagement.entity.sta.StatusDictionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author ssk
 * @createTime 2020-10-30 9:16
 * @projectName authoritymanagement
 * @className DictionaryDao
 * @description
 */
public interface DictionaryDao extends JpaRepository<StatusDictionary,Integer>, JpaSpecificationExecutor<StatusDictionary> {}
