package com.example.a38096.fitnessproject.presenters;

import android.text.TextUtils;
import android.util.Base64;

import com.example.a38096.fitnessproject.model.RegisterDataSource;
import com.example.a38096.fitnessproject.model.UserDataSource;
import com.example.a38096.fitnessproject.utils.rx.RxErrorAction;
import com.example.a38096.fitnessproject.utils.rx.RxRetryWithDelay;
import com.example.a38096.fitnessproject.views.RegisterView;

import java.nio.charset.StandardCharsets;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Serhii Boiko on 06.05.2018.
 */
public class RegisterPresenter extends BasePresenter<RegisterView> {

	private final RegisterDataSource registerDataSource;

	private final UserDataSource mDataSource;

	public RegisterPresenter(RegisterDataSource registerDataSource, UserDataSource mDataSource) {
		this.registerDataSource = registerDataSource;
		this.mDataSource = mDataSource;
	}

	public void onRegisterClicked(String firstName, String lastName, String email,
								  String password, String passwordRepeat, String gender) {
		if (isNotValidRegisterData(firstName, lastName, email, password, passwordRepeat)) {
			return;
		}
		register(firstName, lastName, email, password, gender);
//        view.onSuccessLogin();
	}

	private boolean isNotValidRegisterData(String firstName, String lastName, String email,
										   String password, String passwordRepeat) {
		if (TextUtils.isEmpty(firstName)) {
			view.showEmptyFirstNameError();
			return true;
		}
		if (TextUtils.isEmpty(lastName)) {
			view.showEmptySecondNameError();
			return true;
		}
		if (TextUtils.isEmpty(email)) {
			view.showEmptyEmailError();
			return true;
		}
		if (TextUtils.isEmpty(password)) {
			view.showEmptyPasswordError();
			return true;
		}
		if (TextUtils.isEmpty(passwordRepeat)) {
			view.showEmptyPasswordRepeatError();
			return true;
		}
		if (!password.equals(passwordRepeat)) {
			view.showWrongPasswordRepeatError();
			return true;
		}
		return false;
	}

	private void register(String firstName, String lastName, String email, String password, String gender) {
		String source = email + ":" + password;
		String base64 = null;
		base64 = "Basic " + Base64.encodeToString(source.trim().getBytes(StandardCharsets.UTF_8), Base64.NO_WRAP);

		String finalBase64 = base64;
		addDisposable(registerDataSource.registerUser(firstName, lastName, email, password, gender)
										.retryWhen(new RxRetryWithDelay())
										.subscribeOn(Schedulers.io())
										.observeOn(AndroidSchedulers.mainThread())
										.subscribe(user -> {
											mDataSource.setAuthorized();
											mDataSource.setToken(user.getUuid());
											mDataSource.setFirstName(user.getFirstName());
											mDataSource.setSecondName(user.getLastName());
											mDataSource.setGender(user.getGender());
											mDataSource.setBase64Data(finalBase64);
											view.onSuccessRegistration();
										}, new RxErrorAction(view))
		);
	}
}
