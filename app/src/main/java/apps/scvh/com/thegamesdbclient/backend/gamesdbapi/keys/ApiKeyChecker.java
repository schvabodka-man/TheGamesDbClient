package apps.scvh.com.thegamesdbclient.backend.gamesdbapi.keys;

import apps.scvh.com.thegamesdbclient.frontend.dialogs.ApiKeyDialogManager;

/**
 * This class just checks is api key set. If not the dialog window will be called
 */
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
