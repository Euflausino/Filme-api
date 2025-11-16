package com.Euflausino.application.port.output.user;

import com.Euflausino.adapter.output.JpaUserEntity;
import com.Euflausino.application.domain.User;

public interface ISalvarUserOutput {
    User save(User user);
}
