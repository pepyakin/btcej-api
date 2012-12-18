package com.ideasium.btcej;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author pepyakin
 */
class NonceHelper {

    private AtomicLong nonce;

    public NonceHelper() {
        nonce = new AtomicLong();

        // TODO: Придумать механизм генерации nonce получше.
        nonce.set(System.currentTimeMillis() / 1000);
    }

    public long getNonce() {
        return nonce.incrementAndGet();
    }
}
