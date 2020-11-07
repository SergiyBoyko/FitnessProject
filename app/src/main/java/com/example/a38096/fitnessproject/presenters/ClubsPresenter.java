package com.example.a38096.fitnessproject.presenters;

import com.example.a38096.fitnessproject.model.ClubsDataSource;
import com.example.a38096.fitnessproject.model.UserDataSource;
import com.example.a38096.fitnessproject.model.entities.Club;
import com.example.a38096.fitnessproject.utils.rx.AsyncTransformer;
import com.example.a38096.fitnessproject.utils.rx.RxErrorAction;
import com.example.a38096.fitnessproject.views.ClubsView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Serhii Boiko on 20.05.2019.
 */
public class ClubsPresenter extends BasePresenter<ClubsView> {
	private final ClubsDataSource clubsDataSource;
	private final UserDataSource userDataSource;

	public ClubsPresenter(ClubsDataSource clubsDataSource, UserDataSource userDataSource) {
		this.clubsDataSource = clubsDataSource;
		this.userDataSource = userDataSource;
	}

	public void fetchClubs() {
		addDisposable(clubsDataSource.fetchClubs(userDataSource.getToken(), userDataSource.getBase64Data())
									 .compose(new AsyncTransformer<>())
									 .subscribe(view::showClubs, new RxErrorAction(view)));
	}

	public void addToFavorites(Club club) {
		addDisposable(clubsDataSource.addToFavorites(userDataSource.getToken(), club.getUuid(), userDataSource.getBase64Data())
									 .subscribeOn(Schedulers.io())
									 .observeOn(AndroidSchedulers.mainThread())
									 .subscribe(
											 () -> {
												 club.setFavorite(!club.isFavorite());
												 view.applyChanges(club);
											 }, new RxErrorAction(view)
									 ));
	}

	public void removeFromFavorites(Club club) {
		addDisposable(clubsDataSource.removeFromFavorites(userDataSource.getToken(), club.getUuid(), userDataSource.getBase64Data())
									 .subscribeOn(Schedulers.io())
									 .observeOn(AndroidSchedulers.mainThread())
									 .subscribe(
											 () -> {
												 club.setFavorite(!club.isFavorite());
												 view.applyChanges(club);
											 }, new RxErrorAction(view)
									 ));
	}
}
