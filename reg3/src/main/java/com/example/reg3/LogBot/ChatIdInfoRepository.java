package com.example.reg3.LogBot;

import com.example.reg3.dao.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatIdInfoRepository
        extends JpaRepository<ChatIdInfo, Long> {

     Optional<ChatIdInfo> findChatIdInfoByChatId(Long id);

}
