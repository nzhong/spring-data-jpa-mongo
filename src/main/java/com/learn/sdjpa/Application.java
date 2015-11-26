package com.learn.sdjpa;

import com.learn.sdjpa.domain.Customer;
import com.learn.sdjpa.repos.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Application {

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
        MongoOperations mongoOperation = (MongoOperations) context.getBean("mongoTemplate");

        // Seed one line of data
        Customer c = new Customer("Jack", "Bauer");
        c.setRelatives(Arrays.asList("A", "B", "Z"));
        Map<String, List<String>> mp = new HashMap<String, List<String>>();
        mp.put("1", Arrays.asList("1a", "1b"));
        mp.put("2", Arrays.asList("2a", "2c"));
        mp.put("3", Arrays.asList("3x", "3z"));
        c.setComplex(mp);
        mongoOperation.save( c );

        // find the saved user again.
        Query searchUserQuery = new Query(Criteria.where("firstName").is("Jack"));
        Customer savedUser = mongoOperation.findOne(searchUserQuery, Customer.class);
        System.out.println("find - savedUser : " + savedUser);

        CustomerRepository customerRepo = context.getBean(CustomerRepository.class);
        System.out.println( "number of rows in collection: "+customerRepo.count() );

        // use repo, instead of mongoOperations
        savedUser = customerRepo.findByFirstName("Jack");
        System.out.println("find - byFirstName : " + savedUser);

        List<Customer> searchResults = customerRepo.findByLastName("Bauer");
        System.out.println("find - byLastName : " + searchResults);
    }
}