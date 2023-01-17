package com.foray.gw.Repository;

import com.foray.gw.Entity.ApprovalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApprovalRepository extends JpaRepository<ApprovalEntity,Long> {
        //    List<DocumentEntity> findById(int idx);
        ApprovalEntity findByIdx(Long idx) ;
        Optional<ApprovalEntity> findById(Long idx);

}
