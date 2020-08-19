/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2014 Red Hat, Inc., and individual contributors
 * as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package io.undertow.util;

/**
 * Utility class for dealing with certificates
 *
 * @author Stuart Douglas
 */
public class Certificates {
    public static final java.lang.String BEGIN_CERT = "-----BEGIN CERTIFICATE-----";

    public static final java.lang.String END_CERT = "-----END CERTIFICATE-----";

    public static String toPem(final java.security.cert.X509Certificate certificate) throws java.security.cert.CertificateEncodingException {
        final StringBuilder builder = new StringBuilder();
        builder.append(BEGIN_CERT);
        builder.append('\n');
        builder.append(FlexBase64.encodeString(certificate.getEncoded(), true));
        builder.append('\n');
        builder.append(END_CERT);
        return builder.toString();
    }
/*
    @Deprecated
    public static String toPem(final javax.security.cert.X509Certificate certificate) throws javax.security.cert.CertificateEncodingException {
        final StringBuilder builder = new StringBuilder();
        builder.append(BEGIN_CERT);
        builder.append('\n');
        builder.append(FlexBase64.encodeString(certificate.getEncoded(), true));
        builder.append('\n');
        builder.append(END_CERT);
        return builder.toString();
    }
*/
    private Certificates() {

    }
}
