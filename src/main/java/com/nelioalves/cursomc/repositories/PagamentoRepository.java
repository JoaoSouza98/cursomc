package com.nelioalves.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nelioalves.cursomc.domain.Pagamento;

//soh eh necessario definir repositories nas superclasses
@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

}
