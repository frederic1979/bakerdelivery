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
    public List<MatrixDto> getMatrixListByRestaurantIdAndStartDateBefore(Long restaurantId, LocalDate date){

        return matrixMapper.toDto(matrixRepository.findFirstMatrixByRestaurantIdAndStartDateIsBeforeOrderByStartDateDesc(restaurantId,date));
    }



    @Override
    public MatrixDto getMatrixByRestaurantIdAndDayAndStartDateEqualsDate(Long restaurantId, Integer day, LocalDate date){
        Optional<Matrix> existingMatrix = matrixRepository.findMatrixByRestaurantIdAndDayAndStartDateEqualsDate(restaurantId,day,date);
        if (existingMatrix.isPresent())
        {return matrixMapper.toDto(existingMatrix.get());}
        else throw new MatrixNotFoundException();
    }


    @Override
    public MatrixDto getFirstMatrixByRestaurantIdAndDayAndStartDateIsBeforeOrderByStartDateDesc(Long restaurantId, Integer day, LocalDate date) {
        Optional<Matrix> existingMatrixList = matrixRepository.findFirstMatrixByRestaurantIdAndDayAndStartDateIsBeforeOrderByStartDateDesc(restaurantId,day, date);
        if (existingMatrixList.isPresent()) {
            return matrixMapper.toDto(existingMatrixList.get());
        } else throw new MatrixNotFoundException();
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


        matrixRepository.save(matrix);
         return matrixMapper.toDto(matrix);
    }

    @Override
    public MatrixDto getMatrixByRestaurantIdAndEndDate(Long restaurantId, LocalDate endDate) {
        Matrix matrix = matrixRepository.findMatrixByRestaurantIdAndEndDate(restaurantId, endDate);
        return matrixMapper.toDto(matrix);
    }




}
