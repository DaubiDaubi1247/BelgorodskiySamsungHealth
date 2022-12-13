package com.example.reg3.LogBot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatIdInfoRepository
        extends JpaRepository<ChatIdInfo, Long> {

}
