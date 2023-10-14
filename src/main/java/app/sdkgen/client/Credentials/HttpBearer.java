/*
 * SDKgen is a powerful code generator to automatically build client SDKs for your REST API.
 * For the current version and information visit <https://sdkgen.app>
 *
 * Copyright (c) Christoph Kappestein <christoph.kappestein@gmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package app.sdkgen.client.Credentials;

import app.sdkgen.client.CredentialsInterface;

public class HttpBearer implements CredentialsInterface {
    private final String token;

    public HttpBearer(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
