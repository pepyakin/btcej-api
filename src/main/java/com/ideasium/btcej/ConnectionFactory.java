/*
 * ideasium (c) 2012.
 *
 * This software is provided 'as-is', without any express or implied
 * warranty.  In no event will the authors be held liable for any damages
 * arising from the use of this software.
 *
 * Permission is granted to anyone to use this software for any purpose,
 * including commercial applications, and to alter it and redistribute it
 * freely, subject to the following restrictions:
 *
 * 1. The origin of this software must not be misrepresented; you must not
 *    claim that you wrote the original software. If you use this software
 *    in a product, an acknowledgment in the product documentation would be
 *    appreciated but is not required.
 *
 * 2. Altered source versions must be plainly marked as such, and must not be
 *    misrepresented as being the original software.
 *
 * 3. This notice may not be removed or altered from any source distribution.
 */

package com.ideasium.btcej;

import org.jetbrains.annotations.NotNull;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author pepyakin
 */
class ConnectionFactory {

    private URL url;

    private NonceHelper nonceHelper;
    private BtceAuth signer;

    ConnectionFactory(
            @NotNull String rootUrl,
            @NotNull NonceHelper nonceHelper,
            @NotNull BtceAuth signer) {
        try {
            this.url = new URL(rootUrl);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException();
        }

        if (!"https".equalsIgnoreCase(url.getProtocol())) {
            throw new IllegalArgumentException();
        }

        this.nonceHelper = nonceHelper;
        this.signer = signer;
    }

    public HttpsURLConnection createConnection(
            @NotNull String method,
            @NotNull RequestParams params) throws IOException {
        params.setMethod(method);
        params.setNonce(nonceHelper.getNonce());

        String postQuery = params.buildQuery();
        String signChecksum = signer.signMessage(postQuery);

        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

        connection.setDoOutput(true);
        connection.setRequestProperty("Key", signer.getKey());
        connection.setRequestProperty("Sign", signChecksum);

        writeQueryInPost(postQuery, connection.getOutputStream());

        return connection;
    }

    private void writeQueryInPost(String postQuery, OutputStream postOutputStream) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(postOutputStream);
        writer.write(postQuery);
        writer.close();
    }
}
