package com.example.Ruokavalio;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.Ruokavalio.domain.Ravinto;
import com.example.Ruokavalio.domain.RavintoRepository;
import com.example.Ruokavalio.domain.UserRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class RavintoRepositoryTest {

	@Autowired
	private RavintoRepository repository;
	private UserRepository urepository;

	@Test
	public void findByRavintoShouldReturnKalorit() {
		List<Ravinto> ravinto = repository.findByRavintoNimi("Riisi");

		assertThat(ravinto).hasSize(1);
		assertThat(ravinto.get(0).getKalorit()).isEqualTo(660.0);
	}
	
	@Test
	public void createNewRavinto() {
		Ravinto ravinto = new Ravinto("testi", 0 , 0, 0, 0);
		repository.save(ravinto);
		assertThat(ravinto.getId()).isNotNull();
	}
}
