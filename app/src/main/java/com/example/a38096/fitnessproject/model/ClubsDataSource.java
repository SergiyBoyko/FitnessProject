package com.example.a38096.fitnessproject.model;

import com.example.a38096.fitnessproject.model.entities.Club;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Serhii Boiko on 20.05.2019.
 */
public interface ClubsDataSource {
    Observable<List<Club>> fetchClubs();
}
