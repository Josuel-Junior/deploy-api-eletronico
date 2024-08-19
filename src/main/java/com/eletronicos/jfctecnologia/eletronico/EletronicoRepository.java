package com.eletronicos.jfctecnologia.eletronico;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EletronicoRepository extends JpaRepository<Eletronico, Long>{

	List<Eletronico> findAllByAtivoTrue();

}
