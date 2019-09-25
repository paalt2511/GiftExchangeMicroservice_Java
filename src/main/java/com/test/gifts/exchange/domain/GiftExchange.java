package com.test.gifts.exchange.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class GiftExchange {

    @JsonProperty("member_id")
    private String memberId;

    @JsonProperty("recipient_member_id")
    private String receipientMemberId;

}
