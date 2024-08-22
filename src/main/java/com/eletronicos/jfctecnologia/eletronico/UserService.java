package com.eletronicos.jfctecnologia.eletronico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public DadosCadastroUser saveUser(DadosCadastroUser dadosCadastroUser) {

        String encodedString = passwordEncoder.encode(dadosCadastroUser.getSenha());
        DadosCadastroUser cadastroUserWithEncodePassword = new DadosCadastroUser(dadosCadastroUser.getId(),
                dadosCadastroUser.getLogin(), encodedString);

        return userRepository.save(cadastroUserWithEncodePassword);

    }

    public Optional<DadosCadastroUser> findByUsername(String username) {
        return userRepository.findByLogin(username);
    }

}
