package com.kramar.sample.repositories;

import com.kramar.sample.domain.Email;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mbart on 28.02.2016.
 */
public interface EmailRepository extends JpaRepository<Email, Long>{
}
