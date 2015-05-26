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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.RandomStringUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public abstract class KeyDatabase {

	public abstract Key getKey(String name);
	public abstract void registerKey(String name, Key k);
	
	public boolean checkKey(Key k, BookMeta m)
	{
		List<String> pages = k.pages;
		
		for(int x = 0; x < k.pages.size(); x++)
		{
			if(!pages.get(x).equalsIgnoreCase(m.getPage(x + 1)))
				return false;
		}
		return true;
	}
	
	public ItemStack createKeyBook(String name, Key k)
	{
		ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
		BookMeta meta = (BookMeta) book.getItemMeta();
		meta.setPages(k.pages);
		meta.setTitle("Key for '" + name + "'");
		meta.setAuthor("The BookKeys Plugin");
		book.setItemMeta(meta);
		return book;
	}
	
	public Key createKey(String name, UUID owner)
	{
		List<String> pages = new ArrayList<String>();
		for(int i = 0; i <= 10; i++)
		{
			pages.add(RandomStringUtils.random(256, "abcdefghijklmnopqrstuvwxyz1234567890"));
		}
		Key k = new Key(owner, new ArrayList<UUID>(), pages);
		Main.database.registerKey(name, k);
		return k;
	}
}
