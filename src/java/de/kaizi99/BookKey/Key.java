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

import java.util.List;
import java.util.UUID;

public class Key {

	UUID owner;
	List<UUID> members;
	List<String> pages;
	
	public Key(UUID owner, List<UUID> members, List<String> pages)
	{
		this.owner = owner;
		this.members = members;
		this.pages = pages;
	}

	public void addMember(UUID member)
	{
		for(UUID m : members)
		{
			if(m.equals(member))
				return;
		}
		
		members.add(member);
	}
	
	public List<UUID> getMembers()
	{
		return members;
	}
	
	public UUID getOwner()
	{
		return owner;
	}
	
}
