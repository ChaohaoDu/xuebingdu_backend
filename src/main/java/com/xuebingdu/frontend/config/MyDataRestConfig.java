package com.xuebingdu.frontend.config;

import com.xuebingdu.frontend.entity.Address;
import com.xuebingdu.frontend.entity.Customer;
import com.xuebingdu.frontend.entity.Order;
import com.xuebingdu.frontend.entity.OrderItem;
import com.xuebingdu.frontend.entity.Product;
import com.xuebingdu.frontend.entity.ProductSize;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {
	private EntityManager entityManager;

	@Value("${allowed.origins}")
	private String[] allowedOrigins;

	@Autowired
	public MyDataRestConfig(EntityManager theEntityManger) {
		entityManager = theEntityManger;
	}

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
		HttpMethod[] theUnsupportedActions = {HttpMethod.PUT,  HttpMethod.DELETE, HttpMethod.PATCH};

		disableHttpMethods(Product.class, config, theUnsupportedActions);
		disableHttpMethods(ProductSize.class, config, theUnsupportedActions);
		disableHttpMethods(Order.class, config, theUnsupportedActions);
		disableHttpMethods(OrderItem.class, config, theUnsupportedActions);
		disableHttpMethods(Address.class, config, theUnsupportedActions);
		disableHttpMethods(Customer.class, config, theUnsupportedActions);

		exposeIds(config);
		cors.addMapping(config.getBasePath() + "/**").allowedOrigins(allowedOrigins);
	}

	private void disableHttpMethods(Class theClass, RepositoryRestConfiguration config,
	   HttpMethod[] theUnsupportedActions) {
		config.getExposureConfiguration().forDomainType(theClass)
		   .withItemExposure((metadata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
		   .withCollectionExposure((metadata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
	}

	private void exposeIds(RepositoryRestConfiguration config) {
		// - get a list of all entity classes from the entity manager
		Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

		// - create an array of the entity types
		List<Class> entityClasses = new ArrayList<>();

		// - get the entity types for the entities
		for (EntityType tempEntityType : entities) {
			entityClasses.add(tempEntityType.getJavaType());
		}

		// - expose the entity ids for the array of entity/domain types
		Class[] domainTypes = entityClasses.toArray(new Class[1]);

		config.exposeIdsFor(domainTypes);
	}
}

