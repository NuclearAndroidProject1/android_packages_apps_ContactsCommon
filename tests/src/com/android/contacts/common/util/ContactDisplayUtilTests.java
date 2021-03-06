/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.android.contacts.common.util;

import static android.provider.ContactsContract.CommonDataKinds.Phone;

import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import com.android.contacts.common.R;

/**
 * Unit tests for (@link ContactDisplayUtils}
 */
@SmallTest
public class ContactDisplayUtilTests extends AndroidTestCase {

    public void testIsCustomPhoneTypeReturnsTrue() {
        assertTrue(ContactDisplayUtils.isCustomPhoneType(Phone.TYPE_CUSTOM));
        assertTrue(ContactDisplayUtils.isCustomPhoneType(Phone.TYPE_ASSISTANT));
    }

    public void testIsCustomPhoneTypeReturnsFalse() {
        assertFalse(ContactDisplayUtils.isCustomPhoneType(Phone.TYPE_HOME));
        assertFalse(ContactDisplayUtils.isCustomPhoneType(Phone.TYPE_FAX_WORK));
        assertFalse(ContactDisplayUtils.isCustomPhoneType(Phone.TYPE_MOBILE));
        assertFalse(ContactDisplayUtils.isCustomPhoneType(Phone.TYPE_OTHER));
    }

    public void testGetLabelForCallOrSmsReturnsCustomLabel() {
        final CharSequence smsResult = ContactDisplayUtils.getLabelForCallOrSms(Phone.TYPE_CUSTOM,
                "expected sms label", ContactDisplayUtils.INTERACTION_SMS, getContext());
        assertEquals("expected sms label", smsResult);

        final CharSequence callResult = ContactDisplayUtils.getLabelForCallOrSms(Phone.TYPE_CUSTOM,
                "expected call label", ContactDisplayUtils.INTERACTION_CALL, getContext());
        assertEquals("expected call label", callResult);
    }

    public void testGetLabelForCallOrSmsReturnsCallLabels() {
        CharSequence result = ContactDisplayUtils.getLabelForCallOrSms(Phone.TYPE_HOME, "",
                ContactDisplayUtils.INTERACTION_CALL, getContext());
        CharSequence expected = getContext().getResources().getText(R.string.call_home);
        assertEquals(expected, result);

        result = ContactDisplayUtils.getLabelForCallOrSms(Phone.TYPE_MOBILE, "",
                ContactDisplayUtils.INTERACTION_CALL, getContext());
        expected = getContext().getResources().getText(R.string.call_mobile);
        assertEquals(expected, result);
    }

    public void testGetLabelForCallOrSmsReturnsSmsLabels() {
        CharSequence result = ContactDisplayUtils.getLabelForCallOrSms(Phone.TYPE_HOME, "",
                ContactDisplayUtils.INTERACTION_SMS, getContext());
        CharSequence expected = getContext().getResources().getText(R.string.sms_home);
        assertEquals(expected, result);

        result = ContactDisplayUtils.getLabelForCallOrSms(Phone.TYPE_MOBILE, "",
                ContactDisplayUtils.INTERACTION_SMS, getContext());
        expected = getContext().getResources().getText(R.string.sms_mobile);
        assertEquals(expected, result);
    }

    public void testGetPhoneLabelResourceIdReturnsOther() {
        assertEquals(R.string.call_other, ContactDisplayUtils.getPhoneLabelResourceId(null));
    }

    public void testGetPhoneLabelResourceIdReturnsMatchHome() {
        assertEquals(R.string.call_home, ContactDisplayUtils.getPhoneLabelResourceId(
                Phone.TYPE_HOME));
    }

    public void testGetSmsLabelResourceIdReturnsOther() {
        assertEquals(R.string.sms_other, ContactDisplayUtils.getSmsLabelResourceId(null));
    }

    public void testGetSmsLabelResourceIdReturnsMatchHome() {
        assertEquals(R.string.sms_home, ContactDisplayUtils.getSmsLabelResourceId(Phone.TYPE_HOME));
    }

}
