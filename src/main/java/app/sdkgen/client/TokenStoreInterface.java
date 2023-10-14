/*
 * SDKgen is a powerful code generator to automatically build client SDKs for your REST API.
 * For the current version and information visit <https://sdkgen.app>
 *
 * Copyright (c) Christoph Kappestein <christoph.kappestein@gmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package app.sdkgen.client;

import app.sdkgen.client.Exception.Authenticator.TokenPersistException;

public interface TokenStoreInterface {
    AccessToken get();

    void persist(AccessToken token) throws TokenPersistException;

    void remove();
}
