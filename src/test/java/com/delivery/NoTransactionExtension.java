package com.delivery;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

public class NoTransactionExtension implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext extensionContext) {
        var applicationContext = SpringExtension.getApplicationContext(extensionContext);
        cleanDatabase(applicationContext);
    }

    private static void cleanDatabase(ApplicationContext applicationContext) {
        DatabaseCleaner.clear(applicationContext);
    }
}
