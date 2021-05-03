package com.fruit.mvvm.data;

import android.util.Log;

import com.fruit.mvvm.model.LoggedInUser;

import org.jetbrains.annotations.NotNull;

import cn.leancloud.AVException;
import cn.leancloud.AVUser;
import io.reactivex.disposables.Disposable;


/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */

public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) throws InterruptedException {

        AVUser avUser = new AVUser();
        final boolean[] exist = {false};

        Thread au = new Thread(new Runnable() {
            @Override
            public void run() {
                // Todo
                // Email Auth
                // 3rd Party Auth
                avUser.logIn(username, password).subscribe(new io.reactivex.Observer<AVUser>() {
                    public void onSubscribe(@NotNull Disposable disposable) {
                    }

                    public void onNext(@NotNull AVUser user) {
                        // 登录成功
                        Log.d("用户名", AVUser.getCurrentUser().getUsername());
                        exist[0] = true;
                    }

                    public void onError(@NotNull Throwable throwable) {
                        // 登录失败（可能是密码错误）

                        Log.getStackTraceString(throwable);
                        Log.d("错误", String.valueOf(throwable.hashCode()));

                    }

                    @Override
                    public void onComplete() {
                    }
                });
            }
        });

        au.start();
        //wait the au to die.
        au.join();

        try {
            if (exist[0]) {
                Log.d("displayName", AVUser.getCurrentUser().getString("displayName"));
                LoggedInUser User =
                        new LoggedInUser(username, AVUser.getCurrentUser().getString("displayName"));
                return new Result.Success<>(User);
            } else {
                return new Result.Error(new AVException(211, "没有此用户!"));
            }
        } catch (Exception e) {
            return new Result.Error(e);
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}