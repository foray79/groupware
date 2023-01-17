package com.foray.gw.Repository;

import com.foray.gw.Entity.DocumentEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface DocumentRepository extends JpaRepository<DocumentEntity,Long>{
    //    List<DocumentEntity> findById(int idx);
    DocumentEntity findByIdx(Long idx) ;

    Optional<DocumentEntity> findById(Long idx);


}
