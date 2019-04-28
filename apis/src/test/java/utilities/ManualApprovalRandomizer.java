package utilities;

import dtos.ManualApprovalRequestDTO;
import io.github.benas.randombeans.randomizers.FakerBasedRandomizer;

public class ManualApprovalRandomizer extends FakerBasedRandomizer<ManualApprovalRequestDTO> {

    private static class SingletonHolder {
        public static final ManualApprovalRandomizer instance = new ManualApprovalRandomizer();
    }

    public static ManualApprovalRandomizer getInstance() {
        return SingletonHolder.instance;
    }

    @Override
    public ManualApprovalRequestDTO getRandomValue() {
        ManualApprovalRequestDTO request = new ManualApprovalRequestDTO();
        request.setAprovar(true);
        request.setMensagem(faker.lorem().fixedString(25));
        return request;
    }
}
