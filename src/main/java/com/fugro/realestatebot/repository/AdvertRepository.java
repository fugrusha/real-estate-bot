package com.fugro.realestatebot.repository;

import com.fugro.realestatebot.domain.Advert;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdvertRepository extends MongoRepository<Advert, String> {
}
