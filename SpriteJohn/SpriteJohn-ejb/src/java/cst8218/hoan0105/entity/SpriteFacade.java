/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cst8218.hoan0105.entity;

import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * EJB use for restful HTTP requests
 * @author J-PC
 */
//changes made = changed path to my ejb entity sprite 
@DeclareRoles({"Admin","RestGroup","JsfGroup"})
@RolesAllowed({"Admin","RestGroup","JsfGroup"})
@javax.ejb.Stateless
@javax.ws.rs.Path("cst8218.hoan0105.entity.sprite")
public class SpriteFacade extends AbstractFacade<Sprite> {
//use persistent unit for Sprite
    @PersistenceContext(unitName = "SpriteJohnPU")
    private EntityManager em;

    public SpriteFacade() {
        super(Sprite.class);
    }
    
      /**
     * POST with an id to modify the existing sprite.
     * @param id the id from the url 
  
     
    @POST
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response modify(@PathParam("id") Long id, Sprite entity) throws Exception {
        //check to see if the url field id or the id in the body request have been specified
        if( id == null || entity.getId() == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("id was not specified").build();
        }
        //check to see if the id in the url field and id in the body are mismatched
        else if(id.longValue() != entity.getId().longValue()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("id mismatch").build();
        }
        else {
            //if the sprite with given id exists, update the existing sprite
            Sprite old = super.find(entity.getId());
            if(old == null || super.find(id) == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("not found in database").build();
            }
            else {
                entity.updates(old); //update non-null values         
                super.edit(old);
            }
        }
        return Response.status(Response.Status.OK).entity(super.find(entity.getId())).build();
    }//end modify
    
        /**
     * POST on the root resource (sprite table) to create a new sprite or update the existing sprite.
    
     */
 /*   
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response modifyRoot(Sprite entity) throws Exception {
        if(entity.getId() == null) {
            //entity.checkNull(); //uncomment this to change null values to default values.
            super.create(entity);
            return Response.status(Response.Status.CREATED).entity(super.find(entity.getId())).build();
        }
        else {
            Sprite old = super.find(entity.getId());
            if(old == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("not found in database").build();
            }
            else {
                entity.updates(old);
                super.edit(old);
            }
        }
        return Response.status(Response.Status.OK).entity(super.find(entity.getId())).build();
    }//end modifyRoot
    
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Long id, Sprite entity) throws Exception {
        if(id == null || entity.getId() == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("id was not specified").build();
        }
        else if(id.longValue() != entity.getId().longValue()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("id mismatch").build();
        } 
        else {
            Sprite old = super.find(entity.getId());
            if(old == null || super.find(id) == null) {
               return Response.status(Response.Status.NOT_FOUND).entity("not found in database").build();
            }
            else {
               
                super.edit(entity); //if the new value is null it still store into the data
            }
        }   
       return Response.status(Response.Status.OK).entity(super.find(entity.getId())).build(); 
    }//end edit
*/
    @javax.ws.rs.POST
    @Override
    @javax.ws.rs.Consumes({javax.ws.rs.core.MediaType.APPLICATION_XML, javax.ws.rs.core.MediaType.APPLICATION_JSON})
    public void create(Sprite entity) {
        super.create(entity);
    }

    @javax.ws.rs.PUT
    @javax.ws.rs.Path("{id}")
    @javax.ws.rs.Consumes({javax.ws.rs.core.MediaType.APPLICATION_XML, javax.ws.rs.core.MediaType.APPLICATION_JSON})
    public void edit(@javax.ws.rs.PathParam("id") Long id, Sprite entity) {
        super.edit(entity);
    }

    @javax.ws.rs.DELETE
    @javax.ws.rs.Path("{id}")
    public void remove(@javax.ws.rs.PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @javax.ws.rs.GET
    @javax.ws.rs.Path("{id}")
    @javax.ws.rs.Produces({javax.ws.rs.core.MediaType.APPLICATION_XML, javax.ws.rs.core.MediaType.APPLICATION_JSON})
    public Sprite find(@javax.ws.rs.PathParam("id") Long id) {
        return super.find(id);
    }

    @javax.ws.rs.GET
    @Override
    @javax.ws.rs.Produces({javax.ws.rs.core.MediaType.APPLICATION_XML, javax.ws.rs.core.MediaType.APPLICATION_JSON})
    public List<Sprite> findAll() {
        return super.findAll();
    }

    @javax.ws.rs.GET
    @javax.ws.rs.Path("{from}/{to}")
    @javax.ws.rs.Produces({javax.ws.rs.core.MediaType.APPLICATION_XML, javax.ws.rs.core.MediaType.APPLICATION_JSON})
    public List<Sprite> findRange(@javax.ws.rs.PathParam("from") Integer from, @javax.ws.rs.PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @javax.ws.rs.GET
    @javax.ws.rs.Path("count")
    @javax.ws.rs.Produces(javax.ws.rs.core.MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
