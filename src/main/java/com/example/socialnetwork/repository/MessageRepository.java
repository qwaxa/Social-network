package com.example.socialnetwork.repository;

import com.example.socialnetwork.entity.Message;
import com.example.socialnetwork.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MessageRepository extends JpaRepository<Message,Integer> {
    List<Message> findMessagesBySender_IdOrRecipient_Id(Integer senderId,Integer recipientId);

    @Query(value = "SELECT m FROM Message m WHERE (m.sender.id = :sender1 AND m.recipient.id = :recipient1) OR (m.sender.id = :sender2 AND m.recipient.id = :recipient2)")
    List<Message> findMessagesBySendersAndRecipients(@Param("sender1") Integer sender1, @Param("recipient1") Integer recipient1, @Param("sender2") Integer sender2, @Param("recipient2") Integer recipient2);
}
