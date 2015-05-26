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

import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	
	public static Main instance;
	public static KeyDatabase database;
	
	@Override
	public void onEnable()
	{
		instance = this;
		getCommand("test").setExecutor(new TestCommand());
		database = new YamlDatabase();
	}
	
	@Override
	public void onDisable()
	{
		
	}

	public boolean isPresentBookRealKey(BookMeta b, String name)
	{
		if(database.checkKey(database.getKey(name), b))
			return true;
		
		return false;
	}
	
}
