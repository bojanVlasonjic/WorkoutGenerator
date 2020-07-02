package rbs.wg.WorkoutGenerator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rbs.wg.WorkoutGenerator.dto.ExerciseDto;
import rbs.wg.WorkoutGenerator.exception.ApiBadRequestException;
import rbs.wg.WorkoutGenerator.exception.ApiNotFoundException;
import rbs.wg.WorkoutGenerator.model.Exercise;
import rbs.wg.WorkoutGenerator.repository.ExerciseRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExerciseService {

    @Autowired
    ExerciseRepository exerciseRepository;


    public List<ExerciseDto> getAllExercises() {

        return exerciseRepository
                .findAll()
                .stream()
                .map(ex -> new ExerciseDto(ex))
                .collect(Collectors.toList());
    }

    public ExerciseDto getExerciseById(Long id) {

        return exerciseRepository
                .findById(id)
                .map(ExerciseDto::new)
                .orElseThrow(() -> new ApiNotFoundException("Exercise not found"));

    }

    public ExerciseDto createNewExercise(ExerciseDto newExercise) {

         Exercise ex = new Exercise(newExercise);
         ex = exerciseRepository.save(ex);
         newExercise.setId(ex.getId());

         return newExercise;
    }


    public ExerciseDto updateExercise(ExerciseDto exerciseDto) {

        if(exerciseDto.getId() == null) {
            throw new ApiBadRequestException("The exercise id wasn't specified");
        }

        Exercise exercise = exerciseRepository
                .findById(exerciseDto.getId())
                .orElseThrow(() -> new ApiNotFoundException("Failed to find exercise with id " + exerciseDto.getId()));

        exercise.updateExercise(exerciseDto);
        exerciseRepository.save(exercise);
        return exerciseDto;
    }

    public boolean deleteExercise(Long exerciseId) {

        Exercise exercise = exerciseRepository
                .findById(exerciseId)
                .orElseThrow(() -> new ApiNotFoundException("Exercise not found"));

        exerciseRepository.delete(exercise);
        return true;
    }


}
