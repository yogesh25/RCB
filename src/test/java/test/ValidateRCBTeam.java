package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Utilities.JsonReader;

public class ValidateRCBTeam {
	JSONObject getTeam;

	@BeforeMethod
	public void loadJson() {
		try {
			getTeam = JsonReader.getJSON();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void validatePlayers() {

		List<String> countries = JsonReader.getKey(getTeam, "country");
		int countForeignPlayers=0;
		for(String country:countries) {
			if(!country.equalsIgnoreCase("India")) {
				countForeignPlayers+=1;
			}
		}
		if(countForeignPlayers!=4)
			Assert.fail("Test Case ValidateForeignPlayersCount Failed because Foreign Players should be 4 but it is :"+countForeignPlayers);
		else
			System.out.println("Test Case ValidateForeignPlayersCount Passed because Foreign Players count is 4...");

	}
	
	@Test
	public void validateWicketKeeper() {

		List<String> roles = JsonReader.getKey(getTeam, "role");
		Boolean wicketKeeperPresent=false;
		for(String role:roles) {
			if(role.equalsIgnoreCase("Wicket-keeper")) {
				wicketKeeperPresent = true;
				break;
			}
		}
		if(wicketKeeperPresent)
			System.out.println("Test Case ValidateWicketKeeper Passed because Wicket-Keeper is playing in the Team...");			
		else
			Assert.fail("Test Case ValidateWicketKeeper Failed because there is no Wicket-Keeper in the Team");

	}
}
