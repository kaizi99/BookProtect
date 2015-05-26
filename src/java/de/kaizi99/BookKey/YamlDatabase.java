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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.configuration.file.YamlConfiguration;

public class YamlDatabase extends KeyDatabase {

	YamlConfiguration keys;
	File keyYAML;
	
	public YamlDatabase() 
	{
		keyYAML = new File(Main.instance.getDataFolder(), "keys.yml");
		keys = YamlConfiguration.loadConfiguration(keyYAML);
	}
	
	@Override
	public Key getKey(String name) 
	{
		List<String> members = keys.getStringList(name + ".members");
		String owner = keys.getString(name + ".owner");
		List<String> pages = keys.getStringList(name + ".pages");
		
		List<UUID> uuidMembers = new ArrayList<UUID>();
		
		for(String s : members)
		{
			uuidMembers.add(UUID.fromString(s));
		}
		
		return new Key(UUID.fromString(owner), uuidMembers, pages);
	}

	@Override
	public void registerKey(String name, Key k) 
	{
		keys.createSection(name);
		
		List<String> stringMembers = new ArrayList<String>();
		
		for(UUID uuid : k.members)
		{
			stringMembers.add(uuid.toString());
		}
		
		keys.set(name + ".members", stringMembers);
		keys.set(name + ".owner", k.getOwner().toString());
		keys.set(name + ".pages", k.pages);
		
		try {
			keys.save(keyYAML);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
