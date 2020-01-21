package com.example;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service("simpleUserDetailsService")
public class SimpleUserDetailsService implements UserDetailsService {

    private final KaiinRepository kaiinRepository;

    public SimpleUserDetailsService(KaiinRepository kaiinRepository) {
        this.kaiinRepository = kaiinRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String name) {
        // emailでデータベースからユーザーエンティティを検索する
        return (UserDetails) kaiinRepository.findByName(name);
    }
}