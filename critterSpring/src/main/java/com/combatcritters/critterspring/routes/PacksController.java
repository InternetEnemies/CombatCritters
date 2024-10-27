package com.combatcritters.critterspring.routes;

import com.combatcritters.critterspring.payloads.CardPayload;
import com.combatcritters.critterspring.payloads.ItemStackPayload;
import com.combatcritters.critterspring.payloads.packs.PackCreatorPayload;
import com.combatcritters.critterspring.payloads.packs.PackResultPayload;
import com.combatcritters.critterspring.payloads.packs.PackPayload;
import com.internetEnemies.combatCritters.Logic.IUserDataFactory;
import com.internetEnemies.combatCritters.Logic.inventory.cards.ICardRegistry;
import com.internetEnemies.combatCritters.Logic.inventory.packs.IPackCatalog;
import com.internetEnemies.combatCritters.Logic.inventory.packs.IPackInventoryManager;
import com.internetEnemies.combatCritters.Logic.users.IUserManager;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.Pack;
import com.internetEnemies.combatCritters.objects.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PacksController {
    
    private final IUserDataFactory userDataFactory;
    private final IPackCatalog packCatalog;
    private final IUserManager userManager;
    private final ICardRegistry cardRegistry;
    
    @Autowired
    public PacksController(IUserDataFactory userDataFactory, IPackCatalog packCatalog, IUserManager userManager, ICardRegistry cardRegistry) {
        this.userDataFactory = userDataFactory;
        this.packCatalog = packCatalog;
        this.userManager = userManager;
        this.cardRegistry = cardRegistry;
    }
    
    
    @GetMapping("/packs")
    public List<PackPayload> getPacks() {
        List<Pack> packs = packCatalog.getListOfPacks();
        return packs.stream().map(PackPayload::from).toList();
    }
    
    @GetMapping("/packs/{packid}")
    public PackPayload getPack(@PathVariable int packid) {
        Pack pack = packCatalog.getPack(packid);
        return PackPayload.from(pack);
    }
    
    @GetMapping("/users/{userid}/packs")
    public List<ItemStackPayload<PackPayload>> getUserPacks(@PathVariable int userid) {
        User user = this.userManager.getUserById(userid);
        IPackInventoryManager packInv = this.userDataFactory.getPackInventoryManger(user);
        List<ItemStack<Pack>> packs = packInv.getPackCounts();
        return packs.stream().map(pack -> new ItemStackPayload<>(
                pack.getAmount(),
                PackPayload.from(pack.getItem()
                ))).toList();
    }
    
    @GetMapping("/packs/{packid}/contents")
    public List<CardPayload<?>> getPackContents(@PathVariable int packid) {
        Pack pack = packCatalog.getPack(packid);
        return CardPayload.fromList(pack.getSetList());
    }
    
    @PostMapping("/users/{userid}/packs/{packid}")
    public PackResultPayload openPack(@PathVariable int userid, @PathVariable int packid) {
        User user = this.userManager.getUserById(userid);
        Pack pack = this.packCatalog.getPack(packid);
        IPackInventoryManager packInv = this.userDataFactory.getPackInventoryManger(user);
        return PackResultPayload.from(packInv.openPack(pack));
    }
    
    @PostMapping("/admin/packs")
    public ResponseEntity<PackPayload> createPack(@RequestBody PackCreatorPayload packCreate) {
        Pack newPack = packCreate.toPack(this.cardRegistry);
        Pack created = this.packCatalog.createPack(newPack);
        
        return new ResponseEntity<>(PackPayload.from(created), HttpStatus.CREATED);
    }
}
