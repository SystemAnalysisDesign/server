package com.example.SystemAnalysisDesign.notification.respository;

import com.example.SystemAnalysisDesign.notification.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationJpaRepository extends JpaRepository<Notification, Long> {

    List<Notification> findAllByUser_Id(long userId);
}
