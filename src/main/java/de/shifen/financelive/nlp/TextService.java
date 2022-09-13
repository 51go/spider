package de.shifen.financelive.nlp;

import lombok.Data;
import org.springframework.stereotype.Service;

/**
 * @author ms404
 */
@Service
public class TextService {
    @Data
    public static class Model {
        String title;
        String content;
    }
    public Model guess(String full){
        if(full.contains("】")){
            Model model = new Model();
            String title1 = full.substring(0, full.indexOf("】")).replace("【", "");
            model.setTitle(title1);
            String content1 = full.substring(full.indexOf("】") + 1);
            model.setContent(content1);
            return model;
        }
        Model model = new Model();
        model.setTitle(full);
        model.setContent(full);
        return  model;
    }
}
