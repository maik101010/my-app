package com.mycompany.app.main.abilities;

import com.mycompany.app.main.database.DatabaseConnectionInfo;
import com.mycompany.app.main.database.persistence.JpaEntityManagerFactory;
import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.Actor;

import javax.persistence.EntityManager;

public class InteractWithDb implements Ability {
    private EntityManager entityManager;
    public EntityManager getManager(){
        return entityManager;
    }

    public InteractWithDb(DatabaseConnectionInfo databaseConnectionInfo) {
        this.entityManager = new JpaEntityManagerFactory(databaseConnectionInfo).getEntityManager();
    }

    public static InteractWithDb using(DatabaseConnectionInfo databaseConnectionInfo){
        return new InteractWithDb(databaseConnectionInfo);
    }

    public static InteractWithDb as(Actor actor){
        return actor.abilityTo(InteractWithDb.class);
    }
}
