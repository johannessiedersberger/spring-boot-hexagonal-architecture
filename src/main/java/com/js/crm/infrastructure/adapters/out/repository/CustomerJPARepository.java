package com.js.crm.infrastructure.adapters.out.repository;

import com.js.crm.infrastructure.adapters.out.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerJPARepository extends JpaRepository<CustomerEntity, Long> {

}
