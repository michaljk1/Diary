package my.sinewave.diary.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RatingRepositoryDao extends JpaRepository<RatingEntity, LocalDate> {


    List<RatingEntity> findAllByOrderByDayAsc();


}
