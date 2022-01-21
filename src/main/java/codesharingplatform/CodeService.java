package codesharingplatform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class CodeService {
    @Autowired
    CodeRepository codeRepository;

    public void save(Code code) {
        this.codeRepository.save(code);
    }

    public Code findById(UUID id) {
        if (this.codeRepository.existsById(id)) {
            this.updateViews(id);
            Code code = this.codeRepository.findById(id).get();
            if (!code.isTimeExpirable() && !code.isViewsExpirable()) { return code; }
            if (code.isViewsExpirable() && code.isTimeExpirable()) {
                if (code.getTime() > 0) { return code; }
                if (code.getViews() > 0) { return code; }
            }
            if (code.isTimeExpirable() && code.getTime() > 0) { return code; }
            if (code.isViewsExpirable() && code.getViews() >= 0) { return code; }
        }
        throw (new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<Code> findLatest10() {
        List<Code> listCode = this.codeRepository.findLatest10();
        for (Code code: listCode) {
            this.updateViews(code.getId());
        }
        return listCode;
    }

    public void updateViews(UUID uuid) {
        this.codeRepository.updateViews(uuid);
    }

}
