package apps.scvh.com.thegamesdbclient.backend.gamesdbapi.keys;

import apps.scvh.com.thegamesdbclient.frontend.dialogs.ApiKeyDialogManager;

public class ApiKeyChecker {

    private ApiKeyDialogManager dialogManager;
    private ApiKeyManager keyManager;

    public ApiKeyChecker(ApiKeyDialogManager dialogManager, ApiKeyManager keyManager) {
        this.dialogManager = dialogManager;
        this.keyManager = keyManager;
    }

    public void loadDialogIfKeyIsNull() {
        keyManager.getApiKey().subscribe(key -> {
            if (key.length() == 0) {
                dialogManager.showDialog();
            }
        });
    }
}
