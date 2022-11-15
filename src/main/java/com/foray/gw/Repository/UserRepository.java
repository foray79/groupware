package com.foray.gw.Repository;

import com.foray.gw.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    UserEntity findByIdx(Long idx) ;

    Optional<UserEntity> findById(Long idx);
}
