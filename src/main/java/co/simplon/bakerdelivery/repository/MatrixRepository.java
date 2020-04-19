package co.simplon.bakerdelivery.repository;

import co.simplon.bakerdelivery.dto.MatrixDto;
import co.simplon.bakerdelivery.model.Matrix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MatrixRepository extends JpaRepository<Matrix, Long> {

List<Matrix> findMatrixByRestaurantId(Long restaurantId);

Matrix findMatrixByRestaurantIdAndEndDate(Long restaurantId, LocalDate date);


}
