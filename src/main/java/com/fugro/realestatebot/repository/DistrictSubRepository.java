package com.fugro.realestatebot.repository;

import com.fugro.realestatebot.domain.DistrictSub;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DistrictSubRepository extends MongoRepository<DistrictSub, String> {

    Optional<DistrictSub> findByChatIdAndDistrictId(String chatId, Integer districtId);

    List<DistrictSub> findByChatId(String chatId);

    void deleteByChatId(String chatId);
}
