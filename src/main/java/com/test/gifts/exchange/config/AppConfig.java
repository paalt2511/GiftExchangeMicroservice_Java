package com.test.gifts.exchange.config;

import com.test.gifts.exchange.domain.FamilyMember;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
@EnableAutoConfiguration
public class AppConfig {

    private Map<String, FamilyMember>  familyMemberConcurrentHashMap = new ConcurrentHashMap<>();
    private Map<String, Queue<String>> threeYearRecipientTrackerMap  = new ConcurrentHashMap<>();

    @Bean
    public Map<String, FamilyMember> familyMemberMap() {

        FamilyMember familyMember1 = new FamilyMember("1", "John");
        FamilyMember familyMember2 = new FamilyMember("2", "John_Aunt");
        FamilyMember familyMember3 = new FamilyMember("3", "John_Uncle");
        FamilyMember familyMember4 = new FamilyMember("4", "John_Father");
        FamilyMember familyMember5 = new FamilyMember("5", "John_Mother");
        FamilyMember familyMember6 = new FamilyMember("6", "John_GrandMother");
        FamilyMember familyMember7 = new FamilyMember("7", "John_GrandFather");

        familyMemberConcurrentHashMap.put(familyMember1.getId(), familyMember1);
        familyMemberConcurrentHashMap.put(familyMember2.getId(), familyMember2);
        familyMemberConcurrentHashMap.put(familyMember3.getId(), familyMember3);
        familyMemberConcurrentHashMap.put(familyMember4.getId(), familyMember4);
        familyMemberConcurrentHashMap.put(familyMember5.getId(), familyMember5);
        familyMemberConcurrentHashMap.put(familyMember6.getId(), familyMember6);
        familyMemberConcurrentHashMap.put(familyMember7.getId(), familyMember7);

        return familyMemberConcurrentHashMap;

    }


    @Bean
    public Map<String, Queue<String>> threeYearRecipientTrackerMap() {
        return threeYearRecipientTrackerMap;
    }




}
