package com.mycompany.app.main.database.persistence;

import com.mycompany.app.main.database.DatabaseConnectionInfo;
import com.mycompany.app.main.database.DatabaseType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceUnitInfo;

import org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl;
import org.hibernate.jpa.boot.internal.PersistenceUnitInfoDescriptor;

public class JpaEntityManagerFactory {
    private DatabaseType databaseType;
    private String url;
    private String username;
    private String password;
    private List<String> entityNames;

    public JpaEntityManagerFactory(DatabaseConnectionInfo connectionInfo) {
        this.databaseType = connectionInfo.getDatabaseType();
        this.url = connectionInfo.getUrl();
        this.username = connectionInfo.getUsername();
        this.password = connectionInfo.getPassword();
        this.entityNames = connectionInfo.getEntityNames();
    }

    public EntityManager getEntityManager() {
        return getEntityManagerFactory().createEntityManager();
    }

    protected EntityManagerFactory getEntityManagerFactory() {
        PersistenceUnitInfo persistenceUnitInfo = new HibernatePersistenceUnitInfo(getClass().getSimpleName(), getEntityClassNames(), getProperties());
        Map<String, Object> configuration = new HashMap<>();
        return new EntityManagerFactoryBuilderImpl(new PersistenceUnitInfoDescriptor(persistenceUnitInfo), configuration)
                .build();
    }

    protected HibernatePersistenceUnitInfo getPersistenceUnitInfo(String name) {
        return new HibernatePersistenceUnitInfo(name, getEntityClassNames(), getProperties());
    }

    protected List<String> getEntityClassNames() {
        return entityNames;
    }
    protected Properties getProperties() {
        Properties settings = new Properties();
        settings.put("hibernate.dialect",  this.databaseType.getDialect());
        settings.put("hibernate.id.new_generator_mappings", false);
        settings.put("hibernate.connection.url", url);
        settings.put("hibernate.connection.username", username);
        settings.put("hibernate.connection.password", password);
        return settings;
    }
}