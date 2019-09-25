package com.test.gifts.exchange.services;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.test.gifts.exchange.domain.FamilyMember;
import com.test.gifts.exchange.exception.FamilyMemberAlreadyPresentException;
import com.test.gifts.exchange.exception.FamilyMemberNotFoundException;
import jdk.nashorn.internal.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FamilyMemberService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FamilyMemberService.class);

    @Autowired
    private Map<String, FamilyMember> familyMemberMap;

    public List<FamilyMember> listAllFamilyMembers() {
        if (isFamilyMemberMapNullOrEmpty(familyMemberMap)) {
            return new ArrayList<>();
        }
        return new ArrayList<>(familyMemberMap.values());
    }

    public void addFamilyMember(FamilyMember familyMember) throws FamilyMemberAlreadyPresentException {
        if (familyMember == null || familyMemberMap.containsKey(familyMember.getId())) {
            LOGGER.error("Exception Occurred");
            throw new FamilyMemberAlreadyPresentException("family member already present with the given id " + familyMember.getId());
        }
        FamilyMember newFamilyMember = new FamilyMember(familyMember.getId(), familyMember.getName());
        familyMemberMap.put(familyMember.getId(), newFamilyMember);
    }

    public void removeFamilyMember(String id) throws FamilyMemberNotFoundException {
        if (isFamilyMemberMapNullOrEmpty(familyMemberMap) || !familyMemberMap.containsKey(id)) {
            LOGGER.error("Exception Occurred");
            throw new FamilyMemberNotFoundException("family member does not exists with the given id " + id);
        }
        familyMemberMap.remove(id);
    }

    public void updateFamilyMember(String id, String name) throws FamilyMemberNotFoundException, IOException {
        if (isFamilyMemberMapNullOrEmpty(familyMemberMap) || !familyMemberMap.containsKey(id)) {
            LOGGER.error("Exception Occurred");
            throw new FamilyMemberNotFoundException("family member does not exists with the given id " + id);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap hashMap = objectMapper.readValue(name, HashMap.class);
        String value = (String)hashMap.get("name");
        FamilyMember newFamilyMember = new FamilyMember(id, value);
        familyMemberMap.put(id, newFamilyMember); // Parse Json from name

    }

    public FamilyMember fetchFamilyMember(String id) throws FamilyMemberNotFoundException {
        if (isFamilyMemberMapNullOrEmpty(familyMemberMap) || !familyMemberMap.containsKey(id)) {
            LOGGER.error("Exception Occurred");
            throw new FamilyMemberNotFoundException("family member does not exists with the given id " + id);
        }
        return familyMemberMap.get(id);

    }

    private boolean isFamilyMemberMapNullOrEmpty(Map familyMemberMap) {
        if (familyMemberMap == null || familyMemberMap.isEmpty()) {
            return true;
        }
        return false;
    }
}
