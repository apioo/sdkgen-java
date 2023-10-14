/*
 * SDKgen is a powerful code generator to automatically build client SDKs for your REST API.
 * For the current version and information visit <https://sdkgen.app>
 *
 * Copyright (c) Christoph Kappestein <christoph.kappestein@gmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package app.sdkgen.client.TokenStore;

import app.sdkgen.client.AccessToken;
import app.sdkgen.client.TokenStoreInterface;

public class MemoryTokenStore implements TokenStoreInterface {
    private AccessToken token;

    @Override
    public AccessToken get() {
        return this.token;
    }

    @Override
    public void persist(AccessToken token) {
        this.token = token;
    }

    @Override
    public void remove() {
        this.token = null;
    }
}
