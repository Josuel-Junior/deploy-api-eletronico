package com.eletronicos.jfctecnologia.cadastroUsuario;

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

    public boolean saveUser(DadosCadastroUser dadosCadastroUser) {

        Optional<DadosCadastroUser> existingUser = userRepository.findByLogin(dadosCadastroUser.getLogin());

        if (existingUser.isPresent()) {
            return false;
        }

        String encodedString = passwordEncoder.encode(dadosCadastroUser.getSenha());
        DadosCadastroUser cadastroUserWithEncodePassword = new DadosCadastroUser(dadosCadastroUser.getId(),
                dadosCadastroUser.getLogin(), encodedString);
        userRepository.save(cadastroUserWithEncodePassword);

        return true;

    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public Optional<DadosCadastroUser> findByUsername(String username) {
        return userRepository.findByLogin(username);
    }

}
