package org.java.lessons.springLaMiaPizzeriaCrud.repository;

import org.java.lessons.springLaMiaPizzeriaCrud.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<Offer, Integer> {
}
