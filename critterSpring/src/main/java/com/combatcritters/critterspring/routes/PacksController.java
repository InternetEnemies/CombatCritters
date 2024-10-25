package com.combatcritters.critterspring.routes;

import com.combatcritters.critterspring.payloads.CardPayload;
import com.combatcritters.critterspring.payloads.ItemStackPayload;
import com.combatcritters.critterspring.payloads.packs.PackResultPayload;
import com.combatcritters.critterspring.payloads.packs.PackPayload;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PacksController {
    
    @GetMapping("/packs")
    public List<PackPayload> getPacks() {
        throw new RuntimeException("not implemented");//todo
    }
    
    @GetMapping("/packs/{packid}")
    public PackPayload getPack(@PathVariable int packid) {
        throw new RuntimeException("not implemented");//todo
    }
    
    @GetMapping("/users/{userid}/packs")
    public ItemStackPayload<PackPayload> getPacks(@PathVariable int userid) {
        throw new RuntimeException("not implemented");//todo
    }
    
    @GetMapping("/packs/{packid}/contents")
    public List<CardPayload<?>> getPackContents(@PathVariable int packid) {
        throw new RuntimeException("not implemented");//todo
    }
    
    @PostMapping("/users/{userid}/packs/{packid}")
    public PackResultPayload openPack(@PathVariable int userid, @PathVariable int packid) {
        throw new RuntimeException("not implemented");//todo
    }
}
