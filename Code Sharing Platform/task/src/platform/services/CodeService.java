package platform.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.data.CodeRepository;
import platform.entities.Code;
import platform.exceptions.SnippetNotFoundException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CodeService {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    CodeRepository codeRepository;

    @Autowired
    public CodeService(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    public Code findCodeByUUID(String id) {
        Code code = codeRepository.findById(id)
                .orElseThrow(SnippetNotFoundException::new);
        if (!code.isTimeRestricted() || !code.isViewRestricted())
            updateViewsAndTime(code);

        return code;
    }

    public void saveCode(Code code) {
        code.setDate(LocalDateTime.now().format(formatter));
        code.setTimeRestricted(code.getTime() == 0);
        code.setViewRestricted(code.getViews() == 0);
        codeRepository.save(code);
    }

    public List<Code> findTenLatest() {
        List<Code> list = codeRepository.findAll();
        Collections.reverse(list);
        return list.stream().filter(
                i -> i.isTimeRestricted() && i.isViewRestricted()
        ).limit(10).collect(Collectors.toList());

    }

    public void updateViewsAndTime(Code code) {
        if (!code.isViewRestricted()) {
            if (code.getViews() == 0) {
                codeRepository.deleteById(code.getId());
                throw new SnippetNotFoundException();
            }
            code.setViews(code.getViews() - 1);
            codeRepository.save(code);
        }
        if (!code.isTimeRestricted()) {
            LocalDateTime time = LocalDateTime.parse(code.getDate(), formatter);
          long s = Duration.between(LocalDateTime.now(), time.plusSeconds(code.getTime())).getSeconds();
            if (s <= 0) {
                codeRepository.deleteById(code.getId());
                throw new SnippetNotFoundException();
            }
            code.setTime(s);
        }

    }



}
