package lk.grp.synergy.control.impl;

import lk.grp.synergy.control.NotificationControllerInterface;
import lk.grp.synergy.model.Notification;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by USER on 1/24/2017.
 */
public class NotificationController implements NotificationControllerInterface {

    @Override
    public List<Notification> getAllPendingNotifications() throws SQLException {
        return null;
    }

    @Override
    public List<Notification> getAllNotificationsPerHour(LocalDateTime time) throws SQLException {
        return null;
    }
}
