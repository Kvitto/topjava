package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    Meal getMealByIdAndUserId(int id, int userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Meal m WHERE m.id=?1 and m.user.id=?2")
    int delete(int id, int userId);

    List<Meal> findAllByUserIdOrderByDateTimeDesc(int userId);

    @Modifying
    @Query("FROM Meal m WHERE m.user.id=?3 AND m.dateTime>=?1 AND m.dateTime<?2 ORDER BY m.dateTime DESC")
    List<Meal> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId);
}