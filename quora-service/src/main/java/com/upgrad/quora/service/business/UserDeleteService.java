package com.upgrad.quora.service.business;

import com.upgrad.quora.service.dao.UserDao;
import com.upgrad.quora.service.entity.UserEntity;
import com.upgrad.quora.service.exception.AuthorizationFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDeleteService {

    @Autowired
    private UserDao userDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public String deleteUser(final UserEntity userEntity,final UserEntity signedUpUserEntity) throws AuthorizationFailedException {
        if(signedUpUserEntity.getRole().equalsIgnoreCase("admin")){
            return userDao.deleteUser(userEntity);
        }
        else {
            throw new AuthorizationFailedException("ATHR-003","Unauthorized Access, Entered user is not an admin");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public UserEntity getUserByUuid(final String uuid){
        return userDao.getUserByUuid(uuid);
    }
}
