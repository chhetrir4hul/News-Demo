package com.rahul.newsdemo.extensions

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import java.time.Instant

class DateTest {
    @Test
    fun formatNewsTime() {
        assertThat("2022-08-05T02:39:33Z".formatNewsTime()).isEqualTo("12:39 PM 05 Aug 2022")
    }

    @Test
    fun formatNewsTime_invalid_format() {
        assertThat("2022".formatNewsTime()).isEqualTo("")
    }

    @Test
    fun toNewsListFormat() {
        assertThat(
            Instant.ofEpochSecond(1659672447).toNewsListFormat()
        ).isEqualTo("02:07 PM 05 Aug 2022")
    }
}