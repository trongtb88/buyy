/**
 * 
 */
package com.dragon88.microservices.invoice.repository;

import com.dragon88.microservices.invoice.models.ModeOfPay;
import org.springframework.data.repository.CrudRepository;

/**
 * @author hitjoshi
 *
 */
public interface ModeOfPayRepository extends CrudRepository<ModeOfPay, Long> {

}
