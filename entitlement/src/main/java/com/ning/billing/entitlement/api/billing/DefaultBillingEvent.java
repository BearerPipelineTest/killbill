/*
 * Copyright 2010-2011 Ning, Inc.
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

package com.ning.billing.entitlement.api.billing;

import java.math.BigDecimal;
import java.util.UUID;

import org.joda.time.DateTime;

import com.ning.billing.catalog.api.BillingPeriod;
import com.ning.billing.catalog.api.CatalogApiException;
import com.ning.billing.catalog.api.Currency;
import com.ning.billing.catalog.api.InternationalPrice;
import com.ning.billing.entitlement.api.user.SubscriptionTransition;

public class DefaultBillingEvent implements BillingEvent {
    final private int billCycleDay;
    final private UUID subscriptionId;
    final private DateTime effectiveDate;
    final private String planPhaseName;
    final private String planName;
    final private InternationalPrice fixedPrice;
    final private InternationalPrice recurringPrice;
    final private String description;
    final private BillingMode billingMode;
    final private BillingPeriod billingPeriod;
    
    public DefaultBillingEvent(SubscriptionTransition transition) {
        billCycleDay;
        subscriptionId = transition.getSubscriptionId();
        effectiveDate = transition.getEffectiveTransitionTime();
        planPhaseName = transition.getNextPhase().getName();
        planName = transition.getNextPlan().getName();
        fixedPrice = transition.getNextPhase().getFixedPrice();
        recurringPrice = transition.getNextPhase().getRecurringPrice();
        description = transition.getTransitionType().toString();
        billingMode=BillingMode.IN_ADVANCE;
        billingPeriod = transition.getNextPhase().getBillingPeriod();
        
    }
    
    @Override
    public int compareTo(BillingEvent e1) {
        if (getSubscriptionId().equals(e1.getSubscriptionId())) {
            return getEffectiveDate().compareTo(e1.getEffectiveDate());
        } else {
            return getSubscriptionId().compareTo(e1.getSubscriptionId());
        }
    }

    @Override
    public int getBillCycleDay() {
         return billCycleDay;
    }

    @Override
    public UUID getSubscriptionId() {
        return subscriptionId;
    }

    @Override
    public DateTime getEffectiveDate() {
        return effectiveDate;
    }

    @Override
    public String getPlanPhaseName() {
        return planPhaseName;
    }

    @Override
    public String getPlanName() {
        return planName;
    }

    @Override
    public BillingPeriod getBillingPeriod() {
        return billingPeriod;
    }

    @Override
    public BillingMode getBillingMode() {
        return billingMode;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public InternationalPrice getFixedPrice() {
        return fixedPrice;
    }

    @Override
    public InternationalPrice getRecurringPrice() {
        return recurringPrice;
    }

}
