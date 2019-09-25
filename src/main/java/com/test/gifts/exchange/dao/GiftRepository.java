package com.test.gifts.exchange.dao;

import com.test.gifts.exchange.domain.FamilyMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class GiftRepository {

    private Map<String, FamilyMember> familyMemberMap;

    @Autowired
    private GiftRepository(Map<String, FamilyMember> familyMemberMap) {
        this.familyMemberMap = familyMemberMap;
    }

    public Map fetchFamilyMemberMap() {
        return familyMemberMap;
    }
}
