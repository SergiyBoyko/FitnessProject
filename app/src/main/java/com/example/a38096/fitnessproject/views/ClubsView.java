package com.example.a38096.fitnessproject.views;

import com.example.a38096.fitnessproject.model.entities.Club;

import java.util.List;

/**
 * Created by Serhii Boiko on 20.05.2019.
 */
public interface ClubsView extends BaseView {
    void showClubs(List<Club> clubs);
}
