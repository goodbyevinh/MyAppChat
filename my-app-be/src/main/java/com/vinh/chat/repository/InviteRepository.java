package com.vinh.chat.repository;

import com.vinh.chat.entity.InviteFriendEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InviteRepository  extends JpaRepository<InviteFriendEntity, Integer> {

}
