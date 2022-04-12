package net.croz.nrichdemobackend.seach.repository;

import net.croz.nrich.search.api.repository.SearchExecutor;
import net.croz.nrich.search.api.repository.StringSearchExecutor;
import net.croz.nrichdemobackend.seach.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarSearchDemoRepository extends JpaRepository<Car, Long>, SearchExecutor<Car>, StringSearchExecutor<Car> {
}
