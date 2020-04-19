package co.simplon.bakerdelivery.controller;

import co.simplon.bakerdelivery.dto.MatrixDto;
import co.simplon.bakerdelivery.exception.MatrixNotFoundException;
import co.simplon.bakerdelivery.mappers.MatrixMapper;
import co.simplon.bakerdelivery.model.Matrix;
import co.simplon.bakerdelivery.service.MatrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/bakerdelivery/matrix")
@CrossOrigin("*")
public class MatrixController {

    @Autowired
    MatrixService matrixService;

    @Autowired
    MatrixMapper matrixMapper;


    @GetMapping()
    public List<MatrixDto> getMatrix() {
        return matrixService.getMatrix();

    }

    @GetMapping("/{matrixId}")
    public ResponseEntity<MatrixDto> getMatrixById(@PathVariable Long matrixId) {
        Optional<Matrix> matrix = matrixService.getMatrixById(matrixId);
        if (matrix.isPresent()) {
            return ResponseEntity.ok(matrixMapper.toDto(matrix.get()));
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("restaurant/{restaurantId}")
    public List<MatrixDto> getMatrixListByRestaurantId(@PathVariable Long restaurantId) {

        return matrixService.getMatrixListByRestaurantId(restaurantId);

    }

    @GetMapping("/restaurants/{restaurantId}")
    public MatrixDto getMatrixByRestaurantIdAndEndDate(@PathVariable Long restaurantId,
                                                       @RequestParam(value = "endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        System.out.println("end date :" + endDate);
        return matrixService.getMatrixByRestaurantIdAndEndDate(restaurantId, endDate);
    }



    @PutMapping("/{matrixId}")
    public ResponseEntity<MatrixDto> updateCommand(@RequestBody MatrixDto matrixDto, @PathVariable Long matrixId) {

        try {

            return ResponseEntity.ok(matrixService.updateMatrix(matrixDto, matrixId));
        } catch (MatrixNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping()
    public MatrixDto createMatrix(@RequestBody MatrixDto matrixDto){
        return matrixService.createMatrix(matrixDto);
    }

}
