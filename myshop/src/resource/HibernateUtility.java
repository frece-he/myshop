package resource;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtility {

	private static final String CONFIGURATION_LOCATION = "resource/hibernate.cfg.xml";
	private static SessionFactory sessionFactory = null;
	private static ServiceRegistry serviceRegistry;

	public synchronized static SessionFactory createSessionFactory() {
		if (sessionFactory == null) {
			try {
				// Step1 : Loading the configuration details from
				// hibernate.cfg.xml
				Configuration configuration = new Configuration()
						.configure(CONFIGURATION_LOCATION);
//				System.out.println("configuration  " + configuration);
				
				// Step2 : Creating ServiceRegistry using the
				// StandardServiceRegistryBuilder and Configuration defined in				
				serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();				
//				System.out.println("serviceRegistry  " + serviceRegistry);
				
				// Step3 : Creating the SessionFactory using the Configuration
				// and serviceRegistry.
				sessionFactory = configuration
						.buildSessionFactory(serviceRegistry);
			} catch (Exception e) {
				MyShopLogger.logError("HibernateUtility",
						"createSessionFactory", e.getMessage());				
				e.printStackTrace();
			}
		}
//		System.out.println(sessionFactory);
		return sessionFactory;
	}

}
