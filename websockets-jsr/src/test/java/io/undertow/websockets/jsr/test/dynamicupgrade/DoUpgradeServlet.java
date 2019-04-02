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

package io.undertow.websockets.jsr.test.dynamicupgrade;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.ContainerProvider;
import javax.websocket.Decoder;
import javax.websocket.Encoder;
import javax.websocket.Extension;
import javax.websocket.server.ServerEndpointConfig;

import io.undertow.websockets.jsr.ServerWebSocketContainer;

/**
 * @author Stuart Douglas
 */
public class DoUpgradeServlet extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        ((ServerWebSocketContainer)ContainerProvider.getWebSocketContainer()).doUpgrade(req, resp, new ServerEndpointConfig() {
            @Override
            public Class<?> getEndpointClass() {
                if(req.getParameter("annotated") != null) {
                    return EchoEndpoint.class;
                } else {
                    return EchoProgramaticEndpoint.class;
                }
            }

            @Override
            public String getPath() {
                return req.getPathInfo();
            }

            @Override
            public List<String> getSubprotocols() {
                return Collections.emptyList();
            }

            @Override
            public List<Extension> getExtensions() {
                return Collections.emptyList();
            }

            @Override
            public Configurator getConfigurator() {
                return null;
            }

            @Override
            public List<Class<? extends Encoder>> getEncoders() {
                return Collections.emptyList();
            }

            @Override
            public List<Class<? extends Decoder>> getDecoders() {
                return Collections.emptyList();
            }

            @Override
            public Map<String, Object> getUserProperties() {
                return Collections.emptyMap();
            }
        }, Collections.singletonMap("foo", req.getPathInfo()));
    }
}
