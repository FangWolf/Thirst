package com.fangwolf.library_base.widget;

import android.app.Activity;
import android.util.Log;

import com.orhanobut.logger.Logger;

import java.lang.ref.SoftReference;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.internal.schedulers.NewThreadScheduler;
import io.reactivex.internal.util.AppendOnlyLinkedArrayList;

/**
 * @Auther 獠牙血狼
 * @Date 2019/3/19
 * @Desc 定时器
 */
public class RxTimer {
    private Observable<Long> observable;
    private Disposable disposable;
    private TimerCall timerCall;
    private SoftReference<Activity> activitySoftReference;

    /**
     * 绑定activity
     * 当act销毁时，不会再回调
     *
     * @param activity
     */
    public void bindAct(Activity activity) {
        if (activitySoftReference != null)
            activitySoftReference.clear();
        activitySoftReference = new SoftReference<Activity>(activity);
    }

    /**
     * @param period    周期  TimeUnit.SECONDS  以秒为单位
     * @param hz        次数
     * @param timerCall 倒计时回调
     */
    public RxTimer(long period, final long hz, TimerCall timerCall) {
        observable = Observable.interval(period, TimeUnit.SECONDS)
                .take(hz)
                .filter(new AppendOnlyLinkedArrayList.NonThrowingPredicate<Long>() {
                    @Override
                    public boolean test(Long aLong) {
                        if (activitySoftReference == null) {
                            return true;
                        } else if ((activitySoftReference.get() != null && !activitySoftReference.get().isFinishing())) {
                            return true;
                        }
                        Logger.e("activity has destoryed");
                        return false;
                    }

                })
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(Long aLong) throws Exception {
                        Logger.i("observable", aLong + "");
                        return hz - aLong;
                    }
                })
                .subscribeOn(new NewThreadScheduler())
                .observeOn(AndroidSchedulers.mainThread());
        this.timerCall = timerCall;
    }

    public void start() {
        this.observable.subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(Long aLong) {
                timerCall.onTick(aLong);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                timerCall.onFinish();
                Logger.e("onComplete");
                if (!disposable.isDisposed())
                    disposable.dispose();
            }
        });
    }

    public void stop() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    public interface TimerCall {
        void onTick(long t);

        void onFinish();
    }

    public static void destoryTimer(RxTimer timer) {
        if (timer != null) {
            timer.stop();
        }
    }
}
