package com.eletronicos.jfctecnologia.eletronico;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<DadosCadastroUser, Long> {

    Optional<DadosCadastroUser> findByLogin(String login);

}