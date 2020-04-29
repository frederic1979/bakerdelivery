package co.simplon.bakerdelivery.repository;

import co.simplon.bakerdelivery.dto.MatrixDto;
import co.simplon.bakerdelivery.model.Matrix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MatrixRepository extends JpaRepository<Matrix, Long> {

List<Matrix> findMatrixByRestaurantId(Long restaurantId);

Matrix findMatrixByRestaurantIdAndEndDateAndDay(Long restaurantId, LocalDate date, Long day);

Matrix findMatrixByRestaurantIdAndEndDate(Long restaurantId, LocalDate date);

/*@Query("select matrix from Matrix matrix join matrix.restaurant restaurant where restaurant.id = :restaurantId and matrix.endDate = null and matrix.startDate>= :begin and matrix.startDate<= :finish")
Matrix findMatrixByRestaurantIdAndEndDateNullAndStartDateBefore(Long restaurantId, LocalDate begin);*/

@Query("select matrix from Matrix matrix join matrix.restaurant restaurant where restaurant.id = :restaurantId and matrix.endDate = null and matrix.startDate>= :begin and matrix.startDate<= :finish")
Matrix findMatrixByRestaurantIdAndEndDateNullAndStartDateBetweenBeginAndFinish(Long restaurantId, LocalDate begin, LocalDate finish);


@Query("select matrix from Matrix matrix where matrix.endDate = null and matrix.startDate>= :begin and matrix.startDate<= :finish")
List<Matrix> findMatrixByEndDateNullAndStartDateBetweenBeginAndFinish(LocalDate begin, LocalDate finish);

/*
@Query("select matrix from Matrix matrix join matrix.restaurant restaurant where restaurant.id = :restaurantId and matrix.endDate = null and matrix.startDate<= :dateLink ORDER BY matrix.startDate DESC LIMIT 1", nativeQuery = true))
Matrix findMatrixClosestDateLink(Long restaurantId, LocalDate dateLink);
*/

}
