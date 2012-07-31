/*
 * Copyright 2010-2012 Ning, Inc.
 *
 * Ning licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.ning.billing.jaxrs;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.ning.billing.KillbillTestSuite;
import com.ning.billing.jaxrs.json.AuditLogJson;
import com.ning.billing.util.clock.Clock;
import com.ning.billing.util.clock.ClockMock;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;

public abstract class JaxrsTestSuite extends KillbillTestSuite {

    private final Clock clock = new ClockMock();

    protected static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.registerModule(new JodaModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    protected List<AuditLogJson> createAuditLogsJson() {
        final List<AuditLogJson> auditLogs = new ArrayList<AuditLogJson>();
        for (int i = 0; i < 20; i++) {
            auditLogs.add(new AuditLogJson(UUID.randomUUID().toString(), clock.getUTCNow(), UUID.randomUUID().toString(),
                                           UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString()));
        }

        return auditLogs;
    }
}
