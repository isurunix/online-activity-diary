package lk.grp.synergy.control;

import lk.grp.synergy.model.Notification;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by isuru on 1/19/17.
 */
public interface NotificationControllerInterface {

    public List<Notification> getAllPendingNotifications() throws SQLException;
    public List<Notification> getAllNotificationsPerHour(LocalDateTime time) throws SQLException;
}
