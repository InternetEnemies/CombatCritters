package com.combatcritters.critterspring.routes;

import com.combatcritters.critterspring.payloads.WalletPayload;
import com.internetEnemies.combatCritters.Logic.IUserDataFactory;
import com.internetEnemies.combatCritters.Logic.inventory.IBank;
import com.internetEnemies.combatCritters.Logic.users.IUserManager;
import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.objects.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WalletController {

    private final IUserDataFactory userDataFactory;
    private final IUserManager userManager;

    @Autowired
    public WalletController(IUserDataFactory userDataFactory, IUserManager userManager) {
        this.userDataFactory = userDataFactory;
        this.userManager = userManager;
    }
    
    @GetMapping("/users/{userid}/wallet")
    public WalletPayload getWallet(@PathVariable int userid){
        User user = this.userManager.getUserById(userid);
        IBank bank = this.userDataFactory.getBank(user);
        Currency currency = bank.getCurrentBalance();
        return WalletPayload.from(currency);
    }
}
