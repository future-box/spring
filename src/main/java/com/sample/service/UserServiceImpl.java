package com.sample.service;

import com.sample.dao.UserDao;
import com.sample.domain.User;
import java.util.List;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private MailSender mailSender;


    public UserServiceImpl userDao(UserDao userDao) {
        this.userDao = userDao;
        return this;
    }

    public UserServiceImpl mailSender(MailSender mailSender) {
        this.mailSender = mailSender;
        return this;
    }

    @Override
    public void add(User user) {

    }

    @Override
    public void upgradeLevels() {
        List<User> users = this.userDao.get();
        for (User user : users) {
            if (user.canUpgradeLevel(user)) {
                this.upgradeLevel(user);
            }
        }
    }

    protected void upgradeLevel(User user) {
        user.upgradeLevel();
        userDao.update(user);
        sendUpgradeEMail(user);
    }

    private void sendUpgradeEMail(User user) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setFrom("yun27jin@gmail.com");
        mailMessage.setSubject("Upgrade 안내");
        mailMessage.setText("사용자님의 등급이 " + user.getLevel().name());

        mailSender.send(mailMessage);
    }

    public static class TestUserService extends UserServiceImpl {

        private String id;

        TestUserService(String id) {
            this.id = id;
        }

        @Override
        protected void upgradeLevel(User user) {
            if (user.getId().equals(this.id)) {
                throw new TestUserServiceException();
            }
            super.upgradeLevel(user);
        }
    }

    public static class TestUserServiceException extends RuntimeException {

    }
}