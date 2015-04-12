package org.apache.geode.demo.fastfootshoes.repositories;

import org.apache.geode.demo.fastfootshoes.model.Alert;
import org.springframework.data.gemfire.repository.GemfireRepository;

/**
 * This class is used to do all CRUD operations into the Alert Repository
 * @author lshannon
 *
 */
public interface AlertRepository extends GemfireRepository<Alert, String> {


}
