package il.co.idocare.dependencyinjection.modules;

import android.accounts.AccountManager;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import il.co.idocare.authentication.MyAccountManager;
import il.co.idocare.authentication.LoginStateManager;
import il.co.idocare.dependencyinjection.ControllerScope;
import il.co.idocare.dependencyinjection.components.ControllerComponent;
import il.co.idocare.networking.ServerSyncController;
import il.co.idocare.nonstaticproxies.ContentResolverProxy;
import il.co.idocare.nonstaticproxies.TextUtilsProxy;
import il.co.idocare.utils.Logger;

@Module
public class ControllerModule {

    private Context mContext;

    public ControllerModule(Context context) {
        mContext = context;
    }

    @Provides
    @ControllerScope
    Context provideContext() {
        return mContext;
    }


    @Provides
    @ControllerScope
    LoginStateManager provideLoginStateManager(Context context, AccountManager accountManager,
                                               MyAccountManager myAccountManager) {
        return new LoginStateManager(context, accountManager, myAccountManager);
    }


    @Provides
    @ControllerScope
    MyAccountManager provideMyAccountManager(AccountManager accountManager, Logger logger,
                                             TextUtilsProxy textUtilsProxy) {
        return new MyAccountManager(accountManager, logger, textUtilsProxy);
    }

    @Provides
    @ControllerScope
    ServerSyncController provideServerSyncController(LoginStateManager loginStateManager,
                                                     MyAccountManager myAccountManager,
                                                     ContentResolverProxy contentResolverProxy) {
        return new ServerSyncController(loginStateManager, myAccountManager, contentResolverProxy);
    }

}
