package com.example.appfit.tests.activities;

import java.time.LocalDate;
import java.util.List;

public class Activities {

  private Integer userId;
  private List<Integer> exercisesIds;
  private LocalDate date;

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer id) {
    this.userId = id;
  }

  public List<Integer> getExercisesIds() {
    return exercisesIds;
  }

  public void setExercisesIds(List<Integer> exercisesIds) {
    this.exercisesIds = exercisesIds;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }
}
