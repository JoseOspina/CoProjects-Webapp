package org.collectiveone.modules.users;

import java.util.List;
import java.util.UUID;

import org.collectiveone.common.BaseController;
import org.collectiveone.common.dto.GetResult;
import org.collectiveone.common.dto.PostResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/1")
public class UsersController extends BaseController {
	
	@Autowired
	AppUserService appUserService;
	
	@RequestMapping(path = "/user/myProfile",  method = RequestMethod.GET)
    public GetResult<AppUserDto> myProfile() {
		
		String auth0Id = SecurityContextHolder.getContext().getAuthentication().getName();
		if (auth0Id.equals("anonimousUser")) {
			return new GetResult<AppUserDto>("error", "anonymous user", null);
		}
		
		AppUser user = appUserService.getOrCreateFromAuth0Id(auth0Id);
		
		return appUserService.getUserLight(user.getC1Id());
	}
	
	@RequestMapping(path = "/user/profile/{userId}",  method = RequestMethod.GET)
    public GetResult<AppUserDto> getProfile(@PathVariable("userId") UUID userId) {
		return appUserService.getUserFull(userId);
	}
	
	@RequestMapping(path = "/user/username/exist",  method = RequestMethod.GET)
    public GetResult<Boolean> getProfile(@RequestParam String username) {
		return appUserService.usernameExist(username);
	}
	
	@RequestMapping(path = "/user/profile/{userId}",  method = RequestMethod.PUT)
    public PostResult editProfile(@PathVariable("userId") UUID userId, @RequestBody AppUserDto userDto) {
		
		if (getLoggedUser() == null) {
			return new PostResult("error", "endpoint enabled users only", null);
		}
		
		if (getLoggedUser().getC1Id().compareTo(userId) == 0) {
			return appUserService.editUserProfile(userId, userDto);
		}
		
		return new PostResult("error", "only the profile owner can edit a profile", "");
	}
	
	@RequestMapping(path = "/user/{userId}/lang",  method = RequestMethod.PUT)
    public PostResult setPreferredLocale(@PathVariable("userId") String userIdStr, @RequestParam String locale) {
		
		if (getLoggedUser() == null) {
			return new PostResult("error", "endpoint enabled users only", null);
		}
		
		UUID userId = UUID.fromString(userIdStr);
		
		if (!getLoggedUserId().equals(userId)) {
			return new PostResult("error", "only the profile owner can edit a profile", "");
		}
		
		return appUserService.setPreferredLocale(userId, locale);
		
	}
	
	@RequestMapping(path = "/user/{userId}/subscription",  method = RequestMethod.PUT)
    public PostResult setPushSubscription(@PathVariable("userId") UUID userId, 
    		@RequestBody SubscriptionDto subscription) {
		
		if (getLoggedUser() == null) {
			return new PostResult("error", "endpoint enabled users only", null);
		}
		
		if (!getLoggedUserId().equals(userId)) {
			return new PostResult("error", "only the profile owner can edit a profile", "");
		}
		
		return appUserService.addSubscription(userId, subscription);
	}
	
	
	
	@RequestMapping(path = "/users/suggestions", method = RequestMethod.GET)
	public GetResult<List<AppUserDto>> suggestions(@RequestParam("q") String query) {
		return appUserService.searchBy(query);
	}
	
}
