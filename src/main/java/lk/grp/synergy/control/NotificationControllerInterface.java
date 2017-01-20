package lk.grp.synergy.control;

import lk.grp.synergy.model.Notification;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by isuru on 1/19/17.
 */
public interface NotificationControllerInterface {

    public List<Notification> getAllPendingNotifications();
    public List<Notification> getAllNotificationsPerHour(LocalDateTime time);
}
