package com.example.a38096.fitnessproject.model.remote;

import com.example.a38096.fitnessproject.model.ClubsDataSource;
import com.example.a38096.fitnessproject.model.entities.Club;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Serhii Boiko on 20.05.2019.
 */
public class ClubsRemoteDataSource implements ClubsDataSource {
    @Override
    public Observable<List<Club>> fetchClubs() {
        return Observable.create(emitter -> {
            List<Club> clubs = new ArrayList<>();
            Club club = new Club();
            club.setUuid("x");
            club.setName("Test1");
            club.setLatitude(50.447116);
            club.setLongitude(30.454222);
            club.setFavorite(false);
            clubs.add(club);
            Club club2 = new Club();
            club2.setUuid("z");
            club2.setName("Test2");
            club2.setLatitude(50.446216);
            club2.setLongitude(30.454232);
            club2.setFavorite(true);
            clubs.add(club2);
            Club club3 = new Club();
            club3.setUuid("y");
            club3.setName("Test3");
            club3.setLatitude(50.449216);
            club3.setLongitude(30.444232);
            club3.setFavorite(false);
            clubs.add(club3);
            emitter.onNext(clubs);
            emitter.onComplete();
        });
    }
}
