/**
 * 
 */
package org.apache.geode.demo.fastfootshoes.repositories;

import java.util.Collection;

import org.apache.geode.demo.fastfootshoes.model.Product;
import org.springframework.data.gemfire.repository.GemfireRepository;
import org.springframework.data.gemfire.repository.Query;

/**
 * This class is used to do all CRUD operations into the Product Repository
 * @author lshannon
 * @author Josh Long
 */
public interface ProductRepository extends GemfireRepository<Product, String> {

	Collection<Product> findAll();
	
	Product findById(String id);

	@Query("SELECT * FROM /Product p WHERE p.stockOnHand > 0")
	Collection<Product> findAllWithStock();

	@Query("SELECT * FROM /Product p WHERE p.brand = $1 and p.\"type\"= $2 and p.gender = $3")
	Collection<Product> findAllByBrandTypeGender(String brand, String type,
			String gender);

	@Query("SELECT * FROM /Product p   WHERE  p.brand = $1 and  p.\"type\" = $2 and  p.gender = $3 and  p.stockOnHand > 0")
	Collection<Product> findAllWithStockByBrandTypeGender(String brand,String type, String gender);

    @Query("SELECT * FROM /Product p WHERE p.\"type\" LIKE $1 and p.stockOnHand > 0")
    Collection<Product> findAllWithStockByBrand( String brand) ;

}
