package com.example.a38096.fitnessproject.presenters;

import com.example.a38096.fitnessproject.model.ClubsDataSource;
import com.example.a38096.fitnessproject.model.entities.Club;
import com.example.a38096.fitnessproject.utils.rx.AsyncTransformer;
import com.example.a38096.fitnessproject.utils.rx.RxErrorAction;
import com.example.a38096.fitnessproject.views.ClubsView;

/**
 * Created by Serhii Boiko on 20.05.2019.
 */
public class ClubsPresenter extends BasePresenter<ClubsView> {
    private final ClubsDataSource clubsDataSource;

    public ClubsPresenter(ClubsDataSource clubsDataSource) {
        this.clubsDataSource = clubsDataSource;
    }

    public void fetchClubs() {
        addDisposable(clubsDataSource.fetchClubs()
                .compose(new AsyncTransformer<>())
                .subscribe(view::showClubs, new RxErrorAction(view)));
    }

    public void addToFavorites(Club club) {
        addDisposable(clubsDataSource.addToFavorites(club)
                .compose(new AsyncTransformer<>())
                .subscribe(
                        voidResponse -> {
                            club.setFavorite(!club.isFavorite());
                            view.showChanged(club);
                        }, new RxErrorAction(view)
                ));
    }

    public void removeFromFavorites(Club club) {
        addDisposable(clubsDataSource.removeFromFavorites(club)
                .compose(new AsyncTransformer<>())
                .subscribe(
                        voidResponse -> {
                            club.setFavorite(!club.isFavorite());
                            view.showChanged(club);
                        }, new RxErrorAction(view)
                ));
    }
}
