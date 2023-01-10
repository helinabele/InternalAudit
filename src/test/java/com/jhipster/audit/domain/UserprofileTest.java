package com.jhipster.audit.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.jhipster.audit.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class UserprofileTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Userprofile.class);
        Userprofile userprofile1 = new Userprofile();
        userprofile1.setId("id1");
        Userprofile userprofile2 = new Userprofile();
        userprofile2.setId(userprofile1.getId());
        assertThat(userprofile1).isEqualTo(userprofile2);
        userprofile2.setId("id2");
        assertThat(userprofile1).isNotEqualTo(userprofile2);
        userprofile1.setId(null);
        assertThat(userprofile1).isNotEqualTo(userprofile2);
    }
}
