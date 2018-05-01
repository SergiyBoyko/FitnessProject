package com.example.a38096.fitnessproject.utils.rx;

import android.content.Context;
import android.widget.Toast;

import com.example.a38096.fitnessproject.R;

import rx.functions.Action1;

/**
 * Created by Serhii Boiko on 01.05.2018.
 */
public class RxErrorAction implements Action1<Throwable> {
    private final Context context;

    public RxErrorAction(Context context) {
        this.context = context;
    }

    @Override
    public void call(Throwable throwable) {
        throwable.printStackTrace();

        Toast.makeText(context, context.getString(R.string.request_execution_has_failed),
                Toast.LENGTH_SHORT).show();
    }
}
