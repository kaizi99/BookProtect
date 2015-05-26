//Copyright 2015 Kai-Uwe Zimdars
//
//Licensed under the Apache License, Version 2.0 (the "License");
//you may not use this file except in compliance with the License.
//You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
//Unless required by applicable law or agreed to in writing, software
//distributed under the License is distributed on an "AS IS" BASIS,
//WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//See the License for the specific language governing permissions and
//limitations under the License.

package de.kaizi99.BookKey;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class TestCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		Player p  = (Player) sender;
		
		if(args[0].equalsIgnoreCase("create"))
		{
			Main.database.createKey(args[1], p.getUniqueId());
			Key k = Main.database.getKey(args[1]);
			ItemStack book = Main.database.createKeyBook(args[1], k);
			p.getInventory().addItem(book);
		}
		if(args[0].equalsIgnoreCase("check"))
		{
			ItemStack b = p.getInventory().getItemInHand();
			BookMeta m = (BookMeta) b.getItemMeta();
			
			p.sendMessage("Key: " + Main.instance.isPresentBookRealKey(m, args[1]));
		}
		return false;
	}

}
