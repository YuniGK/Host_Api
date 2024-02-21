package yun.jung.kim.Host_Api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yun.jung.kim.Host_Api.domain.UserAccount;
import yun.jung.kim.Host_Api.dto.UserAccountDto;
import yun.jung.kim.Host_Api.repository.UserAccountRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;

    @Transactional(readOnly = true)
    public Optional<UserAccountDto> searchUser(String username) {
        return userAccountRepository.findById(username)
                .map(UserAccountDto::from);
    }

    public UserAccountDto saveUser(String username, String password, String email) {
        return UserAccountDto.from(
                userAccountRepository.save(UserAccount.of(username, password, email))
        );
    }

}