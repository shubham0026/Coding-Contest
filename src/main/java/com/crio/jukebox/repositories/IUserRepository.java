package com.crio.jukebox.repositories;

import com.crio.jukebox.entities.User;

public interface IUserRepository extends CRUDRepository<User,String>{
    public User getUserById(String userId);
}
