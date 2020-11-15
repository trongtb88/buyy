/**
 * 
 */
package com.dragon88.microservices.inventory.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.dragon88.microservices.inventory.models.PrimaryCategory;
import com.dragon88.microservices.inventory.models.SecondaryCategory;

/**
 * @author dragon
 *
 */
public interface SecondaryCategoryRepository extends CrudRepository<SecondaryCategory, Long> {

	public List<SecondaryCategory> findByPrimaryCategory(PrimaryCategory primaryCategory);
	
}
