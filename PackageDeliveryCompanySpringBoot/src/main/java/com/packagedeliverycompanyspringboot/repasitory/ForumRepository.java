package com.packagedeliverycompanyspringboot.repasitory;

import com.packagedeliverycompanyspringboot.model.Forum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForumRepository extends JpaRepository<Forum, Integer> {
    Forum findAllByForumTitle(String forumTitle);
}
