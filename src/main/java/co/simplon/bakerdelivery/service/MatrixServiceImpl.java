package co.simplon.bakerdelivery.service;

import co.simplon.bakerdelivery.dto.MatrixDto;
import co.simplon.bakerdelivery.exception.MatrixNotFoundException;
import co.simplon.bakerdelivery.mappers.MatrixMapper;
import co.simplon.bakerdelivery.model.Matrix;
import co.simplon.bakerdelivery.repository.MatrixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MatrixServiceImpl implements MatrixService {

    @Autowired
    MatrixRepository matrixRepository;

    @Autowired
    MatrixMapper matrixMapper;


    @Override
    public List<MatrixDto> getMatrix() {
        return matrixMapper.toDto(matrixRepository.findAll());
    }

    @Override
    public Optional<Matrix> getMatrixById(Long matrixId) {
        return matrixRepository.findById(matrixId);
    }


    @Override
    public List<MatrixDto> getMatrixListByRestaurantId(Long restaurantId){

        return matrixMapper.toDto(matrixRepository.findMatrixByRestaurantId(restaurantId));
    }


    @Override
    public MatrixDto updateMatrix(MatrixDto matrixDto, Long matrixId) throws MatrixNotFoundException {
        if (!matrixRepository.existsById(matrixId)) {
            throw new MatrixNotFoundException();
        } else {
            Matrix matrix = matrixMapper.toEntity(matrixDto);
            matrix.setId(matrixId); //si on ne met pas le set, on créé des news
            matrix = matrixRepository.save(matrix);

            return matrixMapper.toDto(matrix);
        }
    }


    @Transactional
    @Override
    public MatrixDto createMatrix( MatrixDto matrixDto){
      
        Matrix matrix = matrixMapper.toEntity(matrixDto);
        LocalDate todayDate = LocalDate.now();
        matrix.setEndDate(todayDate);
        matrixRepository.save(matrix);
         return matrixMapper.toDto(matrix);
    }

    @Override
    public MatrixDto getMatrixByRestaurantIdAndEndDate(Long restaurantId, LocalDate endDate) {
        Matrix matrix = matrixRepository.findMatrixByRestaurantIdAndEndDate(restaurantId, endDate);
        return matrixMapper.toDto(matrix);
    }

    @Override
    public MatrixDto getMatrixByRestaurantIdAndEndDateNullAndStartDateBetweenBeginAndFinish(Long restaurantId, LocalDate begin, LocalDate finish){
        Matrix matrix = matrixRepository.findMatrixByRestaurantIdAndEndDateNullAndStartDateBetweenBeginAndFinish(restaurantId, begin, finish);
        return matrixMapper.toDto(matrix);
    }

    @Override
    public List<MatrixDto> getMatrixByEndDateNullAndStartDateBetweenBeginAndFinish(LocalDate begin, LocalDate finish){
        List<Matrix> matrixList = matrixRepository.findMatrixByEndDateNullAndStartDateBetweenBeginAndFinish(begin, finish);
        return matrixMapper.toDto(matrixList);
    }


}
