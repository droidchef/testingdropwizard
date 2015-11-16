package me.negotiatewith.app.resources;

import com.google.inject.Inject;
import me.negotiatewith.app.core.dto.model.ProfileDto;
import me.negotiatewith.app.core.service.api.ProfileService;
import me.negotiatewith.app.db.model.entity.Profile;
import me.negotiatewith.app.db.model.entity.User;
import me.negotiatewith.app.core.dto.model.UserDto;
import me.negotiatewith.app.core.service.api.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by ishan on 13/11/15.
 */
@Path("/v1/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResourceV1 {

    private final UserService userService;
    private final ProfileService profileService;

    @Inject
    public UserResourceV1(UserService userService, ProfileService profileService) {
        this.userService = userService;
        this.profileService = profileService;
    }

    @GET
    public Response getUser(@QueryParam("email") String email, @QueryParam("password") String password){
        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("No Email/Password Provided").build();
        }

        User user = userService.findByEmailPassword(email, password);

        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("user not found").build();
        } else {
            return Response.status(Response.Status.OK).entity(new UserDto(user)).build();
        }
    }

    @GET
    @Path("/{user_id}/profile")
    public Response getProfile(@PathParam("user_id") Long id) {

        if (id == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("No User Id Provided").build();
        }

        User user = userService.findById(id);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("profile not found").build();
        } else {
            UserDto userDto = new UserDto(user);
            userDto.setProfile(user.getProfile());
            return Response.status(Response.Status.OK).entity(userDto).build();
        }
    }
}
