package com.test.gifts.exchange.services;

import com.test.gifts.exchange.dao.GiftRepository;
import com.test.gifts.exchange.domain.FamilyMember;
import com.test.gifts.exchange.domain.GiftExchange;
import com.test.gifts.exchange.exception.FamilyMemberNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GiftExchangeServiceTest {

    @Mock
    private GiftRepository giftRepository;

    @Mock
    private Map threeYearRecipientTrackerMap;

    @InjectMocks
    private GiftExchangeService giftExchangeService;

    @Before
    public void setUp() throws Exception {
        when(giftRepository.fetchFamilyMemberMap()).thenReturn(setupStubData());

    }

    @Test
    public void shouldfetchGiftExchangeDetails() throws FamilyMemberNotFoundException {

        Set<GiftExchange> giftExchanges = giftExchangeService.fetchGiftExchangeDetails();
        assertNotNull(giftExchanges);

    }

    private Map setupStubData() {

        FamilyMember familyMember1 = new FamilyMember("1", "John");
        FamilyMember familyMember2 = new FamilyMember("2", "John_Aunt");
        FamilyMember familyMember3 = new FamilyMember("3", "John_Uncle");
        FamilyMember familyMember4 = new FamilyMember("4", "John_Father");
        FamilyMember familyMember5 = new FamilyMember("5", "John_Mother");
        FamilyMember familyMember6 = new FamilyMember("6", "John_GrandMother");
        FamilyMember familyMember7 = new FamilyMember("7", "John_GrandFather");

        Map<String, FamilyMember>  familyMemberConcurrentHashMap = new ConcurrentHashMap<>();
        familyMemberConcurrentHashMap.put(familyMember1.getId(), familyMember1);
        familyMemberConcurrentHashMap.put(familyMember2.getId(), familyMember2);
        familyMemberConcurrentHashMap.put(familyMember3.getId(), familyMember3);
        familyMemberConcurrentHashMap.put(familyMember4.getId(), familyMember4);
        familyMemberConcurrentHashMap.put(familyMember5.getId(), familyMember5);
        familyMemberConcurrentHashMap.put(familyMember6.getId(), familyMember6);
        familyMemberConcurrentHashMap.put(familyMember7.getId(), familyMember7);

        return familyMemberConcurrentHashMap;
    }
}