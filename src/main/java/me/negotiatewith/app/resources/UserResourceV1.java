package me.negotiatewith.app.resources;

import com.google.inject.Inject;
import me.negotiatewith.app.core.dto.model.ProfileDto;
import me.negotiatewith.app.core.dto.model.UserDto;
import me.negotiatewith.app.core.service.api.ProfileService;
import me.negotiatewith.app.core.service.api.UserService;
import me.negotiatewith.app.db.model.entity.User;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


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
            return Response.status(Response.Status.OK).entity(new UserDto(user, false)).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(@Valid UserDto userDto) {
        return Response.status(Response.Status.OK).entity(userService.saveEntity(userDto)).build();
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
            UserDto userDto = new UserDto(user, true);
            return Response.status(Response.Status.OK).entity(userDto).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{user_id}/profile")
    public Response createProfile(@Valid ProfileDto profileDto, @PathParam("user_id") Long id) {
        return Response.status(Response.Status.OK).entity(profileService.saveEntity(profileDto, id)).build();
    }
}
