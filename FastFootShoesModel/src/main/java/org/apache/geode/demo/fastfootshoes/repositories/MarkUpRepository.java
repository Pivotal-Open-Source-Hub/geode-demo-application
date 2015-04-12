/**
 * 
 */
package org.apache.geode.demo.fastfootshoes.repositories;

import org.apache.geode.demo.fastfootshoes.model.MarkUp;
import org.springframework.data.gemfire.repository.GemfireRepository;

/**
 * This class is used to do all CRUD operations into the Mark Up Repository
 * @author lshannon
 *
 */
public interface MarkUpRepository extends GemfireRepository<MarkUp, String> {

}
