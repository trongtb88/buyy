/**
 * 
 */
package com.dragon88.microservices.inventory.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.dragon88.microservices.inventory.models.SecondaryCategory;
import com.dragon88.microservices.inventory.models.TertiaryCategory;

/**
 * @author dragon
 *
 */
public interface TertiaryCategoryRepository extends CrudRepository<TertiaryCategory, Long> {

	public List<TertiaryCategory> findBySecondaryCategory(SecondaryCategory secondaryCategory);
	
}