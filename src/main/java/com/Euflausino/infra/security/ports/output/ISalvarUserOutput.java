package com.Euflausino.infra.security.ports.output;

import com.Euflausino.infra.security.entity.User;

public interface ISalvarUserOutput {
    User save(User user);
}
