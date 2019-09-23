package com.rtcab.cemds.web;

import com.haulmont.cuba.core.app.PersistenceManagerService;
import com.haulmont.cuba.gui.components.Label;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;
import com.haulmont.cuba.web.app.login.LoginScreen;

import javax.inject.Inject;


@UiController("login")
@UiDescriptor("dbms-aware-login-screen.xml")
public class DbmsAwareLoginScreen extends LoginScreen {

    @Inject
    protected PersistenceManagerService persistenceManagerService;

    @Override
    protected void initPoweredByLink() {
        super.initPoweredByLink();

        Label poweredByLink = (Label) getWindow().getComponent("poweredByLink");
        if (poweredByLink != null) {
            poweredByLink.setVisible(webConfig.getLoginDialogPoweredByLinkVisible());
            poweredByLink.setValue(getDbmsAwarePoweredByMessage());
        }
    }

    private String getDbmsAwarePoweredByMessage() {
        switch (persistenceManagerService.getDbmsType()) {
            case "mysql": return mainMessage("poweredByCubaAndMysql");
            case "postgres": return mainMessage("poweredByCubaAndPostgres");
            default: return mainMessage("poweredByCuba");
        }
    }

    private String mainMessage(String mainMessageKey) {
        return messages.getMainMessage(mainMessageKey, app.getLocale());
    }
}