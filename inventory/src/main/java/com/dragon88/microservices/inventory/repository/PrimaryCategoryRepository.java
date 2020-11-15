/**
 * 
 */
package com.dragon88.microservices.inventory.repository;

import org.springframework.data.repository.CrudRepository;

import com.dragon88.microservices.inventory.models.PrimaryCategory;

/**
 * @author dragon
 *
 */
public interface PrimaryCategoryRepository extends CrudRepository<PrimaryCategory, Long> {

}
