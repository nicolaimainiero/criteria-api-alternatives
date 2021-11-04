package de.sidion.blog.criteria_api_alternatives;

import com.speedment.jpastreamer.application.JPAStreamer;
import de.sidion.blog.criteria_api_alternatives.model.Customer;
import de.sidion.blog.criteria_api_alternatives.model.PurchaseOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

class Application {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Application.class);
        // Use persistence.xml configuration
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("criteria-api");
        EntityManager em = emf.createEntityManager();
        JPAStreamer jpaStreamer = JPAStreamer.of(emf);

        logger.atInfo().log("-------------- Criteria API --------------");
        // use criteria API
        CriteriaApiExample api = new CriteriaApiExample(em);
        Customer apiByName = api.findByName("Stuart");
        logger.atInfo().log("Found customer via criteria API: {}", apiByName);

        PurchaseOrder apiOrderOfCustomer = api.findOrderOfCustomer("Stuart");
        logger.atInfo().log("Found oder via criteria API: {}", apiOrderOfCustomer);

        logger.atInfo().log("--------------- JOOQ ---------------");
        // use jooq
        JOOQExample jooq = new JOOQExample(em);
        Customer jooqByName = jooq.findByName("Stuart");
        logger.atInfo().log("Found customer via jooq: {}", jooqByName);

        PurchaseOrder jooqOrderOfCustomer = jooq.findOrderOfCustomer("Stuart");
        logger.atInfo().log("Found oder via jooq: {}", jooqOrderOfCustomer);


        logger.atInfo().log("--------------- JPA Streamer ---------------");
        JPAStreamerExample streamer = new JPAStreamerExample(jpaStreamer);
        Customer jpaByName = streamer.findByName("Stuart");
        logger.atInfo().log("Found customer via jpa streamer: {}", jpaByName);

        PurchaseOrder jpaOrderOfCustomer = jooq.findOrderOfCustomer("Stuart");
        logger.atInfo().log("Found oder via jpa streamer: {}", jpaOrderOfCustomer);

        logger.atInfo().log("--------------- QueryDSL ---------------");
        QueryDSLExample dsl = new QueryDSLExample(em);
        Customer dslByName = dsl.findByName("Stuart");
        logger.atInfo().log("Found customer via QueryDSL: {}", dslByName);

        PurchaseOrder dslOrderOfCustomer = jooq.findOrderOfCustomer("Stuart");
        logger.atInfo().log("Found oder via QueryDSL: {}", dslOrderOfCustomer);


        //close at application end
        jpaStreamer.close();
        em.close();
        emf.close();
    }


}