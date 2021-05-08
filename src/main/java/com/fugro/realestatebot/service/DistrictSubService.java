package com.fugro.realestatebot.service;

import com.fugro.realestatebot.domain.DistrictSub;

import java.util.List;
import java.util.Optional;

public interface DistrictSubService {

    List<DistrictSub> getUserSubs(String chatId);

    Optional<DistrictSub> getSub(String chatId, Integer districtId);

    DistrictSub createSub(String chatId, Integer districtId);

    boolean deleteSub(String chatId, Integer districtId);

}
