package com.vinh.chat.repository;

import com.vinh.chat.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {
    AccountEntity findByEmail(String email);

    @Query(value = "select a2 from account a1 join account_account aa on a1.id = aa.accountMeId" +
            " join account a2 on a2.id = aa.accountOtherId" +
            " where a1.email = ?1 ")
    List<AccountEntity> findListFriendOfMe(String email);

    List<AccountEntity> findAccountEntitiesByEmailNotIn(List<String> emails);

    @Query(value = "select a2 from account a1 join invite_friend aa on a1.id = aa.accountFriendId" +
            " join account a2 on a2.id = aa.accountMeId" +
            " where a1.email = ?1 ")
    List<AccountEntity> findListInvitedOfMe(String email);


}
