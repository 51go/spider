package de.shifen.financelive.http;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ms404
 */
@RestController
public class ApiController {
    @GetMapping("/_status")
    public Object status(){
        return "200 ok";
    }
}
